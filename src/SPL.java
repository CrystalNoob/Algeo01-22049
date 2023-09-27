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
        Gauss(matrix) ;
        System.out.printf("Hasil OBE Gauss : \n") ;
        matrix.displayMatrix();
        

        // mengecek jenis hasil gauss (unik, banyak, tidak ada hasil)
        int check  = 0 ; // 0 = unik, 1 = banyak, 2 = tidak ada
        int count = 0 ;
        for (int i = 0 ; i < matrix.getCol()-1; i++) {
            if (matrix.ELMT(jumlahPers-1, i) > 0 || matrix.ELMT(jumlahPers-1, i) < 0) {
                count += 1 ;
            }
        }
        if (count >= 2) {
            check = 1 ;
        }
        else {
            if (count == 0) {
                if (matrix.ELMT(jumlahPers-1, matrix.getCol()-1) != 0) {
                    check = 2 ;
                }
            }
        }
        
        if(check == 0){
            // mencari solusi dari variabel jika solusi unik
            double[] solution = new double[matrix.getCol()-1];
            for (int i = matrix.getCol()-1 - 1; i >= 0; i--) {
                double sum = 0.0;
                for (int j = i + 1; j < matrix.getCol() - 1; j++) 
                    sum += matrix.ELMT(i, j) * solution[j];
                solution[i] = (matrix.ELMT(i, matrix.getCol()-1) - sum) / matrix.ELMT(i, i);
            }   
            
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
        if (Matrix.DetEkspansiKofaktor(matrix) == 0 && baris != kolom) {
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

    public static void Gauss(Matrix matrix) {
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

            // membagi baris agar dpt leading one
            for (int n = 0 ; n < matrix.getCol() ; n++) {
                if (matrix.ELMT(k, n) != 0) {
                    matrix.barisBagi(k, matrix.ELMT(k, n));
                    break ;
                }
            }
        
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


    }

}