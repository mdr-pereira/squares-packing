# pr-project-1

---
## Ideas
This section will encompass possible ideas that we have for improving the algorithm.

### General Improvements
    Implement our solution as a parallel to scheduling problems, where task height and width are equal to the square's size.
        !! The speedup this gives us is significant

### Global Constraints
    Implement the non-overlap checks as diffN. 
        !! The speedup is **very** significant.
    
### Symmetry Breaking
    This has to really be studied, we have not implemented anything at all #TODO

### Redundancy

### Heuristic

    Regarding heuristics there's very little which may be used. But, we may want
    to indicate which variables should be allocated first.

    Under suppositions made by us, it should be the largest boxes' variables first.

    This should be able to be done through the following snippet

    solver.setSearch(inputOrderUBSearch(boxX));
    solver.setSearch(inputOrderUBSearch(boxY));

    Nvm, this sets the search tree to start from right-left instead of bottom-up. To get the effect that we desire we have to redefine these arrays so that the size
    of the box is in actuality n-ix.

---
## Versions

### V.1

    This model was built in a index-depedent way. We firstly created two variable
    arrays to store the x- and y-axis coordinates of the boxes' origins, respectively.
    
    These two arrays have a lower bound set to 0 and an upper bound to n-1.

    The boxes' size is stored implicitly as ix+1 where ix is their index within the
    previously referred arrays.

    In terms of global constraints we solely use bound constraints regarding the
    boxes' origin's coordinates in relation to their size.

    ---

    The minimizer for this version simply re-uses the above-described code, and states
    variables for the encompassing rectangle's size (x- and y-axis), which are then
    solved for with a minimize call.

    
