package Chapter32.StringMatching;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yang on 2017/5/19.
 ************************************************************************************************
 * 字符串匹配自动机
 *      预处理时间：0(m|∑|)
 *      匹配时间：O(n)
 ************************************************************************************************
 */
public class FiniteAutomatonMatcher {
    public List<Integer> finiteAutomatonMatcher(String T, String P) {
        List<Integer> shifts = new LinkedList<Integer>();
        int n = T.length();
        int m = P.length();
        if (m == 0) {
            shifts.add(0);
        }
        int[][] delta = computeTransitionFunction(P, 256);
        int q = 0;
        for (int i = 0; i < n; i++) {
            q = delta[q][T.charAt(i)];
            if (q == m) {
                shifts.add(i - m + 1);
            }
        }

        return shifts;
    }

    /**
     * 根据给定模式P计算转移函数，下面这个算法的复杂度是O((m^3)*|∑|)
     * 存在更快的算法，计算转移函数的时间可以改进为0(m|∑|)
     * @param P 模式
     * @param alphabetSize 字母表的大小
     * @return
     */
    private int[][] computeTransitionFunction(String P, int alphabetSize) {
        int m = P.length();
        int[][] delta = new int[m+1][alphabetSize];
        for (int q = 0; q <= m; q++) {
            for (int i = 0; i < alphabetSize; i++) {
                int k = Math.min(m+1, q+2);
                do {
                    k--;
                } while (!isSuffix(P.substring(0,q) + (char)i, P.substring(0,k)));
                delta[q][i] = k;
            }
        }

        return delta;
    }

    /**
     * 判断suffix是否是s的后缀
     * @param s
     * @param suffix
     * @return
     */
    private boolean isSuffix(String s, String suffix) {
        if (suffix.length() > s.length()) {
            return false;
        }
        for (int i = 0; i < suffix.length(); i++) {
            if (s.charAt(s.length()-1-i) != suffix.charAt(suffix.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FiniteAutomatonMatcher finiteAutomatonMatcher = new FiniteAutomatonMatcher();

        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("acaabc", "aab"));
        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("acaabc", ""));
        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("acaabc", "a"));
        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("aab", "aab"));
        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("a", "a"));
        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("", ""));
        System.out.println(finiteAutomatonMatcher.finiteAutomatonMatcher("ac", "ab"));
    }
}
