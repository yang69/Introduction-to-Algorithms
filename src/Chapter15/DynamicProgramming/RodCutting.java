package Chapter15.DynamicProgramming;

import java.util.ArrayList;

/**
 * Created by yang on 2017/4/28.
 ************************************************************************************************
 * 钢条切割
 *
 * 长度为 i 英寸的钢条价格为 pi(i=1,2,...,10)
 * | 长度i  | 1 | 2 | 3 | 4 |  5 |  6 |  7 |  8 |  9 | 10 |
 * | 价格pi | 1 | 5 | 8 | 9 | 10 | 17 | 17 | 20 | 24 | 30 |
 * 给定一段长度为 n 英寸的钢条和一个价格表pi，求钢条的切割方案，使得销售收益最大（切割没有成本）
 ************************************************************************************************
 */
public class RodCutting {
    int[] prices = new int[]{0,1,5,8,9,10,17,17,20,24,30};
    public int maxProfit(int n) {
        if(n <= 0) {
            return 0;
        }
        int[] maxProfit = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int q = 0;
            for (int j = 0; j <= i && j <= 10; j++) {
                q = Math.max(q, prices[j] + maxProfit[i-j]);
            }
            maxProfit[i] = q;
        }

        return maxProfit[n];
    }

    public ArrayList<Integer> cuttingPlanOfMaxProfit(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        if(n <= 0) {
            return res;
        }
        int[] maxProfit = new int[n+1];
        int[] firstCutLength = new int[n+1];
        for (int i = 1; i <= n; i++) {
            int q = 0;
            for (int j = 0; j <= i && j <= 10; j++) {
                if(q < prices[j] + maxProfit[i-j]) {
                    q = prices[j] + maxProfit[i-j];
                    firstCutLength[i] = j;
                }
            }
            maxProfit[i] = q;
        }

        while (n > 0) {
            res.add(firstCutLength[n]);
            n -= firstCutLength[n];
        }

        return res;
    }

    public static void main(String[] args) {
        RodCutting rodCutting = new RodCutting();

        for (int i = 0; i < 12; i++) {
            System.out.println(rodCutting.maxProfit(i));
        }

        System.out.println(rodCutting.maxProfit(131));
        System.out.println(rodCutting.cuttingPlanOfMaxProfit(131));
        System.out.println((char)186);
    }
}
