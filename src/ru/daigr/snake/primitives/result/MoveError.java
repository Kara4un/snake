package ru.daigr.snake.primitives.result;

public enum MoveError implements IError {
	
	COLLISION(100, "Вы столкнулись с хвостом"),
	NO_CODE(101, "Код/коды не приняты"),
	NOT_NEAR(102, "Голова змеи не граничит с клеткой соответствующей введенному коду");
	
	private String desc = "";
	private int code = 0;
	
	private MoveError(int aCode, String aDesc){
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
