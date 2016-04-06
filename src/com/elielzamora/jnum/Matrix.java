package com.elielzamora.jnum;

public class Matrix {
	public double [][] matrix;
	public Matrix (double [][] mat){matrix = mat;}
	public static int m(double[][] A){return A.length;}
	public static int n(double[][] A){return A[0].length;}
	public static double[] row(double[][] A, int i){
		assert i < n(A);
		return A[i];
	}
	public static double[] col(double[][] A, int i){
		assert i < m(A);
		double [] col = new double[m(A)];
		for(int j = 0; j < m(A); j++) col[j] = A[i][j];
		return col;
	}
	public static double[][] add(double[][] A, double[][] B)
			throws DimentionsIncompatibleException{
		if(n(A) != n(B) || m(A) != m(B)) throw new DimentionsIncompatibleException();
		double[][] C = new double[m(A)][n(A)];
		for(int i = 0; i < m(A); i++){
			for(int j = 0; j < n(A); j++){
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}
	public static double[][] sub(double[][] A, double[][] B)
			throws DimentionsIncompatibleException{
		if(n(A) != n(B) || m(A) != m(B)) throw new DimentionsIncompatibleException();
		double[][] C = new double[m(A)][n(A)];
		for(int i = 0; i < m(A); i++){
			for(int j = 0; j < n(A); j++){
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		return C;
	}
	/** transpose matrix */
	public static double[][] T(double[][] A){
		double[][] ATrans = new double[n(A)][m(A)];
		for(int i=0; i < m(A); i++){
			for(int j=0; j < n(A); j++){
				ATrans[j][i] = A[i][j];
			}
		}
		return ATrans;
	}
	public static double[][] mult(double[][] A, double[][] B)
		throws DimentionsIncompatibleException{
		if(n(A) != m(B)) throw new DimentionsIncompatibleException();
		double[][] C = new double[m(A)][n(B)];
		for(int i = 0; i < C.length; i ++){
			for(int j = 0; j < C[0].length; j++){
				for(int k = 0; k < n(A); k++){
					C[i][j] += A[i][k] * A[k][j];
				}
			}
		}
		return C;
	}
	public static double[][] SqrMatMult(double[][] A, double[][] B)
			throws DimentionsIncompatibleException{
		if(n(A) != n(B) || m(A) != m(B) || n(A) != m(B)) throw new DimentionsIncompatibleException();
		//check if 2n matrix
		if(n(A) ==1 ) return new double[][] {{A[0][0]*B[0][0]}};
		/*if(n(A) == 2) {
			return new double[][]{
				{A[0][0]*B[0][0]+ A[0][1]*B[1][0], A[0][0]*B[0][1]+ A[0][1]*B[1][1]},
				{A[1][0]*B[0][0]+ A[1][1]*B[1][0], A[1][0]*B[0][1]+ A[1][1]*B[1][1]}
			};
		}*/
		int half = m(A)/2; // n(A)/2 = m(B)/2 ...
		double[][]  A1 = new double[half][half],
					A2 = new double[half][half],
					A3 = new double[half][half],
					A4 = new double[half][half];
		double[][]  B1 = new double[half][half],
					B2 = new double[half][half],
					B3 = new double[half][half],
					B4 = new double[half][half];
		//i\j
		//	[ c1 c2 ]
		//	[ c3 c4 ]
		for(int i = 0; i < m(A); i++){
			for(int j = 0; j < n(A); j++){
				if(i < half && j < half){
					A[i][j] = A[i][j];
					B[i][j] = B[i][j];
				}else if (i < half && half <= j){
					A[i][j-half] = A[i][j];
					B[i][j-half] = B[i][j];
				}else if (half <= i && j < half){
					A[i-half][j] = A[i][j];
					B[i-half][j] = B[i][j];
				}else{
					A[i - half][j - half] = A[i][j];
					B[i - half][j - half] = B[i][j];
				}
			}
		}
		double[][] C = new double[m(A)][n(A)];
		double[][] 	C1 = add(mult(A1, B1),mult(A2, B3)),
					C2 = add(mult(A1, B2),mult(A2, B4)),
					C3 = add(mult(A3, B1),mult(A4, B3)),
					C4 = add(mult(A3, B2),mult(A4, B4));
		
		for(int i = 0; i < m(A); i++){
			for(int j = 0; j < n(A); j++){
				if(i < half && j < half){
					C[i][j] = C1[i][j];
				}else if (i < half && half <= j){
					C[i][j] = C2[i][j];
				}else if (half <= i && j < half){
					C[i][j] = C3[i][j];
				}else{
					C[i][j] = C4[i][j];
				}
			}
		}
		return C;
	}
	public static double[][] strassen(double[][] A, double[][] B) 
		throws DimentionsIncompatibleException{
		assert n(A) == n(B) && m(A) == m(B) && squareMatrix(A);
		if(n(A) == 1) return new double[][]{{A[0][0] * B[0][0]}};
		assert n(A) % 2 == 0 && m(A) % 2 == 0;
		double[][] A00 = subMatrix(A,0,0,m(A)/2, n(A)/2);
		double[][] A10 = subMatrix(A,m(A)/2, 0, m(A), n(A)/2);
		double[][] A01 = subMatrix(A,0,n(A)/2,m(A)/2, n(A));
		double[][] A11 = subMatrix(A,m(A)/2, n(A)/2, m(A), n(A));
		double[][] B00 = subMatrix(B,0,0,m(A)/2, n(A)/2);
		double[][] B10 = subMatrix(B,m(A)/2, 0, m(A), n(A)/2);
		double[][] B01 = subMatrix(B,0,n(A)/2,m(A)/2, n(A));
		double[][] B11 = subMatrix(B,m(A)/2, n(A)/2, m(A), n(A));
		double[][] M1 = strassen(add(A00,A11),add(B00,B11));
		double[][] M2 = strassen(add(A10,A11), B00);
		double[][] M3 = strassen(A00, sub(B01,B11));
		double[][] M4 = strassen(A11, sub(B10,B00));
		double[][] M5 = strassen(add(A00, A01), B11);
		double[][] M6 = strassen(sub(A10, A00),add(B00, B01));
		double[][] M7 = strassen(sub(A01,A11),add(B10,B11));
		double[][] C00 = sub(add(M1,M4),add(M5,M7));
		double[][] C01 = add(M3,M5);
		double[][] C10 = add(M2,M4);
		double[][] C11 = add(sub(M1,M2),add(M3,M6));
		return mergeMatrix(C00,C01,C10,C11);
	}
	public static boolean squareMatrix(double[][] A){
		return m(A) == n(A);
	}
	public static double[][] subMatrix(double[][] A, int m0, int n0, int m1, int n1){
		assert m0 < m1 && n0 < n1 && 0 <= m0 && 0 <= n0 && m1 <= m(A) && n1 <= n(A);
		int dm = m1-m0;
		int dn = n1-n0;
		double[][] a = new double[dm][dn];
		for(int i = m0; i < m1; i++){
			for(int j = n0; j < n1; j++){
				a[i-m0][j-n0] = A[i][j];
			}
		}
		return a;
	}
	public static double[][] mergeMatrix(	double[][] a00,
											double[][] a01,
											double[][] a10,
											double[][] a11){
		assert m(a00) == m(a01) && n(a00) == n(a10) && m(a10) == m(a11) && n(a01) == n(a11);
		int m = m(a00) + m(a10);
		int n = n(a00) + n(a01);
		double[][] A = new double[m][n];
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(i < m(a00) && j < n(a00)) A[i][j] = a00[i][j];
				else if(i < m(a00) && n(a00) <= j) A[i][j] = a01[i][j-n(a00)];
				else if(m(a00) <= i	&& j < n(a00)) A[i][j] = a10[i-m(a00)][j];
				else A[i][j] = a11[i-m(a00)][j-n(a00)];
			}
		}
		return null;
	}
	public static int cofactor(int m, int n){
		return (n+m) % 2 == 0? 1: -1;
	}
	public static double[][] minor(double[][] A, int m, int n){
		assert squareMatrix(A);
		double[][] a = new double[m(A)-1][n(A)-1];
		for(int i = 0; i < n(a); i++){
			for (int j = 0; j < n(a); j++){
				if(i < m && j < n) a[i][j] = A[i][j];
				else if(i < m && n <= j) a[i][j] = A[i][j+1];
				else if(m <= i && j < n) a[i][j] = A[i+1][j];
				else a[i][j] = A[i+1][j+1];
			}
		}
		return a;
	}
	public static double det(double[][] A){
		assert squareMatrix(A);
		//return
		if(n(A) == 1) return A[0][0];
		if(n(A) == 2) return A[0][0]*A[1][1] - A[0][1]*A[1][0];
		double determ = 0;
		int cofactorRow = 0;
		//cofactor expansion from first row
		for(int i = 0; i < n(A); i++){
			determ += A[0][i] * cofactor(0,i) * det(minor(A,i,0));
		}
		return determ;//change this
	}
	public static void display(double[][] A){
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < n(A); j++){
				double k = A[i][j];
				System.out.print(k + "\t\t\t");
			}
			System.out.print('\n');
		}
	}
	public static void main(String[] args) throws DimentionsIncompatibleException {
		double[][] matA	 = {{3,4},
							{5,1}};
		double[][] matA1 = {{2,0,0},
							{0,3,4},
							{0,5,1}};
		double[][] mat = {{-3,0.25,4,-4},
						{-5, 2, -20, 3},
						{6,-180, 7,3},
						{2,1,-2, 5}};
		double[][] matB =  {{-1,3},
							{2,-8}};
		//System.out.println(minor(matA,1,1)[0][0]);
		System.out.println(det(matA));
		System.out.println(det(matA1));
		System.out.println(det(mat));
		System.out.println(T(matA)[0][1]); // 5
		//System.out.println(subMatrix(mat, m(mat)/2,
		//n(mat)/2, m(mat), n(mat))[0][1]);
		display(strassen(new double[][]{{4}}, new double[][]{{6}}));
		try{
			display(strassen(matA,matA));
		}catch(Exception e){
			e.printStackTrace();
		}//display(strassen(mat,mat));
	}

}
