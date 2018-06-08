package utils;

import java.util.LinkedList;
import java.util.List;

import data.Grid;

public class GridUtils3 implements GridUtil3{

	public void turnRight(Grid grid) {
		
		
		//RAUSSCHREIBEN DER ROWS IN EINE LINKED LIST
		List<List> allRows= new LinkedList<List>();
		int[] rowTemp;
		
		for(int rowIndex = 0; rowIndex < grid.getRowValues(1).length; rowIndex++){
			rowTemp = grid.getRowValues(rowIndex+1);
			List<Integer> singleRow= new LinkedList<Integer>();
			
			for(int i = 0; i < rowTemp.length; i++){
				singleRow.add(i, rowTemp[i]);
			}
			allRows.add(rowIndex, singleRow);
		}
		
		
		//RECHTSGEDREHT NEU EINFÜGEN
		int gridColIndex = 9;
		
		for(int allRowsIndex = 0; allRowsIndex < allRows.size(); allRowsIndex++){
			int[] colTemp = new int[allRows.get(allRowsIndex).size()];
			
			for(int rowTempIndex = 0; rowTempIndex < allRows.get(allRowsIndex).size(); rowTempIndex++){
				colTemp[rowTempIndex] = (int) allRows.get(allRowsIndex).get(rowTempIndex);
			}
			grid.setColValues(gridColIndex, colTemp);
			gridColIndex--;
		}
		
	}

	public boolean isGridTurn(Grid grid1, Grid grid2) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getGridTurnNumber(Grid grid1, Grid grid2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
