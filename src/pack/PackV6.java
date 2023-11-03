package pack;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.List;

public class PackV6 extends AbstractPack implements Pack {

    public PackV6(int n, int maxW, int maxH) {
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
        IntVar[][] boxes_invert = new IntVar[2][n];

        IntVar[] sizes = model.intVarArray(n, 1, n);

        for(int i = 0; i < n; i++) {
            boxes[0][i] = model.intVar(1, maxW-(n-i)+1);
            boxes[1][i] = model.intVar(1, maxH-(n-i)+1);

            boxes_invert[0][i] = model.intVar(1, maxW-i);
            boxes_invert[1][i] = model.intVar(1, maxH-i);
        }

        boxes[0][0].le(1+ ((maxW - n) / 2)).post();
        boxes[1][0].le(1+ ((maxH - n) / 2)).post();

        for(int i = 0; i < n; i++) {
            sizes[i].eq(n-i).post();

            boxes_invert[0][i].eq(boxes[0][n-i-1]).post();
            boxes_invert[1][i].eq(boxes[1][n-i-1]).post();
        }

        model.diffN(boxes[0], boxes[1], sizes, sizes, true).post();

        return super.solve(model, boxes_invert);
    }

}