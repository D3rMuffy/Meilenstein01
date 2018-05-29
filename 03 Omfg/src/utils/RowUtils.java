package utils;

import java.util.LinkedList;
import java.util.List;

import data.Cell;
import data.Grid;

public class RowUtils implements RowIsoUtil, RowSolvingUtil{

	public boolean isValidRow(Grid grid, Cell anchor) {
		/*
		Entscheidet, ob in der Einheit eine zulässige Belegung vorliegt;
		liefert den Wert true, falls ja, false sonst.
		Die Einheit wird durch anchor eindeutig festgelegt. 
		return false;
		*/
		int[] row = grid.getRowValues(anchor.getrIndex());
		int counter = 0;
		
		for(int i = 0; i < row.length; i++){
			int digit = row[i];
			
			for(int j = 0; j < row.length; j++){
				if(digit == -1){
					//empty cell, nothing violates rule
				}else if(digit != -1){
					if(i == j){
						//cell tries to compare with itself; skip
					}else if(i != j){
						if(digit == row[j]){
							counter++;
						}
					}
				}
			}
		}
		
		if(counter == 0){
			return true;
		}else{
				return false;
			}
	}

	public boolean hasFullHouseRow(Grid grid) {
		/*
		 *  Entscheidet, ob in grid eine FullHouse-Row existiert;
		 *  liefert den Wert true, falls ja, false sonst. 
		 */
		
		int j = 1;
		int stopWhile = 0;
		boolean answer = false;
		
		while(stopWhile != 1 && j < grid.getRowValues(j).length){
			int minusCounter = 0;
		
			for(int i = 0; i < grid.getRowValues(j).length; i++){
				if(grid.getRowValues(j)[i] == -1){
					minusCounter++;
				}
			}
			
			if(minusCounter == 1){
				stopWhile = 1;
				answer = true;
			}
			j++;
		}
		
		return answer;
	}

	public boolean isFullHouseRow(Grid grid, Cell anchor) {
		/*
		 * Entscheidet, ob die Einheit eine FullHouse-Einheit ist;
		 * liefert den Wert true, falls ja, false sonst.
		 * Die Einheit wird durch anchor eindeutig festgelegt.
		 */
		
		int minusCounter = 0;
		
		for(int i = 0; i < grid.getRowValues(anchor.getrIndex()).length; i++){
			if(grid.getRowValues(anchor.getrIndex())[i] == -1){
				minusCounter++;
			}
		}
		
		if(minusCounter == 1){
			return true;
		}else{
			return false;
		}

	}

	public boolean isRowWithNakedSingleCell(Grid grid, Cell anchor) {
		/*
		 *  Entscheidet, ob die Einheit mindestens eine
		 *  NakedSingle-Zelle enthält.
		 *  Die Einheit wird durch anchor eindeutig festgelegt.
		 */
		
		boolean answer = false;
		
		if(isFullHouseRow(grid, anchor) == true){
			if(isValidRow(grid, anchor) == true){
				answer = true;
			}
		}else{
			answer = false;
		}
		
		return answer;
	}

	public Cell getRowMinimalNakedSingleCell(Grid grid, Cell anchor) {
		/*
		 * Gibt die minimale NakedSingle-Zelle der Einheit zurück.
		 * Die Einheit wird durch anchor eindeutig festgelegt.
		 * Dabei brauchen Sie nur den Fall zu betrachten,
		 * in dem die Einheit mindestens eine NakedSingle-Zelle enthält.
		 * Andere Fälle müssen Sie mit dieser Methode nicht abfangen.
		 */
		
		int cIndex = 0;
		
		if(isRowWithNakedSingleCell(grid, anchor) == true){
			for(int i = 0; i < grid.getRowValues(anchor.getrIndex()).length; i++){
				if(grid.getRowValues(anchor.getrIndex())[i] == -1){
					cIndex = i+1;
				}
			}
		return grid.getCell(anchor.getrIndex(), cIndex);
		}else{
			return null;
		}
	}

