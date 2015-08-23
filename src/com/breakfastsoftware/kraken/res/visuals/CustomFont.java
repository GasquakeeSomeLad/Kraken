package com.breakfastsoftware.kraken.res.visuals;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by SomeLad on 8/23/2015.
 */
public enum CustomFont {
    BLACK("BLKCHCRY.ttf");

    private Font font, underlined;

    private CustomFont(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            Map<TextAttribute, Object> changes =
                    new Hashtable<TextAttribute, Object>();
            changes.put(TextAttribute.UNDERLINE,
                    TextAttribute.UNDERLINE_ON);
            underlined = font.deriveFont(changes);
        }
        catch (Exception ex) {
            System.out.println("error");
        }
    }

    public Font getFont(boolean bold, boolean italic, float size) {
        int type = Font.PLAIN;
        if (bold) {
            type += Font.BOLD;
        }
        if (italic) {
            type += Font.ITALIC;
        }
        return font.deriveFont(type, size);
    }

    public Font getUnderlined(boolean bold, boolean italic, float size) {
        int type = Font.PLAIN;
        if (bold) {
            type += Font.BOLD;
        }
        if (italic) {
            type += Font.ITALIC;
        }
        return underlined.deriveFont(type, size);
    }
}
