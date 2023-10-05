import java.util.Scanner;

public class Regresi{

    public static void RegresiLinear()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah peubah x: ");
        int n = sc.nextInt();
        System.out.print("Masukkan jumlah sampel: ");
        int m = sc.nextInt();

        Matrix peubahANDsampel = new Matrix(m, n+1);
        Matrix xTaksir = new Matrix(1, n);
        Matrix mHasil = new Matrix(peubahANDsampel.getCol(), peubahANDsampel.getCol()+1);

        System.out.println("Silakan masukkan sampel di kolom terakhir!");
        System.out.println("Masukkan peubah x dan sampel: ");
        peubahANDsampel.readMatrixAugmented(sc);
        System.out.println("");
        System.out.print("Masukkan nilai x yang ingin ditaksir: ");
        xTaksir.readMatrixAugmented(sc);

        for (int k = 0; k < mHasil.getRow(); k++) {
            for (int i = 0; i < mHasil.getCol(); i++) {
                if (k == 0) {
                    if (i == 0)
                    {
                        mHasil.matrix[0][0] = m;
                    }
                    else
                    {
                        mHasil.matrix[k][i] = 0;
                        for (int j = 0; j < peubahANDsampel.getRow(); j++) {
                            mHasil.matrix[k][i] += peubahANDsampel.matrix[j][i-1];
                        }
                    }
                }   
                else
                {
                    if (i == 0)
                    {
                        mHasil.matrix[k][i] = mHasil.matrix[i][k];
                    }
                    else
                    {
                        mHasil.matrix[k][i] = 0;
                        for (int j = 0; j < peubahANDsampel.getRow(); j++) {
                            mHasil.matrix[k][i] += peubahANDsampel.matrix[j][k-1]*peubahANDsampel.matrix[j][i-1];
                        }
                    }
                }    
            }
        }
        System.out.print("\n") ;
        SPL.GaussianElimination(mHasil);
        double[] solution = new double[mHasil.getCol()-1];
        for (int i = mHasil.getCol()-1 - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < mHasil.getCol() - 1; j++) 
                sum += mHasil.ELMT(i, j) * solution[j];
            solution[i] = (mHasil.ELMT(i, mHasil.getCol()-1) - sum) / mHasil.ELMT(i, i);
        }   
        displayRegresi(solution);
        System.out.print("\nf(");
        for (int i = 0; i < xTaksir.getCol(); i++) {
            if (i == xTaksir.getCol()-1) {
                System.out.printf("%f) = ",xTaksir.ELMT(0, i));
            }
            else
            {
                System.out.printf("%f,",xTaksir.ELMT(0, i));
            }
        }
        double value = solution[0];
        for (int j = 1; j < solution.length; j++) {
            value += solution[j] * xTaksir.ELMT(0, j-1);
        }
        System.out.printf("%f", value);
    }

    public static void displayRegresi(double[] arr)
    {
        if (arr.length == 2){
            System.out.printf("f(x) = %f ", arr[0]);
            if (arr[1] < 0) {
                System.out.printf("- %f X%d ", arr[1]*-1, 1);
            }
            else{
                System.out.printf("+ %f X%d ", arr[1], 1);
            }
        }
        else{
            System.out.print("f(x");
            for (int q = 1; q < arr.length; q++) {
                if (q == arr.length-1) {
                    System.out.printf("%d) = %f ", q, arr[0]);
                }
                else{
                    System.out.printf("%d,x", q);
                }
            }
            for (int i = 1; i < arr.length-1; i++) {
                if (arr[i] < 0) {
                    System.out.printf("- %f X%d ", arr[i]*-1, i);
                }
                else{
                    System.out.printf("+ %f X%d ", arr[i], i);
                }
            }
            if (arr[arr.length-1]<0) {
                System.out.printf("- %f X%d", arr[arr.length-1]*-1, arr.length-1);
            }            
            else{
                System.out.printf("+ %f X%d", arr[arr.length-1], arr.length-1);
            }
        }
    }

    public static void main (String[] args)
    {
        RegresiLinear();
    }
}