	public boolean isRowWithHiddenSingleCell(Grid grid, Cell anchor) {
		int length = grid.getRowValues(anchor.getrIndex()).length;
		int[][] cIndRInd = new int[9][2];
		int[][] allMin = new int[9][2];
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 2; j++){
				cIndRInd[i][j] = 0;
				allMin[i][j] = 0;
			}
		}
		
		int row = 0;
		int column = 0;
		int number = 1;
		int CIRIR = 0;
		int nullCounter = 0;
		boolean answer = false;
		
		for(number = 1; number < 10; number++){
			nullCounter = 0;
			
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 2; j++){
					cIndRInd[i][j] = 0;
				}
			}
			
			for(row = 0; row < length; row++){
				for(column = 0; column < length; column++){
					int[] rowTemp = grid.getRowValues(row+1);
					
					if(isValidRow(grid, anchor) == true){
						if(rowTemp[column] == number){
							cIndRInd[row][CIRIR] = row+1;
//							System.out.println("rowTemp[col]: "+ rowTemp[column]+" mit ROW/COLUMN "+row+"/"+column+" und number: "+number);
							cIndRInd[row][CIRIR+1] = column+1;
							CIRIR = 0;
						}
					}
					
				}
			}
			
			for(int i = 0; i < length; i++){
				for(int j = 0; j < 2; j++){
					for(int k = 0; k < length; k++){
						for(int l = 0; l < 2; l++){
							
							if(cIndRInd[i][0] == cIndRInd[k][0] && cIndRInd[i][j] == cIndRInd[k][l]){
							
							}else if(cIndRInd[i][0] == cIndRInd[k][0] && cIndRInd[i][1] != cIndRInd[k][1] || cIndRInd[i][0] != cIndRInd[k][0] && cIndRInd[i][1] == cIndRInd[k][1]){
								cIndRInd[i][j] = 0;
								cIndRInd[k][l] = 0;
							}
						}
					}
				}
			}
			
