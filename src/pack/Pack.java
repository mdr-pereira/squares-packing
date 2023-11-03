package pack;

import java.util.List;

public interface Pack {

    /**
     * Solves the packing problem.
     *
     * @return A list of solutions, each solution being a list of boxes, each box being a list of coordinates.
     */
    public List<int[][]> pack();

    void printSolution(int[][] solution);
}
