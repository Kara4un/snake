package ru.daigr.snake.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import ru.daigr.snake.primitives.Cell;
import ru.daigr.snake.primitives.PlayingCourt;
import ru.daigr.snake.primitives.Snake;
import ru.daigr.snake.primitives.result.GameImageBuilder;
import ru.daigr.snake.primitives.result.MoveError;
import ru.daigr.snake.primitives.result.Result;
import ru.daigr.snake.primitives.result.Error;

public class GameEngine {
	
	private PlayingCourt court = null;
	private Snake snake = null;	
	private LinkedList<Cell> eat = new LinkedList<>();
	private long playerId = 0;
	
	public GameEngine(long aPlayerId) {
		init(6, new Cell('C', 3), playerId, initEat());
	}
	
	public void reset(long aPlayerId){				
		init(6, new Cell('C', 3), playerId, initEat());
	}
	
	private void init (int courtSize, Cell initialPos, long aPlayerId,
			LinkedList<Cell> anEat){
		court = new PlayingCourt(courtSize, initCodes());
		snake = new Snake(initialPos);
		playerId = aPlayerId;	
		eat = anEat;	
	}
	
	public long getPlayerId(){
		return playerId;
	}
	
	public Result enterCommand(String src){
		ArrayList<String> codes = CommandParser.parse(src);
		if (codes.size() == 0) return new Result(false, Error.FORMAT);
		
		Result ret = null;
		
		for (String code : codes){
			Cell currentCell = court.enterCode(code);
			if (currentCell == null) {
				return new Result(false, MoveError.NO_CODE);
			} else {
				if (court.isNear(currentCell, snake.getHeadCell())){
					int orientation = court.getOrientation(currentCell, snake.getHeadCell());
					if (currentCell.equals(eat.get(0))){
						ret = snake.eat(currentCell, orientation);
						if (ret.isOk()){							
							eat.removeFirst();
							if (eat.size() == 0){
								ret = new Result(true, null);
							}
							court.addEat(eat.getFirst());
						} 						
					} else {
						ret = snake.move(currentCell, orientation);
					}
				} else {				
					return new Result(false, MoveError.NOT_NEAR);
				}
			}
		}
		
		try {
			GameImageBuilder.BuildSnapshotImage(snake, court, eat.size() == 0 ? null : eat.getFirst(), playerId);
		} catch (IOException io) {
			io.printStackTrace();			
		}
		
		return ret;
	}		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private LinkedList<Cell> initEat() {
		LinkedList<Cell> ret = new LinkedList<>();
		ret.add(new Cell('A',1));
		ret.add(new Cell('B',2));
		ret.add(new Cell('C',3));
		ret.add(new Cell('D',4));
		return ret;
	}
	
	private ArrayList<String> initCodes(){
		ArrayList<String> codes = new ArrayList<>(); 
		//		A1				A2				A3				A4				A5				A6
		codes.add("DR1");codes.add("DR2");codes.add("DR3");codes.add("DR4");codes.add("DR5");codes.add("DR6");
		//		B1				B2					B3				B4				B5				B6
		codes.add("DR7");codes.add("DR8");codes.add("DR9");codes.add("DR10");codes.add("DR11");codes.add("DR12");
		//		C1				C2				C3					C4				C5					C6
		codes.add("DR13");codes.add("DR14");codes.add("DR15");codes.add("DR16");codes.add("DR17");codes.add("DR18");
		//		D1				D2				D3					D4				D5					D6
		codes.add("DR19");codes.add("DR20");codes.add("DR21");codes.add("DR22");codes.add("DR23");codes.add("DR24");
		//		E1				E2				E3					E4				E5					E6
		codes.add("DR25");codes.add("DR26");codes.add("DR27");codes.add("DR28");codes.add("DR29");codes.add("DR30");
		//		F1				F2				F3					F4				F5					F6
		codes.add("DR31");codes.add("DR32");codes.add("DR33");codes.add("DR34");codes.add("DR35");codes.add("DR36");
		
		return codes;
	}	

}