//			System.out.println("für: "+number);
//			for(int i = 0; i < 9; i++){
//				for(int j = 0; j < 2; j++){
//					System.out.print(cIndRInd[i][j]);
//				}
//				System.out.println("");
//			}
			
			for(int i = 0; i < cIndRInd.length; i++){
				if(cIndRInd[i][0] == 0){
					nullCounter++;
				}
			}
			
			if(nullCounter == 1){
				answer = true;
				break;
			}
		}
		return answer;
	}

	public Cell getRowMinimalHiddenSingleCell(Grid grid, Cell anchor) {

		boolean foundAnswer = false;
		int lostRow = 0;
		int lostCol = 0;
		
		int[][] gridTemp = new int[8][8];
		
		for(int number = 1; number < 10; number++){
//			System.out.println("----------------------------");
//			System.out.println("NUMMER " + number);
			lostRow = 0;
			lostCol = 0;
			boolean nextNumber = false;
			int rowValidCounter = 0;
			
			for(int reihe = 0; reihe < 9; reihe++){
//				System.out.println("------------------------------");
//				System.out.println("REIHE " + reihe);
				int[] row = grid.getRowValues(reihe+1);
				
				for(int rInd = 0; rInd < row.length; rInd++){
					
					if(row[rInd] == number){		//Wenn durchsuchte Reihe gleich der gesuchten Number, dann checke Row, Column und Block auf einmalige Existenz eben dieser
						int rowCounter = 0;
						int colCounter = 0;
						int blockCounter = 0;
						
						//Check Column auf Number
						int[] col = grid.getColValues(rInd+1);
						for(int cCheck = 0; cCheck < col.length; cCheck++){
							if(col[cCheck] == number){
//								System.out.println("col[cCheck] " + col[cCheck] + " cCheck " + cCheck);
								colCounter++;
							}
						}
						
						//Check Row auf Number
						for(int rCheck = 0; rCheck < row.length; rCheck++){
							if(row[rCheck] == number){
								rowCounter++;
							}
						}
						
						//Erstelle Array[][] für Block
						int rMin = 0;
						int rMax = 0;
						int cMin = 0;
						int cMax = 0;
						
						if(reihe+1 == 1 || reihe+1 == 2 || reihe+1 == 3){
							rMin = 0;
							rMax = 2;
						}else if(reihe+1 == 4 || reihe+1 == 5 || reihe+1 == 6){
							rMin = 3;
							rMax = 5;
						}else if(reihe+1 == 7 || reihe+1 == 8 || reihe+1 == 9){
							rMin = 6;
							rMax = 8;
						}
						
						if(rInd+1 == 1 || rInd+1 == 2 || rInd+1 == 3){
							cMin = 0;
							cMax = 2;
						}else if(rInd+1 == 4 || rInd+1 == 5 || rInd+1 == 6){
							cMin = 3;
							cMax = 5;
						}else if(rInd+1 == 7 || rInd+1 == 8 || rInd+1 == 9){
							cMin = 6;
							cMax = 8;
						}
						
//						System.out.println("Es wird Block " + rMin + ", " + cMin + " ausgelesen");
						
						int[][] block = new int[3][3];
						int tempCMin = cMin;
						for(int i = 0; i < block.length; i++){
							for(int j = 0; j < block.length; j++){
								int[] a = grid.getRowValues(rMin+1);
								block[i][j] = a[cMin];
//								System.out.print(block[i][j]);
								cMin++;
//								System.out.println("cMin "+cMin+" a[cMin] "+a[cMin]);
							}
//							System.out.println("");
							rMin++;
							cMin = tempCMin;
						}						
						//check Block auf Number
						for(int i = 0; i < block.length; i++){
							for(int j = 0; j < block.length; j++){
								
								if(block[i][j] == number){
									blockCounter++;
								}
							}
						}
//						System.out.println("bc " + blockCounter + " rc " + rowCounter + " cc " + colCounter);
//						System.out.println("number " + number);
//						System.out.println("row " + (reihe+1));
						
						if(blockCounter == 1 && rowCounter == 1 && colCounter == 1){
							rowValidCounter++;
//							System.out.println("rowValidCounter: " + rowValidCounter + " for number " + number);
						}else{
//							nextNumber = true;
						}
					}
					if(nextNumber == true){
						break;
					}
				}
				if(nextNumber == true){
					break;
				}
			}
			if(rowValidCounter == 8){
//				System.out.println("rowValid for number: " + number);
				
				for(int i = 1; i < 10; i++){
					int[] tempRow = grid.getRowValues(i);
					
					for(int j = 0; j < tempRow.length; j++){
						if(tempRow[j] == number){							
							lostRow += i;
							lostCol += j+1;
						}
					}
				}
				if(grid.getCell(45-lostRow, 45-lostCol).getValue() == -1){
//					System.out.println("MAXIMALER BREAK MIT NUMMBER " + number);
					foundAnswer = true;
					break;
				}
			}
		}
		
		if(foundAnswer == true && anchor.getrIndex() == 45-lostRow){
			return grid.getCell(45-lostRow, 45-lostCol);
		}else{
			return null;
		}
	}
	
	public boolean isRowWithHiddenPairCells(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	public Cell[] getRowMinimalHiddenPairCells(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isRowWithNakedPairCells(Grid grid, Cell anchor) {
		/* Entscheidet, ob die Einheit ein NakedPair-Zellpaar enthält;
		 * liefert den Wert true, falls ja, sonst false.
		 * Die Einheit wird durch anchor eindeutig festgelegt. 
		 */
		
		int[] rowTemp = grid.getRowValues(anchor.getrIndex());
		int[] numbers = {1,2,3,4,5,6,7,8,9};
		int nullCounter = 0;
		boolean answer = false;
		
		if(isRowWithNakedSingleCell(grid, anchor) == false && isRowWithHiddenSingleCell(grid, anchor) == false && isValidRow(grid, anchor) == true && isFullHouseRow(grid, anchor) == false){
			
			for(int i = 0; i < numbers.length; i++){
				for(int j = 0; j < rowTemp.length; j++){
					
					if(rowTemp[j] == numbers[i]){
						numbers[i] = 0;
					}
				}
			}
			
			for(int i = 0; i < numbers.length; i++){
				if(numbers[i] != 0){
					nullCounter++;
				}
			}
			
			if(nullCounter == 2){
				answer = true;
			}
		}
		
		return answer;
	}

	public Cell[] getRowMinimalNakedPairCells(Grid grid, Cell anchor) {
		
		for(int i = 1; i < grid.getRowValues(anchor.getrIndex()).length-1; i++){
			
			if(isRowWithNakedPairCells(grid, grid.getCell(i, 1)) == true){
				
				int[] rowTemp = grid.getRowValues(i);
				int cIndex1 = 0;
				int cIndex2 = 0;
				int rIndex = 0;
				
				for(int j = 0; j < rowTemp.length; j++){
					if(rowTemp[j] == -1 && cIndex1 == 0 && cIndex2 == 0){
						cIndex1 = j+1;
						rIndex = i;
					}else if(rowTemp[j] == -1 && cIndex1 != 0 && cIndex2 == 0){
						cIndex2 = j+1;
					}
					
				}
				Cell[] a = {grid.getCell(rIndex, cIndex1), grid.getCell(rIndex, cIndex2)}; 
				return a;
			}
		}
		return null;
	}

	public void applyBlockInternRowPermutation(Grid grid, Cell anchor, int[] image) {
		/*
		 * Wendet die durch den Bildvektor image beschriebene blockinterne Zeilenpermutation auf die Row an,
		 * deren Ankerzelle in anchor abzulesen ist.
		 */
		
		int number = 0;
		
		int[] row1 = grid.getRowValues(anchor.getrIndex());
		int[] row2 = grid.getRowValues(anchor.getrIndex() + 1);
		int[] row3 = grid.getRowValues(anchor.getrIndex() + 2);
		
		for(int i = 0; i < image.length; i++){
			number = image[i];
			
			switch(number){
				case 1:{
					if(i == 0){
						grid.setRowValues(anchor.getrIndex(), row1);
						
					}else if(i == 1){
						grid.setRowValues(anchor.getrIndex(), row2);
						
					}else if(i == 2){
						grid.setRowValues(anchor.getrIndex(), row3);
					}
					break;
				}case 2:{
					if(i == 0){
						grid.setRowValues(anchor.getrIndex()+1, row1);
						
					}else if(i == 1){
						grid.setRowValues(anchor.getrIndex()+1, row2);
						
					}else if(i == 2){
						grid.setRowValues(anchor.getrIndex()+1, row3);
					}		
					break;
				}case 3:{
					if(i == 0){
						grid.setRowValues(anchor.getrIndex()+2, row1);
						
					}else if(i == 1){
						grid.setRowValues(anchor.getrIndex()+2, row2);
						
					}else if(i == 2){
						grid.setRowValues(anchor.getrIndex()+2, row3);
					}
				}
			}
		}
		
	}

	public boolean isBlockInternRowPermutation(Grid grid1, Grid grid2) {
		
		int[][] aRows = new int[27][2];
		int[][] bRows = new int[27][2];
		int rowsInd = 0;
		int simCounter = 0;
		int k = 0;
		int counter = 0;
		int startwert = 0;
		int obereGrenze = 9;
		int i = 0;
		int permCounter = 0;
		int rowAdd = 1;
		int threeRowCounter = 0;
		int emptyCounter = 0;
			
		while (permCounter < 3 && threeRowCounter < 3) {
			
			rowsInd = 0; emptyCounter = 0; permCounter = 0; counter = 0;
			
			for (int m = 0; m < 3; m++) {
				for (int n = 0; n < 9; n++) {
					
					aRows[rowsInd][0] = n + 1;
					aRows[rowsInd][1] = grid1.getRowValues(m + rowAdd)[n];

					bRows[rowsInd][0] = n + 1;
					bRows[rowsInd][1] = grid2.getRowValues(m + rowAdd)[n];
					rowsInd++;
				}
			}
			
			for(int l = 0; l < 9; l++){
				if(aRows[l][1] == -1){
					emptyCounter++;
				}
				if(emptyCounter == 9){
					counter = 4;
				}
			}
			
			while (counter < 3) {
				k = 0;
				while (k < 27) {
					i = startwert;
					while (i < obereGrenze) {
						if (aRows[i][1] == bRows[k][1]) {
							simCounter++;
						}
						i++; k++;
					}

					System.out.println(simCounter);
					
					if (simCounter == 9) {
//						System.out.println(simCounter);
						permCounter++;
						
					}
					simCounter = 0;
				}
				startwert += 9; obereGrenze += 9; counter++;
				System.out.println("");
			} 
			rowAdd += 3; threeRowCounter++;
		}
		
		if(permCounter == 3){
			return true;
		}else{
			return false;
		}
	}

	public int[] getBlockInternRowPermutationImage(Grid grid1, Grid grid2, Cell anchor) {
		int[][] aRows = new int[27][2];
		int[][] bRows = new int[27][2];
		int rowsInd = 0;
		int simCounter = 0;
		int k = 0;
		int counter = 0;
		int startwert = 0;
		int obereGrenze = 9;
		int i = 0;
		int emptyCounter = 0;
		
		int rIndex = 0;
		int[][] perm= new int[3][3];
		int pCol = 0;
		int pRow = 0;
		
		if(anchor.getrIndex() == 1 || anchor.getrIndex() == 2 || anchor.getrIndex() == 3){
			rIndex = 0;
		}else if(anchor.getrIndex() == 4 || anchor.getrIndex() == 5 || anchor.getrIndex() == 6){
			rIndex = 3;
		}else if(anchor.getrIndex() == 7 || anchor.getrIndex() == 8 || anchor.getrIndex() == 9){
			rIndex = 6;
		}
		
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 9; n++) {
				
				aRows[rowsInd][0] = n + 1;
				aRows[rowsInd][1] = grid1.getRowValues(rIndex+1)[n];

				bRows[rowsInd][0] = n + 1;
				bRows[rowsInd][1] = grid2.getRowValues(rIndex+1)[n];
				rowsInd++;
			}
			rIndex++;
		}
		
			for(int l = 0; l < 9; l++){
				if(aRows[l][1] == -1){
					emptyCounter++;
				}
				if(emptyCounter == 9){
					counter = 4;
				}
			}
			
			while (counter < 3) {
				k = 0;
				while (k < 27) {
					i = startwert;
					while (i < obereGrenze) {
						if (aRows[i][1] == bRows[k][1]) {
							simCounter++;
						}
						i++; k++;
					}
					
					if(pCol == 3){
						pRow++;
						pCol = 0;
					}
					perm[pRow][pCol] = simCounter;
					pCol++;
					simCounter = 0;
				}
				startwert += 9; obereGrenze += 9; counter++;
			} 
		
		int[] image = new int[3];
		int imageCounter = 0;
		for(int p = 0; p < image.length; p++){
			for(int q = 0; q < image.length; q++){
				if(perm[p][q] == 9){
					image[imageCounter] = q+1;
					imageCounter++;
				}
			}
		}
		emptyCounter = 0;
		for(int s = 0; s < image.length; s++){
			if(image[s] == 0){
				emptyCounter++;
			}
		}
		
		if(emptyCounter > 0){
			return null;
		}else{
			return image;
		}
	}

	public void applyRowValuePermutation(Grid grid, Cell anchor, int[] image) {
		
		int[] temp = new int [9];
		int[] row = grid.getRowValues(anchor.getrIndex());
		int number = 1;
		
		for(int i = 0; i <  image.length; i++){
			for(int j = 0; j < row.length; j++){
				if(row[j] == number){
					temp[j] = image[i];
				}
			}
			number++;
		}
		grid.setRowValues(anchor.getrIndex(), temp);
	}

	public int[] getRowValuePermutationImage(Grid grid1, Grid grid2, Cell anchor) {
		int number = 1;
		int[] image = new int[9];
		int noValuePermutationCounter = 0;
		
		while(number < 10){
			for(int i = 0; i < grid1.getRowValues(anchor.getrIndex()).length; i++){
				if(grid1.getRowValues(anchor.getrIndex())[i] == number){
					image[number-1] = grid2.getRowValues(anchor.getrIndex())[i];
				}
			}
			number++;
		}
		
		number = 1;
		for(int i = 0; i < image.length; i++){
			if(image[i] == number){
				noValuePermutationCounter++;
			}
			number++;
		}
		
		if(noValuePermutationCounter == 9){
			return null;
		}else{
			return image;
		}
	}

	public boolean isRowValuePermutation(Grid grid1, Grid grid2, Cell anchor) {
		boolean answer = false;
		if(getRowValuePermutationImage(grid1, grid2, grid1.getCell(anchor.getrIndex(), anchor.getrIndex())) != null){
			answer = true;
		}else if(getRowValuePermutationImage(grid1, grid2, grid1.getCell(anchor.getrIndex(), anchor.getrIndex())) == null){
			answer = false;
		}
		return answer;
	}
	
	public List<Cell> getRowWhiteSpaces(Grid grid, Cell anchor){
		
		List<Cell> a = new LinkedList <Cell>();
		int[] row = grid.getRowValues(anchor.getrIndex());
		int k = 0;
		
		for(int i = 0; i < row.length; i++){
			if(row[i] == -1){
				a.add(k, grid.getCell(anchor.getrIndex(), i+1));
				k++;
			}
		}		
		return a;
	}

	public List<Grid> solveRowBased(Grid grid){
		return null;
	}

}
