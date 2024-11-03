package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentrics;
import javafx.scene.paint.Color;
import java.util.Objects;

public class MonotoneColorer implements Colorer{
    private final ColorRGB c;

    public MonotoneColorer(final ColorRGB c) {
        Objects.requireNonNull(c);

        this.c = c;
    }

    public MonotoneColorer(final Color c) {
        Objects.requireNonNull(c);

        this.c = new ColorRGB(c);
    }

    @Override
    public ColorRGB get(final Barycentrics b) {
        Objects.requireNonNull(b);

        return c;
    }
}
