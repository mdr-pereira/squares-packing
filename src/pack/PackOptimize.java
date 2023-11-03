package pack;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.util.List;

public class PackOptimize extends AbstractPack implements Pack{

    public PackOptimize(int n) {
        super(n, 0, 0);
    }

    private int sumN(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    @Override
    public List<int[][]> pack() {
        Model model = new Model("Scheduling-based Packing Problem");

        //Create the variables for the boxes' origin coordinates.
        IntVar[][] boxes = new IntVar[2][n];

        IntVar[] sizes = model.intVarArray(n, 1, n);

        int maxWH = sumN(n);

        IntVar rectW = model.intVar(n, maxWH);
        IntVar rectH = model.intVar(n, maxWH);

        IntVar rectArea = model.intVar(n*n, maxWH*n);

        rectArea.eq(rectW.mul(rectH)).post();

        for(int i = 0; i < n; i++) {
            boxes[0][i] = model.intVar(1, maxWH);
            boxes[1][i] = model.intVar(1, maxWH);
        }

        boxes[0][0].le(1+ ((maxW - n) / 2)).post();
        boxes[1][0].le(1+ ((maxH - n) / 2)).post();

        for(int i = 0; i < n; i++) {
            sizes[i].eq(n-i).post();

            model.arithm(model.intOffsetView(boxes[0][i], n-i-1), "<=", rectW).post();
            model.arithm(model.intOffsetView(boxes[1][i], n-i-1), "<=", rectH).post();
        }

        model.diffN(boxes[0], boxes[1], sizes, sizes, true).post();
        model.setObjective(Model.MINIMIZE, rectArea);

        return super.solve(model, boxes, rectW, rectH, true);
    }
}
