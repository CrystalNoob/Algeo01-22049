package Matrix;

import java.util.Scanner;

public class Matrix{
    static int[][] matrix;
    static void readMatrix()
        {
            System.out.print("Masukkan baris: ");
            Scanner sc = new Scanner(System.in);
            int m = sc.nextInt();
            System.out.print("Masukkan kolom: ");
            int n = sc.nextInt();

            matrix = new int[m][n];
            for (int i = 0; i < m; i++) 
            {
                for (int j = 0; j < n; j++) 
                {
                    matrix[i][j] = sc.nextInt();
                }    
            }
        }

        static void readSPL()
        {
            System.out.println("Bentuk Sistem Persamaan Linier yang dapat digunakan:");
            System.out.println("1. Ax = b");
            System.out.println("2. Matriks augmented");
            System.out.println("3. ax1 + bx2 + cx3 = d");
        }
        
    public static void main(String[] args){
        readMatrix();
        System.out.println(matrix[0][1]);
    }
}