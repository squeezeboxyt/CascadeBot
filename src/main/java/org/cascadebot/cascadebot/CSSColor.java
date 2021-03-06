package org.cascadebot.cascadebot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cascadebot.cascadebot.data.language.Language;
import org.cascadebot.cascadebot.data.language.Locale;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum CSSColor {

    ALICEBLUE(new Color(240, 248, 255)),
    ANTIQUEWHITE(new Color(250, 235, 215)),
    AQUA(new Color(0, 255, 255)),
    AQUAMARINE(new Color(127, 255, 212)),
    AZURE(new Color(240, 255, 255)),
    BEIGE(new Color(245, 245, 220)),
    BISQUE(new Color(255, 228, 196)),
    BLACK(new Color(0, 0, 0)),
    BLANCHEDALMOND(new Color(255, 235, 205)),
    BLUE(new Color(0, 0, 255)),
    BLUEVIOLET(new Color(138, 43, 226)),
    BROWN(new Color(165, 42, 42)),
    BURLYWOOD(new Color(222, 184, 135)),
    CADETBLUE(new Color(95, 158, 160)),
    CHARTREUSE(new Color(127, 255, 0)),
    CHOCOLATE(new Color(210, 105, 30)),
    CORAL(new Color(255, 127, 80)),
    CORNFLOWERBLUE(new Color(100, 149, 237)),
    CORNSILK(new Color(255, 248, 220)),
    CRIMSON(new Color(220, 20, 60)),
    CYAN(new Color(0, 255, 255)),
    DARKBLUE(new Color(0, 0, 139)),
    DARKCYAN(new Color(0, 139, 139)),
    DARKGOLDENROD(new Color(184, 134, 11)),
    DARKGRAY(new Color(169, 169, 169)),
    DARKGREEN(new Color(0, 100, 0)),
    DARKGREY(new Color(169, 169, 169)),
    DARKKHAKI(new Color(189, 183, 107)),
    DARKMAGENTA(new Color(139, 0, 139)),
    DARKOLIVEGREEN(new Color(85, 107, 47)),
    DARKORANGE(new Color(255, 140, 0)),
    DARKORCHID(new Color(153, 50, 204)),
    DARKRED(new Color(139, 0, 0)),
    DARKSALMON(new Color(233, 150, 122)),
    DARKSEAGREEN(new Color(143, 188, 143)),
    DARKSLATEBLUE(new Color(72, 61, 139)),
    DARKSLATEGRAY(new Color(47, 79, 79)),
    DARKSLATEGREY(new Color(47, 79, 79)),
    DARKTURQUOISE(new Color(0, 206, 209)),
    DARKVIOLET(new Color(148, 0, 211)),
    DEEPPINK(new Color(255, 20, 147)),
    DEEPSKYBLUE(new Color(0, 191, 255)),
    DIMGRAY(new Color(105, 105, 105)),
    DIMGREY(new Color(105, 105, 105)),
    DODGERBLUE(new Color(30, 144, 255)),
    FIREBRICK(new Color(178, 34, 34)),
    FLORALWHITE(new Color(255, 250, 240)),
    FORESTGREEN(new Color(34, 139, 34)),
    FUCHSIA(new Color(255, 0, 255)),
    GAINSBORO(new Color(220, 220, 220)),
    GHOSTWHITE(new Color(248, 248, 255)),
    GOLDENROD(new Color(218, 165, 32)),
    GOLD(new Color(255, 215, 0)),
    GRAY(new Color(128, 128, 128)),
    GREEN(new Color(0, 128, 0)),
    GREENYELLOW(new Color(173, 255, 47)),
    GREY(new Color(128, 128, 128)),
    HONEYDEW(new Color(240, 255, 240)),
    HOTPINK(new Color(255, 105, 180)),
    INDIANRED(new Color(205, 92, 92)),
    INDIGO(new Color(75, 0, 130)),
    IVORY(new Color(255, 255, 240)),
    KHAKI(new Color(240, 230, 140)),
    LAVENDERBLUSH(new Color(255, 240, 245)),
    LAVENDER(new Color(230, 230, 250)),
    LAWNGREEN(new Color(124, 252, 0)),
    LEMONCHIFFON(new Color(255, 250, 205)),
    LIGHTBLUE(new Color(173, 216, 230)),
    LIGHTCORAL(new Color(240, 128, 128)),
    LIGHTCYAN(new Color(224, 255, 255)),
    LIGHTGOLDENRODYELLOW(new Color(250, 250, 210)),
    LIGHTGRAY(new Color(211, 211, 211)),
    LIGHTGREEN(new Color(144, 238, 144)),
    LIGHTGREY(new Color(211, 211, 211)),
    LIGHTPINK(new Color(255, 182, 193)),
    LIGHTSALMON(new Color(255, 160, 122)),
    LIGHTSEAGREEN(new Color(32, 178, 170)),
    LIGHTSKYBLUE(new Color(135, 206, 250)),
    LIGHTSLATEGRAY(new Color(119, 136, 153)),
    LIGHTSLATEGREY(new Color(119, 136, 153)),
    LIGHTSTEELBLUE(new Color(176, 196, 222)),
    LIGHTYELLOW(new Color(255, 255, 224)),
    LIME(new Color(0, 255, 0)),
    LIMEGREEN(new Color(50, 205, 50)),
    LINEN(new Color(250, 240, 230)),
    MAGENTA(new Color(255, 0, 255)),
    MAROON(new Color(128, 0, 0)),
    MEDIUMAQUAMARINE(new Color(102, 205, 170)),
    MEDIUMBLUE(new Color(0, 0, 205)),
    MEDIUMORCHID(new Color(186, 85, 211)),
    MEDIUMPURPLE(new Color(147, 112, 219)),
    MEDIUMSEAGREEN(new Color(60, 179, 113)),
    MEDIUMSLATEBLUE(new Color(123, 104, 238)),
    MEDIUMSPRINGGREEN(new Color(0, 250, 154)),
    MEDIUMTURQUOISE(new Color(72, 209, 204)),
    MEDIUMVIOLETRED(new Color(199, 21, 133)),
    MIDNIGHTBLUE(new Color(25, 25, 112)),
    MINTCREAM(new Color(245, 255, 250)),
    MISTYROSE(new Color(255, 228, 225)),
    MOCCASIN(new Color(255, 228, 181)),
    NAVAJOWHITE(new Color(255, 222, 173)),
    NAVY(new Color(0, 0, 128)),
    OLDLACE(new Color(253, 245, 230)),
    OLIVE(new Color(128, 128, 0)),
    OLIVEDRAB(new Color(107, 142, 35)),
    ORANGE(new Color(255, 165, 0)),
    ORANGERED(new Color(255, 69, 0)),
    ORCHID(new Color(218, 112, 214)),
    PALEGOLDENROD(new Color(238, 232, 170)),
    PALEGREEN(new Color(152, 251, 152)),
    PALETURQUOISE(new Color(175, 238, 238)),
    PALEVIOLETRED(new Color(219, 112, 147)),
    PAPAYAWHIP(new Color(255, 239, 213)),
    PEACHPUFF(new Color(255, 218, 185)),
    PERU(new Color(205, 133, 63)),
    PINK(new Color(255, 192, 203)),
    PLUM(new Color(221, 160, 221)),
    POWDERBLUE(new Color(176, 224, 230)),
    PURPLE(new Color(128, 0, 128)),
    REBECCAPURPLE(new Color(102, 51, 153)),
    RED(new Color(255, 0, 0)),
    ROSYBROWN(new Color(188, 143, 143)),
    ROYALBLUE(new Color(65, 105, 225)),
    SADDLEBROWN(new Color(139, 69, 19)),
    SALMON(new Color(250, 128, 114)),
    SANDYBROWN(new Color(244, 164, 96)),
    SEAGREEN(new Color(46, 139, 87)),
    SEASHELL(new Color(255, 245, 238)),
    SIENNA(new Color(160, 82, 45)),
    SILVER(new Color(192, 192, 192)),
    SKYBLUE(new Color(135, 206, 235)),
    SLATEBLUE(new Color(106, 90, 205)),
    SLATEGRAY(new Color(112, 128, 144)),
    SLATEGREY(new Color(112, 128, 144)),
    SNOW(new Color(255, 250, 250)),
    SPRINGGREEN(new Color(0, 255, 127)),
    STEELBLUE(new Color(70, 130, 180)),
    TAN(new Color(210, 180, 140)),
    TEAL(new Color(0, 128, 128)),
    THISTLE(new Color(216, 191, 216)),
    TOMATO(new Color(255, 99, 71)),
    TURQUOISE(new Color(64, 224, 208)),
    VIOLET(new Color(238, 130, 238)),
    WHEAT(new Color(245, 222, 179)),
    WHITE(new Color(255, 255, 255)),
    WHITESMOKE(new Color(245, 245, 245)),
    YELLOW(new Color(255, 255, 0)),
    YELLOWGREEN(new Color(154, 205, 50));

    private static Map<Color, String> colorNameMap = new HashMap<>();

    static {
        for (CSSColor value : CSSColor.values()) {
            colorNameMap.put(value.color, value.name().toLowerCase());
        }
    }

    private Color color;

    public static String getLocalNameOrDefault(Locale locale, Color color, String defaultName) {
        if (!colorNameMap.containsKey(color)) {
            return defaultName;
        }
        return Language.i18n(locale, "utils.color.colors." + colorNameMap.get(color));
    }

    public static Map<Color, String> getColorNameMap() {
        return colorNameMap;
    }

}