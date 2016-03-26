package com.elielzamora.jnum;

import java.math.BigDecimal;

public class Derivative {
	public double slope(double x1, double x2, double y1, double y2){
		return (y2-y1)/(x2-x1);
	}
	public static double prime(Function f, double x, double dx){
		return (f.val(x+dx) - f.val(x))/dx;
		//same as
		//return slope(x, x+dx, f.val(x), f.val(x+dx));
	}
	public static double prime(Function f, double x){return prime(f,x,0.000000001);}
	public static void main(String[] args) {
		Function sin = new Function(){
			public double val(double x){
				return Math.sin(x);
			}
		};
		for(double x: new double[]{
				-Math.PI,
				-Math.PI/2,
				0,
				Math.PI/2,
				Math.PI}){
			System.out.println(prime(sin, x ));
		
		}
	}
}
