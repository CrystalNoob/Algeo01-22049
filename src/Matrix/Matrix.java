package Matrix;

import java.util.Scanner;

public class Matrix{
    static int[][] matrix;
    static int[][] readMatrix()
        {
            Scanner sc = new Scanner(System.in);

            System.out.print("Masukkan baris: ");
            int baris = sc.nextInt();

            System.out.print("Masukkan kolom: ");
            int kolom = sc.nextInt();

            matrix = new int[baris][kolom];
            for (int i = 0; i < baris; i++) 
            {
                for (int j = 0; j < kolom; j++) 
                {
                    System.out.print("Kolom " + (i+1) + " ,Baris " + (j+1) + ": ") ;
                    matrix[i][j] = sc.nextInt();
                }    
            }
            return matrix ;
        }

        static int[][] readSPL()
        {
            System.out.println("Sistem Persamaan berapa variabel : ") ;
            Scanner sc = new Scanner(System.in) ;
            int jumlahVar = sc.nextInt() ;

            System.out.println("Berapa persamaan : ") ;
            int jumlahPers = sc.nextInt() ;

            int[][] matrix = new int[jumlahPers][jumlahVar+1] ;

            for (int i = 0 ; i < jumlahPers ; i++) { // idx 0 itu persamaan pertama terus ngurut terus
                System.out.print("Persamaan ke-" + i + "\n") ;
                for (int j = 0 ; j <= jumlahVar ; j++) { // idx 0 itu x1 sampe idx jumlahVar = hasil
                    if (j != jumlahVar) {
                        System.out.print("Masukkan koefisien variabel ke-" + j + ": ") ;
                        matrix[i][j] = sc.nextInt() ;
                    }
                    else {
                        System.out.print("Masukkan hasil persamaan : ") ;
                        matrix[i][j] = sc.nextInt() ;
                    }
                }
            }
            return matrix ;

        }
        
    public static void main(String[] args){
        int[][] m = readMatrix();
        System.out.println(m[0][1]);
    }
}