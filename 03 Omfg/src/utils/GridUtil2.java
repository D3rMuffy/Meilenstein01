package utils;

import data.Cell;
import data.Grid;

public interface GridUtil2 {
	/**
	* Beinhaltet Methoden bezueglich der Spiegelung
	* von Gittern.
	*/
	
	//GridReflection
	void reflect(Grid grid, Cell anchor);
	boolean isReflection(Grid grid1, Grid grid2);
	Cell getReflectionAnchor(Grid grid1, Grid grid2);

}
