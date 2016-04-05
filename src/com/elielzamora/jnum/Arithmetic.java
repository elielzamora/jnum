package com.elielzamora.jnum;

public class Arithmetic {
	public static int gcd(int a, int b){
		assert a >= 0 && b >= 0;
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	/** a^b */
	public static int exp(int a, int b){
		if(b == 1) return a;
		if(b % 2 == 1) return a * exp(a, b-1);
		else return exp(a, b/2) * exp(a, b/2);
	}
	public static int multiply(int a, int b){
		if(a < b) return multiply(b, a);
		if(b == 1) return a;
		if(b % 2 == 1) return a + multiply(a, b-1);
		else return multiply(a, b/2) + multiply(a, b/2);
		//return 2 * mulitply(a,b/2);
	}
	public static void main(String[] args) {
		System.out.println(gcd(9,81));
		System.out.println(exp(5, 7));
		System.out.println(multiply(3, 7));
	}

}
