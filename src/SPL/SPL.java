package SPL;

import java.util.Scanner;
import Matrix.* ;

public class SPL{
    Matrix m1;
    static Scanner scan = new Scanner(System.in);
    public double SPLGauss(){

        Matrix matrix = new Matrix(3,3);
        m1 = new Matrix(3 , 3);
        m1.readMatrix(scan);

        for(int i = 0; i < matrix.getRow(); i++) {
            for(int j = 0; j < matrix.getCol(); j++){
                if(i == j){
                    matrix.barisBagi(i, matrix.ELMT(i, j));
                }
            }
        }

        for (int i = 0; i < matrix.getRow(); i++){
            for (int j = 0; j < matrix.getCol(); j++){
                System.out.print(matrix.ELMT(i,j));
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
}