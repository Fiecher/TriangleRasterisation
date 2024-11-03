package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentric;
import javafx.scene.paint.Color;
import java.util.Objects;

public class MonotoneTexture implements Texture {
    private final ColorRGB color;

    public MonotoneTexture(final ColorRGB color) {
        Objects.requireNonNull(color);

        this.color = color;
    }

    public MonotoneTexture(final Color color) {
        Objects.requireNonNull(color);

        this.color = new ColorRGB(color);
    }

    @Override
    public ColorRGB get(final Barycentric b) {
        Objects.requireNonNull(b);

        return color;
    }
}
