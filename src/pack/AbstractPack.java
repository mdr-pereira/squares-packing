package pack;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;
import pack.Pack;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPack implements Pack {

    protected final int n;

    protected final int maxW;

    protected final int maxH;

    public AbstractPack(int n, int maxW, int maxH) {
        this.n = n;
        this.maxW = maxW;
        this.maxH = maxH;
    }

    public abstract List<int[][]> pack();

    protected List<int[][]> solve(Model model, IntVar[][] boxes) {
        Solver solver = model.getSolver();

        List<int[][]> solutions = new ArrayList<>();
        while (solver.solve()) {
            int[][] sol = new int[2][n];
            for(int i = 0; i < n; i++) {
                sol[0][i] = boxes[0][i].getValue();
                sol[1][i] = boxes[1][i].getValue();
            }
            solutions.add(sol);
        }

        //Print time needed to solve the problem
        System.out.println("Time needed to solve the problem: " + solver.getTimeCount() + "s");

        return solutions;
    }

    public void printSolution(int[][] solution) {
        //Print solution as { {x1, x2, ..., xn}, {y1, y2, ..., yn} }
        System.out.print("{\t{");
        for(int i = 0; i < n; i++) {
            System.out.print(solution[0][i]);
            if(i < n-1) {
                System.out.print(", ");
            }
        }
        System.out.print("}\n\t{");
        for(int i = 0; i < n; i++) {
            System.out.print(solution[1][i]);
            if(i < n-1) {
                System.out.print(", ");
            }
        }
        System.out.print("}\t}\n");
    }
}
