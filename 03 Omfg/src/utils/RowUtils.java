package utils;

import java.util.LinkedList;
import java.util.List;

import data.Cell;
import data.Grid;


/**
 * @author Teichmeister, Christoph
 */
public class RowUtils implements RowIsoUtil, RowSolvingUtil{
	
	/**
	 * Entscheidet, ob in der Einheit eine zulaessige Belegung vorliegt;
	 *	liefert den Wert true, falls ja, false sonst.
	 *	Die Einheit wird durch anchor eindeutig festgelegt.
	 *	
	 * @param grid Das Sudoku auf dem geprueft werden soll, ob sich eine validRow befindet
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return boolean ob die Row valid ist oder nicht
	 */
	public boolean isValidRow(Grid grid, Cell anchor) {
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

	/**
	 * Entscheidet, ob in grid eine FullHouse-Row existiert;
	 *  liefert den Wert true, falls ja, false sonst.
	 *	
	 * @param grid Das Sudoku auf dem geprueft werden soll, ob sich eine fullHouse Reihe befindet
	 * @return answer boolean ob geprueftes Sudoku eine fullHouseRow besitzt oder nicht
	 */
	public boolean hasFullHouseRow(Grid grid) {		
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

	/**
	 * Entscheidet, ob die Einheit eine FullHouse-Einheit ist;
	 * liefert den Wert true, falls ja, false sonst.
	 * Die Einheit wird durch anchor eindeutig festgelegt.
	 *	
	 * @param grid Das Sudoku auf dem geprueft werden soll, ob sich eine fullHouse befindet
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return boolean ob die gepruefte Row eine fullHouseRow ist oder nicht
	 */
	public boolean isFullHouseRow(Grid grid, Cell anchor) {	
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

	/**
	 * Entscheidet, ob die Einheit mindestens eine
	 *  NakedSingle-Zelle enthaelt.
	 *  Die Einheit wird durch anchor eindeutig festgelegt.
	 *	
	 * @param grid Das Sudoku auf dem geprueft werden soll, ob sich eine Row mit einer NakedSingleCell befindet
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return answer boolean ob die gepruefte Row eine NakedSingleCell hat oder nicht
	 */
	public boolean isRowWithNakedSingleCell(Grid grid, Cell anchor) {
		boolean answer = false;
		
		if(getRowMinimalNakedSingleCell(grid, anchor) != null){
			answer = true;
		}else{
			answer = false;
		}
		
		return answer;
	}

	/**
	 * Gibt die minimale NakedSingle-Zelle der Einheit zurueck.
	 * Die Einheit wird durch anchor eindeutig festgelegt.
	 * Betrachtet nur den Fall in dem die Einheit mindestens eine NakedSingle-Zelle enthaelt, andere Faelle werden nicht abgegriffen
	 *	
	 * @param grid Das Sudoku auf dem die Zelle ermittelt werden soll, die eine NakedSingleCell ist.
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return grid.getCell(anchor.getrIndex(), cIndex+1) Die Zelle, die eine NakedSingleCell ist. null falls keine solche existiert.
	 */
	public Cell getRowMinimalNakedSingleCell(Grid grid, Cell anchor) {
		updateAllCandidates(grid);
		
		for(int cIndex = 0; cIndex < 9; cIndex++){
			if(allCandidateArray[anchor.getrIndex()-1][cIndex].size() == 1){
				return grid.getCell(anchor.getrIndex(), cIndex+1);
			}
		}
		return  null;
	}

	/**
	 *  Entscheidet, ob die Einheit mindestens eine HiddenSingle-Zelle enthaelt.
	 *  Die Einheit wird durch anchor eindeutig festgelegt.
	 *	
	 * @param grid Das Sudoku auf dem ermittelt werden soll, ob eine Reihe eine HiddenSingle-Zelle enthaelt.
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return boolean true, falls eine solche Zelle existiert, false wenn nicht
	 */
	public boolean isRowWithHiddenSingleCell(Grid grid, Cell anchor) {
		if(getRowMinimalHiddenSingleCell(grid, anchor) != null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 *  Gibt die minimale HiddenSingle-Zelle der Einheit zurueck.
	 *  Die Einheit wird durch anchor eindeutig festgelegt.
	 *  Es wird nur der Fall betrachtet, in dem die Einheit mindestens eine HiddenSingle-Zelle enthaelt.
	 *  Andere Faelle werden nicht abgefangen 
	 *	
	 * @param grid Das Sudoku auf dem die Zelle ermittelt werden soll, die eine HiddenSingle-Zelle ist.
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return grid.getCell(45-lostRow, 45-lostCol) returnt die Zelle, die eine HiddenSingle-Zelle ist, null falls es keine solche gibt.
	 */
	public Cell getRowMinimalHiddenSingleCell(Grid grid, Cell anchor) {
		boolean foundAnswer = false;
		int lostRow = 0;
		int lostCol = 0;
		
		int[][] gridTemp = new int[8][8];
		
		for(int number = 1; number < 10; number++){
			lostRow = 0;
			lostCol = 0;
			boolean nextNumber = false;
			int rowValidCounter = 0;
			
			for(int reihe = 0; reihe < 9; reihe++){
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
								colCounter++;
							}
						}
						
						//Check Row auf Number
						for(int rCheck = 0; rCheck < row.length; rCheck++){
							if(row[rCheck] == number){
								rowCounter++;
							}
						}
						
						//Erstelle Array[][] fuer Block
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
						
						int[][] block = new int[3][3];
						int tempCMin = cMin;
						for(int i = 0; i < block.length; i++){
							for(int j = 0; j < block.length; j++){
								int[] a = grid.getRowValues(rMin+1);
								block[i][j] = a[cMin];
								cMin++;
							}
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
						
						if(blockCounter == 1 && rowCounter == 1 && colCounter == 1){
							rowValidCounter++;
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
	
	/**
	 *  Entscheidet, ob die Einheit ein HiddenPair-Zellpaar enthaelt.
	 *  Die Einheit wird durch anchor eindeutig festgelegt. 
	 *	
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden sollen, die ein HiddenSingle-Paar bilden.
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return boolean true, falls Pair existiert, false falls nicht.
	 */
	public boolean isRowWithHiddenPairCells(Grid grid, Cell anchor) {
		if(getRowMinimalHiddenPairCells(grid, anchor) != null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 *  Gibt das minimale HiddenPair-Zellpaar der Einheit zurueck.
	 *  Dabei sind die zwei in Cell[] gespeicherten Zellen aufsteigend sortiert. 
	 *  Gibt es kein HiddenPair-Zellpaarin der Einheit, so ist Cell[] leer.
	 *  Die Einheit wird durch anchor eindeutig festgelegt.
	 *	
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden sollen, die ein HiddenSingle-Paar bilden.
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return answer Das Cell[] Array in dem beide HiddenPair Zellen gespeichert sind. Falls es keine gibt, so wird ein leeres Cell[] Array returnt.
	 */
	public Cell[] getRowMinimalHiddenPairCells(Grid grid, Cell anchor) {
	
		updateAllCandidates(grid);
		List<Cell> hiddenCellsList = new LinkedList<Cell>();
		Cell[] answer = new Cell[2];
		boolean foundPair = false;
		
		int[] candidates = {0,0,0,0,0,0,0,0,0};
		
		for(int cIndex = 0; cIndex < allCandidateArray.length; cIndex++){ //DURCHLaeUFT ROW
			if(grid.getValue(anchor.getrIndex(), cIndex+1) == -1){ //DA WO ZELLE KEINEN WERT BESCHRIEBEN HAT
				for(int candInd = 0; candInd < allCandidateArray[anchor.getrIndex()-1][cIndex].size(); candInd++){ //DURCHLaeUFT KANDIDATENLISTE DER ZELLE
					
					candidates[((int) allCandidateArray[anchor.getrIndex()-1][cIndex].get(candInd))-1]++; //ERHoeHE CANDIDATES AN GEFUNDENER ZAHL
					
				}
			}
		}
		
		for(int i = 0; i < candidates.length; i++){ //DURCHLaeUFT CANDIDATES ARRAY UND SUCHT AUF ZWEIMAL ZWEI
			if(candidates[i] == 2){
				for(int j = 0; j < candidates.length; j++){
					if(candidates[j] == 2 && j != i){
						foundPair = true;
						
						for(int cIndex = 0; cIndex < allCandidateArray.length; cIndex++){ //DURCHLaeUFT ROW
							if(grid.getValue(anchor.getrIndex(), cIndex+1) == -1){ //DA WO ZELLE KEINEN WERT BESCHRIEBEN HAT
								for(int candInd = 0; candInd < allCandidateArray[anchor.getrIndex()-1][cIndex].size(); candInd++){ //DURCHLaeUFT KANDIDATENLISTE DER ZELLE
									//SCHREIBT ZELLEN RAUS DIE i UND j BEINHALTEN									
									if((int) allCandidateArray[anchor.getrIndex()-1][cIndex].get(candInd) == i+1){ 
										hiddenCellsList.add(grid.getCell(anchor.getrIndex(), cIndex+1));
									}
									
								}
							}
						}
						
					}
				}
			}
		}
		if (foundPair == true){
			//SCHREIBT ZELLEN AUS LIST IN ARRAY
			Cell[] hiddenCellArray = new Cell[hiddenCellsList.size()];
			for (int i = 0; i < hiddenCellsList.size(); i++) {
				hiddenCellArray[i] = hiddenCellsList.get(i);
			}
			
			//PRueFT UND TAUSCHT GGF ZELLEN SO DASS KLEINSTER INDEX VORNE GRoessTER INDEX HINTEN
			int smaller = 0;
			while (smaller < hiddenCellArray.length - 1) {
				for (int i = 0; i < hiddenCellArray.length - 1; i++) {
					if (hiddenCellArray[i].getcIndex() > hiddenCellArray[i + 1].getcIndex()) {
						Cell temp = hiddenCellArray[i];
						hiddenCellArray[i] = hiddenCellArray[i + 1];
						hiddenCellArray[i + 1] = temp;
					} else if (hiddenCellArray[i].getcIndex() < hiddenCellArray[i + 1].getcIndex()) {
						smaller++;
					}
				}
			}
			answer[0] = hiddenCellArray[0];
			answer[1] = hiddenCellArray[1];
		}
		
		return answer;
	}

	/**
	 *  Entscheidet, ob die Einheit ein NakedPair-Zellpaar enthaelt;
	 * liefert den Wert true, falls ja, sonst false.
	 * Die Einheit wird durch anchor eindeutig festgelegt. 
	 *	
	 * @param grid Das Sudoku auf dem ermittelt werden soll, ob ein NakedPair existiert..
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return boolean true, falls ein solches Pair existiert. False falls nicht.
	 */
	public boolean isRowWithNakedPairCells(Grid grid, Cell anchor) {
		if(getRowMinimalNakedPairCells(grid,anchor) != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 *  Gibt das minimale NakedPair-Zellpaar der Einheit zurueck.
	 *  Dabei sind die zwei in Cell[] gespeicherten Zellen aufsteigend sortiert.
	 *  Gibt es kein NakedPair-Zellpaarin der Einheit, so ist dementsprechend Cell[] leer.
	 *  Die Einheit wird durch anchor eindeutig festgelegt 
	 *	
	 * @param grid Das Sudoku auf dem die Zellen ermittelt werden sollen, die ein NakedPair bilden.
	 * @param anchor Die Ankerzelle mittels welcher, die Row auf der geprueft wird, bestimmt wird
	 * @return cells Cell[] Array in dem die NakedPair-Zellen stehen. Returnt leeres cells Array, falls es solche nicht gibt.
	 */
	public Cell[] getRowMinimalNakedPairCells(Grid grid, Cell anchor) {
		updateAllCandidates(grid);
		
		Cell[] cells = new Cell[2];
		List<Cell> candidatesTwo = new LinkedList<Cell>();
		
		for(int cInd = 0; cInd < allCandidateArray.length; cInd++){	
			if(allCandidateArray[anchor.getrIndex()-1][cInd].size() == 2 && (int) allCandidateArray[anchor.getrIndex()-1][cInd].get(0) != 99){
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
		return cells;
	}

	/**
	 * Wendet die durch den Bildvektor image beschriebene blockinterne Zeilenpermutation auf die Row an,
	 * deren Ankerzelle in anchor abzulesen ist.
	 *	
	 * @param grid Das Sudoku auf dem die Blockinterne Row Permutation stattfinden soll..
	 * @param anchor Die Ankerzelle mittels welcher die "Anker Row" ermittelt wird, mit welcher die RowPermutation stattfinden soll.
	 * @param image Bildvektor, der angibt, wie die Reihen permutiert werden. Die 1. Reihe bildet auf die erste Stelle der Vektors ab und so weiter.
	 * 
	 */
	public void applyBlockInternRowPermutation(Grid grid, Cell anchor, int[] image) {		
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

	/**
	 *  Entscheidet, ob grid2 aus grid1 entstanden ist,
	 *  indem eine Blockpermutation vorgenommen wurde;
	 *  falls ja, wird true zurueckgeliefert, sonst false.
	 *	
	 * @param grid1 Das Ausgangssudoku, welches herangezogen wird, um herauszufinden ob grid2 aus einer Blockpermuation daraus entstanden ist.
	 * @param grid2 Das veraenderte Sudoku, auf dem geprueft wird, ob es aus einer Blockpermuation des ersten Sudokus entstand.
	 * @return boolean true falls grid2 durch eine BLockPermutation aus grid1 entstand. False falls dies nicht der Fall.
	 */
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

	/**
	 *   Gibt den Blockbildvektor zurueck, der die Blockpermutation beschreibt,
	 *   welche auf grid1 angewendet wurde, so dass grid2 entstanden ist.
	 *   Es wird nur der Fall betrachtet, in dem grid2 durch eine BlockPermuation von Grid1 entstand.
	 *   
	 * @param grid1 Das Ausgangssudoku, welches herangezogen wird, um herauszufinden ob grid2 aus einer Blockpermuation daraus entstanden ist.
	 * @param grid2 Das veraenderte Sudoku, auf dem geprueft wird, ob es aus einer Blockpermuation des ersten Sudokus entstand.
	 * @param anchor Ankerzelle auf der geprueft wird, ob die Rows aus grid2 durch eine Permutation der Rows aus grid1 entstanden ist.
	 * @return image Der Vektor durch den, grid2 aus grid1 entstanden ist.
	 */
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
		
		return image;
		
	}

	/**
	 *  Wendet die durch den Bildvektor image beschriebene Wertpermutation auf die Einheit in grid an.
	 *  Die Einheit wird durch anchor eindeutig festgelegt.
	 *   
	 * @param grid Das Sudoku, auf dem die Wertepermutation angewendet werden soll.
	 * @param anchor Ankerzelle auf der die Wertepermutation stattfinden soll.
	 * @param image Vektor, der beschreibt welche Zahl auf welche abbildet.
	 */
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

	/**
	 * Gibt den Bildvektor der Wertpermutation wieder, welche die Einheit von grid1 in die Einheit von grid2 ueberfuehrt hat.
	 * Die Einheit wird durch anchor eindeutig festgelegt.
	 * Dabei wird nur der Fall betrachtet, in dem grid2 durch eine Wertpermutation von grid1 entstanden ist.
	 * Andere Faelle werden mit der Methode nicht abgefangen.
	 *   
	 * @param grid1 Das Sudoku, auf dem ermittelt werden soll, durch welchen Vektor grid2 entstanden ist.
	 * @param grid1 Das Sudoku, welches durch Wertepermutation aus grid1 entstanden ist.
	 * @param anchor Ankerzelle auf der ermittelt wird, was der Vektor der Wertepermutation war.
	 * @return image Vektor, der beschreibt welche Zahl auf welche abbildet.
	 */
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
		
		return image;
	}

	/**
	 *  Entscheidet, ob die Einheit in grid2 durch eine Wertpermutation der Einheit in grid1 entstanden ist.
	 *  Falls ja, wird true geliefert, sonst false.
	 *  Beide Einheiten sind durch anchor jeweils eindeutig festgelegt. 
	 *   
	 * @param grid1 Das Sudoku, auf dem ermittelt werden soll, ob grid2 durch eine WertePermutation aus grid1 entstanden ist.
	 * @param grid1 Das Sudoku, von dem ermittelt werden soll, ob es aus grid1 entstanden ist.
	 * @param anchor Ankerzelle auf der ermittelt wird, ob grid2 aus grid1 entstanden ist
	 * @return boolean true, falls grid2 aus grid1 entstanden ist. False falls nicht.
	 */
	public boolean isRowValuePermutation(Grid grid1, Grid grid2, Cell anchor) {
		boolean answer = false;
		if(getRowValuePermutationImage(grid1, grid2, grid1.getCell(anchor.getrIndex(), anchor.getrIndex())) != null){
			answer = true;
		}else if(getRowValuePermutationImage(grid1, grid2, grid1.getCell(anchor.getrIndex(), anchor.getrIndex())) == null){
			answer = false;
		}
		return answer;
	}
	
	/**
	 *   Gibt aufsteigend sortiert alle Zellen der Einheit zurueck, welche eine -1 beinhalten.
	 *   Die Einheit wird durch anchor eindeutig festgelegt.
	 *   Enthaelt die Einheit keine leeren Zellen, so ist die Liste dementsprechend leer. 
	 *   
	 * @param grid Das Sudoku, auf dem die white Spaces ermittelt werden sollen.
	 * @param anchor Ankerzelle auf der ermittelt wird, ob grid2 aus grid1 entstanden ist
	 * @return List von Zellen List, die die Zellen die nicht belegt sind, zurueckgibt. Leer, falls es solche Zellen nicht gibt.
	 */
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
	boolean updateChangedSomething = false;
	
	boolean deleteCandidates = false;
	LinkedList[] deleteCandidatesfromCell = new LinkedList[9];
	
	int[] foundNakedRow = {0,0,0,0,0,0,0,0,0};
	int[] foundHiddenRow = {0,0,0,0,0,0,0,0,0};
	
	List<Grid> changes = new LinkedList<Grid>();
	int arrayIndex = 0;
	
	/**
	 *   Analysiert mittels Abruf vorher programmierter Methoden, ob ein Sudoku loesbar ist und befuellt, falls moeglich,
	 *   die Zellen entsprechend. Gibt eine Liste der Grids zurueck, die nach jeder Zellenveraenderung entstanden sind. 
	 *   
	 * @param grid Das Sudoku, auf dem die Methoden angewandt werden, um es zu loesen.
	 * @return List von Grids List, die die Grids die nach jeder Zellenveraenderung entstanden sind, wiedergibt.
	 */
	public List<Grid> solveRowBased(Grid grid){		
		
		if(updateChangedSomething == false){
			updateAllCandidates(grid);
		}
		
		Grid temp = new Grid(grid.getRowValues(1).length);
		for(int rIndex = 0; rIndex < grid.getRowValues(1).length; rIndex++){
			for(int cIndex = 0; cIndex < grid.getColValues(1).length; cIndex++){
				
				temp.setValue(rIndex+1, cIndex+1, grid.getValue(rIndex+1, cIndex+1));
			}
		}
		changes.add(temp);

		boolean step1 = step1(grid);
		
		label:if(step1 == true){
			boolean step2 = step2(grid);
			if(step2 == false){
				boolean step3 = step3(grid);
				if(step3 == false){
					boolean step4 = step4(grid);
					if(step4 == false){
						boolean step5 = step5(grid);
						if(step5 == false){
							boolean step6 = step6(grid);
							if(step6 == false){
								break label;
								
							}else if(step6 == true){
								solveRowBased(grid);
							}
						}else if(step5 == true){
							solveRowBased(grid);
						}
					}else if(step4 == true){
						solveRowBased(grid);
					}
				}else if(step3 == true){
					solveRowBased(grid);
				}
			}else if(step2 == true){
				solveRowBased(grid);
			}
		}else if(step1 == false){
			break label;
		}
		return changes;
	}

	/**
	 *   Durchlaeuft fuer jede Zelle des Grids, die moeglichen Kandidaten und speichert diese in die LinkedList allCandidateArray. 
	 *   
	 * @param grid Das Sudoku, dessen Zellen durchlaufen werden, dessen Kandidaten ermittelt werden sollen.
	 */
	public void updateAllCandidates(Grid grid){
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
			for(int cIndex = 1; cIndex < 10; cIndex++){
								
				if(grid.getValue(rIndex, cIndex) == -1){
					List<Integer> candidatesToFill = new LinkedList<Integer>();
					int[] candidates = fillCandidateArray();
										
					candidates = checkRow(candidates, grid, grid.getCell(rIndex, cIndex));
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
		
		if(deleteCandidates == true){
			
			for(int dCfCInd = 0; dCfCInd < deleteCandidatesfromCell.length; dCfCInd++){
				
				if(deleteCandidatesfromCell[dCfCInd] != null){
					
					int CtDfIndex = 0;
					while(CtDfIndex < deleteCandidatesfromCell[dCfCInd].size()){
						
						Cell a = (Cell) deleteCandidatesfromCell[dCfCInd].get(CtDfIndex);
						allCandidateArray[a.getrIndex()-1][a.getcIndex()-1].remove((Integer) (dCfCInd+1));
						
						CtDfIndex++;
					}	
				}
			}
		}		
	}
	
	/**
	 *   Hilfsmethode zu updateAllCandidates. DUrchlaeuft die jeweilige Reihe der aktuell uebergebenen Zelle um fuer diese die Kandidaten zu ermitteln.
	 *   
	 * @param candidateArray Ein Array auf dem von Stelle 0 bis 8, alle moeglichen Kandidaten von 1 bis 9 gespeichert sind.
	 * @param grid Grid, dessen Zellen durchlaufen werden um ihre Kandidatenlisten zu erhalten.
	 * @param anchor Zelle die aktuell ihre Kandidatenliste erhaelt.
	 * @return int[] candidateArray Ein Array auf dem, nach durchlaufen der Methode, alle in der Reihe auftretenden Zahlen, mit einer null ersetzt wurden.
	 */
	public int[] checkRow(int[] candidateArray, Grid grid, Cell anchor){
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
	
	/**
	 *   Hilfsmethode zu updateAllCandidates. Durchlaeuft die jeweilige Column der aktuell uebergebenen Zelle um fuer diese die Kandidaten zu ermitteln.
	 *   
	 * @param cellIndex der Index der Column, an dem sich die aktuell abgefragte Zelle befindet.
	 * @param candidateArray Ein Array auf dem von Stelle 0 bis 8, alle moeglichen Kandidaten von 1 bis 9 gespeichert sind.
	 * @param grid Grid, dessen Zellen durchlaufen werden um ihre Kandidatenlisten zu erhalten.
	 * @return int[] candidateArray Ein Array auf dem, nach durchlaufen der Methode, alle in der Column auftretenden Zahlen, mit einer null ersetzt wurden.
	 */
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
	
	/**
	 *   Hilfsmethode zu updateAllCandidates. Durchlaeuft den jeweiligen Block der aktuell uebergebenen Zelle um fuer diese die Kandidaten zu ermitteln.
	 *   
	 * @param cellIndex der Index der Column, an dem sich die aktuell abgefragte Zelle befindet.
	 * @param candidateArray Ein Array auf dem von Stelle 0 bis 8, alle moeglichen Kandidaten von 1 bis 9 gespeichert sind.
	 * @param grid Grid, dessen Zellen durchlaufen werden um ihre Kandidatenlisten zu erhalten.
	 * @param anchor Zelle die aktuell ihre Kandidatenliste erhaelt.
	 * @return int[] candidateArray Ein Array auf dem, nach durchlaufen der Methode, alle in dem Block auftretenden Zahlen, mit einer null ersetzt wurden.
	 */
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
	
	/**
	 *   Hilfsmethode zu Schritt 5 und 6 der Solver Methode. Durchlaeuft die Rows der gefundenen HiddenPair/NakedPair Zellen und loescht die moeglichen Werte dieser aus den Kandidaten Listen der Zellen.
	 *   
	 * @param a "Erste" Zelle des Pairs.
	 * @param b "Zweite" Zelle des Pairs.
	 * @param cand1 Erster Kandidat des Pairs.
	 * @param cand2 Zweiter Kandidat des Pairs.
	 * @param aca allCandidateArray. Das Array in dem die Kandidatenlisten gespeichert sind und auf dem dementsprechend die Updates der Kandidatenlisten durchgefuehrt werden muessen.
	 * @param grid Grid, dessen Zellen durchlaufen werden um ihre Kandidatenlisten zu erhalten.
	 * @param cellsToDeleteFrom List, in der die Zellen, aus deren Kandidatenlisten die Kandidaten der Pairs geloescht wurden, speichert um diese bei zukuenftigen KandidatenUpdates nicht faelschlicherweise mit den Kandidaten der Pairs zu belegen.
	 * @return aca allCandidateArray. Gibt die geaenderte Kandidatenliste zurueck.
	 */
	public LinkedList[][] updateRow(Cell a, Cell b, int cand1, int cand2, LinkedList[][] aca, Grid grid, List cellsToDeleteFrom){
		int changeCounter = 0;
		
		for(int cIndex = 0; cIndex < aca.length; cIndex++){
			for(int candIndex = 0; candIndex < aca[a.getrIndex()-1][cIndex].size(); candIndex++){
					
				if((int) aca[a.getrIndex()-1][cIndex].get(candIndex) == cand1 || (int) aca[a.getrIndex()-1][cIndex].get(candIndex) == cand2){
					
					if(aca[a.getrIndex()-1][cIndex] != aca[a.getrIndex()-1][a.getcIndex()-1] && aca[a.getrIndex()-1][cIndex] != aca[b.getrIndex()-1][b.getcIndex()-1]){
						changeCounter++;
						aca[a.getrIndex()-1][cIndex].remove((Integer) cand1);
						aca[a.getrIndex()-1][cIndex].remove((Integer) cand2);
						
						deleteCandidates = true;
						cellsToDeleteFrom.add(grid.getCell(a.getrIndex(), cIndex+1));
					}
				}
			}
		}
		if(changeCounter > 0){
			updateChangedSomething = true;
		}
		return aca;
	}
	
	/**
	 *   Hilfsmethode zu Schritt 5 und 6 der Solver Methode. Durchlaeuft die Columns der gefundenen HiddenPair/NakedPair Zellen und loescht die moeglichen Werte dieser aus den Kandidaten Listen der Zellen.
	 *   
	 * @param a "Erste" Zelle des Pairs.
	 * @param b "Zweite" Zelle des Pairs.
	 * @param cand1 Erster Kandidat des Pairs.
	 * @param cand2 Zweiter Kandidat des Pairs.
	 * @param aca allCandidateArray. Das Array in dem die Kandidatenlisten gespeichert sind und auf dem dementsprechend die Updates der Kandidatenlisten durchgefuehrt werden muessen.
	 * @param grid Grid, dessen Zellen durchlaufen werden um ihre Kandidatenlisten zu erhalten.
	 * @param cellsToDeleteFrom List, in der die Zellen, aus deren Kandidatenlisten die Kandidaten der Pairs geloescht wurden, speichert um diese bei zukuenftigen KandidatenUpdates nicht faelschlicherweise mit den Kandidaten der Pairs zu belegen.
	 * @return aca allCandidateArray. Gibt die geaenderte Kandidatenliste zurueck.
	 */
	public LinkedList[][] updateCol(Cell a, Cell b, int cand1, int cand2, LinkedList[][] aca, Grid grid, List cellsToDeleteFrom){
		int changeCounter = 0;
		
		for(int rIndex = 0; rIndex < aca.length; rIndex++){
			for(int candIndex = 0; candIndex < aca[rIndex][a.getcIndex()-1].size(); candIndex++){
				
				if((int) aca[rIndex][a.getcIndex()-1].get(candIndex) == cand1 || (int) aca[rIndex][a.getcIndex()-1].get(candIndex) == cand2){
					if(aca[rIndex][a.getcIndex()-1] != aca[a.getrIndex()-1][a.getcIndex()-1] && aca[rIndex][a.getcIndex()-1] != aca[b.getrIndex()-1][b.getcIndex()-1]){	
						changeCounter++;
						
						aca[rIndex][a.getcIndex()-1].remove((Integer) cand1);
						aca[rIndex][a.getcIndex()-1].remove((Integer) cand2);
						deleteCandidates = true;
						cellsToDeleteFrom.add(grid.getCell(rIndex+1, a.getcIndex()));
					}
				}
			}
			if(changeCounter > 0){
				updateChangedSomething = true;
			}
			changeCounter = 0;
			
			for(int candIndex = 0; candIndex < aca[rIndex][b.getcIndex()-1].size(); candIndex++){
				
				if((int) aca[rIndex][b.getcIndex()-1].get(candIndex) == cand1 || (int) aca[rIndex][b.getcIndex()-1].get(candIndex) == cand2){
					if(aca[rIndex][b.getcIndex()-1] != aca[a.getrIndex()-1][a.getcIndex()-1] && aca[rIndex][b.getcIndex()-1] != aca[b.getrIndex()-1][b.getcIndex()-1]){	
						changeCounter++;
						
						aca[rIndex][b.getcIndex()-1].remove((Integer) cand1);
						aca[rIndex][b.getcIndex()-1].remove((Integer) cand2);
						deleteCandidates = true;
						cellsToDeleteFrom.add(grid.getCell(rIndex+1, b.getcIndex()));
					}	
				}
			}
		}
		if(changeCounter > 0){
			updateChangedSomething = true;
		}
		return aca;
	}
	
	/**
	 *   Hilfsmethode zu Schritt 5 und 6 der Solver Methode. Durchlaeuft die Bloecke der gefundenen HiddenPair/NakedPair Zellen und loescht die moeglichen Werte dieser aus den Kandidaten Listen der Zellen.
	 *   
	 * @param a "Erste" Zelle des Pairs.
	 * @param b "Zweite" Zelle des Pairs.
	 * @param cand1 Erster Kandidat des Pairs.
	 * @param cand2 Zweiter Kandidat des Pairs.
	 * @param aca allCandidateArray. Das Array in dem die Kandidatenlisten gespeichert sind und auf dem dementsprechend die Updates der Kandidatenlisten durchgefuehrt werden muessen.
	 * @param grid Grid, dessen Zellen durchlaufen werden um ihre Kandidatenlisten zu erhalten.
	 * @param cellsToDeleteFrom List, in der die Zellen, aus deren Kandidatenlisten die Kandidaten der Pairs geloescht wurden, speichert um diese bei zukuenftigen KandidatenUpdates nicht faelschlicherweise mit den Kandidaten der Pairs zu belegen.
	 * @return aca allCandidateArray. Gibt die geaenderte Kandidatenliste zurueck.
	 */
	public LinkedList[][] updateBlock(Cell a, Cell b, int cand1, int cand2, LinkedList[][] aca, Grid grid, List cellsToDeleteFrom){
		int blockCol = (a.getcIndex() - ((a.getcIndex()-1)%3))-1;
		int blockRow = (a.getrIndex() - ((a.getrIndex()-1)%3))-1;
		
		int changeCounter = 0;
		
		for(int i = blockRow; i < blockRow+3; i++){
			for(int j = blockCol; j < blockCol+3; j++){	
				for(int candInd = 0; candInd < aca[i][j].size(); candInd++){
				
					if((int) aca[i][j].get(candInd) == cand1 || (int) aca[i][j].get(candInd) == cand2){
						if(aca[i][j] != aca[a.getrIndex()-1][a.getcIndex()-1] && aca[i][j] != aca[b.getrIndex()-1][b.getcIndex()-1]){	
							changeCounter++;
							aca[i][j].remove((Integer) cand1);
							aca[i][j].remove((Integer) cand2);
							
							deleteCandidates = true;
							cellsToDeleteFrom.add(grid.getCell(i+1, j+1));
						}
					}
				}
			}
		}
		if(changeCounter > 0){
			updateChangedSomething = true;
		}
		changeCounter = 0;
		
		blockCol = (b.getcIndex() - ((b.getcIndex()-1)%3))-1;
		blockRow = (b.getrIndex() - ((b.getrIndex()-1)%3))-1;
		
		for(int i = blockRow; i < blockRow+3; i++){
			for(int j = blockCol; j < blockCol+3; j++){
				for(int candInd = 0; candInd < aca[i][j].size(); candInd++){
				
					if((int) aca[i][j].get(candInd) == cand1 || (int) aca[i][j].get(candInd) == cand2){
						if(aca[i][j] != aca[a.getrIndex()-1][a.getcIndex()-1] && aca[i][j] != aca[b.getrIndex()-1][b.getcIndex()-1]){
							changeCounter++;
						
							aca[i][j].remove((Integer) cand1);
							aca[i][j].remove((Integer) cand2);
							deleteCandidates = true;
							cellsToDeleteFrom.add(grid.getCell(i+1, j+1));
						}
					}
				}
			}
		}
		if(changeCounter > 0){
			updateChangedSomething = true;
		}
		changeCounter = 0;
		
		return aca;
	}
	
	/**
	 *   Hilfsmethode um das CandidateArray, mit dem in der Methode updateAllCandidates gearbeitet wird, mit den Zahlen 1-9 aufzufuellen.
	 *   
	 *  @return a Das gefuellte CandidateArray
	 */
	public int[] fillCandidateArray(){
		int[] a = {1,2,3,4,5,6,7,8,9};
		return a;
	}
	
	/**
	 *   Erster Schritt der Solver Methode, in dem ermittelt wird ob das Sudoku vollstaendig und zulaessig gefuellt ist.
	 *   Returnt false, falls dem so ist. True falls es nicht vollstaendig oder zulaessig belegt ist.
	 *   
	 *   @param grid grid auf dem Reihen auf Zulaessigkeit geprueft werden.
	 *  @return answer true, falls nicht vollstaendig oder zulaessig. false falls schon.
	 */
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
	
	/**
	 *   Zweiter Schritt der Solver Methode, in dem ermittelt wird ob das Sudoku eine FullHouse-Einheit besitzt. 
	 *   Falls es eine solche besitzt, fuellt es die Zelle dieser mit ihrem einzig moeglichen Wert.
	 *   Returnt true, falls dies geschehen ist. Returnt false, falls es keine solche Zelle bzw Einheit gibt.
	 *   
	 *   @param grid grid auf dem die Reihen nach einer FullHouse Zelle ueberprueft werden.
	 *   @return answer true, falls eine FullHouse Zelle gefunden wurde, false falls nicht.
	 */
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
	
	/**
	 *   Dritter Schritt der Solver Methode, in dem ermittelt wird ob das Sudoku mindestens eine NakedSingle Zelle besitzt. 
	 *   Falls es eine solche besitzt, fuellt es die Zelle dieser mit ihrem einzig moeglichen Wert.
	 *   Returnt true, falls dies geschehen ist. Returnt false, falls es keine solche Zelle bzw Einheit gibt.
	 *   
	 *   @param grid grid auf dem die Reihen nach einer NakedSingle Zelle ueberprueft werden.
	 *   @return answer true, falls eine NakedSingle Zelle gefunden wurde, false falls nicht.
	 */
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
	
	/**
	 *   Vierter Schritt der Solver Methode, in dem ermittelt wird ob das Sudoku mindestens eine HiddenSingle Zelle besitzt. 
	 *   Falls es eine solche besitzt, fuellt es die Zelle dieser mit ihrem einzig moeglichen Wert.
	 *   Returnt true, falls dies geschehen ist. Returnt false, falls es keine solche Zelle bzw Einheit gibt.
	 *   
	 *   @param grid grid auf dem die Reihen nach einer HiddenSingle Zelle ueberprueft werden.
	 *   @return answer true, falls eine HiddenSingle Zelle gefunden wurde, false falls nicht.
	 */
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
	
	/**
	 *   Fuenfter Schritt der Solver Methode, in dem ermittelt wird ob das Sudoku ein NakedPair besitzt.
	 *   Falls es ein solches besitzt, werden die jeweiligen Kandidaten aus allen, von diesen PairZellen, betroffenen Zellen-Kandidatenlisten geloescht.
	 *   Falls dieses Vorgehen in mindestens einer Kandidatenlist etwas geaendert hat, wird true returnt. Falls nicht, false.
	 *   Die Kandidaten werden dabei nicht aus den PairZellen-Kandidatenlisten geloescht.
	 *   
	 *   @param grid grid auf dem ein NakedPair gesucht wird.
	 *   @return boolean true, falls das KAndidatenlisten Update in mindestens einer Kandidatenliste etwas geaendert hat. False falls nicht.
	 */
	public boolean step5(Grid grid){		
		updateChangedSomething = false;
		
		for(int rIndex = 1; rIndex < 10; rIndex++){
			if(getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1)) != null){
				
				if(foundNakedRow[rIndex-1] == 0){
					foundNakedRow[rIndex-1]++;

					Cell first = getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1))[0];
					Cell second = getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1))[1];
					int cand1 = (int) allCandidateArray[getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1))[0].getrIndex()-1][getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1))[0].getcIndex()-1].get(0);
					int cand2 = (int) allCandidateArray[getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1))[0].getrIndex()-1][getRowMinimalNakedPairCells(grid, grid.getCell(rIndex, 1))[0].getcIndex()-1].get(1);
					LinkedList[][] allCandidateArrayTemp = new LinkedList[9][9];
									
					allCandidateArrayTemp = allCandidateArray;
					List<Cell> cellsToDeleteFrom = new LinkedList<Cell>();
			
					allCandidateArrayTemp = updateRow(first, second, cand1, cand2, allCandidateArrayTemp, grid, cellsToDeleteFrom);
					allCandidateArrayTemp = updateCol(first, second, cand1, cand2, allCandidateArrayTemp, grid, cellsToDeleteFrom);
					allCandidateArrayTemp = updateBlock(first, second, cand1, cand2, allCandidateArrayTemp, grid, cellsToDeleteFrom);

					deleteCandidatesfromCell[cand1-1] = (LinkedList) cellsToDeleteFrom;
					deleteCandidatesfromCell[cand2-1] = (LinkedList) cellsToDeleteFrom;
		
					if(updateChangedSomething == true){
						allCandidateArray = allCandidateArrayTemp;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 *   Sechster Schritt der Solver Methode, in dem ermittelt wird ob das Sudoku ein HiddenPair besitzt.
	 *   Falls es ein solches besitzt, werden die jeweiligen Kandidaten aus allen, von diesen PairZellen, betroffenen Zellen-Kandidatenlisten geloescht.
	 *   Falls dieses Vorgehen in mindestens einer Kandidatenlist etwas geaendert hat, wird true returnt. Falls nicht, false.
	 *   Die Kandidaten werden dabei nicht aus den PairZellen-Kandidatenlisten geloescht.
	 *   
	 *   @param grid grid auf dem ein HiddenPair gesucht wird.
	 *   @return boolean true, falls das Kandidatenlisten Update in mindestens einer Kandidatenliste etwas geaendert hat. False falls nicht.
	 */
	public boolean step6 (Grid grid){
		updateChangedSomething = false;
		
		for(int rIndex = 1; rIndex < 10; rIndex++){			
			if(getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[0] != null){
				
				if(foundHiddenRow[rIndex-1] == 0){
					foundHiddenRow[rIndex-1]++;
					
					Cell first = getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[0];
					Cell second = getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[1];
					int cand1 = (int) allCandidateArray[getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[0].getrIndex()-1][getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[0].getcIndex()-1].get(0);
					int cand2 = (int) allCandidateArray[getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[0].getrIndex()-1][getRowMinimalHiddenPairCells(grid, grid.getCell(rIndex, 1))[0].getcIndex()-1].get(1);
					
					LinkedList[][] allCandidateArrayTemp = new LinkedList[9][9];
					
					allCandidateArrayTemp = allCandidateArray;
					List<Cell> cellsToDeleteFrom = new LinkedList<Cell>();
					
					allCandidateArrayTemp = updateRow(first, second, cand1, cand2, allCandidateArrayTemp, grid, cellsToDeleteFrom);
					allCandidateArrayTemp = updateCol(first, second, cand1, cand2, allCandidateArrayTemp, grid, cellsToDeleteFrom);
					allCandidateArrayTemp = updateBlock(first, second, cand1, cand2, allCandidateArrayTemp, grid, cellsToDeleteFrom);
					
					deleteCandidatesfromCell[cand1-1] = (LinkedList) cellsToDeleteFrom;
					deleteCandidatesfromCell[cand2-1] = (LinkedList) cellsToDeleteFrom;
					
					if(updateChangedSomething == true){
						allCandidateArray = allCandidateArrayTemp;
						return true;
					}
				}
				
			}
		}		
		return false;
	}
}