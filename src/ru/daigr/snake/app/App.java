package ru.daigr.snake.app;

import java.util.ArrayList;
import java.util.LinkedList;

import ru.daigr.snake.engine.GameEngine;
import ru.daigr.snake.primitives.Cell;
import ru.daigr.snake.primitives.PlayingCourt;
import ru.daigr.snake.primitives.result.Result;

public class App {
	
	private ArrayList<String> codes = new ArrayList<>();

	public static void main(String[] args){
		
		GameEngine test = new GameEngine(1);
		Result ret = test.enterCommand("DR9");		
		ret = test.enterCommand("DR8");
		ret = test.enterCommand("DR7");
		ret = test.enterCommand("DR1");
		ret = test.enterCommand("DR2");
		ret = test.enterCommand("DR3");
		ret = test.enterCommand("DR4");
		ret = test.enterCommand("DR10");
		ret = test.enterCommand("DR9");
		ret = test.enterCommand("DR8");
		
		System.out.println("This cycle is finished");
	}
	
	public static LinkedList<Cell> initEat() {
		LinkedList<Cell> ret = new LinkedList<>();
		ret.add(new Cell('A',1));
		ret.add(new Cell('B',2));
		ret.add(new Cell('C',3));
		ret.add(new Cell('D',4));
		return ret;
	}
			
}
