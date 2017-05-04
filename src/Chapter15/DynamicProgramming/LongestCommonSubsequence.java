package Chapter15.DynamicProgramming;

/**
 * Created by Yang on 2017/5/4.
 ************************************************************************************************
 * 最长公共子序列
 *
 * 给定两个序列X=<x1,x2,...,xm>和Y=<y1,y2,...,yn>，求X和Y长度最长的公共子序列
 ************************************************************************************************
 */
public class LongestCommonSubsequence {
    public String getLCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] LCSLength = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 两个序列尾字符相等时，尾字符必定是最长公共子序列的一部分
                // 否则，至少有一个序列的尾字符不在最长公共子序列中
                if(X.charAt(i-1) == Y.charAt(j-1)) {
                    LCSLength[i][j] = LCSLength[i-1][j-1] + 1;
                } else {
                    if(LCSLength[i-1][j] < LCSLength[i][j-1]) {
                        LCSLength[i][j] = LCSLength[i][j-1];
                    } else {
                        LCSLength[i][j] = LCSLength[i-1][j];
                    }
                }
            }
        }
        return printLCS(LCSLength, X, X.length(), Y.length());
    }

    private String printLCS(int[][] LCSLength, String X, int m, int n) {
        if(m == 0 || n== 0) {
            return "";
        }
        if(LCSLength[m][n] == LCSLength[m-1][n]) {
            return printLCS(LCSLength, X, m-1, n);
        } else if (LCSLength[m][n] == LCSLength[m][n-1]) {
            return printLCS(LCSLength, X, m, n-1);
        } else {
            return printLCS(LCSLength, X, m-1, n-1) + X.charAt(m-1) + "";
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();

        System.out.println(lcs.getLCS("ABCBDAB", "BDCABA") + " <--> BCBA");
        System.out.println(lcs.getLCS("abc", "abc") + " <--> abc");
        System.out.println(lcs.getLCS("haadbc", "fabggce") + " <--> abc");
    }
}
