package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Utils;
import javafx.scene.paint.Color;

public class ColorRGB {

    private final double red;
    private final double green;
    private final double blue;
    private final double alpha;

    public ColorRGB(final double red, final double green, final double blue, final double alpha) {
        this.red = Utils.confined(0, red, 1);
        this.green = Utils.confined(0, green, 1);
        this.blue = Utils.confined(0, blue, 1);
        this.alpha = Utils.confined(0, alpha, 1);
    }

    public ColorRGB(final Color color) {
        this.red = Utils.confined(0, color.getRed(), 1);
        this.green = Utils.confined(0, color.getGreen(), 1);
        this.blue = Utils.confined(0, color.getBlue(), 1);
        this.alpha = Utils.confined(0, color.getOpacity(), 1);
    }

    public double getRedChannel() {
        return red;
    }


    public double getGreenChannel() {
        return green;
    }


    public double getBlueChannel() {
        return blue;
    }


    public double getAlphaChannel() {
        return alpha;
    }

    public Color convertToJFXColor() {
        return new Color(red, green, blue, alpha);
    }
}
