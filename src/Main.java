// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import pack.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("Not enough parameters");
            return;
        }

        try{
            int n = Integer.parseInt(args[0]);
            int maxW = Integer.parseInt(args[1]);
            int maxH = Integer.parseInt(args[2]);

            Pack pack1 = new PackV1(n, maxW, maxH);
            List<int[][]> sol1 = pack1.pack();
            System.out.println("Found # solutions for V1: " + sol1.size() + "\n");

            Pack pack2 = new PackV2(n, maxW, maxH);
            List<int[][]> sol2 = pack2.pack();
            System.out.println("Found # solutions for V2: " + sol2.size() + "\n");
            for(int[][] sol : sol2) {
                pack2.printSolution(sol);
            }

            //Do the same for pack 3
            Pack pack3 = new PackV3(n, maxW, maxH);
            List<int[][]> sol3 = pack3.pack();
            System.out.println("Found # solutions for V3: " + sol3.size() + "\n");
            for(int[][] sol : sol3) {
                pack3.printSolution(sol);
            }


        } catch (NumberFormatException ignored) {
            System.out.println("Invalid parameters");
        }
    }
}