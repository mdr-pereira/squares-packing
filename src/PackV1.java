import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.*;

import java.util.ArrayList;
import java.util.List;

public class PackV1 implements Pack {
private final int n;
private final int maxW;
private final int maxH;

    public PackV1(int n, int maxW, int maxH) {
        this.n = n;
        this.maxW = maxW;
        this.maxH = maxH;
    }

    public List<int[][]> solve() {
        Model model = new Model("Simple Packing Problem");
        /*
        For this simple solver we'll exhaustively search the search space.

        We want to improve the solution efficacy, so we'll forgo the modelling of the search space. This means that
        we will not be working in the "rectangle" representation of the problem.

        We will rather work with the boxes' origin coordinates, and their consequent boundaries on each axis; additionally
        restraints will be placed regarding the max and min width and height (coming from the rectangle).
        */

        //The upper bound is set to max""-1 because of indexing.
        IntVar[] boxX = model.intVarArray(n, 0, maxW - 1);
        IntVar[] boxY = model.intVarArray(n,0, maxH - 1);

        for(int i = 0; i < n; i++) {
            model.arithm(model.intOffsetView(boxX[i], i), "<=", maxW-1).post();
            model.arithm(model.intOffsetView(boxY[i], i), "<=", maxH-1).post();
        }

        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                 model.or(
                     model.or(
                             model.arithm(model.intOffsetView(boxX[i], i), "<", boxX[j]),
                             model.arithm(model.intOffsetView(boxX[j], j), "<", boxX[i])
                     ),
                     model.or(
                             model.arithm(model.intOffsetView(boxY[i], i), "<", boxY[j]),
                             model.arithm(model.intOffsetView(boxY[j], j), "<", boxY[i])
                     )
                 ).post();
            }
        }

        //Create a solver and return all solutions found.
        Solver solver = model.getSolver();

        //Return all solutions found as a List of an array of arrays of integers.
        List<int[][]> solutions = new ArrayList<>();
        while (solver.solve()) {
            int[][] sol = new int[n][2];
            for(int i = 0; i < n; i++) {
                sol[i][0] = boxX[i].getValue();
                sol[i][1] = boxY[i].getValue();
            }
            solutions.add(sol);
        }

        return solutions;
    }

}
