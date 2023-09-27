import java.util.Scanner;

public class SPL{
    static Scanner scan = new Scanner(System.in);
    public static void SPLGauss(String outputFileName, boolean fileMethod, Scanner txtReader){
        int jumlahVar, jumlahPers;

        // Read from file
        if(fileMethod){
            jumlahVar = txtReader.nextInt();
            jumlahPers = txtReader.nextInt();
        }

        // Scan from manual input
        else{
            System.out.println("Sistem Persamaan berapa variabel: ");
            jumlahVar = scan.nextInt();

            System.out.println("Berapa persamaan: ");
            jumlahPers = scan.nextInt();
        }

        // Declare the matrix
        Matrix matrix = new Matrix(jumlahPers, jumlahVar+1) ; // ditambah 1 karena ada kolom hasil

        // Check if the matrix is valid
        if(jumlahPers <= 0 || jumlahVar <= 0)
            Main.wrongInput();
        else{
            // Read from file
            if(fileMethod)
                matrix.readMatrix(txtReader);
            // Scan from manual input
            else{
                matrix.readSPL();
                System.out.printf("SPL yang anda input : \n") ;
                matrix.displayMatrix();
            }
        }
        //looping utama
        for(int k = 0; k < matrix.getRow(); k++){

            // mencari pivot row (supaya diagonal ga 0)
            int max = k;
            for(int i = k + 1; i < matrix.getRow(); i++)
                if(Math.abs(matrix.ELMT(i, k)) > Math.abs(matrix.ELMT(max, k))) 
                    max = i; 

            // membuat baris temporary  utk menyimpan baris k
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

            // Membuat kolom di bawah diagonal menjadi 0
            double pengurang;
            for(int i = k + 1; i < matrix.getRow(); i++){
                double faktor = matrix.ELMT(i, k) / matrix.ELMT(k, k);
                for(int j = k; j < matrix.getCol(); j++) {
                    pengurang = faktor * matrix.ELMT(k, j);
                    matrix.setELMT(i, j, matrix.ELMT(i, j) - pengurang);
                }
            }        
        }
        System.out.printf("Hasil OBE Gauss : \n") ;
        matrix.displayMatrix();
        
        
        // mencari solusi dari variabel
        double[] solution = new double[matrix.getRow()];
        for (int i = matrix.getRow() - 1; i >= 0; i--) 
        {
            double sum = 0.0;
            for (int j = i + 1; j < matrix.getRow(); j++) 
                sum += matrix.ELMT(i, j) * solution[j];
            solution[i] = (matrix.ELMT(i, matrix.getCol()-1) - sum) / matrix.ELMT(i, i);
        }   

        // mengecek jenis hasil gauss (unik, banyak, tidak ada hasil)
        int check  = 0 ; // 0 = unik, 1 = banyak, 2 = tidak ada
        for (int i = 0 ; i < matrix.getRow() ; i++) {
            for (int j = 0 ; j < matrix.getCol() ; j++) {
                if (j == matrix.getCol()-1) {
                    if (matrix.ELMT(i, j) == 0) {
                        check = 1 ;
                    }
                    else check = 2 ;
                }
                else {
                    if (matrix.ELMT(i, j) != 0) {
                        break ;
                    }
                }   
            }
        }
        if(check == 0){
            for (int i = 0 ; i < solution.length ; i++) {
                System.out.printf("X%d = %.3f " , i+1, solution[i]) ;
            }     
            System.out.print("\n") ;
        }
        else if(check == 1){
            System.out.printf("SPL memiliki solusi banyak.\n") ;
        }
        else{
            System.out.printf("SPL tidak memiliki solusi!\n");
        }
    }

    public static void SPLGaussJordan(String outputFileName, boolean fileMethod, Scanner txtReader){
    }

    public static void SPLInverse(){
        System.out.printf("Masukkan SPL tanpa hasil! \n") ;
        System.out.printf("Masukkan jumlah baris : ") ;
        int baris = scan.nextInt() ;
        System.out.printf("Masukkan jumlah kolom : ") ;  
        int kolom = scan.nextInt() ;
        Matrix matrix = new Matrix(baris, kolom) ;
        matrix.readMatrix(scan);

        System.out.printf("Masukkan hasil! \n") ;
        Matrix hasil = new Matrix(kolom, 1) ; 
        hasil.readMatrix(scan);                   

        // mengecek apakah matriks dapat di inverse
        if (Matrix.DetEkspansiKofaktor(matrix) == 0) {
            System.out.printf("SPL tidak bisa diselesaikan dengan metode inverse!\n") ;
        }
        else {
        // meng-invers matriks spl
            matrix = Matrix.InverseUsingAdjoint(matrix) ;

            Matrix m2 = matrix.perkalianMatrix(matrix , hasil) ; // hasil kali matrix spl dengan hasilnya
            for (int i = 0 ; i < m2.getRow() ; i++) {
                System.out.printf("X%d = %.3f " , i+1 , m2.ELMT(i, 0)) ;
            }
        }
    }

    public static void SPLCramer(){
    }
}