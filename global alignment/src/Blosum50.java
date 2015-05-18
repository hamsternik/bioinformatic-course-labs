import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Blosum50 {

    String[] aminoacids = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S",
                            "T", "W", "Y", "X"};
    private Map<String, Integer> aminoacidsMap;

    private int[][] blosum50;
    private String file;
    private int rows=0;
    private int columns=0;

    public Blosum50(String filename) {
        file = filename;

        readMatrixFromFile();
        makeAminoacidsMap();
    }

    private void readMatrixFromFile() {
        try {
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                ++rows;
                Scanner columnReader = new Scanner(sc.nextLine());
                columns = 0;
                while (columnReader.hasNextInt()) {
                    ++columns;
                    columnReader.nextInt();
                }
            }

            blosum50 = new int[rows][columns];
            sc.close();

            sc = new Scanner(new File(file));
            for (int i = 0; i < rows; ++i)
                for (int j = 0; j < columns; ++j)
                    if (sc.hasNextInt())
                        blosum50[i][j] = sc.nextInt();
            sc.close();
        } catch (IOException e) {
            System.out.println("Choosed file is not exist!\n");  e.printStackTrace();
        }
    }

    private void makeAminoacidsMap() {
        aminoacidsMap = new HashMap<String, Integer>();
        for (int i = 0; i < columns; ++i)
                aminoacidsMap.put(aminoacids[i], i);
    }

    public void printBlosumMatrix() {
        for (int[] matrixLine : blosum50)
            System.out.println(Arrays.toString(matrixLine));
    }

    public int getValue(String first_aminoacid, String second_aminoacid) {
        int i = aminoacidsMap.get(first_aminoacid);
        int j = aminoacidsMap.get(second_aminoacid);

        return blosum50[i][j];
    }
}
