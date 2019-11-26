package BellmanFordAlgorithm;

import java.io.*;

public class TestBellmanFordAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        BellmanFordAlgorithm hw02 = new BellmanFordAlgorithm();

        File file01 = new File("data11_bellman_ford_1.txt");
        File file02 = new File("data11_bellman_ford_2.txt");

        System.out.print("    << data11_bellman_ford_1.txt >>");
        hw02.init();
        hw02.insert(file01);

        System.out.print("\n    << data11_bellman_ford_2.txt >>");
        hw02.init();
        hw02.insert(file02);

    }
}
