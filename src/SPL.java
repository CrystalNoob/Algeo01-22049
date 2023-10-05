import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SPL{
    static Scanner scan = new Scanner(System.in);
    public static void SPLGauss(String outputFileName, boolean fileMethod, Scanner txtReader){
        int jumlahVar = 0, jumlahPers = 0;
        // Read from file
        if(fileMethod){
            int[] arr = countRowCol(txtReader);
            jumlahVar = arr[1];
            jumlahPers = arr[0];
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
            if(fileMethod){
                double[][] matarr = readFile(txtReader);
                for(int i = 0; i < jumlahPers; ++i){
                    for(int j = 0; j < jumlahVar; ++j){
                        if(txtReader.hasNextInt()){
                            matrix.setELMT(i, j, matarr[i][j]);
                        }
                    }
                }
            }
            // Scan from manual input
            else{
                matrix.readSPL();
                System.out.printf("SPL yang anda input : \n") ;
                matrix.displayMatrix();
            }
        }
        GaussianElimination(matrix) ;
        System.out.printf("Hasil OBE Gauss : \n") ;
        matrix.displayMatrix();
        

        // mengecek jenis hasil gauss (unik, banyak, tidak ada hasil)
        int check  = 0 ; // 0 = unik, 1 = banyak, 2 = tidak ada
        int count = 0 ;
        if (matrix.getRow() < matrix.getCol()-1) { // persamaan < variabel
            for (int i = 0 ; i < matrix.getRow() ; i++) {
                for (int j = 0 ; j < matrix.getCol(); j++) {
                    if (j == matrix.getCol()-1) {
                        if (matrix.ELMT(i, j) != 0) {
                            check = 2 ; // tidak ada hasil 
                        }
                    }
                    else {
                        if (matrix.ELMT(i, j) != 0) break ;
                    }
                }
            }
            if (check == 0) check = 1 ; // Kalau persamaan < variable pasti dia solusi banyak
        }
        else if (matrix.getRow() == matrix.getCol()-1) { // persegi
            for (int i = 0 ; i < matrix.getRow() ; i++) {
                if (check == 2) break ;
                for (int j = 0 ; j < matrix.getCol(); j++) {
                    if (j == matrix.getCol()-1) {
                        if (matrix.ELMT(i, j) != 0) {
                            check = 2 ; // tidak ada hasil 
                        }
                        else {
                            check = 1 ;
                        }
                    }
                    else {
                        if (matrix.ELMT(i, j) != 0) break ;
                    }
                }
            }            

        }
        else { // baris > variabel
            for (int i = 0 ; i < matrix.getRow() ; i++) {
                if (check == 2) break ;
                for (int j = 0 ; j < matrix.getCol(); j++) {
                    if (j == matrix.getCol()-1) {
                        if (matrix.ELMT(i, j) != 0) {
                            check = 2 ; // tidak ada hasil 
                        }
                        else {
                            count += 1 ;
                        }
                    }
                    else {
                        if (matrix.ELMT(i, j) != 0) break ;
                    }
                }
            }                    
            if (count > (matrix.getRow() - (matrix.getCol() - 1))) {
                check = 1 ;
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
                System.out.printf("X%d = %.3f \n" , i+1, solution[i]) ;
            }     
            System.out.print("\n") ;
        }
        else if(check == 1){
            System.out.printf("SPL memiliki solusi banyak.\n") ;
            SPL.Parameter(matrix) ;
        }
        else{
            System.out.printf("SPL tidak memiliki solusi!\n");
        }
    }

    public static void SPLGaussJordan(String outputFileName, boolean fileMethod, Scanner txtReader){
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
        GaussianElimination(matrix) ;

        // enolin atas leading one
        for (int i = matrix.getRow()-1 ; i > 0 ; i--) {
            int leadingOne = -999; // idx kolom
            for (int j = 0 ; j < matrix.getCol()-1 ; j++) { // nyari leading one
                if (matrix.ELMT(i, j) != 0) {
                    leadingOne = j ;
                    break ;
                }
            }
            
            if (leadingOne == -999) continue ;
            else {
                for (int j = i - 1 ; j >= 0 ; j--) {
                    double faktor = 1 * matrix.ELMT(j, leadingOne) ;
                    matrix.nPenguranganMatrix(j, i, faktor);
                }
            }
        }
        System.out.printf("Hasil OBE Gauss-Jordan : \n") ;
        matrix.displayMatrix();

        // mengecek jenis hasil gauss (unik, banyak, tidak ada hasil)
        int check  = 0 ; // 0 = unik, 1 = banyak, 2 = tidak ada
        int count = 0 ;
        if (matrix.getRow() < matrix.getCol()-1) { // persamaan < variabel
            for (int i = 0 ; i < matrix.getRow() ; i++) {
                for (int j = 0 ; j < matrix.getCol(); j++) {
                    if (j == matrix.getCol()-1) {
                        if (matrix.ELMT(i, j) != 0) {
                            check = 2 ; // tidak ada hasil 
                        }
                    }
                    else {
                        if (matrix.ELMT(i, j) != 0) break ;
                    }
                }
            }
            if (check == 0) check = 1 ; // Kalau persamaan < variable pasti dia solusi banyak
        }
        else if (matrix.getRow() == matrix.getCol()-1) { // persegi
            for (int i = 0 ; i < matrix.getRow() ; i++) {
                if (check == 2) break ;
                for (int j = 0 ; j < matrix.getCol(); j++) {
                    if (j == matrix.getCol()-1) {
                        if (matrix.ELMT(i, j) != 0) {
                            check = 2 ; // tidak ada hasil 
                        }
                        else {
                            check = 1 ;
                        }
                    }
                    else {
                        if (matrix.ELMT(i, j) != 0) break ;
                    }
                }
            }            

        }
        else { // baris > variabel
            for (int i = 0 ; i < matrix.getRow() ; i++) {
                if (check == 2) break ;
                for (int j = 0 ; j < matrix.getCol(); j++) {
                    if (j == matrix.getCol()-1) {
                        if (matrix.ELMT(i, j) != 0) {
                            check = 2 ; // tidak ada hasil 
                        }
                        else {
                            count += 1 ;
                        }
                    }
                    else {
                        if (matrix.ELMT(i, j) != 0) break ;
                    }
                }
            }                    
            if (count > (matrix.getRow() - (matrix.getCol() - 1))) {
                check = 1 ;
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
                FileWriter writer;
                if(outputFileName != "null")
                    System.out.printf("X%d = %.3f \n" , i+1, solution[i]) ;
                else{
                    try{
                        writer = new FileWriter("../test/" + outputFileName + ".txt");
                        writer.write("X" + (i+1) + " = " + solution[i] + "\n");
                    } catch(IOException e){
                        System.out.println("FileWriter error!");
                    }
                }
            }     
            System.out.print("\n") ;
        }
        else if(check == 1){
            System.out.printf("SPL memiliki solusi banyak.\n") ;
            SPL.Parameter(matrix) ;
        }
        else{
            System.out.printf("SPL tidak memiliki solusi!\n");
        }
    }

    public static void SPLInverse(String outpuFileName, boolean fileMethod, Scanner txtReader){
        System.out.printf("Masukkan SPL tidak dengan hasilnya! \n") ;
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

    public static void SPLCramer(String outpuFileName, boolean fileMethod, Scanner txtReader){
        System.out.print("Masukkan jumlah baris: ");
        int row = scan.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        int col = scan.nextInt();

        if (col-1 != row) {
            System.out.println("Kaidah Cramer gagal karena matriks tidak memiliki determinan");
            System.out.println("Pastikan jumlah kolom = jumlah baris + 1");
        }
        else
        {
            Matrix m = new Matrix(row,col);
            m.readMatrix(scan);
            double solution[] = new double[m.getCol()-1];
            Matrix A = new Matrix(m.getRow(), m.getCol()-1);
            Matrix B = new Matrix(m.getRow(), 1);
            Matrix Ax = new Matrix(m.getRow(), m.getCol()-1);
            double detA;

            for (int i = 0; i < m.getRow(); i++) {
                for (int j = 0; j < m.getCol()-1; j++) {
                    A.matrix[i][j] = m.matrix[i][j];
                }
            }
            detA = Matrix.DetEkspansiKofaktor(A);
            if (detA == 0) {
                System.out.println("Kaidah Cramer gagal karena determinan matriks bernilai 0");
            }
            else
            {
                for (int i = 0; i < m.getRow(); i++) {
                    B.matrix[i][0] = m.matrix[i][m.getCol()-1];
                }
                for (int i = 0; i < A.getCol(); i++) {
                    Matrix.copyMatrix(A, Ax);
                    for (int j = 0; j < A.getRow(); j++) {
                        Ax.matrix[j][i] = B.matrix[j][0];
                    }
                    solution[i] = Matrix.DetEkspansiKofaktor(Ax)/detA;
                }
                System.out.println("Solusi:");
                int numSolution = 1;
                for (int i = 0; i < m.getCol()-1; i++) {
                    System.out.printf("x%d = %f\n", numSolution, solution[i]);
                    numSolution++;
                }
            }
        }
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
            int leadingOne = -999;
            for (int n = 0 ; n < matrix.getCol() -1 ; n++) {
                if (matrix.ELMT(k, n) != 0) {
                    leadingOne = n ;
                    matrix.barisBagi(k, matrix.ELMT(k, n));
                    break ;
                }
            }

        
            // Membuat kolom di bawah diagonal menjadi 0
            if (leadingOne != -999 && k != matrix.getRow()-1) {
                double pengurang;
                for(int i = k + 1; i < matrix.getRow(); i++){
                    double faktor = matrix.ELMT(i, leadingOne) / matrix.ELMT(k, leadingOne);
                    for(int j = leadingOne; j < matrix.getCol(); j++) {
                        pengurang = faktor * matrix.ELMT(k, j);
                        matrix.setELMT(i, j, matrix.ELMT(i, j) - pengurang);
                    }
                }        
            }
        }
    }

    public static void GaussianElimination(Matrix matrix){
        int h = 0; /* Initialization of the pivot row */
        int k = 0; /* Initialization of the pivot column */
        while(h < matrix.getRow() && k < matrix.getCol()){
            int i_max = h;
            /* Find the k-th pivot: */
            for(int i = h; i < matrix.getRow(); i++){
                double max = matrix.ELMT(i, k);
                if(max < Math.abs(matrix.ELMT(i, k))){
                    max = Math.abs(matrix.ELMT(i, k));
                    i_max = i;
                }
            }
            if(matrix.ELMT(i_max, k) == 0)
                /* No pivot in this column, pass to next column */
                k++;
            else{
                Switch(matrix, h, i_max);
                /* Do for all rows below pivot: */
                for(int i = h + 1; i < matrix.getRow(); i++){
                    double f = matrix.ELMT(i, k) / matrix.ELMT(h, k);
                    /* Fill with zeros the lower part of pivot column: */
                    matrix.setELMT(i, k, 0);
                    /* Do for all remaining elements in current row: */
                    for(int j = k + 1; j < matrix.getCol(); j++)
                        matrix.setELMT(i, j, (matrix.ELMT(i, j) - matrix.ELMT(h, j) * f));
                }
                /* Increase pivot row and column */
                h++;
                k++;
            }
        }
 
        // bagi supaya semua leading one jadi 1 ;
        for (int i = 0 ; i < matrix.getRow() ; i++) {
            for (int j = 0 ; j < matrix.getCol() - 1 ; j++) {
                if (matrix.ELMT(i, j) != 0) {
                    matrix.barisBagi(i, matrix.ELMT(i, j));
                    break ;
                }
            }
        }
        // handle -0 ;
        for (int i = 0 ; i < matrix.getRow() ; i++) {
            for (int j = 0 ; j < matrix.getCol() - 1 ; j++) {
                if (matrix.ELMT(i, j) == -0) {
                    matrix.setELMT(i, j, 0) ;
                }
            }
        }
        
    }

    public static void Switch(Matrix mat, int k, int max){
        // membuat baris temporary  utk menyimpan baris k
        double[] temp = new double[mat.getCol()];
        for(int m = 0; m < mat.getCol(); m++){
            temp[m] = mat.ELMT(k, m);
        }
        double[] temp2 = new double[mat.getCol()];
        for(int m = 0; m < mat.getCol(); m++){
            temp2[m] = mat.ELMT(max, m);
        }
        mat.setRow(k, temp2);
        mat.setRow(max, temp);
    }

    public static void Parameter (Matrix matrix) {
        double [] hasil ; // konstanta 
        hasil = new double[matrix.getCol()-1] ;


        String [] solusi ; // string yang di akhir bakal diprint
        solusi = new String[matrix.getCol()-1] ;

        for (int i = 0 ; i < matrix.getCol()-1 ; i++) {
            solusi[i] = "" ;
        }

        double [][] koefmisal ; // koefisien untuk tiap variabel yang di dlmnya ada variabel
        koefmisal = new double[matrix.getCol()-1][matrix.getCol()-1] ;

        for (int i = matrix.getRow()-1 ; i >= 0 ; i--) {
            // nyari leading one 
            int leadingone ;
            leadingone = -999 ;
            for (int k = 0 ; k < matrix.getCol()-2 ; k++) {
                if (matrix.ELMT(i, k) != 0) {
                    leadingone = k ;
                    break ;
                }
            }

            if (leadingone == -999) continue ; // barisnya 0 semua

            for (int j = matrix.getCol()-2 ; j > leadingone ; j--) {
                boolean misal = true ;
                for (int l = i ; l <= matrix.getRow()-1 ; l++) { // ngecek apakah di bawahnya 0 semua
                    if (l != i) {
                        if (matrix.ELMT(l, j) != 0) misal = false ;
                    }
                }
                if (misal == true) { // pemisalan
                    solusi[j] = "X" + Integer.toString(j+1) ;
                    koefmisal[j][j] = 1 ;
                }
            }
            // ngurus leading one
            hasil [leadingone] = matrix.ELMT(i, matrix.getCol()-1) / matrix.ELMT(i, leadingone) ;
            for (int m = leadingone+1 ; m <= matrix.getCol()-2 ; m++) { // kolom kanannya leading one
                hasil[leadingone] -= matrix.ELMT(i, m) * hasil[m] ;
                for (int n = 0 ; n < matrix.getCol()-1 ; n++) {
                    if (koefmisal[m][n] != 0) {
                        koefmisal[leadingone][n] += -koefmisal[m][n] * matrix.ELMT(i, m);
                    }
                }
            }
            // masukin solusi dari leading one 
            if (hasil[leadingone] != 0) {
                solusi[leadingone] += Double.toString(hasil[leadingone]) ;
            }
            for (int O = 0 ; O < matrix.getCol()-1 ; O++) {
                if (koefmisal[leadingone][O] != 0) {
                    if (koefmisal[leadingone][O] > 0) {
                        if (solusi[leadingone] == "") {
                            if (koefmisal[leadingone][O] == 1) {
                                solusi[leadingone] += "X" + Integer.toString(O+1) ;
                            }
                            else {
                                solusi[leadingone] += Double.toString(koefmisal[leadingone][O]) + " X" + Integer.toString(O+1) ;
                            }
                        }
                        else {
                            if (koefmisal[leadingone][O] == 1) {
                                solusi[leadingone] += " + " + "X" + Integer.toString(O+1) ;
                            }
                            else {
                                solusi[leadingone] += " + " + Double.toString(koefmisal[leadingone][O]) + " X" + Integer.toString(O+1) ;
                            }
                        }
                    }
                    else {
                        if (solusi[leadingone] == "") {
                            if (koefmisal[leadingone][O] == 1) {
                                solusi[leadingone] += "X" + Integer.toString(O+1) ;
                            }
                            else {
                                solusi[leadingone] += Double.toString(Math.abs(koefmisal[leadingone][O])) + " X" + Integer.toString(O+1) ;
                            }
                        }
                        else {
                            if (koefmisal[leadingone][O] == 1) {
                                solusi[leadingone] += " - " + "X" + Integer.toString(O+1) ;
                            }
                            else {
                                solusi[leadingone] += " - " + Double.toString(Math.abs(koefmisal[leadingone][O])) + " X" + Integer.toString(O+1) ;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0 ; i < matrix.getCol()-1 ; i++) {
            if (solusi[i] == "") {
                solusi[i] = "0.0" ;
            }
        }
        for (int i = 0 ; i < matrix.getCol()-1 ; i++) {
            System.out.printf("X%d = " , i+1) ;
            System.out.printf("%s" , solusi[i]) ;
            System.out.println() ;
        }
    }

    static int[] countRowCol(Scanner txtReader){
        // mengembalikan sebuah array yang berisi {row, col};
        int[] arr = new int[2];
        int i = 0, j = 0;
        while(txtReader.hasNextLine()){
            String data = txtReader.nextLine();
            String[] splitStr = data.split("\\s+");
            j = 0;
            while(j < splitStr.length)
                j += 1;
            i += 1;
        }
        arr[0] = i;
        arr[1] = j;
        return arr;
    }

    static double[][] readFile(Scanner txtReader){
        int[] count = countRowCol(txtReader);
        double[][] arr = new double[count[0]][count[1]];
        int i = 0, j = 0;
        while(txtReader.hasNextLine()){
            String data = txtReader.nextLine();
            String[] splitStr = data.split("\\s+");
            j = 0;
            for(String str : splitStr){
                arr[i][j] = Double.parseDouble(str);
                j += 1;
            }
            i += 1;
        }
        return arr;
    }

    static void CreateFileOutput(String outputPath){
        try{
            File txtsc = new File(outputPath);
            if (txtsc.createNewFile())
                System.out.println("Output file berhasil dibuat di " + outputPath);
            else
                System.out.println("File sudah ada!");
        } catch(IOException e){
            System.out.println("CreateFileOutput Error");
        }
    }

    // Write ke file
    static void writeFile(String path, double[][] matrix){
        try{
            FileWriter myWriter = new FileWriter(path);
            for(int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix[i].length; j++){
                    myWriter.write(Double.toString(matrix[i][j]) + " ");
                }
                myWriter.write("\n");
            }
            myWriter.write("\n");
            myWriter.close();
        } catch(IOException e){
            System.out.println("writeFile Error!");
        }
    }
}