package MatrixChainMultiplication;

import java.io.*;

public class TestMatrixChainMultiplication {
    public static void main(String[] args) throws FileNotFoundException {
        MatrixChainMultiplication hw01 = new MatrixChainMultiplication();

        File file = new File("data11_matrix_chain.txt");

        hw01.insert(file);

    }
}
