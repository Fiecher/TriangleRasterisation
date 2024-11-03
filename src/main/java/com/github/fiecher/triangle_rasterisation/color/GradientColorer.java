package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentrics;
import com.github.fiecher.triangle_rasterisation.math.Instruments;
import javafx.scene.paint.Color;
import java.util.Objects;

public class GradientColorer implements Colorer{

    private class ThreePointGradient {
        private final ColorRGB c1;
        private final ColorRGB c2;
        private final ColorRGB c3;

        public ThreePointGradient(final ColorRGB c1, final ColorRGB c2, final ColorRGB c3) {

            Objects.requireNonNull(c1);
            Objects.requireNonNull(c2);
            Objects.requireNonNull(c3);

            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
        }
         
        public ColorRGB color1() {
            return c1;
        }

         
        public double red1() {
            return c1.red();
        }

         
        public double green1() {
            return c1.green();
        }

         
        public double blue1() {
            return c1.blue();
        }

         
        public double alpha1() {
            return c1.alpha();
        }

         
        public ColorRGB color2() {
            return c2;
        }

         
        public double red2() {
            return c2.red();
        }

         
        public double green2() {
            return c2.green();
        }

         
        public double blue2() {
            return c2.blue();
        }

         
        public double alpha2() {
            return c2.alpha();
        }

         
        public ColorRGB color3() {
            return c3;
        }

         
        public double red3() {
            return c3.red();
        }

         
        public double green3() {
            return c3.green();
        }

         
        public double blue3() {
            return c3.blue();
        }

         
        public double alpha3() {
            return c3.alpha();
        }
    }
    

    private final ThreePointGradient g;

    public GradientColorer(final ColorRGB c1, final ColorRGB c2, final ColorRGB c3) {
        Objects.requireNonNull(c1);
        Objects.requireNonNull(c2);
        Objects.requireNonNull(c3);

        this.g = new ThreePointGradient(c1, c2, c3);
    }

    public GradientColorer(final Color c1, final Color c2, final Color c3) {
        Objects.requireNonNull(c1);
        Objects.requireNonNull(c2);
        Objects.requireNonNull(c3);

        this.g = new ThreePointGradient(new ColorRGB(c1), new ColorRGB(c2), new ColorRGB(c3));
    }

    private double red(final Barycentrics b) {
        final double r1 = b.lambda1() * g.red1();
        final double r2 = b.lambda2() * g.red2();
        final double r3 = b.lambda3() * g.red3();

        return Instruments.confined(0, r1 + r2 + r3, 1);
    }

    private double green(final Barycentrics b) {
        final double g1 = b.lambda1() * g.green1();
        final double g2 = b.lambda2() * g.green2();
        final double g3 = b.lambda3() * g.green3();

        return Instruments.confined(0, g1 + g2 + g3, 1);
    }

    private double blue(final Barycentrics b) {
        final double b1 = b.lambda1() * g.blue1();
        final double b2 = b.lambda2() * g.blue2();
        final double b3 = b.lambda3() * g.blue3();

        return Instruments.confined(0, b1 + b2 + b3, 1);
    }

    private double opacity(final Barycentrics b) {
        final double o1 = b.lambda1() * g.alpha1();
        final double o2 = b.lambda2() * g.alpha2();
        final double o3 = b.lambda3() * g.alpha3();

        return Instruments.confined(0, o1 + o2 + o3, 1);
    }

    @Override
    public ColorRGB get(final Barycentrics b) {
        Objects.requireNonNull(b);

        return new ColorRGB(red(b), green(b), blue(b), opacity(b));
    }

}
