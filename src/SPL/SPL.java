package SPL;

import java.util.Scanner;

public class SPL{
    static Scanner input = new Scanner(System.in);
    public static double SPLGauss(){
        double[][] matrix = Matrix.readMatrix();

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++){
                if(i == j){
                    barisBagi(matrix, i, matrix[i][j]);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]);
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