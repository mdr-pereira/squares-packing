// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

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

            pack.solve();
        } catch (NumberFormatException e) {
            return;
        }


    }
}