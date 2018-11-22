package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Frame frame = new Frame();
        frame.setVisible(true);
        long end = System.nanoTime();
        System.out.println((end-start)/1000000);
    }
}
