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

    private int sumSqrN(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += i*i;
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

        IntVar rectArea = model.intVar(sumSqrN(n), (maxWH - sumN((int) (Math.ceil(n / 2.0) - 1))) * n);

        model.times(rectW, rectH, rectArea).post();

        for(int i = 0; i < n; i++) {
            boxes[0][i] = model.intVar(1, maxWH);
            boxes[1][i] = model.intVar(1, maxWH);
        }

        for(int i = 0; i < n; i++) {
            sizes[i].eq(n-i).post();

            model.arithm(model.intOffsetView(boxes[0][i], n-i-1), "<=", rectW).post();
            model.arithm(model.intOffsetView(boxes[1][i], n-i-1), "<=", rectH).post();
        }

        model.diffN(boxes[0], boxes[1], sizes, sizes, true).post();
        model.setObjective(Model.MINIMIZE, rectArea);

        super.solve(model, boxes, rectW, rectH, rectArea);

        return null;
    }
}
