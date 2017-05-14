package Chapter32.StringMatching;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yang on 2017/5/14.
 ************************************************************************************************
 * 朴素字符串匹配算法
 *
 * 通过一个循环找到所有有效偏移s，该循环对n-m+1个可能是s值进行检测，
 * 看是否满足条件P[1...m]=T[s+1...s+m]
 ************************************************************************************************
 */
public class NaiveStringMatcher {
    /**
     * 朴素字符串匹配算法
     * @param T 文本Text，长度为n的数组：[1...n]
     * @param P 模式Pattern，长度为m的数组：P[1...m]
     * @return 使P匹配T的有效偏移s的集合，即列表中的每一个值s都可以使P[1...m]=T[s+1...s+m]
     */
    public List<Integer> naiveStringMatcher(String T, String P) {
        List<Integer> shifts = new LinkedList<Integer>();
        int n = T.length();
        int m = P.length();
        for (int s = 0; s <= n-m; s++) {
            if (P.equals(T.substring(s, s+m))) {
                shifts.add(s);
            }
        }

        return shifts;
    }

    public static void main(String[] args) {
        NaiveStringMatcher naiveStringMatcher = new NaiveStringMatcher();

        System.out.println(naiveStringMatcher.naiveStringMatcher("acaabc", "aab"));
        System.out.println(naiveStringMatcher.naiveStringMatcher("acaabc", "a"));
        System.out.println(naiveStringMatcher.naiveStringMatcher("aab", "aab"));
        System.out.println(naiveStringMatcher.naiveStringMatcher("", ""));
        System.out.println(naiveStringMatcher.naiveStringMatcher("ac", "ab"));
    }
}
