package Matrix;

import java.util.Scanner;

public class Matrix{
    static double[][] matrix;
    static Scanner sc;

    static int getRow(){
        return 5;
    }

    static int getCol(){
        return 5;
    }

    public static double[][] readMatrix(){
        sc = new Scanner(System.in);

        System.out.print("Masukkan baris: ");
        int baris = sc.nextInt();

        System.out.print("Masukkan kolom: ");
        int kolom = sc.nextInt();

        matrix = new double[baris][kolom];
        for (int i = 0; i < baris; i++){
            for (int j = 0; j < kolom; j++){
                System.out.print("Kolom " + (i+1) + ", Baris " + (j+1) + ": ");
                matrix[i][j] = sc.nextInt();
            }    
        }
        return matrix;
    }

    public static double[][] readSPL(){
        System.out.println("Sistem Persamaan berapa variabel: ");
        sc = new Scanner(System.in);
        int jumlahVar = sc.nextInt();

        System.out.println("Berapa persamaan: ");
        int jumlahPers = sc.nextInt();

        double[][] matrix = new double[jumlahPers][jumlahVar+1];

        for (int i = 0; i < jumlahPers; i++){ // idx 0 itu persamaan pertama terus ngurut terus
            System.out.print("Persamaan ke-" + i + "\n");
            for (int j = 0; j <= jumlahVar; j++){ // idx 0 itu x1 sampe idx jumlahVar = hasil
                if (j != jumlahVar){
                    System.out.print("Masukkan koefisien variabel ke-" + j + ": ");
                    matrix[i][j] = sc.nextInt();
                }
                else{
                    System.out.print("Masukkan hasil persamaan: ");
                    matrix[i][j] = sc.nextInt();
                }
            }
        }
        return matrix;
    }

    public static double SPLGauss(){
        double[][] matrix = readMatrix() ;

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[i].length ; j++) {
                if (i == j) {
                    barisBagi(matrix, i, matrix[i][j]);
                }
            }
        }

        for (int i = 0 ; i < matrix.length ; i++) {
            for (int j = 0 ; j < matrix[i].length ; j++) {
                System.out.print(matrix[i][j]) ;
            }
        }

        return 1.23d;
    }

    public static double SPLGaussJordan(){
        return 1.23d;
    }

    public static double SPLInverse(){
        return 1.23d;
    }

    public static double SPLCramer(){
        return 1.23d;
    }

    public static double DetGauss(){
        return 1.23d;
    }

    public static double DetGaussJordan(){
        return 1.23d;
    }

    public static double DetInverse(){
        return 1.23d;
    }

    public static double DetEkspansiKofaktor(){
        return 1.23d;
    }

    public static double [][] InvertGauss(){
        return matrix;
    }

    public static double [][] InvertGaussJordan(){
        return matrix;
    }

    public static double [][] InvertInverse(){
        return matrix;
    }

    public static double [][] InvertCramer(){
        return matrix;
    }

    public static double IP(){
        return 1.23d;
    }

    public static double IBS(){
        return 1.23d;
    }

    public static double MLR(){
        return 1.23d;
    }

    // Primitif

    public static double Kofaktor(double[][] matrix) {
        return 1.23d ;
    }

    public static void nPenguranganMatrix (double[][] matrix, int idxDikurang, int idxPengurang, int brpkali) {
        for (int j = 0 ; j < brpkali ; j++) {
            for (int i = 0 ; i < matrix[0].length ; i++) {
                matrix[idxDikurang][i] -= matrix[idxPengurang][i] ;
            }
        }
    }

    public static void nPenjumlahanMatrix (double[][] matrix, int idxDitambah, int idxPenambah, int brpkali) {
        for (int j = 0 ; j < brpkali ; j++) {
            for (int i = 0 ; i < matrix[0].length ; i++) {
                matrix[idxDitambah][i] -= matrix[idxPenambah][i] ;
            }
        }
    }

    public static void barisKali (double[][] matrix, int idxBaris, double x) {
        for (int i = 0 ; i < matrix[0].length ; i++) {
            matrix[idxBaris][i] *= x ;
        }
    }

    public static void barisBagi (double[][] matrix, int idxBaris, double x) {
        for (int i = 0 ; i < matrix[0].length ; i++) {
            matrix[idxBaris][i] /= x ;
        }
    }

    public static void main(String args[]){
        SPLGauss() ;
    }


}

