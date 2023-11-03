package pack;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.List;

public class PackV1 extends AbstractPack implements Pack {

    public PackV1(int n, int maxW, int maxH) {
        super(n, maxW, maxH);
    }

    /**
     * For this simple solver we'll exhaustively search the search space.
     * <p>
     * We want to improve the solution efficacy, so we'll forgo the modelling of the search space. This means that
     * we will not be working in the "rectangle" representation of the problem.
     * <p>
     * We will rather work with the boxes' origin coordinates, and their consequent boundaries on each axis; additionally
     * restraints will be placed regarding the max and min width and height (coming from the rectangle).
     *
     * @return A list of solutions, each solution being a list of boxes, each box being a list of coordinates.
     */
    public List<int[][]> pack() {
        Model model = new Model("Simple Packing Problem");

        IntVar[][] boxes = new IntVar[2][n];
        boxes[0] = model.intVarArray(n, 1, maxW);
        boxes[1] = model.intVarArray(n, 1, maxH);

        for(int i = 0; i < n; i++) {
            model.arithm(model.intOffsetView(boxes[0][i], i), "<=", maxW).post();
            model.arithm(model.intOffsetView(boxes[1][i], i), "<=", maxH).post();
        }

        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                 model.or(
                     model.arithm(model.intOffsetView(boxes[0][i], i), "<", boxes[0][j]),
                     model.arithm(model.intOffsetView(boxes[0][j], j), "<", boxes[0][i]),
                     model.arithm(model.intOffsetView(boxes[1][i], i), "<", boxes[1][j]),
                     model.arithm(model.intOffsetView(boxes[1][j], j), "<", boxes[1][i])
                 ).post();
            }
        }

        return super.solve(model, boxes);
    }

}
