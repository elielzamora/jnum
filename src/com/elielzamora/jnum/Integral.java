package com.elielzamora.jnum;

public class Integral {
	public static double Integral(Function f, int a, int b){
		
		return 0;
	}
	public static double rectangle(Function f, int a, int b, int n){
		double h = (b-a)/n; //step size
		double integral = 0;
		for(int i = 0; i < n; i++){
			integral += f.val(a+(i+0.5)*h) * h;
		}
		return integral;
	}
	public static double Trapezoid(Function f, int a, int b, int n){
		double h = (b-a)/n; //step size
		double integral = 0;
		for(int i = 0; i < n; i++){
			integral += (f.val(a+i*h) + f.val(a+(i+1)*h)) * (h/2);
		}
		return integral;
	}
	public static double SimpsonThird(Function f, int a, int b, int n){
		if(n%2 != 0) return SimpsonThird (f, a, b, n+1);
		double h = (b-a)/n; //step size
		double integral = 0;
		for(int i = 0; i < n; i++){
			integral += (f.val(a+i*h) + f.val(a+(i+1)*h)) * (h/2);
		}
		return integral;
	}
	public static double SimpsonThreeEights(Function f, int a, int b, int n){
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
