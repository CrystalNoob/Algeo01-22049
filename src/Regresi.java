import java.util.Scanner;

public class Regresi{
    

    public static void RegresiLinear()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan jumlah peubah x: ");
        int n = sc.nextInt();
        System.out.println("Masukkan jumlah sampel: ");
        int m = sc.nextInt();

        Matrix peubahANDsampel = new Matrix(m, n+1);
        Matrix xTaksir = new Matrix(1, n);
        Matrix mHasil = new Matrix(peubahANDsampel.getCol(), peubahANDsampel.getCol()+1);

        System.out.println("Silakan masukkan sampel di kolom terakhir!");
        System.out.println("Masukkan peubah x dan sampel:");
        peubahANDsampel.readMatrix(sc);
        System.out.println("Masukkan nilai x yang ingin ditaksir: ");
        xTaksir.readMatrix(sc);

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
        SPL.Gauss(mHasil);
        mHasil.displayMatrix();
    }
    public static void main (String[] args)
    {
        RegresiLinear();
    }
}