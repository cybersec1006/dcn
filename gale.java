import java.util.*;

public class gale {
    static int N = 4;

    static boolean prefersNew(int[][] womenPref, int w, int current, int m) {
        for (int i = 0; i < N; i++) {
            if (womenPref[w][i] == m)
                return true;
            if (womenPref[w][i] == current)
                return false;
        }
        return false;
    }

    static void gs(int[][] menPref, int[][] womenPref) {
        int[] wPartner = new int[N]; // woman to man match
        Arrays.fill(wPartner, -1);
        boolean[] mFree = new boolean[N]; // men free status
        Arrays.fill(mFree, true);
        int free = N;

        while (free > 0) {
            int m;
            for (m = 0; m < N; m++)
                if (mFree[m])
                    break;

            for (int i = 0; i < N && mFree[m]; i++) {
                int w = menPref[m][i];

                // If woman is free
                if (wPartner[w] == -1) {
                    wPartner[w] = m;
                    mFree[m] = false;
                    free--;
                } else {
                    int current = wPartner[w];
                    if (prefersNew(womenPref, w, current, m)) {
                        wPartner[w] = m;
                        mFree[m] = false;
                        mFree[current] = true;
                    }
                }
            }
        }

        // Print the final matches
        System.out.println("Woman  -  Man");
        for (int i = 0; i < N; i++) {
            System.out.println("  " + i + "     " + wPartner[i]);
        }
    }

    public static void main(String[] args) {
        // Men preference
        int[][] menPref = {
                { 2, 0, 1, 3 }, // Man 0 prefers Woman 2 > 0 > 1 > 3
                { 1, 2, 3, 0 }, // Man 1 prefers Woman 1 > 2 > 3 > 0
                { 0, 1, 3, 2 }, // Man 2 prefers Woman 0 > 1 > 3 > 2
                { 3, 2, 0, 1 } // Man 3 prefers Woman 3 > 2 > 0 > 1
        };

        // Women preference
        int[][] womenPref = {
                { 1, 0, 3, 2 }, // Woman 0 prefers Man 1 > 0 > 3 > 2
                { 0, 2, 1, 3 }, // Woman 1 prefers Man 0 > 2 > 1 > 3
                { 3, 1, 0, 2 }, // Woman 2 prefers Man 3 > 1 > 0 > 2
                { 2, 3, 0, 1 } // Woman 3 prefers Man 2 > 3 > 0 > 1
        };

        gs(menPref, womenPref);
    }
}
