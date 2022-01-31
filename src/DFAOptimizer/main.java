package DFAOptimizer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        ArrayList<int[]> table = new ArrayList<>();
        while (in.hasNext()) {
            String line = in.nextLine();
            int[] arr = new int[line.split("\\s+").length];
            int i = 0;
            for (String s: line.split("\\s+")) {
                if (s.equals("E")) {
                    s = "-1";
                }
                if (s.equals("+")) {
                    s = "1";
                }
                if (s.equals("-")) {
                    s = "0";
                }
                arr[i] = Integer.parseInt(s);
                i++;
            }
            table.add(arr);
        }
        int[][] ans = Optimizer.optimizeDFA(table.toArray(new int[0][0]));
        for (int[] row: ans) {
            if (row == null) {
                System.out.println();
                continue;
            }
            if (row[0] == 1) {
                System.out.print("+ ");
            } else {
                System.out.print("- ");
            }
            for (int i = 1; i < row.length; i++) {
                if (row[i] == -1) {
                    System.out.print("E ");
                } else {
                    System.out.print(row[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
