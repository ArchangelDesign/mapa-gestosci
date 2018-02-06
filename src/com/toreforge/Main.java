package com.toreforge;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Processor proc = new Processor();
        //proc.generateMap(10, 10);
        //proc.saveMap();
        proc.loadMap();
        //proc.printMap();
        proc.generateOutput();
        //proc.printOutput();
        proc.saveOutput();
        System.out.println(proc.getOutputSum());
    }
}
