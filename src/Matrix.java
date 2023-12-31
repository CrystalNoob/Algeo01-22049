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
            System.out.printf("Baris %d:", i+1);
            for(int j = 0; j < getCol(); j++){
                value = scan.nextDouble();
                setELMT(i, j, value);
            }
        }
    }

    public void readMatrixAugmented(Scanner scan){
        double value;
        for(int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                value = scan.nextDouble();
                setELMT(i, j, value);
            }
        }
    }

    public static Matrix readDet()
    {
        System.out.print("Masukkan jumlah baris dan kolom: ");
        int RowCol = sc.nextInt();

        Matrix m = new Matrix(RowCol,RowCol);
        m.readMatrix(sc);
        return m;
    }

    public void displayMatrix(){
        for (int i = 0; i < getRow(); i++){
            for(int j = 0; j < getCol(); j++){
                if(j == getCol() - 1){
                    System.out.printf("%f\n",ELMT(i, j));
                }
                else{
                    System.out.printf("%f ", ELMT(i, j));
                }
            }
        }
    }

    public double[][] readSPL(){
        sc = new Scanner(System.in);


        for (int i = 0; i < getRow(); i++){ // idx 0 itu persamaan pertama terus ngurut terus
            System.out.print("Persamaan ke-" + (i+1) + "\n");
            for (int j = 0; j < getCol(); j++){ // idx 0 itu x1 sampe idx jumlahVar = hasil
                if (j != getCol()-1){
                    System.out.print("Masukkan koefisien variabel ke-" + (j+1) + ": ");
                    matrix[i][j] = sc.nextDouble();
                }
                else{
                    System.out.print("Masukkan hasil persamaan: ");
                    matrix[i][j] = sc.nextDouble();
                }
            }
        }
        return matrix;
    }

    public static double DetReduksiBaris(Matrix matrix){
        int count_swap = 0;
        double det = 1;

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
            if (k != max) {
                count_swap += 1;
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
        for (int i = 0; i < matrix.getRow(); i++) {
            det *= matrix.ELMT(i, i);
            }

        if (count_swap % 2 == 0) {
            det *= 1;
        }
        else
        {
            det *= -1;
        }
        if (Double.isNaN(det)) {
            return 0;
        }
        else
        {
            return det;
        }
    }

    public static double DetEkspansiKofaktor(Matrix m){
        double det = 0;
        int sign = 1;

        if (m.getRow() == 1 && m.getCol() == 1) {
            return m.ELMT(0, 0);
        }
        else
        {
            for (int i = 0; i < m.getCol(); i++) {
                det += sign*m.ELMT(0, i)*DetEkspansiKofaktor(Minor(m, 0, i));
                sign *= -1;
            }
        }
        if (Double.isNaN(det))
        {
            return 0;
        }
        else
        {
            return det;
        }
    }

    public static Matrix InverseUsingAdjoint(Matrix m){
        Matrix adj = new Matrix(m.getCol(), m.getRow());

        adj = Adjoint(m);
        MultiplybyConst(adj, (1/DetEkspansiKofaktor(m)));
        for (int i = 0; i < adj.getRow(); i++) {
            for (int j = 0; j < adj.getCol(); j++) {
                if (adj.ELMT(i, j) == -0) {
                    adj.matrix[i][j] = 0;
                }
            }
        }
        return adj;
    }

    public static Matrix InverseGaussJordan(Matrix m)
    {
        Matrix mInverse = new Matrix((m.getCol()), m.getRow());
        for (int i = 0; i < m.getCol(); i++) {
            for (int j = 0; j < m.getRow(); j++) {
                if (i == j) {
                    mInverse.matrix[i][j] = 1;
                }
                else
                {
                    mInverse.matrix[i][j] = 0;
                }
            }
        }

        Matrix matrix = new Matrix(m.getRow(), 2*m.getCol());
        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < matrix.getCol(); j++) {
                if (j >= m.getCol())
                {
                    matrix.matrix[i][j] = mInverse.matrix[i][j-m.getCol()];
                }
                else
                {
                    matrix.matrix[i][j] = m.matrix[i][j];
                }
            }
        }

        for(int k = 0; k < matrix.getRow(); k++){

            // mencari pivot row (supaya diagonal ga 0)
            int max = k;
            for(int i = k + 1; i < matrix.getRow(); i++)
                if(Math.abs(matrix.ELMT(i, k)) > Math.abs(matrix.ELMT(max, k))) 
                    max = i; 

            // membuat baris temporary  utk menyimpan baris k
            double[] temp = new double[matrix.getCol()];
            for(int p = 0; p < matrix.getCol(); p++){
                temp[p] = matrix.ELMT(k, p);
            }

            // membuat baris temporary utk menyimpan baris max
            double[] temp2 = new double[matrix.getCol()];
            for(int p = 0; p < matrix.getCol(); p++){
                temp2[p] = matrix.ELMT(max, p);
            }          

            // menukar baris max dengan baris paling atas
            matrix.setRow(k, temp2);
            matrix.setRow(max, temp);


            // membagi baris agar dpt leading one
            int leadingOne = -999;
            for (int n = 0 ; n < matrix.getCol(); n++) {
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

        for (int i = matrix.getRow()-1 ; i >= 0 ; i--) {
            int leadingOne2 = -999; // idx kolom
            for (int j = 0 ; j < m.getCol(); j++) { // nyari leading one
                if (matrix.ELMT(i, j) != 0) {
                    leadingOne2 = j ;
                    break ;
                }
            }
                
            if (leadingOne2 == -999) {
                continue;
            } 
            else {
                for (int j = i - 1 ; j >= 0 ; j--) {
                    double faktor = 1 * matrix.ELMT(j, leadingOne2);
                    matrix.nPenguranganMatrix(j, i, faktor);
                }
            }
        }
        Matrix mOut = new Matrix(m.getRow(), m.getCol());
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getCol(); j++) {
                mOut.setELMT(i, j, matrix.ELMT(i, j+m.getCol()));
            }
        }
        return mOut;
    }

    // PRIMITIF

    public static Matrix Kofaktor(Matrix m)
    {
        Matrix kofaktor = new Matrix(m.getRow(), m.getCol());
        int sign;

        for (int i = 0; i < m.getRow(); i++) {
            if (i % 2 == 1){
                sign = -1;
            }
            else{
                sign = 1;
            }
            for (int j = 0; j < m.getCol(); j++) {
                kofaktor.matrix[i][j] = sign*DetEkspansiKofaktor(Minor(m, i, j));
                sign *= -1;
            }
        }
        return kofaktor;
    }

    public static Matrix Minor(Matrix m, int delRow, int delCol)
    {
        Matrix minor = new Matrix(m.getRow()-1, m.getCol()-1);
        int x, y;

        y = 0;
        for (int i = 0; i < m.getRow(); i++)    /* Indeks Baris untuk pengisian minor  */
        {         
            if (i != delRow)    
                {
                    x = 0;
                    for (int j = 0; j < m.getCol(); j++)    /* Indeks kolom untuk pengisian minor */
                    {
                        if (j != delCol)
                        {                                                   
                            minor.matrix[y][x] = m.ELMT(i, j);
                            x += 1;
                        }
                    } 
                    y += 1;
                }        
        }                       
        return minor;
    }

    public static Matrix Adjoint(Matrix m)
    {
        return transpose(Kofaktor(m));
    }  

    public static void MultiplybyConst(Matrix m, double konstanta)
    {
        for (int i = 0; i < m.getRow(); i++)
        {
            for (int j = 0; j < m.getCol(); j++)
            {
                m.matrix[i][j] *= konstanta;
            }
        }
    }

    public void nPenguranganMatrix (int idxDikurang, int idxPengurang, double pengali){
            for (int i = 0; i < getCol(); i++) {
                setELMT(idxDikurang, i, ELMT(idxDikurang, i) - (ELMT(idxPengurang, i) * pengali)) ;
            }
        }
    

    public void nPenjumlahanMatrix (int idxDitambah, int idxPenambah, double pengali){
            for (int i = 0; i < getCol(); i++) {
                setELMT(idxDitambah, i, ELMT(idxDitambah, i) - (ELMT(idxPenambah, i) * pengali)) ;
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

    public static void copyMatrix(Matrix m1, Matrix m2)
    // Menyalin elemen pada matrix m1 ke matrix m2
    // I.S. m1 dan m2 terdefinisi dan ukurannya sama
    // F.S. m2 = m1
    {
        for (int i = 0; i < m1.getRow(); i++) {
            for (int j = 0; j < m1.getCol(); j++) {
                m2.matrix[i][j] = m1.matrix[i][j];
            }
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

    public Matrix perkalianMatrix(Matrix m1 , Matrix m2) { // m1 X m2 . validasi kolom m1 = baris m2
        Matrix matrix = new Matrix(m1.getRow(), m2.getCol()) ; // dilakukan sebelum fungsi dipanggil
        double value = 0 ;
        for (int i = 0 ; i < m1.getRow() ; i++) {
            for (int j = 0 ; j < m2.getCol() ; j++) {
                for (int k = 0 ; k < m1.getCol() ; k++) {
                    value += m1.ELMT(i, k) * m2.ELMT(k, j) ;
                }
                matrix.setELMT(i, j, value);
                value = 0 ;
            }
        }
        return matrix ;
    }

    public static boolean isRowZero(Matrix m, int row)
    {
        boolean check = true;
        for (int i = 0; i < m.getCol(); i++) {
            if (m.ELMT(row, i) != 0) {
                check = false;
            }
        }
        return check;
    }
}