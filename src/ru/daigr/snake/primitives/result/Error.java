package ru.daigr.snake.primitives.result;

public enum Error implements IError {
	
	FORMAT(200, "Неверный формат команды"),
	WRONG_ID(201, "Игры с указанным id не существует");
	
	private String desc = "";
	private int code = 0;
	
	private Error(int aCode, String aDesc){
		code = aCode;
		desc = aDesc;
	}

	@Override
	public int getCode() { 
		return code;
	}

	@Override
	public String getDesc() {
		return desc;
	}

}
