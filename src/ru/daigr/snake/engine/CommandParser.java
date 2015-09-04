package ru.daigr.snake.engine;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
	
	public static ArrayList<String> parse (String src){
		ArrayList<String> ret = new ArrayList<>();
		ret.addAll(Arrays.asList(src.split(";")));
		return ret;
	}

}
