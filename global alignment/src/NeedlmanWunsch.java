import java.util.*;
import java.util.Scanner;

public class NeedlmanWunsch {

    private static final int d = -8;
    private static Blosum50 blosumMatrix;

    public static void main(String[] args) {

        blosumMatrix = new Blosum50("src/blosum50.txt");

        Scanner sc = new Scanner(System.in);

        String S1, S2;
        S1 = sc.nextLine();
        S2 = sc.nextLine();

        String[] S1_arr = new String[S1.length()];
        for (int i = 0; i < S1.length(); ++i)
            S1_arr[i] = S1.substring(i, i+1);
        String[] S2_arr = new String[S2.length()];
        for (int j = 0; j < S2.length(); ++j)
            S2_arr[j] = S2.substring(j, j+1);


        int[][] matrix = new int[S2.length() + 1][S1.length() + 1];

        for (int i = 0; i < S2.length() + 1; ++i)
            matrix[i][0] = i*d;
        for (int j = 0; j < S1.length() + 1; ++j)
            matrix[0][j] = j*d;
        for (int i = 1; i < S2.length() + 1; ++i) {
            for (int j = 1; j < S1.length() + 1; ++j) {
                int diagSum = matrix[i - 1][j - 1] + blosumMatrix.getValue(S1_arr[j - 1], S2_arr[i - 1]);
                int rightSide = Math.max(matrix[i - 1][j] + d, matrix[i][j - 1] + d);
                matrix[i][j] = Math.max(diagSum, rightSide);
            }
        }

        System.out.println("Output the matrix of coefficients");
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));

        StringBuilder AlignmentA = new StringBuilder();
        StringBuilder AlignmentB = new StringBuilder();
        int first_sequence_length = S2.length();
        int second_sequence_length = S1.length();

        while ( (first_sequence_length > 0) && (second_sequence_length > 0) ) {
            int score = matrix[first_sequence_length][second_sequence_length];
            int scoreDiag = matrix[first_sequence_length - 1][second_sequence_length - 1];
            int scoreUp = matrix[first_sequence_length][second_sequence_length - 1];
            int scoreLeft = matrix[first_sequence_length - 1][second_sequence_length];

            if (score == (scoreDiag + blosumMatrix.getValue(S2_arr[first_sequence_length - 1],
                    S1_arr[second_sequence_length - 1]))) {
                AlignmentA.append(S2_arr[first_sequence_length - 1]);
                AlignmentB.append(S1_arr[second_sequence_length - 1]);
                first_sequence_length--;
                second_sequence_length--;
            } else if (score == scoreLeft + d) {
                AlignmentA.append(S2_arr[first_sequence_length - 1]);
                AlignmentB.append("-");
                first_sequence_length--;
            } else if (score == scoreUp + d) {
                AlignmentA.append("-");
                AlignmentB.append(S1_arr[second_sequence_length - 1]);
                second_sequence_length--;
            }
        }

        while (first_sequence_length > 0) {
            AlignmentA.append(S2_arr[first_sequence_length - 1]);
            AlignmentB.append("-");
            first_sequence_length--;
        }
        while (second_sequence_length > 0) {
            AlignmentA.append("-");
            AlignmentB.append(S1_arr[second_sequence_length - 1]);
            second_sequence_length--;
        }

        System.out.println("\nGlobal aligned sequences");
        System.out.println(AlignmentB.reverse().toString());
        System.out.println(AlignmentA.reverse().toString());
    }
}
