package com.company;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class Mandelbrot implements Callable<Object> {

    private int x, x2, y, y2;
    private int max_iter;
    private double zoom;
    private BufferedImage img;
    private double zx, zy, cX, cY, tmp;

    public Mandelbrot(int x, int x2, int y, int y2, int max_iter, double zoom, BufferedImage i) {
        this.x = x;
        this.y = y;
        this.max_iter = max_iter;
        this.zoom = zoom;
        this.img = i;
        this.x2 = x2;
        this.y2 = y2;
    }


    @Override
    public Object call() {
        for (int i = y; i < y2; i++) {
            for (int j = x; j < x2; j++) {
                zx = zy = 0;
                cX = (j - 400) / zoom;
                cY = (i - 300) / zoom;
                int iter = max_iter;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                img.setRGB(j,  i, iter | (iter << 8));
            }
        }
        return null;
    }
}