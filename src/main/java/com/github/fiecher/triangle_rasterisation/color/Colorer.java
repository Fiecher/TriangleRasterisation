package com.github.fiecher.triangle_rasterisation.color;

import com.github.fiecher.triangle_rasterisation.math.Barycentrics;

/**
 * A functional interface for {@code TriangleRasterizer} to get colors for
 * individual pixels.
 *
 * @see {@code TriangleRasterizer}
 */
@FunctionalInterface
public interface Colorer {

    /**
     * Gets a {@code Colorf} at specified barycentic coordinates.
     *
     * @param b barycentric coordinates.
     *
     * @return {@code Colorf} at specified barycentric coordinates.
     *
     * @throws {@code NullPointerException} if b is null.
     */
    public ColorRGB get(final Barycentrics b);
}

