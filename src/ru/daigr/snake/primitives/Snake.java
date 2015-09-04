package ru.daigr.snake.primitives;
import java.util.LinkedList;

import ru.daigr.snake.primitives.result.MoveError;
import ru.daigr.snake.primitives.result.Result;

public class Snake {
	
	private LinkedList<Cell> snake = new LinkedList<>();
	// 0 - left 1 - down 2 - right 3 - up
	private int headOrientation = 0;
	
	public Snake (Cell initialPosition){
		snake.addFirst(initialPosition);		
	}
	
	public Result move(Cell toCell, int orientation){
		headOrientation = orientation;
		
		snake.addFirst(toCell);
		snake.removeLast();		
		
		return checkCorrectness();
	}
	
	public Result eat(Cell eatCell, int orientation){
		headOrientation = orientation;
		
		snake.addFirst(eatCell);
		
		return checkCorrectness();
	}
	
	public int getHeadOrientation(){
		return headOrientation;
	}
	
	public Cell getHeadCell(){
		return snake.getFirst();
	}
	
	private Result checkCorrectness(){
		if (haveCollision()){
			return new Result(false, MoveError.COLLISION);
		}
		
		return new Result(true, null);
		
	}
	
	public LinkedList<Cell> getCells(){
		return snake;
	}

	private boolean haveCollision(){
		for (int i = 0; i < snake.size() - 1; i++){
			for (int j = i + 1; j < snake.size(); j++){
				if (snake.get(i).equals(snake.get(j))){
					return true;
				}
			}
		}
		return false;
	}
	
}
