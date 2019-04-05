/*
 * Copyright (c) 2019 CascadeBot. All rights reserved.
 * Licensed under the MIT license.
 */

package org.cascadebot.cascadebot.messaging;

import org.cascadebot.cascadebot.UnicodeConstants;
import org.cascadebot.shared.SharedConstants;

import java.awt.Color;

public enum MessageType {

    INFO(Color.CYAN, UnicodeConstants.INFORMATION),
    SUCCESS(Color.GREEN, UnicodeConstants.TICK),
    WARNING(Color.YELLOW, UnicodeConstants.WARNING),
    MODERATION(Color.WHITE, UnicodeConstants.POLICE),
    DANGER(Color.RED, UnicodeConstants.RED_CROSS),
    NEUTRAL(SharedConstants.CASCADE_COLOR, "");

    private final Color color;
    private final String emoji;

    MessageType(Color color, String emoji) {
        this.color = color;
        this.emoji = emoji;
    }

    public Color getColor() {
        return color;
    }

    public String getEmoji() {
        return emoji;
    }

}