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
            for(int j = 0; j < getCol(); j++){
                System.out.printf("Baris %d, kolom %d: ", i+1, j+1);
                value = scan.nextDouble();
                setELMT(i, j, value);
            }
        }
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
                    matrix[i][j] = sc.nextInt();
                }
                else{
                    System.out.print("Masukkan hasil persamaan: ");
                    matrix[i][j] = sc.nextInt();
                }
            }
        }
        return matrix;
    }

    public static void DetReduksiBaris(){
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
        return det;
    }

    public static Matrix InverseUsingAdjoint(Matrix m){
        Matrix adj = new Matrix(m.getCol(), m.getRow());

        adj = Adjoint(m);
        MultiplybyConst(adj, (1/DetEkspansiKofaktor(m)));
        return adj;
    }

    public double[][] InvertMinor(){
        return matrix;
    }

    public double[][] InvertInverse(){
        return matrix;
    }

    public double[][] InvertCramer(){
        return matrix;
    }

    // PRIMITIF

    public static Matrix Kofaktor(Matrix m)
    {
        Matrix kofaktor = new Matrix(m.getRow(), m.getCol());
        int sign = 1;

        for (int i = 0; i < m.getRow(); i++) {
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

    public void nPenguranganMatrix (double[][] matrix, int idxDikurang, int idxPengurang, int brpkali){
        for (int j = 0; j < brpkali; j++){
            for (int i = 0; i < matrix[0].length; i++) {
                setELMT(idxDikurang, i, ELMT(idxPengurang, i)) ;
            }
        }
    }

    public void nPenjumlahanMatrix (int idxDitambah, int idxPenambah, int brpkali){
        while(brpkali > 0){
            for (int i = 0; i < getCol() ; i++){
                setELMT(idxDitambah, i, ELMT(idxPenambah, i)) ;
            }
            brpkali--;
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

    public static void main(String args[]){
        Matrix x;
        x = new Matrix(3,3);
        x.readMatrix(sc);
        x.displayMatrix(); 
        System.out.printf("%f", DetEkspansiKofaktor(x));
        System.out.println("");
        InverseUsingAdjoint(x).displayMatrix();
    }
}