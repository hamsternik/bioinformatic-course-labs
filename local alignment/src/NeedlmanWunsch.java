import java.util.*;
import java.util.Scanner;

public class NeedlmanWunsch {

    private static final int d = -8;
    private static Blosum50 blosumMatrix;

    private static int max(int a, int b, int c) {
        if (a > b && a > c) { return a; }
        else if (a > b && a < c) { return c; }
        else if (a > c && a < b) { return b; }
        else { return -1; }
    }

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


        int[][] matrix = new int[S1.length() + 1][S2.length() + 1];

        for (int i = 0; i < S1.length() + 1; ++i)
            matrix[i][0] = i*d;
        for (int j = 0; j < S2.length() + 1; ++j)
            matrix[0][j] = j*d;
        try {
            for (int i = 1; i < S1.length() + 1; ++i)
                for (int j = 1; j < S2.length() + 1; ++j)
                    matrix[i][j] = max(matrix[i-1][j-1] + blosumMatrix.getValue(S1_arr[i-1], S2_arr[j-1]),
                            matrix[i-1][j] + d, matrix[i][j-1] + d);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка работы с индексами массива: " + e);
        }

        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));

        StringBuilder AlignmentA = new StringBuilder();
        StringBuilder AlignmentB = new StringBuilder();
        StringBuilder LCS = new StringBuilder();
        int first_sequence_length = S1.length() - 1;
        int second_sequence_length = S2.length() - 1;

        try {
            while ( (first_sequence_length >= 0) && (second_sequence_length >= 0) ) {
                if (S1_arr[first_sequence_length].equals(S2_arr[second_sequence_length])) {
                    LCS.append(S1_arr[first_sequence_length]);
                    first_sequence_length -= 1;
                    second_sequence_length -= 1;
                } else if (matrix[first_sequence_length][second_sequence_length+1] > matrix[first_sequence_length+1][second_sequence_length]) {
                    first_sequence_length -= 1;
                } else {
                    second_sequence_length -= 1;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка работы с индексами массива: " + e);
            e.printStackTrace();
        }

        System.out.println("Наша искомая подпоследовательность: " + LCS.reverse().toString());

        first_sequence_length = S1.length() - 1;
        second_sequence_length = S2.length() - 1;
        try {
            while ( (first_sequence_length >= 0) && (second_sequence_length >= 0) ) {
                int score = matrix[first_sequence_length + 1][second_sequence_length + 1];
                int scoreDiag = matrix[first_sequence_length][second_sequence_length];
                int scoreUp = matrix[first_sequence_length + 1][second_sequence_length];
                int scoreLeft = matrix[first_sequence_length][second_sequence_length + 1];

                if (score == (scoreDiag + blosumMatrix.getValue(S1_arr[first_sequence_length], S2_arr[second_sequence_length]))) {
                    AlignmentA.append(S1_arr[first_sequence_length]);
                    AlignmentB.append(S2_arr[second_sequence_length]);
                    first_sequence_length--;
                    second_sequence_length--;
                } else if (score == scoreLeft + d) {
                    AlignmentA.append(S1_arr[first_sequence_length]);
                    AlignmentB.append("-");
                    first_sequence_length--;
                } else if (score == scoreUp + d) {
                    AlignmentA.append("-");
                    AlignmentB.append(S2_arr[second_sequence_length]);
                    second_sequence_length--;
                } else {
                    first_sequence_length--;
                    second_sequence_length--;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка при работе с индексами массива" + e);
        }

        System.out.println(AlignmentA.reverse().toString());
        System.out.println(AlignmentB.reverse().toString());
    }
}
