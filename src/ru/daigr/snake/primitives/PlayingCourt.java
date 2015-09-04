package ru.daigr.snake.primitives;

import java.util.ArrayList;

public class PlayingCourt {

	private ArrayList<Cell> court = new ArrayList<>();
	private int size = 0;
	
	public PlayingCourt (int aSize, ArrayList<String> codes){	
		if (codes.size() != aSize*aSize) return;
		size = aSize;
		char currentCharIndex = 'A';
		int currentNumIndex = 1;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				Cell c = new Cell(currentCharIndex, currentNumIndex);
				c.setCode(codes.get(0));
				codes.remove(0);
				court.add(c);
				currentNumIndex++;
				if (currentNumIndex > size) currentNumIndex = 1;
			}				
			currentCharIndex++;
			if (currentCharIndex > 'A' + size) currentCharIndex = 'A';
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public ArrayList<Cell> getCells(){
		return court;
	}
	
	public boolean isNear(Cell aCell, Cell bCell) {
		if (aCell.equals(bCell)){
			return false;
		}
		return Math.abs(aCell.getCharIndex() - bCell.getCharIndex()) < 2
				&& Math.abs(aCell.getNumIndex() - bCell.getNumIndex()) < 2;
	}
	
	public int getOrientation(Cell aCell, Cell bCell){
		if (!isNear(aCell, bCell)) return 0;
		if (aCell.getCharIndex() - bCell.getCharIndex() == -1) {
			return 1;
		} else if (aCell.getCharIndex() - bCell.getCharIndex() == 1) {
			return 3;
		} else if (aCell.getNumIndex() - bCell.getNumIndex() == 1) {
			return 2;
		} else if (aCell.getNumIndex() - bCell.getNumIndex() == -1) {
			return 0;
		} else {
			return 0;
		}					
	}
	
	public Cell enterCode(String code){
		for (Cell c : court){
			if (c.checkCode(code)) return c;
		}
		return null;
	}
	
	public void addEat(Cell aCell){
		changeEat(aCell, true);
	}
	
	public void removeEat(Cell aCell){
		changeEat(aCell, false);
	}
	
	private void changeEat(Cell cell, boolean isAdd){
		for (Cell c : court){
			if (c.equals(cell)){
				c.itsEat(isAdd);
				break;
			}
		}
	}
		
	
	
}
