package com.toreforge;

import java.io.*;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

public class Processor {
    private Map theMap = new Map();
    private Map output = new Map();

    public void generateMap(int x, int y) {
        for (int height = 0; height < y; height++) {
            for (int width = 0; width < x; width++) {
                theMap.putValue(height, width, ThreadLocalRandom.current().nextLong(0, 3));
            }
        }
    }

    public void saveMap() throws IOException {
        int x = 0;
        int y = 0;

        File f = new File("input.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        while (theMap.rows.containsKey(y)) {
            while (theMap.rows.get(y).containsKey(x)) {
                writer.write(String.valueOf(theMap.rows.get(y).get(x).getValue()));
                if (theMap.rows.get(y).containsKey(x+1))
                    writer.write(",");
                x++;
            }
            y++;
            x=0;
            writer.newLine();
        }
        writer.close();
    }

    public void saveOutput() throws IOException {
        int x = 0;
        int y = 0;

        File f = new File("output.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        while (output.rows.containsKey(y)) {
            while (output.rows.get(y).containsKey(x)) {
                writer.write(String.valueOf(output.rows.get(y).get(x).getValue()));
                if (output.rows.get(y).containsKey(x+1))
                    writer.write(",");
                x++;
            }
            y++;
            x=0;
            writer.newLine();
        }
        writer.close();
    }

    public void printMap() {
        int x = 0;
        int y = 0;

        while (theMap.rows.containsKey(y)) {
            while (theMap.rows.get(y).containsKey(x)) {
                System.out.print(theMap.rows.get(y).get(x).getValue());
                if (theMap.rows.get(y).containsKey(x+1))
                    System.out.print(" | ");
                x++;
            }
            y++;
            x=0;
            System.out.println();
        }
    }

    public void printOutput() {
        int x = 0;
        int y = 0;

        while (output.rows.containsKey(y)) {
            while (output.rows.get(y).containsKey(x)) {
                System.out.print(output.rows.get(y).get(x).getValue());
                if (output.rows.get(y).containsKey(x+1))
                    System.out.print(" | ");
                x++;
            }
            y++;
            x=0;
            System.out.println();
        }
    }

    public Long getOutputSum() {
        int x = 0;
        int y = 0;
        Long sum = 0L;

        while (output.rows.containsKey(y)) {
            while (output.rows.get(y).containsKey(x)) {
                sum += output.rows.get(y).get(x).getValue();
                x++;
            }
            y++;
            x=0;
            System.out.println();
        }

        return sum;
    }

    public void loadMap() throws IOException {
        theMap = new Map();
        FileReader fileReader =
                new FileReader("input.txt");
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);
        String line;
        int row = 0;
        while ((line = bufferedReader.readLine()) != null) {
            loadLine(line, row);
            row++;
        }

        bufferedReader.close();
    }

    private void loadLine(String line, int row) {
        String[] cells = line.split(",");
        if (cells.length == 0)
            return;

        for (int index = 0; index < cells.length; index++) {
            theMap.putValue(index, row, Long.valueOf(cells[index]));
        }
    }


    public void generateOutput() {
        for (int row = 0; row < theMap.rows.size(); row++) {
            for (int index=0; index < theMap.rows.get(row).size(); index++) {
                output.putValue(index, row, getOutputValue(index, row));
            }
        }
    }

    private Long getOutputValue(int x, int y) {
        Long sum = theMap.rows.get(y).get(x).getValue();
        if (theMap.rows.get(y).containsKey(x-1))
            sum += theMap.rows.get(y).get(x-1).getValue();
        if (theMap.rows.get(y).containsKey(x+1))
            sum += theMap.rows.get(y).get(x+1).getValue();
        if (theMap.rows.containsKey(y-1))
            sum += theMap.rows.get(y-1).get(x).getValue();
        if (theMap.rows.containsKey(y+1))
            sum += theMap.rows.get(y+1).get(x).getValue();

        if (theMap.rows.containsKey(y-1))
            if (theMap.rows.get(y-1).containsKey(x-1))
            sum += theMap.rows.get(y-1).get(x-1).getValue();

        if (theMap.rows.containsKey(y-1))
            if (theMap.rows.get(y-1).containsKey(x+1))
                sum += theMap.rows.get(y-1).get(x+1).getValue();

        if (theMap.rows.containsKey(y+1))
            if (theMap.rows.get(y+1).containsKey(x-1))
                sum += theMap.rows.get(y+1).get(x-1).getValue();

        if (theMap.rows.containsKey(y+1))
            if (theMap.rows.get(y+1).containsKey(x+1))
                sum += theMap.rows.get(y+1).get(x+1).getValue();

        return sum;
    }
}
