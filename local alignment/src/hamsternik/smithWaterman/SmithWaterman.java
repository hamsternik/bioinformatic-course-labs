package hamsternik.smithWaterman;

import java.util.*;
import java.util.Scanner;

public class SmithWaterman {

    private final static int d = -8;
    private static Blosum50 blosum50;

    public static void main(String[] args) {
	    blosum50 = new Blosum50();

        Scanner sc = new Scanner(System.in);
        String S1 = sc.nextLine();
        String S2 = sc.nextLine();

        String[] S1_arr = new String[S1.length()];
        for (int i = 0; i < S1.length(); ++i)
            S1_arr[i] = S1.substring(i, i+1);
        String[] S2_arr = new String[S2.length()];
        for (int j = 0; j < S2.length(); ++j)
            S2_arr[j] = S2.substring(j, j+1);

        int[][] substitutionMatrix = new int[S1.length()+1][S2.length()+1];
        for (int i = 0; i < S1.length() + 1; ++i)
            substitutionMatrix[i][0] = i*d;
        for (int j = 0; j < S2.length() + 1; ++j)
            substitutionMatrix[0][j] = j*d;

        for (int i = 1; i < S1.length()+1; ++i) {
            for (int j = 1; j < S2.length()+1; ++j) {
                int deletion = substitutionMatrix[i-1][j] + d;
                int insertion = substitutionMatrix[i][j-1] + d;
                int matching = substitutionMatrix[i-1][j-1] + blosum50.getValue(S1_arr[i-1], S2_arr[j-1]);
                substitutionMatrix[i][j] = Math.max(Math.max(0, matching), Math.max(deletion, insertion));
            }
        }

        System.out.println("Output the matrix of coefficients");
        for (int[] row : substitutionMatrix)
            System.out.println(Arrays.toString(row));


    }
}
