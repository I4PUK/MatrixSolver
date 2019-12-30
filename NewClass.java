//============================================================================
// Name        : SMatrix.java
// Author      : Sergey Mishukov
// Version     : 1.0
// Copyright   : Copyright (c) 2018, Sergey Mishukov <Mis.Serj@yandex.ru.ru>
// Description : Calculate height of the circle's segment, with using length
// of radius(r) and chord(a).
//============================================================================
class SMatrix {
//Matrix's size 
	private int n;
	// object value
	private int[][] Matrix;

//set size and create matrix 
//Определение размера и создание матрицы 
	void setBase(int n) {
		this.n = n;
		Matrix = new int[n][n];
	}

//generate random values 
//генерация случайных значений в матрице 
	void setRND() {
		int i, j;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				Matrix[i][j] = (int) (Math.random() * 10);
	}

//Filling same nums 
//Заполнение одинаковыми числами 
	void setVal(int a) {
		int i, j;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				Matrix[i][j] = a;
	}

//Filling with a sequence of nums 
//Заполнение последовательностью цифр 
	void setNums() {
		int i, j;
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++)
				Matrix[i][j] = (i * n + j) % 9 + 1;
	}

//Unit matrix 
//Единичная матрица 
	void setE() {
		int i;
		setVal(0);
		for (i = 0; i < n; i++)
			Matrix[i][i] = 1;
	}

//show Matrix 
//Показ(отображение) матрицы 
	void show() {
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				System.out.print(Matrix[i][j] + (j == n - 1 ? "\n" : ""));
			}
		}
	}

//Matrix's determinate 
//Определитель матрицы 
	int det() {
		int D = 0;
		switch (n) {
//Matrix's size 1x1 
		case 1:
			D = Matrix[0][0];
			break;
//Matrix's size 2x2 
		case 2:
			D = Matrix[0][0] * Matrix[1][1] - Matrix[0][1] * Matrix[1][0];
			break;
//Matrix's size 3x3 
		case 3:
			int i, j, A, B;
			for (j = 0; j < n; j++) {
				A = 1;
				B = 1;
				for (i = 0; i < n; i++) {
					A *= Matrix[i][(j + i) % n];
					B *= Matrix[n - i - 1][(j + i) % n];
				}
				D += A - B;
			}
			break;
//another size 
		default:
			int k, sign = 1;
			SMatrix m;
			for (k = 0; k < n; k++) {
				m = new SMatrix();
				m.setBase(n - 1);
				for (i = 1; i < n; i++) {
					for (j = 0; j < k; j++)
						m.Matrix[i - 1][j] = Matrix[i][j];
					for (j = k + 1; j < n; j++)
						m.Matrix[i - 1][j - 1] = Matrix[i][j];
				}
				D += sign * Matrix[0][k] * m.det();
				sign *= (-1);
			}
		}
		return D;
	}

//transposition 
//транспонирование 
	void trans() {
		int i, j, s;
		for (i = 0; i < n; i++)
			for (j = i + 1; j < n; j++) {
				s = Matrix[i][j];
				Matrix[i][j] = Matrix[j][i];
				Matrix[j][i] = s;
			}
	}
}

class Main {

	public static void main(String[] args) {
		SMatrix obj = new SMatrix();
		obj.setBase(3);
		obj.setNums();
		System.out.println("Исходная матрица:");
		obj.show();
		System.out.println("После транспорнирования:");
		obj.trans();
		obj.show();
		System.out.println("Определитель матрицы: " + obj.det());
	}
}