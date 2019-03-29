/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.cascadebot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.sentry.Sentry;
import io.sentry.SentryClient;
import lavalink.client.io.jda.JdaLavalink;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.SelfUser;
import net.dv8tion.jda.core.requests.RestAction;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.cascadebot.cascadebot.commandmeta.CommandManager;
import org.cascadebot.cascadebot.data.Config;
import org.cascadebot.cascadebot.data.database.DatabaseManager;
import org.cascadebot.cascadebot.events.ButtonEventListener;
import org.cascadebot.cascadebot.events.CommandListener;
import org.cascadebot.cascadebot.events.GeneralEvents;
import org.cascadebot.cascadebot.moderation.ModerationManager;
import org.cascadebot.cascadebot.music.MusicHandler;
import org.cascadebot.cascadebot.permissions.PermissionsManager;
import org.cascadebot.shared.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Scanner;

public class CascadeBot {

    public static final CascadeBot INS = new CascadeBot();
    public static final Logger LOGGER = LoggerFactory.getLogger(CascadeBot.class);

    private static Version version;
    private static Gson gson;

    private ShardManager shardManager;
    private CommandManager commandManager;
    private DatabaseManager databaseManager;
    private PermissionsManager permissionsManager;
    private ModerationManager moderationManager;
    private OkHttpClient httpClient;
    private MusicHandler musicHandler;

    public static void main(String[] args) {
        if (System.getenv("SENTRY_DSN") == null) {
            LOGGER.warn("You haven't set a Sentry DNS in the environment variables! Set SENTRY_DSN to your DSN for this to work!");
        }
        try (Scanner scanner = new Scanner(CascadeBot.class.getResourceAsStream("/version.txt"))) {
            version = Version.parseVer(scanner.next());
        }
        INS.init();
    }

    public static Version getVersion() {
        return version;
    }

    public static Gson getGSON() {
        return gson;
    }

    public static String getInvite() {
        return String.format("https://discordapp.com/oauth2/authorize?client_id=%s&scope=bot&permissions=%s",
                CascadeBot.INS.getSelfUser().getId(), Permission.ALL_GUILD_PERMISSIONS);
    }

    /**
     * Runs once all shards are loaded
     */
    public void run() {
        LOGGER.info("All shards successfully logged in!");
        LOGGER.info("Cascade Bot version {} successfully booted up!", version);
    }


    private void init() {
        new Thread(new ConsoleReader()).start();

        GsonBuilder builder = new GsonBuilder();
        try {
            Config.init("config.yml");
        } catch (IOException e) {
            LOGGER.error("Error reading config file", e);
            ShutdownHandler.exitWithError();
            return;
        }

        // Sends a message to break up the status log flow to see what events apply to each bot run
        Config.INS.getEventWebhook().send(
                "\u200B\n" +
                        StringUtils.repeat("-", 30) + " BOT RESTART " + StringUtils.repeat("-", 30) + "\n" +
                        "\u200B");

        SentryClient client = Sentry.getStoredClient();
        client.setEnvironment(Environment.isDevelopment() ? "development" : "production");
        client.setRelease(version.toString());

        httpClient = new OkHttpClient.Builder().build();

        if (Config.INS.isPrettyJson()) {
            builder.setPrettyPrinting();
        }

        if (Config.INS.getConnectionString() != null) {
            databaseManager = new DatabaseManager(Config.INS.getConnectionString());
        } else {
            databaseManager = new DatabaseManager(
                    Config.INS.getUsername(),
                    Config.INS.getPassword(),
                    Config.INS.getDatabase(),
                    Config.INS.getHosts(),
                    Config.INS.isSsl()
            );
        }

        musicHandler = new MusicHandler(this);
        musicHandler.buildMusic();

        gson = builder.create();
        try {
            DefaultShardManagerBuilder defaultShardManagerBuilder = new DefaultShardManagerBuilder()
                    .addEventListeners(new CommandListener())
                    .addEventListeners(new GeneralEvents())
                    .addEventListeners(new ButtonEventListener())
                    .setToken(Config.INS.getBotToken())
                    .setShardsTotal(-1)
                    .setGameProvider(shardId -> {
                        if (Environment.isDevelopment()) {
                            return Game.streaming(" the devs mistakes", "https://twitch.tv/someone");
                        } else {
                            return Game.playing("CascadeBot Version " + version);
                        }
                    })
                    .setBulkDeleteSplittingEnabled(false)
                    .setEnableShutdownHook(false);

            if(MusicHandler.isLavalinkEnabled()) {
                defaultShardManagerBuilder.addEventListeners(MusicHandler.getLavalink());
            }

            shardManager = defaultShardManagerBuilder.build();
        } catch (LoginException e) {
            LOGGER.error("Error building JDA", e);
            ShutdownHandler.exitWithError();
            return;
        }

        commandManager = new CommandManager();
        permissionsManager = new PermissionsManager();
        permissionsManager.registerPermissions();
        moderationManager = new ModerationManager();

        Thread.setDefaultUncaughtExceptionHandler(((t, e) -> LOGGER.error("Uncaught exception in thread " + t, e)));
        Thread.currentThread()
                .setUncaughtExceptionHandler(((t, e) -> LOGGER.error("Uncaught exception in thread " + t, e)));

        RestAction.DEFAULT_FAILURE = throwable -> {
            LOGGER.error("Uncaught exception in rest action", throwable);
        };

    }

    /**
     * This  will return the first connected JDA shard.
     * This means that a lot of methods like sending embeds works even with shard 0 offline.
     *
     * @return The first possible JDA shard which is connected.
     */
    @Nonnull
    public JDA getClient() {
        for (JDA jda : shardManager.getShardCache()) {
            if (jda.getStatus() == JDA.Status.LOADING_SUBSYSTEMS || jda.getStatus() == JDA.Status.CONNECTED)
                return jda;
        }
        throw new IllegalStateException("getClient was called when no shards were connected!");
    }

    /**
     * Get the SelfUser of the bot, this will be returned from the first connected shard.
     *
     * @return The bot SelfUser from the first connected shard.
     */
    @Nonnull
    public SelfUser getSelfUser() {
        return getClient().getSelfUser();
    }

    public Logger getLogger() {
        return LOGGER;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public PermissionsManager getPermissionsManager() {
        return permissionsManager;
    }

    public ModerationManager getModerationManager() {
        return moderationManager;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public MusicHandler getMusicHandler() {
        return musicHandler;
    }
}
