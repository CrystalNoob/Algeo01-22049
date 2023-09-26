import java.util.Scanner;

public class SPL{
    static Scanner scan = new Scanner(System.in);

    public static void SPLGauss(String outputFileName, boolean fileMethod, Scanner txtReader){
        int baris, kolom;

        // Read from file
        if(fileMethod){
            baris = txtReader.nextInt();
            kolom = txtReader.nextInt();
        }
        // Scan from manual input
        else{
            System.out.print("Masukkan jumlah baris : ");
            baris = scan.nextInt();
            System.out.print("Masukkan jumlah Kolom : ");
            kolom = scan.nextInt();
        }


        // Check if the matrix is valid
        if(baris <= 0 || kolom <= 0)
            Main.wrongInput();
        else{
            // Declare the matrix
            Matrix matrix = new Matrix(baris, kolom);
            
            // Read from file
            if(fileMethod)
                matrix.readMatrix(txtReader);
            // Scan from manual input
            else
                matrix.readMatrix(scan);

            //looping utama
            for(int k = 0; k < matrix.getRow(); k++){

                // mencari pivot row (supaya diagonal ga 0)
                int max = k;
                for(int i = k + 1; i < matrix.getRow(); i++) 
                    if(Math.abs(matrix.ELMT(i, k)) > Math.abs(matrix.ELMT(max, k))) 
                        max = i; 

                // membuat baris temporary utk menyimpan baris k
                double[] temp = new double[matrix.getCol()];
                for(int m = 0; m < matrix.getCol(); m++){
                    temp[m] = matrix.ELMT(k, m);
                }

                // membuat baris temporary utk menyimpan baris max
                double[] temp2 = new double[matrix.getCol()];
                for(int m = 0; m < matrix.getCol(); m++){
                    temp2[m] = matrix.ELMT(max, m);
                }          

                // menukar baris max dengan baris paling atas
                matrix.setRow(k, temp2);
                matrix.setRow(max, temp);

                double pengurang;
                for(int i = k + 1; i < matrix.getRow(); i++){
                    double faktor = matrix.ELMT(i, k) / matrix.ELMT(k, k);
                    for(int j = k; j < matrix.getCol(); j++){
                        pengurang = faktor * matrix.ELMT(k, j);
                        matrix.setELMT(i, j, pengurang);
                    }
                }
            }
            matrix.displayMatrix();
        }
    }

    public static void SPLGaussJordan(){
    }

    public static void SPLInverse(){
    }

    public static void SPLCramer(){
    }
}