package hamsternik.smithWaterman;

import java.util.*;
import java.util.Map;

public class Blosum50 {

    String[] aminoacids = { "A", "R", "N", "D", "C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S",
            "T", "W", "Y", "X"};
    private Map<String, Integer> aminoacidsMap;
    private final static int N = 20;

    private static final int[][] blosum50 =
            {       /* A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  X */
                    {  5,-2,-1,-2,-1,-1,-1, 0,-2,-1,-2,-1,-1,-3,-1, 1, 0,-3,-2, 0  },
                    { -2, 7,-1,-2,-4, 1, 0,-3, 0,-4,-3, 3,-2,-3,-3,-1,-1,-3,-1,-3  },
                    { -1,-1, 7, 2,-2, 0, 0, 0, 1,-3,-4, 0,-2,-4,-2, 1, 0,-4,-2,-3  },
                    { -2,-2, 2, 8,-4, 0, 2,-1,-1,-4,-4,-1,-4,-5,-1, 0,-1,-5,-3,-4  },
                    { -1,-4,-2,-4,13,-3,-3,-3,-3,-2,-2,-3,-2,-2,-4,-1,-1,-5,-3,-1  },
                    { -1, 1, 0, 0,-3, 7, 2,-2, 1,-3,-2, 2, 0,-4,-1, 0,-1,-1,-1,-3  },
                    { -1, 0, 0, 2,-3, 2, 6,-3, 0,-4,-3, 1,-2,-3,-1,-1,-1,-3,-2,-3  },
                    {  0,-3, 0,-1,-3,-2,-3, 8,-2,-4,-4,-2,-3,-4,-2, 0,-2,-3,-3,-4  },
                    { -2, 0, 1,-1,-3, 1, 0,-2,10,-4,-3, 0,-1,-1,-2,-1,-2,-3, 2,-4  },
                    { -1,-4,-3,-4,-2,-3,-4,-4,-4, 5, 2,-3, 2, 0,-3,-3,-1,-3,-1, 4  },
                    { -2,-3,-4,-4,-2,-2,-3,-4,-3, 2, 5,-3, 3, 1,-4,-3,-1,-2,-1, 1  },
                    { -1, 3, 0,-1,-3, 2, 1,-2, 0,-3,-3, 6,-2,-4,-1, 0,-1,-3,-2,-3  },
                    { -1,-2,-2,-4,-2, 0,-2,-3,-1, 2, 3,-2, 7, 0,-3,-2,-1,-1, 0, 1  },
                    { -3,-3,-4,-5,-2,-4,-3,-4,-1, 0, 1,-4, 0, 8,-4,-3,-2, 1, 4,-1  },
                    { -1,-3,-2,-1,-4,-1,-1,-2,-2,-3,-4,-1,-3,-4,10,-1,-1,-4,-3,-3  },
                    {  1,-1, 1, 0,-1, 0,-1, 0,-1,-3,-3, 0,-2,-3,-1, 5, 2,-4,-2,-2  },
                    {  0,-1, 0,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-1, 2, 5,-3,-2, 0  },
                    { -3,-3,-4,-5,-5,-1,-3,-3,-3,-3,-2,-3,-1, 1,-4,-4,-3,15, 2,-3  },
                    { -2,-1,-2,-3,-3,-1,-2,-3, 2,-1,-1,-2, 0, 4,-3,-2,-2, 2, 8,-1  },
                    {  0,-3,-3,-4,-1,-3,-3,-4,-4, 4, 1,-3, 1,-1,-3,-2, 0,-3,-1, 5  }
            };      /* A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  V */

    public Blosum50() {
        makeAminoacidsMap();
    }

    private void makeAminoacidsMap() {
        aminoacidsMap = new HashMap<String, Integer>();
        for (int i = 0; i < N; ++i)
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
