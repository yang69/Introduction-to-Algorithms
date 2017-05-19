package Chapter32.StringMatching;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yang on 2017/5/15.
 ************************************************************************************************
 * Rabin-Karp字符串匹配算法
 *      预处理时间：0(m)
 *      匹配时间：最坏情况下O((n-m+1)m)
 *      总运行时间（预处理时间+匹配时间）：O(n+m)
 * 将字符串转化为数值，当子字符串向右移动的过程中，其代表的数值可以在常数时间内算出
 ************************************************************************************************
 */
public class RabinKarpMatcher {
    /**
     * Rabin-Karp字符串匹配算法
     * @param T 文本Text，长度为n的数组：[1...n]
     * @param P 模式Pattern，长度为m的数组：P[1...m]
     * @param d 基数d，字母表的大小
     * @param q 素数q，满足d*p不溢出，q越大越好
     * @return 使P匹配T的有效偏移s的集合，即列表中的每一个值s都可以使P[1...m]=T[s+1...s+m]
     */
    public List<Integer> rabinKarpMatcher(String T, String P, int d, int q) {
        List<Integer> shifts = new LinkedList<Integer>();
        int n = T.length();
        int m = P.length();
        int h = (int)Math.pow(d, m-1) % q;
        int p = 0;
        int t = 0;
        // 预处理
        for (int i = 0; i < m; i++) {
            p = (d*p + P.charAt(i)%d) % q;
            t = (d*t + T.charAt(i)%d) % q;
        }
        // 匹配
        for (int s = 0; s <= n-m; s++) {
            if (p == t) {
                if(P.equals(T.substring(s, s+m))) {
                    shifts.add(s);
                }
            }
            if (s < n-m) {
                t = (d*(t - (T.charAt(s)%d) * h) + T.charAt(s+m)%d) % q;
            }
        }

        return shifts;
    }

    public static void main(String[] args) {
        RabinKarpMatcher rabinKarpMatcher = new RabinKarpMatcher();

        System.out.println(rabinKarpMatcher.rabinKarpMatcher("acaabc", "aab", 26, 10657601));
        System.out.println(rabinKarpMatcher.rabinKarpMatcher("acaabc", "", 26, 10657601)); //这里有问题
        System.out.println(rabinKarpMatcher.rabinKarpMatcher("acaabc", "a", 26, 10657601));
        System.out.println(rabinKarpMatcher.rabinKarpMatcher("aab", "aab", 26, 10657601));
        System.out.println(rabinKarpMatcher.rabinKarpMatcher("a", "a", 26, 10657601));
        System.out.println(rabinKarpMatcher.rabinKarpMatcher("", "", 26, 10657601));
        System.out.println(rabinKarpMatcher.rabinKarpMatcher("ac", "ab", 26, 10657601));
    }
}
