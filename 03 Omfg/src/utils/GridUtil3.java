package utils;

import data.Grid;

public interface GridUtil3 {
	/**
	* Beinhaltet Methoden bezueglich der Drehung
	* von Gittern.
	*/
	
	//GridRightTurns
	void turnRight(Grid grid);
	boolean isGridTurn(Grid grid1, Grid grid2);
	int getGridTurnNumber(Grid grid1, Grid grid2);

}
