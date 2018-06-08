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
		if(getGridTurnNumber(grid1, grid2) != 99){
			return true;
		}else{
			return false;
		}
	}
	
	public static int[] insideOut(int[] a){		
		int[] aTemp = new int[a.length];
		
		int j = a.length-1;
		for(int i = 0; i < a.length; i++){
			aTemp[j] = a[i];
			j--;
		}		
		return aTemp;
	}
	
	public static boolean checkCol(Grid a, Grid b, int insideOut){
		
		List<List> allRow1 = new LinkedList<List>();
		List<List> allCol2 = new LinkedList<List>();
		
		for(int gridPointer = 0; gridPointer < a.getRowValues(1).length; gridPointer++){
			List<Integer> row1 = new LinkedList<Integer>();
			for(int rowPointer = 0; rowPointer < a.getRowValues(1).length; rowPointer++){
				
				
				row1.add(rowPointer, a.getRowValues(gridPointer+1)[rowPointer]);
			}
			allRow1.add(gridPointer, row1);
		}

		if(insideOut == 1){ //1 TURN
			int allCol2Index = 0;
			
			for(int gridPointer = 9; gridPointer > 0; gridPointer--){
				List<Integer> col2 = new LinkedList<Integer>();
				int col2Index = 0;
				for(int colPointer = 0; colPointer < b.getColValues(1).length; colPointer++){
					
					col2.add(col2Index, b.getColValues(gridPointer)[colPointer]);
					col2Index++;
				}
				allCol2.add(allCol2Index, col2);
				allCol2Index++;
			}
			
		}else if(insideOut == 0){ //3 TURN
			int allCol2Index = 0;
			
			for(int gridPointer = 0; gridPointer < b.getColValues(1).length; gridPointer++){
				List<Integer> col2 = new LinkedList<Integer>();
				int col2Index = 0;
				for(int colPointer = 9; colPointer > 0; colPointer--){
					
					col2.add(col2Index, b.getColValues(gridPointer+1)[colPointer-1]);
					col2Index++;
				}
				allCol2.add(allCol2Index, col2);
				allCol2Index++;
			}
		}
		
		//COMPARISON
		int compCounter = 0;
		
		for(int i = 0; i < allRow1.size(); i++){
			for(int j = 0; j < allRow1.get(i).size(); j++){
				
				if(allRow1.get(i).get(j) == allCol2.get(i).get(j)){
					compCounter++;
				}
			}
		}
		
//		System.out.println(compCounter);
//		System.out.println(allRow1.toString());
//		System.out.println(allCol2.toString());
		
		if(compCounter == 81){
			return true;
		}else{
			return false;
		}
		
	}

	public static boolean checkRow(Grid a, Grid b, int insideOut){
		
		List<List> allRow1 = new LinkedList<List>();
		List<List> allRow2 = new LinkedList<List>();
		
		for(int gridPointer = 0; gridPointer < a.getRowValues(1).length; gridPointer++){
			List<Integer> row1 = new LinkedList<Integer>();
			for(int rowPointer = 0; rowPointer < a.getRowValues(1).length; rowPointer++){
				
				row1.add(rowPointer, a.getRowValues(gridPointer+1)[rowPointer]);
			}
			allRow1.add(gridPointer, row1);
		}
		
		if(insideOut == 1){ //2 TURN
			int allRow2Counter = 0;
			
			for(int gridPointer = 9; gridPointer > 0; gridPointer--){
				List<Integer> row2 = new LinkedList<Integer>();
				int row2Counter = 0;
				for(int rowPointer = 9; rowPointer > 0; rowPointer--){
					
					row2.add(row2Counter, b.getRowValues(gridPointer)[rowPointer-1]);
					row2Counter++;
				}
				allRow2.add(allRow2Counter, row2);
				allRow2Counter++;
			}
			
		}else if(insideOut == 0){ //0 bzw 4 TURN
			
			int allRow2Index = 0;
			
			for(int gridPointer = 0; gridPointer < a.getRowValues(1).length; gridPointer++){
				int row2Index = 0;
				List<Integer> row2 = new LinkedList<Integer>();
				for(int rowPointer = 0; rowPointer < b.getRowValues(1).length; rowPointer++){
					
					row2.add(row2Index, b.getRowValues(gridPointer+1)[rowPointer]);
					row2Index++;
				}
				allRow2.add(allRow2Index, row2);
				allRow2Index++;
			}
		}
		
		//COMPARISON
				int compCounter = 0;
				
				for(int i = 0; i < allRow1.size(); i++){
					for(int j = 0; j < allRow1.get(i).size(); j++){
						
						if(allRow1.get(i).get(j) == allRow2.get(i).get(j)){
							compCounter++;
						}
					}
				}
			
//				System.out.println(compCounter);
//				System.out.println(allRow1.toString());
//				System.out.println(allRow2.toString());
								
				if(compCounter == 81){
					return true;
				}else{
					return false;
				}
	}
	
	public int getGridTurnNumber(Grid grid1, Grid grid2) {
		
		int[] original = grid1.getRowValues(1);
		int[] oneTurn = grid2.getColValues(9);
		int[] twoTurn = insideOut(grid2.getRowValues(9));
		int[] threeTurn = insideOut(grid2.getColValues(1));
		int[] fourTurn = grid2.getRowValues(1);
		
		int oneT = 0;
		int twoT = 0;
		int thrT = 0;
		int fouT = 0;
		
		int[] turnNumber = {1,2,3,0};
		int answer = 99;
		
		for(int compare = 0; compare < oneTurn.length; compare++){
			if(original[compare] == oneTurn[compare]){
				oneT++;
			}
			if(original[compare] == twoTurn[compare]){
				twoT++;
			}
			if(original[compare] == threeTurn[compare]){
				thrT++;
			}
			if(original[compare] == fourTurn[compare]){
				fouT++;
			}
		}
		
//		System.out.println(oneT + " " + twoT + " " + thrT + " " + fouT);
		
		if(oneT==9){
			if(checkCol(grid1, grid2, 1) == true){
				answer = turnNumber[0];
			}
		}else if(twoT==9){
			if(checkRow(grid1, grid2, 1) == true){
				answer = turnNumber[1];
			}
		}else if(thrT==9){
			if(checkCol(grid1, grid2, 0) == true){
				answer = turnNumber[2];
			}
		}else if(fouT==9){
			if(checkRow(grid1, grid2, 0) == true){
				answer = turnNumber[3];
			}			
		}
		
		return answer;
	}
}
