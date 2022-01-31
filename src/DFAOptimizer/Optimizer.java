package DFAOptimizer;

import javafx.util.Pair;

import java.util.*;

public class Optimizer {
    public static int[][] optimizeDFA(int[][] table) {
        Stack<Pair<ArrayList<int[]>, Integer>> L = new Stack();
        Stack<ArrayList<int[]>> M = new Stack();
        ArrayList<int[]> set = new ArrayList<>();
        for (int[] row: table) {
            if (row[0] == 0) {
                set.add(row);
            }
        }
        L.push(new Pair(set.clone(), 2));
        set.clear();
        for (int[] row: table) {
            if (row[0] == 1) {
                set.add(row);
            }
        }
        L.push(new Pair(set, 2));
        while (!L.empty()) {
            Pair<ArrayList<int[]>, Integer> P = L.pop();
            Map<Integer, ArrayList<int[]>> states = new HashMap();
            for (int[] row: P.getKey()) {
                if (states.containsKey(row[P.getValue()])) {
                    states.get(row[P.getValue()]).add(row);
                } else {
                    ArrayList<int[]> S = new ArrayList<>();
                    S.add(row);
                    states.put(row[P.getValue()], S);
                }
            }
            for (ArrayList<int[]> v: states.values()) {
                if (v.size() < 2) {
                    continue;
                }
                if (P.getValue() + 1 >= table[0].length) {
                    M.add(v);
                } else {
                    L.add(new Pair<>(v, P.getValue()+1));
                }
            }
        }
        for (ArrayList<int[]> dupe: M) {
            for (int[] row: table) {
                if (row == null || dupe.contains(row)) {
                    continue;
                }
                for (int c = 0; c < row.length; c++) {
                    int item = row[c];
                    for (int i = 1; i < dupe.size(); i++) {
                        if (item == dupe.get(i)[1]) {
                            row[c] = dupe.get(0)[1];
                        }
                    }
                }
            }
            for (int i = 1; i < dupe.size(); i++) {
                table[dupe.get(i)[1]] = null;
            }
        }
        return table;
    }

}
