package utils;

import data.Cell;
import data.Grid;

public class RowUtilsAlt implements RowIsoUtil, RowSolvingUtil{

	@Override
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
	}

	@Override
	public List<Cell> getRowWhiteSpaces(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFullHouseRow(Grid grid) {
		/*
		 *  Entscheidet, ob in grid eine FullHouse-Row existiert;
		 *  liefert den Wert true, falls ja, false sonst. 
		 */
		int i = 1;
		int fullHouseUnit = 0;
		
		
		while(fullHouseUnit != 1){
			int[] row = grid.getRowValues(i);
			
			int rowMin = row[0];
			
			for(int j = 0; j < row.length; j++){
				if(rowMin >= row[j]){
					rowMin = row[j];
				}
			}
			
			if(rowMin == -1){
				fullHouseUnit++;
			}
			i++;
		}
		
		if(fullHouseUnit == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isFullHouseRow(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRowWithNakedSingleCell(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cell getRowMinimalNakedSingleCell(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenSingleCell(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cell getRowMinimalHiddenSingleCell(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithNakedPairCells(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cell[] getRowMinimalNakedPairCells(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenPairCells(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cell[] getRowMinimalHiddenPairCells(Grid grid, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grid> solveRowBased(Grid grid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyBlockInternRowPermutation(Grid grid, Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlockInternRowPermutation(Grid grid1, Grid grid2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getBlockInternRowPermutationImage(Grid grid1, Grid grid2, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyRowValuePermutation(Grid grid, Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getRowValuePermutationImage(Grid grid1, Grid grid2, Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowValuePermutation(Grid grid1, Grid grid2, Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<utils.Cell> getRowWhiteSpaces(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFullHouseRow(utils.Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFullHouseRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRowWithNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<utils.Grid> solveRowBased(utils.Grid grid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyBlockInternRowPermutation(utils.Grid grid, utils.Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlockInternRowPermutation(utils.Grid grid1, utils.Grid grid2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getBlockInternRowPermutationImage(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyRowValuePermutation(utils.Grid grid, utils.Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getRowValuePermutationImage(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowValuePermutation(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<utils.Cell> getRowWhiteSpaces(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFullHouseRow(utils.Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFullHouseRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRowWithNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<utils.Grid> solveRowBased(utils.Grid grid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyBlockInternRowPermutation(utils.Grid grid, utils.Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlockInternRowPermutation(utils.Grid grid1, utils.Grid grid2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getBlockInternRowPermutationImage(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyRowValuePermutation(utils.Grid grid, utils.Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getRowValuePermutationImage(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowValuePermutation(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<utils.Cell> getRowWhiteSpaces(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFullHouseRow(utils.Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFullHouseRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRowWithNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<utils.Grid> solveRowBased(utils.Grid grid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyBlockInternRowPermutation(utils.Grid grid, utils.Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlockInternRowPermutation(utils.Grid grid1, utils.Grid grid2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getBlockInternRowPermutationImage(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void applyRowValuePermutation(utils.Grid grid, utils.Cell anchor, int[] image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getRowValuePermutationImage(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowValuePermutation(utils.Grid grid1, utils.Grid grid2, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<utils.Cell> getRowWhiteSpaces(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFullHouseRow(utils.Grid grid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFullHouseRow(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRowWithNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalNakedSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell getRowMinimalHiddenSingleCell(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalNakedPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRowWithHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public utils.Cell[] getRowMinimalHiddenPairCells(utils.Grid grid, utils.Cell anchor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<utils.Grid> solveRowBased(utils.Grid grid) {
		// TODO Auto-generated method stub
		return null;
	}

}
