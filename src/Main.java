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

            //Do the same for pack 3
            Pack pack3 = new PackV3(n, maxW, maxH);
            List<int[][]> sol3 = pack3.pack();
            System.out.println("Found # solutions for V3: " + sol3.size() + "\n");

            //Do the same for pack 5
            Pack pack5 = new PackV4(n, maxW, maxH);
            List<int[][]> sol5 = pack5.pack();
            System.out.println("Found # solutions for V5: " + sol5.size() + "\n");

            //Do the same for pack 6
            Pack pack6 = new PackV5(n, maxW, maxH);
            List<int[][]> sol6 = pack6.pack();
            System.out.println("Found # solutions for V6: " + sol6.size() + "\n");

            //Do the same for pack 7
            Pack pack7 = new PackV6(n, maxW, maxH);
            List<int[][]> sol7 = pack7.pack();
            System.out.println("Found # solutions for V7: " + sol7.size() + "\n");

            //Do the same for pack optimize
            Pack packOptimize = new PackOptimize(n);
            List<int[][]> solOptimize = packOptimize.pack();
            System.out.println("Found # solutions for Optimize: " + solOptimize.size() + "\n");
            for(int[][] sol : solOptimize) {
                packOptimize.printSolution(sol);
            }

        } catch (NumberFormatException ignored) {
            System.out.println("Invalid parameters");
        }
    }
}