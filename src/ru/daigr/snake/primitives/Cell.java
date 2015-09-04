package ru.daigr.snake.primitives;

public class Cell {
	
	private int numIndex = 0;
	private char charIndex = 'A';
	private String code;
	private boolean isEat = false;
	
	public Cell (char aCharIndex, int aNumIndex) {
		numIndex = aNumIndex;
		charIndex = aCharIndex;
	}

	public Cell(int aNumIndex, char aCharIndex, String aCode){
		numIndex = aNumIndex;
		charIndex = aCharIndex;
		code = aCode;
	}
	
	public void itsEat(boolean isEat){
		this.isEat = isEat;
	}
	
	public boolean isEat(){
		return isEat;
	}
	
	public void setCode(String aCode){
		code = aCode;
	}	
	
	public int getNumIndex(){
		return numIndex;
	}
	
	public int getCharIndex(){
		return charIndex;
	}
	
	public boolean checkCode(String code){
		return code.toLowerCase().equals(this.code.toLowerCase());
	}
		
	public boolean equals(Cell aCell){
		return (getNumIndex() == aCell.getNumIndex()) &&
				(getCharIndex() == aCell.getCharIndex());
	}
	
	@Override
	public String toString(){
		return Character.toString(charIndex) + Integer.toString(numIndex);
	}
}
