import java.util.Scanner;
import Matrix.* ;

public class SPL{
    Matrix matrix;
    static Scanner scan = new Scanner(System.in);
    public double SPLGauss(){

        int baris, kolom ;
        System.out.print("Masukkan jumlah baris : ") ;
        baris = scan.nextInt() ;
        System.out.print("Masukkan jumlah Kolom : ") ;
        kolom = scan.nextInt() ;

        Matrix matrix = new Matrix(baris, kolom);
        matrix.readMatrix(scan);

        //looping utama
        for (int k = 0 ; k < matrix.getRow() ; k++) {

            // mencari pivot row (supaya diagonal ga 0)
            int max = k;
            for (int i = k + 1; i < matrix.getRow(); i++) 
                if (Math.abs(matrix.ELMT(i, k)) > Math.abs(matrix.ELMT(max, k))) 
                    max = i; 

            // membuat baris temporary utk menyimpan baris k
            double[] temp = new double[matrix.getCol()] ;
            for (int m = 0 ; m < matrix.getCol() ; m++) {
                temp[m] = matrix.ELMT(k, m) ;
            }

            // membuat baris temporary utk menyimpan baris max
            double[] temp2 = new double[matrix.getCol()] ;
            for (int m = 0 ; m < matrix.getCol() ; m++) {
                temp2[m] = matrix.ELMT(max, m) ;
            }          

            // menukar baris max dengan baris paling atas
            matrix.setRow(k, temp2);
            matrix.setRow(max, temp);
            
            double pengurang ;
            for (int i = k + 1; i < matrix.getRow(); i++) 
            {
                double faktor = matrix.ELMT(i, k) / matrix.ELMT(k, k);
                for (int j = k; j < matrix.getCol(); j++)  {
                    pengurang = faktor * matrix.ELMT(k, j) ;
                    matrix.setELMT(i, j, pengurang);
                }
            }
        
        
        }

        return 1.23d ;
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

    public void Main (String args []) {
        SPLGauss() ;
    }
}