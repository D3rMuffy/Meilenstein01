import data.Cell;
import data.Grid;
import utils.GridUtils3;
import utils.RowUtils;

public class App {
	
	public static void main(String[] args) {
		Grid a = new Grid(9);
		Grid b = new Grid(9);
		RowUtils abc = new RowUtils();
		GridUtils3 cba = new GridUtils3();
		int[] rImage = {2,3,1};
		int[] vImage = {9,8,7,6,5,4,3,2,1};
		
		int[] row1 = {+5,+3,-1,-1,+7,-1,-1,-1,-1};
		int[] row2=  {+6,-1,-1,+1,+9,+5,-1,-1,-1};
		int[] row3 = {-1,+9,+8,-1,-1,-1,-1,+6,-1};
		int[] row4 = {+8,-1,-1,-1,+6,-1,-1,-1,+3};
		int[] row5 = {+4,-1,-1,+8,-1,+3,-1,-1,+1};
		int[] row6 = {+7,-1,-1,-1,+2,-1,-1,-1,+6};
		int[] row7 = {-1,+6,-1,-1,-1,-1,+2,+8,-1};
		int[] row8 = {-1,-1,-1,+4,+1,+9,-1,-1,+5};
		int[] row9 = {-1,-1,-1,-1,+8,-1,-1,+7,+9};
		
		int[] row11 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row22=  {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row33 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row44 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row55 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row66 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row77 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row88 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] row99 = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
		
		fillRow(a, 1, row1);
		fillRow(a, 2, row2);
		fillRow(a, 3, row3);
		fillRow(a, 4, row4);
		fillRow(a, 5, row5);
		fillRow(a, 6, row6);
		fillRow(a, 7, row7);
		fillRow(a, 8, row8);
		fillRow(a, 9, row9);
		
		fillRow(b, 1, row11);
		fillRow(b, 2, row22);
		fillRow(b, 3, row33);
		fillRow(b, 4, row44);
		fillRow(b, 5, row55);
		fillRow(b, 6, row66);
		fillRow(b, 7, row77);
		fillRow(b, 8, row88);
		fillRow(b, 9, row99);
		
//		callRowUtils(a, b, abc);
		callGridUtils3(a, b, cba);
		
	}

	public static void callGridUtils3(Grid a, Grid b, GridUtils3 cba){
		a.print();
		System.out.println("");
		
		System.out.println("turnRight");
		cba.turnRight(a);
		
		a.print();
	}
	
		
	public static void callRowUtils(Grid a, Grid b, RowUtils abc){
		a.print();
		System.out.println("");
//		b.print();
//		System.out.println("");
			
		System.out.println("isValidRow: "+abc.isValidRow(a, a.getCell(1, 1)));
//		System.out.println("hasFullHouseRow: "+abc.hasFullHouseRow(a));
//		System.out.println("isFullHouseRow: "+abc.isFullHouseRow(a, a.getCell(1, 1)));
//		System.out.println("isRowWithNakedSingleCell: "+abc.isRowWithNakedSingleCell(a, a.getCell(1, 1)));
//		System.out.println("getRowWithNakedSingleCell: " + abc.getRowMinimalNakedSingleCell(a, a.getCell(1, 1)));
//		System.out.println("isRowWithHiddenSingleCell: "+ abc.isRowWithHiddenSingleCell(a, a.getCell(1, 1)));
//		System.out.println("getRowMinimalHiddenSingleCell: "+ abc.getRowMinimalHiddenSingleCell(a, a.getCell(1, 1)));
//		System.out.println("getRowMinimalHiddenSingleCell: "+ abc.getRowMinimalHiddenSingleCell(a, a.getCell(1, 1)).getrIndex() + " " + abc.getRowMinimalHiddenSingleCell(a, a.getCell(1, 1)).getcIndex());
//		System.out.println("isRowWithNakedPairCells: "+ abc.isRowWithNakedPairCells(a, a.getCell(1, 1)));
//		System.out.println("getRowMinimalNakedPairCells: "+ abc.getRowMinimalNakedPairCells(a, a.getCell(1, 1)));
//		
//		System.out.println("applyBlockInternRowPermutation");abc.applyBlockInternRowPermutation(a, a.getCell(1, 1), rImage);
//		a.print();
//		
//		System.out.println("isBlockInternRowPermutation: " + abc.isBlockInternRowPermutation(a, b));
//		
//		System.out.println("getBlockInternRowPermutationImage: ");
//		if(abc.getBlockInternRowPermutationImage(a, b, a.getCell(1, 1)) == null){
//			System.out.println("null");
//		}else{
//			for(int i = 0; i < abc.getBlockInternRowPermutationImage(a, b, a.getCell(1, 1)).length; i++){
//			System.out.print(abc.getBlockInternRowPermutationImage(a, b, a.getCell(1, 1))[i]);
//			}
//		}
//		
//		System.out.println("applyRowValuePermutation");
//		abc.applyRowValuePermutation(a, a.getCell(1, 2), vImage);
//		a.print();
//		
//		System.out.println("getRowValuePermutationImage: ");
//		auslesen(abc.getRowValuePermutationImage(a, b, a.getCell(1, 2)));
//		
//		System.out.println("isRowValuePermutation: "+abc.isRowValuePermutation(a, b, a.getCell(1, 1)));
//		System.out.println("getRowWhiteSpaces: " + abc.getRowWhiteSpaces(a, a.getCell(1, 1)));
//		
//		System.out.println("getRowMinimalHiddenPairCells: ");
//		auslesen(abc.getRowMinimalHiddenPairCells(a, a.getCell(1, 1)));
//		
//		System.out.println("isRowMinimalHiddenPairCell: " + abc.isRowWithHiddenPairCells(a, a.getCell(1, 1)));
	}
		
	public static void fillRow(Grid grid, int row, int[] values){
		
		for(int i = 1; i <= values.length; i++){
			grid.setValue(row,i,values[i-1]);
		}
	}
	
	public static void auslesen(int[] a){
		if(a == null){
			System.out.println("null");
		}else{
			for(int i = 0; i < a.length; i++){
				System.out.print(a[i]+",");
			}
		}
		System.out.println("");
	}
	
	public static void auslesen(Cell[] a){
		if(a == null){
			System.out.println("null");
		}else{
			for(int i = 0; i < a.length; i++){
				System.out.print(a[i].getrIndex()+","+a[i].getcIndex());
				System.out.println("");
			}
		}
		
	}
}


