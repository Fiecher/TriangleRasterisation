package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentric;


public interface Texture {
    public ColorRGB get(final Barycentric b);
}

