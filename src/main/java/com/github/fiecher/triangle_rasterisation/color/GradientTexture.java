package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentric;
import com.github.fiecher.triangle_rasterisation.math.Utils;
import javafx.scene.paint.Color;
import java.util.Objects;

public class GradientTexture implements Texture {

    private class ThreePointGradient {
        private final ColorRGB color1;
        private final ColorRGB color2;
        private final ColorRGB color3;

        public ThreePointGradient(final ColorRGB color1, final ColorRGB color2, final ColorRGB color3) {
            Objects.requireNonNull(color1);
            Objects.requireNonNull(color2);
            Objects.requireNonNull(color3);

            this.color1 = color1;
            this.color2 = color2;
            this.color3 = color3;
        }

        public ColorRGB getColor1() {
            return color1;
        }

        public ColorRGB getColor2() {
            return color2;
        }

        public ColorRGB getColor3() {
            return color3;
        }
    }
    private final ThreePointGradient gradient;

    public GradientTexture(final ColorRGB c1, final ColorRGB c2, final ColorRGB c3) {
        Objects.requireNonNull(c1);
        Objects.requireNonNull(c2);
        Objects.requireNonNull(c3);

        this.gradient = new ThreePointGradient(c1, c2, c3);
    }

    public GradientTexture(final Color c1, final Color c2, final Color c3) {
        Objects.requireNonNull(c1);
        Objects.requireNonNull(c2);
        Objects.requireNonNull(c3);

        this.gradient = new ThreePointGradient(new ColorRGB(c1), new ColorRGB(c2), new ColorRGB(c3));
    }

    private double red(final Barycentric b) {
        final double r1 = b.getLambda1() * gradient.getColor1().getRedChannel();
        final double r2 = b.getLambda2() * gradient.getColor2().getRedChannel();
        final double r3 = b.getLambda3() * gradient.getColor3().getRedChannel();

        return Utils.confined(0, r1 + r2 + r3, 1);
    }

    private double green(final Barycentric b) {
        final double g1 = b.getLambda1() * gradient.getColor1().getGreenChannel();
        final double g2 = b.getLambda2() * gradient.getColor2().getGreenChannel();
        final double g3 = b.getLambda3() * gradient.getColor3().getGreenChannel();

        return Utils.confined(0, g1 + g2 + g3, 1);
    }

    private double blue(final Barycentric b) {
        final double b1 = b.getLambda1() * gradient.getColor1().getBlueChannel();
        final double b2 = b.getLambda2() * gradient.getColor2().getBlueChannel();
        final double b3 = b.getLambda3() * gradient.getColor3().getBlueChannel();

        return Utils.confined(0, b1 + b2 + b3, 1);
    }

    private double opacity(final Barycentric b) {
        final double o1 = b.getLambda1() * gradient.getColor1().getAlphaChannel();
        final double o2 = b.getLambda2() * gradient.getColor2().getAlphaChannel();
        final double o3 = b.getLambda3() * gradient.getColor3().getAlphaChannel();

        return Utils.confined(0, o1 + o2 + o3, 1);
    }

    @Override
    public ColorRGB get(final Barycentric b) {
        Objects.requireNonNull(b);

        return new ColorRGB(red(b), green(b), blue(b), opacity(b));
    }

}
