package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Frame extends JFrame {

    private final int max_iter = 2500;
    private final double zoom = 150;
    private BufferedImage img;

    public Frame() {
        super("Mandelbrot set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Set<Future<Object>> callables = new HashSet<>();


        for (int i = 0; i < 20; i++) {
            Callable<Object> callable = new Mandelbrot(i*40, (i+1)*40, 0, getHeight(), max_iter, zoom, img);
            callables.add(service.submit(callable));
        }



        for (Future<Object> call: callables) {
            try {
                call.get();
            }
            catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}
