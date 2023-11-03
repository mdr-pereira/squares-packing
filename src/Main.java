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

            Pack pack = new PackV1(n, maxW, maxH);
            List<int[][]> sol = pack.pack();
            System.out.println("Found # solutions for V1: " + sol.size() + "\n");


            pack = new PackV2(n, maxW, maxH);
            sol = pack.pack();
            System.out.println("Found # solutions for V2: " + sol.size() + "\n");


            pack = new PackV3(n, maxW, maxH);
            sol = pack.pack();
            System.out.println("Found # solutions for V3: " + sol.size() + "\n");


            pack = new PackV4(n, maxW, maxH);
            sol = pack.pack();
            System.out.println("Found # solutions for V4: " + sol.size() + "\n");


            pack = new PackV5(n, maxW, maxH);
            sol = pack.pack();
            System.out.println("Found # solutions for V5: " + sol.size() + "\n");


            //Do the same for pack optimize
            Pack packOptimize = new PackOptimize(n);
            packOptimize.pack();

        } catch (NumberFormatException ignored) {
            System.out.println("Invalid parameters");
        }
    }
}