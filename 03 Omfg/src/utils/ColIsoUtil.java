package utils;

public interface ColIsoUtil {
	/**
	* Beinhaltet hilfreiche Methoden,
	* um mit zueinander isomorphen Sudokus zu arbeiten.
	* Methoden beziehen sich auf die Einheit ’Col’ (also Spalte).
	*/
	
	//BlockInternCol
	void applyBlockInternColPermutation(Grid grid, Cell anchor, int[] image);
	boolean isBlockInternColPermutation(Grid grid1, Grid grid2);
	int[] getBlockInternColPermutationImage(Grid grid1, Grid grid2, Cell anchor);
	
	//ValuePermutation
	void applyColValuePermutation(Grid grid, Cell anchor, int[] image);
	int[] getColValuePermutationImage(Grid grid1, Grid grid2, Cell anchor);
	// Achtung: Diese Methode ist neu! (2018 05 03)
	boolean isColValuePermutation(Grid grid1, Grid grid2, Cell anchor);
}
