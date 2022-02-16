package CFG;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CFG {
    public static void main(String[] args) throws FileNotFoundException {
        File grammer = new File(args[0]);
        Scanner reader = new Scanner(grammer);
        Set<String> nonTerminals = new HashSet();
        Set<String> terminals = new HashSet();
        String last = "";
        String start = "";
        String RHS = null;
        int count = 1;
        do {
            String line = reader.nextLine();
            String LHS = line.split("->")[0].trim();
            if (LHS.equals(line.trim())) {
                RHS = line.trim().split("\\|", 2)[1];
            } else {
                last = LHS;
                RHS = line.split("->")[1].trim();
                nonTerminals.add(LHS);
            }
            for (String rule: RHS.split("\\|")) {
                for (String symbol : rule.split("\\s+")) {
                    if (symbol.equals("$")) {
                        start = last;
                        continue;
                    }
                    if (symbol.equals("lambda")) {
                        continue;
                    }
                    if (symbol.equals(symbol.toLowerCase())) {
                        terminals.add(symbol.trim());
                    }
                }
                System.out.println("(" + count++ + ")  " + last + " -> " + rule);
            }
        } while (reader.hasNext());
        System.out.println();
        System.out.println("Grammar Start Symbol: " + start);
        System.out.println();
        System.out.println("Grammar Non-terminals:");
        for (String s: nonTerminals) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("Grammar terminals:");
        for (String s: terminals) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
