import java.util.*;

public class StableMarriage {

    // Number of men and women
    static int N = 4;

    // Function to check if woman prefers new man over current engagement
    static boolean prefersNewProposal(int[][] womenPref, int woman, int newMan, int currentMan) {
        for (int i = 0; i < N; i++) {
            if (womenPref[woman][i] == newMan)
                return true;
            if (womenPref[woman][i] == currentMan)
                return false;
        }
        return false; // Shouldn't reach here
    }

    public static void galeShapley(int[][] menPref, int[][] womenPref) {
        // Array to store the current partner of women. -1 indicates single.
        int[] womenPartner = new int[N];
        Arrays.fill(womenPartner, -1);

        // Array to check if man is free
        boolean[] manFree = new boolean[N];
        Arrays.fill(manFree, true);

        // Number of free men
        int freeCount = N;

        // While there are free men
        while (freeCount > 0) {
            int m;
            for (m = 0; m < N; m++) {
                if (manFree[m])
                    break;
            }

            // Go through man m's preference list
            for (int i = 0; i < N && manFree[m]; i++) {
                int w = menPref[m][i];

                // If woman w is free, engage them
                if (womenPartner[w] == -1) {
                    womenPartner[w] = m;
                    manFree[m] = false;
                    freeCount--;
                }
                else {
                    // If woman w prefers m over her current partner
                    int m2 = womenPartner[w];
                    if (prefersNewProposal(womenPref, w, m, m2)) {
                        womenPartner[w] = m;
                        manFree[m] = false;
                        manFree[m2] = true;
                    }
                }
            }
        }

        // Print final stable matches
        System.out.println("Woman  -  Man");
        for (int i = 0; i < N; i++) {
            System.out.println("  W" + i + "     M" + womenPartner[i]);
        }
    }

    public static void main(String[] args) {
        // Men's preference list (rows: men, columns: preference ranking)
        int[][] menPref = {
            {0, 1, 2, 3},
            {2, 0, 1, 3},
            {1, 2, 3, 0},
            {0, 1, 2, 3}
        };

        // Women's preference list (rows: women, columns: preference ranking)
        int[][] womenPref = {
            {2, 1, 3, 0},
            {0, 1, 2, 3},
            {1, 0, 3, 2},
            {0, 1, 2, 3}
        };

        galeShapley(menPref, womenPref);
    }
}
