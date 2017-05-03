package Chapter15.DynamicProgramming;

/**
 * Created by yang on 2017/4/29.
 ************************************************************************************************
 * 矩阵链乘法
 *
 * 给定一个n个矩阵的序列（矩阵链）<A(1),A(2),...,A(n)>，矩阵A(i)的规模为p(i-1)×p(i), (1≤i≤n)，
 * 求完全括号化方案，使得计算乘积A(1)A(2)...A(n)所需标量乘法次数最少。
 ************************************************************************************************
 * 用minScalarMultiplicationNum[i,j]表示A(i)A(i+1)...A(j)的矩阵链需要的最少标量乘法次数。
 * 用optimalPartitionPosition[i,j]表示使得A(i)A(i+1)...A(j)达到最少标量乘法次数的切分位置k。即，按
 * [A(i)A(i+1)...A(k)][A(k+1)...A(j)]的运算方式，将使得A(i)A(i+1)...A(j)标量乘法次数最少。
 ************************************************************************************************
 */
public class MatrixChainMultiplication {
    public String printOptimalParens(int[][] matrixSize) {
        int n = matrixSize.length;
        int[][] minScalarMultiplicationNum = new int[n][n];
        int[][] optimalPartitionPosition = new int[n][n];

        for (int chainLength = 2; chainLength <= n; chainLength++) {
            for (int i = 0; i < n-chainLength+1; i++) {
                int j = i + chainLength-1;
                minScalarMultiplicationNum[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int multipleNum = minScalarMultiplicationNum[i][k] + minScalarMultiplicationNum[k+1][j] + matrixSize[i][0]*matrixSize[k][1]*matrixSize[j][1];
                    if(multipleNum < minScalarMultiplicationNum[i][j]) {
                        minScalarMultiplicationNum[i][j] = multipleNum;
                        optimalPartitionPosition[i][j] = k;
                    }
                }
            }
        }
        return printOptimalParens(optimalPartitionPosition, 0, n-1).toString();
    }

    private StringBuilder printOptimalParens(int[][] optimalPartitionPosition, int i, int j) {
        StringBuilder res = new StringBuilder();
        if (i == j) {
            res.append("A" + i);
        } else {
            res.append("(");
            res.append(printOptimalParens(optimalPartitionPosition, i, optimalPartitionPosition[i][j]));
            res.append(printOptimalParens(optimalPartitionPosition, optimalPartitionPosition[i][j]+1, j));
            res.append(")");
        }

        return res;
    }

    public static void main(String[] args) {
        MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication();

        int[][] matrixSize = new int[][]{{30,35},{35,15},{15,5},{5,10},{10,20},{20,25}};
        System.out.println(matrixChainMultiplication.printOptimalParens(matrixSize) + " <--> ((A0(A1A2))((A3A4)A5))");
    }
}
