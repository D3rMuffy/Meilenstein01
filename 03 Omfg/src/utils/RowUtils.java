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
		
		if(getRowMinimalNakedSingleCell(grid, anchor) != null){
			answer = true;
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
		
		updateAllCandidates(grid);
		
		for(int cIndex = 0; cIndex < 9; cIndex++){
			if(allCandidateArray[anchor.getrIndex()-1][cIndex].size() == 1){
				return grid.getCell(anchor.getrIndex(), cIndex+1);
			}
		}
		
		return  null;
		
		
		
		
		
		
		
		
		
//		int cIndex = 0;
//		
//		if(isRowWithNakedSingleCell(grid, anchor) == true){
//			for(int i = 0; i < grid.getRowValues(anchor.getrIndex()).length; i++){
//				if(grid.getRowValues(anchor.getrIndex())[i] == -1){
//					cIndex = i+1;
//				}
//			}
//		return grid.getCell(anchor.getrIndex(), cIndex);
//		}else{
//			return null;
//		}
	}

	public boolean isRowWithHiddenSingleCell(Grid grid, Cell anchor) {
		
		if(getRowMinimalHiddenSingleCell(grid, anchor) != null){
			return true;
		}else{
			return false;
		}
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
		if(getRowMinimalHiddenPairCells(grid, anchor) != null){
			return true;
		}else{
			return false;
		}
	}

	public Cell[] getRowMinimalHiddenPairCells(Grid grid, Cell anchor) {
	
		updateAllCandidates(grid);
		
		int[] numbers = {0,0,0,0,0,0,0,0,0};
		Cell[] cellsCorrNum = new Cell[9];
		Cell[] answer = new Cell[2];
		
		for(int rIndex = 0; rIndex < allCandidateArray.length; rIndex++){
			for(int cIndex = 0; cIndex < allCandidateArray.length; cIndex++){
				
				int candInd = 0;
				while(candInd < allCandidateArray[rIndex][cIndex].size()){
					
					int number = (int) allCandidateArray[rIndex][cIndex].get(candInd);
					
					if(number < 10 && number > 0){
						numbers[number-1]++;
						if(numbers[number-1] == 1){
							cellsCorrNum[number-1] = grid.getCell(rIndex+1, cIndex+1);
						}else if(numbers[number-1] > 1){
							cellsCorrNum[number-1] = null;
						}
					}
					candInd++;
				}
			}
		}
		
		List<Cell> cells = new LinkedList<Cell>();
		for(int i = 0; i < cellsCorrNum.length; i++){
			if(cellsCorrNum[i] != null && cellsCorrNum[i].getrIndex() == anchor.getrIndex()){
				cells.add(cellsCorrNum[i]);
			}
		}
		
		if(cells.size() > 2){
			int cellsInd = 0;
			int smaller = 0;
			
			while(cellsInd < cells.size()-1 && smaller != cells.size()-1){
				if(cells.get(cellsInd).getrIndex() > cells.get(cellsInd+1).getrIndex()){
					Cell temp = cells.get(cellsInd);
					cells.set(cellsInd, cells.get(cellsInd+1));
					cells.set(cellsInd+1, temp);
					
				}else if(cells.get(cellsInd).getcIndex() < cells.get(cellsInd+1).getcIndex()){
					smaller++;
				}
				cellsInd++;
			}
			
			for(int i = 0; i < cells.size()-1; i++){
				if(cells.get(i).getrIndex() == cells.get(i+1).getrIndex()){
					answer[0] = cells.get(i);
					answer[1] = cells.get(i+1);
				}
			}
			
		}else if(cells.size() == 2){
			if(cells.get(0).getrIndex() == cells.get(1).getrIndex()){
				if(cells.get(0).getcIndex() > cells.get(1).getcIndex()){
					Cell temp = cells.get(0);
					cells.set(0, cells.get(1));
					cells.set(1, temp);
				}
			}
			answer[0] = cells.get(0);
			answer[1] = cells.get(1);
		}
		return answer;
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
						System.out.println(rowTemp[j]);
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
		
		updateAllCandidates(grid);
		
		Cell[] cells = new Cell[2];
		List<Cell> candidatesTwo = new LinkedList<Cell>();
		
		for(int cInd = 0; cInd < allCandidateArray.length; cInd++){	
			//NICHT NUMMER ERHÖHEN SONDERN DA WO CANDSIZE == 2 SPEICHERN UND CAND VERGLEICHEN!
			if(allCandidateArray[anchor.getrIndex()-1][cInd].size() == 2 && (int) allCandidateArray[anchor.getrIndex()-1][cInd].get(0) != 99){
//				System.out.println(anchor.getrIndex() + ", " + (cInd+1));
				candidatesTwo.add(grid.getCell(anchor.getrIndex(), cInd+1));
			}				
		}
		
		for(int cTwoInd = 0; cTwoInd < candidatesTwo.size()-1; cTwoInd++){
			int cand1 = 0;
			int cand2 = 1;
			int cellsInd = 0;
			
			if(allCandidateArray[candidatesTwo.get(cTwoInd).getrIndex()-1][candidatesTwo.get(cTwoInd).getcIndex()-1].get(cand1) == allCandidateArray[candidatesTwo.get(cTwoInd+1).getrIndex()-1][candidatesTwo.get(cTwoInd+1).getcIndex()-1].get(cand1)){
				if(allCandidateArray[candidatesTwo.get(cTwoInd).getrIndex()-1][candidatesTwo.get(cTwoInd).getcIndex()-1].get(cand2) == allCandidateArray[candidatesTwo.get(cTwoInd+1).getrIndex()-1][candidatesTwo.get(cTwoInd+1).getcIndex()-1].get(cand2)){
					
					cells[cellsInd] = grid.getCell(candidatesTwo.get(cTwoInd).getrIndex(), candidatesTwo.get(cTwoInd).getcIndex());
					cellsInd++;
					cells[cellsInd] = grid.getCell(candidatesTwo.get(cTwoInd+1).getrIndex(), candidatesTwo.get(cTwoInd+1).getcIndex());
				}
			}
		}
		
//		auslesen(cells);
		
		if(cells[0] != null && cells[1] != null){
			return cells;
		}else{
			return null;
		}
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
		
		for(int i = 0; i < temp.length; i++){
			if(temp[i] == 0){
				temp[i] = -1;
			}
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
	
	LinkedList[][] allCandidateArray = new LinkedList[9][9];
	
	public List<Grid> solveRowBased(Grid grid){
		
		updateAllCandidates(grid);
		
		if(step1(grid) == true){
			System.out.println("step1 weiter");
			if(step2(grid) == false){
				System.out.println("step2 weiter");
				
				if(step3(grid) == true){
					System.err.println("STEP3 PASSIERT - BACK TO STEP 1");
					solveRowBased(grid);
				}else if(step3(grid) == false){
					System.out.println("step3 weiter");
		
					if(step4(grid) == true){
						System.err.println("STEP4 PASSIERT - BACK TO STEP 1");
						solveRowBased(grid);
					}else if(step4(grid) == false){
						System.out.println("step4 weiter");
						step5(grid);
					}
				}
			}else if(step2(grid) == true){
				System.err.println("STEP2 PASSIERT - BACK TO STEP 1");
				solveRowBased(grid);
			}
		}else if(step1(grid) == false){
				System.err.println("STEP1 STOP");
		}
		
		for(int i = 0; i < allCandidateArray.length; i++){
			for(int j = 0; j < allCandidateArray.length; j++){
				System.out.println(allCandidateArray[i][j]);
			}
			System.out.println("");
		}
		
		grid.print();
		
		return null;
	}
	
	
	
	
	
	
	public void updateAllCandidates(Grid grid){
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
			for(int cIndex = 1; cIndex < 10; cIndex++){
								
				if(grid.getValue(rIndex, cIndex) == -1){
					List<Integer> candidatesToFill = new LinkedList<Integer>();
					int[] candidates = fillCandidateArray();
										
					candidates = checkRow(cIndex, candidates, grid, grid.getCell(rIndex, cIndex));
					candidates = checkCol(cIndex, candidates, grid);
					candidates = checkBlock(cIndex, candidates, grid, grid.getCell(rIndex, cIndex));					
					
					int ctfIndex = 0;
					while(ctfIndex < candidates.length){
						candidatesToFill.add(ctfIndex, candidates[ctfIndex]);
						ctfIndex++;
					}
					
					//DELETES ZEROS FROM CANDIDATELIST FOR EACH CELL
					ctfIndex = 0;
					while(ctfIndex < candidates.length){
						int zero = 0;
						candidatesToFill.remove((Integer) zero);
						ctfIndex++;
					}
					
					allCandidateArray[rIndex-1][cIndex-1] = (LinkedList) candidatesToFill;
				}else{
					List<Integer> fullCell = new LinkedList<Integer>();
					fullCell.add(99);
					fullCell.add(99);
					allCandidateArray[rIndex-1][cIndex-1] = (LinkedList) fullCell;
				}
			}	
		}	
		
//		for(int i = 0; i < allCandidateArray.length; i++){
//		for(int j = 0; j < allCandidateArray.length; j++){
//			System.out.println(allCandidateArray[i][j]);
//		}
//		System.out.println("");
//	}
		
	}
	
	public int[] checkRow(int cellIndex, int[] candidateArray, Grid grid, Cell anchor){
		int[] row = grid.getRowValues(anchor.getrIndex());
		
		for(int candidateArrayPointer = 0; candidateArrayPointer < candidateArray.length; candidateArrayPointer++){
			for(int rowPointer = 0; rowPointer < row.length; rowPointer++){
				
				if(candidateArray[candidateArrayPointer] == row[rowPointer]){
					candidateArray[candidateArrayPointer] = 0;
				}
			}
		}
		return candidateArray;
	}
	
	public int[] checkCol(int cellIndex, int[] candidateArray, Grid grid){
		int[] col = grid.getColValues(cellIndex);
		
		for(int candidateArrayPointer = 0; candidateArrayPointer < candidateArray.length; candidateArrayPointer++){
			for(int colPointer = 0; colPointer < col.length; colPointer++){
				
				if(candidateArray[candidateArrayPointer] == col[colPointer]){
					candidateArray[candidateArrayPointer] = 0;
				}
			}
		}
		return candidateArray;
	}
	
	public int[] checkBlock(int cellIndex, int[] candidateArray, Grid grid, Cell anchor){
		
		int blockCol = cellIndex - ((cellIndex-1)%3);
		int blockRow = anchor.getrIndex() - ((anchor.getrIndex()-1)%3);
		
		for(int i = blockRow; i < blockRow+3; i++){
			for(int j = blockCol; j < blockCol+3; j++){
				
				for(int candidateArrayPointer = 0; candidateArrayPointer < candidateArray.length; candidateArrayPointer++){
					if(grid.getRowValues(i)[j-1] == candidateArray[candidateArrayPointer]){
						candidateArray[candidateArrayPointer] = 0;
					}
				}
			}
		}
		
		return candidateArray;
	}
	
	public int[] fillCandidateArray(){
		int[] a = {1,2,3,4,5,6,7,8,9};
		return a;
	}
	
	public boolean step1(Grid grid){
		
		boolean answer = true;
		int nnCounter = 0;
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
			if(isValidRow(grid, grid.getCell(rIndex, 1)) != true){
				answer = false;
			}
			
			for(int cIndex = 1; cIndex < 10; cIndex++){
				if((int)(allCandidateArray[rIndex-1][cIndex-1].get(0)) == 99){
					nnCounter++;
				}
			}
		}
		
		if(nnCounter == 81){
			answer = false;
		}
		
		return answer;
	}
	
	public boolean step2(Grid grid){
		boolean answer = false;
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
			if(isFullHouseRow(grid, grid.getCell(rIndex, 1)) == true){
				
				for(int i = 0; i < allCandidateArray.length; i++){
					if(allCandidateArray[rIndex-1][i].size() == 1){
						grid.setValue(rIndex, i+1, (int) allCandidateArray[rIndex-1][i].get(0));
						updateAllCandidates(grid);
						answer = true;
					}
				}
			}
		}
		
		return answer;
	}
	
	public boolean step3(Grid grid){
		
		boolean answer = false;
		
		for(int rIndex = 1; rIndex < 10; rIndex++){			
			if(getRowMinimalNakedSingleCell(grid, grid.getCell(rIndex, 1)) != null){
				grid.setValue(rIndex, getRowMinimalNakedSingleCell(grid, grid.getCell(rIndex, 1)).getcIndex(), (int) allCandidateArray[rIndex-1][getRowMinimalNakedSingleCell(grid, grid.getCell(rIndex, 1)).getcIndex()-1].get(0));
				updateAllCandidates(grid);
				answer = true;
			}
		}
		return answer;
	}
	
	public boolean step4(Grid grid){
		
		boolean answer = false;
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
			if(getRowMinimalHiddenSingleCell(grid, grid.getCell(rIndex, 1)) != null){
				grid.setValue(rIndex, getRowMinimalHiddenSingleCell(grid, grid.getCell(rIndex, 1)).getcIndex(), (int) allCandidateArray[rIndex-1][getRowMinimalHiddenSingleCell(grid, grid.getCell(rIndex, 1)).getcIndex()-1].get(0));
				updateAllCandidates(grid);
				answer = true;
			}
		}
		
		return answer;
	}
	
	public boolean step5(Grid grid){
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
		//NAKED PAIR IST FALSCH
			if(getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1)) != null){
				System.out.println("SERWE");
			}
		}
		
		return false;
	}
	
	public static void auslesen(int[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+",");
		}
		System.out.println("");
	}
	
	public static void auslesen(Cell[] a){
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+",");
		}
		System.out.println("");
	}

}