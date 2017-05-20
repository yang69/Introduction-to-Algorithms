package Chapter32.StringMatching;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yang on 2017/5/20.
 ************************************************************************************************
 * Knuth-Morris-Pratt算法
 *      预处理时间：0(m)
 *      匹配时间：O(n)
 ************************************************************************************************
 */
public class KMPMatcher {
    public List<Integer> KMPMatcher(String T, String P) {
        List<Integer> shifts = new LinkedList<Integer>();
        int n = T.length();
        int m = P.length();
        int q = 0;
        int[] pi = computePrefixFunction(P);
        if (m == 0) {
            shifts.add(0);
        }
        for (int i = 0; i < n; i++) {
            while (q > 0 && P.charAt(q) != T.charAt(i)) {
                q = pi[q];
            }
            if (q < m && P.charAt(q) == T.charAt(i)) {
                q++;
            }
            if (q == m) {
                shifts.add(i+1-m);
                q = pi[q];
            }
        }

        return shifts;
    }

    private int[] computePrefixFunction(String P) {
        int m = P.length();
        int[] pi = new int[m+1];
        int k = 0;
        for (int q = 2; q <= m; q++) {
            while (k > 0 && P.charAt(k) != P.charAt(q-1)) {
                k = pi[k];
            }
            if (P.charAt(k) == P.charAt(q-1)) {
                k++;
            }
            pi[q] = k;
        }

        return pi;
    }

    public static void main(String[] args) {
        KMPMatcher kmpMatcher = new KMPMatcher();

        System.out.println(kmpMatcher.KMPMatcher("acaabc", "aab"));
        System.out.println(kmpMatcher.KMPMatcher("acaabc", ""));
        System.out.println(kmpMatcher.KMPMatcher("acaabc", "a"));
        System.out.println(kmpMatcher.KMPMatcher("aab", "aab"));
        System.out.println(kmpMatcher.KMPMatcher("a", "a"));
        System.out.println(kmpMatcher.KMPMatcher("", ""));
        System.out.println(kmpMatcher.KMPMatcher("ac", "ab"));
    }
}
