package pack;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.List;

public class PackV2 extends AbstractPack implements Pack {

    public PackV2(int n, int maxW, int maxH) {
        super(n, maxW, maxH);
    }

    /**
     * This version of the solver will face the problem set as a scheduling problem, using thus the Cumulative constraints,
     * as well as other functions and constraints which present a far more efficient way of solving the problem.
     *
     * @return A list of solutions, each solution being a list of boxes, each box being a list of coordinates.
     */
    @Override
    public List<int[][]> pack() {
        Model model = new Model("Scheduling-based Packing Problem");

        //Create the variables for the boxes' origin coordinates.
        IntVar[][] boxes = new IntVar[2][n];
        IntVar[] sizes = model.intVarArray(n, 1, n);

        boxes[0] = model.intVarArray(n, 1, maxW);
        boxes[1] = model.intVarArray(n, 1, maxH);

        for(int i = 0; i < n; i++) {
            sizes[i].eq(i+1).post();
            model.arithm(model.intOffsetView(boxes[0][i], i), "<=", maxW).post();
            model.arithm(model.intOffsetView(boxes[1][i], i), "<=", maxH).post();
        }

        model.diffN(boxes[0], boxes[1], sizes, sizes, true).post();

        return super.solve(model, boxes, false);
    }
}
