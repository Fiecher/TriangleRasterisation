package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Instruments;
import javafx.scene.paint.Color;

public class ColorRGB {

    private final double r;
    private final double g;
    private final double b;
    private final double alpha;

    public ColorRGB(
            final double r, final double g, final double b, final double a) {

        this.r = Instruments.confined(0, r, 1);
        this.g = Instruments.confined(0, g, 1);
        this.b = Instruments.confined(0, b, 1);
        this.alpha = Instruments.confined(0, a, 1);
    }

    public ColorRGB(final Color color) {
        this.r = Instruments.confined(0, color.getRed(), 1);
        this.g = Instruments.confined(0, color.getGreen(), 1);
        this.b = Instruments.confined(0, color.getBlue(), 1);
        this.alpha = Instruments.confined(0, color.getOpacity(), 1);
    }

    public double red() {
        return r;
    }


    public double green() {
        return g;
    }


    public double blue() {
        return b;
    }


    public double alpha() {
        return alpha;
    }


    public double opacity() {
        return alpha;
    }


    public double transparency() {
        return 1f - alpha;
    }


    public Color jfxColor() {
        return new Color(r, g, b, alpha);
    }
}
