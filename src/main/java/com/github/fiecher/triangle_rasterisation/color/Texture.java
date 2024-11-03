package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentrics;

public interface Texture {
    public ColorRGB get(final Barycentrics b);
}
