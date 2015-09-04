package ru.daigr.snake.primitives.result;


public class Result {

	private boolean isOk = true;	
	private IError error = null;	
	
	public Result(boolean isOk, IError anError){
		this.isOk = isOk;
		error = anError;
	}
	
	public boolean isOk(){
		return isOk;
	}
	
	public IError getError(){
		return error;
	}
	
}
