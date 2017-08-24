package org.b3log.zephyr.model;

import java.awt.*;

/**
 * Author: Zhang Yu
 * Date: 17年8月24日
 * Email: yu.zhang@7fresh.com
 */
public class MarkColor {
    private Color foregroundColor;
    private Color backgroundColor;

    public MarkColor(Color foregroundColor, Color backgroundColor) {
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
