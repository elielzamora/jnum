package com.elielzamora.jnum;

public class Approx {
	public static double trueError(double trueValue, double approx){
		return trueValue - approx;
	}
	public static double relError(double trueValue, double approx){
		return trueError(trueValue, approx)/trueValue;
	}
	public static double approxError(double prev, double current){
		return current-prev;
	}
	public static double approxRelError(double prev, double current){
		return approxError(prev, current)/current;
	}
	public static boolean within(double error1, double error2, int sigdig){
		if(Math.abs(relError(error1, error2)) < (0.5)*Math.pow(10, -sigdig))
			return true;
		else return false;
	}
	public static void main(String[] args) {
		
	}

}
