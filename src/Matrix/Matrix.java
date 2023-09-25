package Matrix;

import java.util.Scanner;

public class Matrix{
    int row, col;
    double[][] matrix;
    static Scanner sc;

    public Matrix(int row, int col){
        matrix = new double[row][col];
        this.row = row;
        this.col = col;
    }

    public Matrix(double[][] matrix){
        row = matrix.length;
        col = matrix[0].length;
        this.matrix = new double[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public double ELMT(int i, int j){
        return this.matrix[i][j];
    }

    public void setRow(){
        this.row = row;
    }

    public void setCol(){
        this.col = col;
    }

    public void setELMT(int i, int j, double n){
        this.matrix[i][j] = n;
    }
   
    public void readMatrix(Scanner scan){ 
        double value;
        for(int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                System.out.printf("Baris %d, kolom %d: ", i, j);
                value = scan.nextDouble();
                setELMT(i, j, value);
                System.out.println();
            }
        }
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

    public double[][] InvertGauss(){
        return this.matrix;
    }

    public double[][] InvertGaussJordan(){
        return this.matrix;
    }

    public double[][] InvertInverse(){
        return this.matrix;
    }

    public double[][] InvertCramer(){
        return this.matrix;
    }

    // Primitif

    public double Kofaktor(double[][] matrix){
        return 1.23d;
    }

    public void nPenguranganMatrix (double[][] matrix, int idxDikurang, int idxPengurang, int brpkali){
        for (int j = 0; j < brpkali; j++) {
            for (int i = 0; i < matrix[0].length; i++) {
                setELMT(idxDikurang, i, ELMT(idxPengurang, i)) ;
            }
        }
    }

    public void nPenjumlahanMatrix (int idxDitambah, int idxPenambah, int brpkali){
        while(brpkali > 0){
            for (int i = 0; i < getCol() ; i++){
                setELMT(idxDitambah, i, ELMT(idxPenambah, i)) ;
            }
            brpkali--;
        }
    }

    public void barisKali (int idxBaris, double x){
        for (int j = 0 ; j < getCol() ; j++){
            setELMT(idxBaris, j, ELMT(idxBaris, j) * x) ;
        }
    }

    public void barisBagi (int idxBaris, double x){
        for (int j = 0; j < getCol(); j++){
            setELMT(idxBaris, j, ELMT(idxBaris, j)/x);
        }
    }

    public static void main(String args[]){

    }
}