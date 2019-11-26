package MatrixChainMultiplication;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixChainMultiplication {

    static int[][] m = null;        // 행렬의 곱셈 횟수 저장 배열
    static int[] p = null;          // 입력 행렬의 사이즈 저장 배열

    public void insert(File F) throws FileNotFoundException {
        /** File read & insert */
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        String[] temp = null;
        List<Matrix> matrixList = new ArrayList<>();

        try {
            String line = "";
            while ((line = br.readLine()) != null) {
                temp = line.split(",");
                Matrix M = new Matrix(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                matrixList.add(M);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        int n = matrixList.size();
        p = new int[n + 1];

        p[0] = matrixList.get(0).column;
        for(int i = 1; i < n + 1; i++)
            p[i] = matrixList.get(i - 1).row;

        m = new int[n + 1][n + 1];

        /** 출력문 */
        print();
    }

    private int[][] MatrixChainOrder(int[] p) {
        int n = p.length - 1;

        for (int i = 1; i <= n; i++)
            m[i][i] = 0;

        /** l is the chain length*/
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + (p[i - 1] * p[k] * p[j]);
                    if(q < m[i][j])
                        m[i][j] = q;
                }
            }
        }
       return m;
    }

    private void print() {
        /** 출력문 */
        int[][] printM = MatrixChainOrder(p);

        for(int i = 1; i < printM.length; i++) {
            for(int j = 1; j < i; j++)
                System.out.printf("%7s", " ");
            for(int j = i; j < printM[i].length; j++)
                System.out.printf("%7d", printM[i][j]);
            System.out.println();
        }
    }

}
