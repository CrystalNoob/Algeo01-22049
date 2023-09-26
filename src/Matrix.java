import java.util.Scanner;

public class Matrix{
    int row, col;
    double[][] matrix;
    static Scanner sc = new Scanner(System.in);

    // KONSTRUKTOR
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

    // SELEKTOR
    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public double ELMT(int i, int j){
        return this.matrix[i][j];
    }

    public void setRow(int row, double[] newRow){
        this.matrix[row] = newRow;
    }

    public void setELMT(int i, int j, double n){
        this.matrix[i][j] = n;
    }

    // FUNGSI DAN PROSEDUR
    public void readMatrix(Scanner scan){
        double value;
        for(int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                System.out.printf("Baris %d, kolom %d: ", i, j);
                value = scan.nextDouble();
                setELMT(i, j, value);
            }
        }
    }

    public void displayMatrix(){
        for (int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                if (j == getCol()-1){
                    System.out.printf("%f\n",ELMT(i, j));
                }
                else{
                    System.out.printf("%f ", ELMT(i, j));
                }
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

    public double[][] InvertGauss(){    // output diganti sementara biar gak error. Tipe output sebelumnya Matrix
        return matrix;
    }

    public double[][] InvertGaussJordan(){
        return matrix;
    }

    public double[][] InvertInverse(){
        return matrix;
    }

    public double[][] InvertCramer(){
        return matrix;
    }

    // Primitif

    public double Kofaktor(double[][] matrix){
        return 1.23d;
    }

    public void nPenguranganMatrix (double[][] matrix, int idxDikurang, int idxPengurang, int brpkali){
        for (int j = 0; j < brpkali; j++){
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

    public static Matrix transpose(Matrix m)
    {
        Matrix mTranspose = new Matrix(m.getCol(), m.getRow());

        for (int i = 0; i < m.getRow(); i++) 
        {
            for (int j = 0; j < m.getCol(); j++) 
            {
                mTranspose.matrix[j][i] = m.matrix[i][j];
            }
        }
        return mTranspose;
    }

    // PREDIKAT
    public boolean isIdentity()
    {
        boolean check = false;

        if (getRow() == getCol()) 
        {
            check = true;
            for (int i = 0; i < getRow(); i++) 
            {
                for (int j = 0; j < getCol(); j++) 
                {
                    if (i == j && ELMT(i, j) != 1)
                    {
                        check = false;
                    }
                    else
                    {
                        if (ELMT(i, j) != 0)
                        {
                            check = false;
                        }
                    }
                }
            }
        }
        return check;

    }


    public static void main(String args[]){
        Matrix x;
        x = new Matrix(2,3);
        x.readMatrix(sc);
        x.displayMatrix();
    }
}