import java.util.Scanner;

import javax.imageio.ImageIO;

public class Interpolasi{
    public static void InterpolasiPolinom(String outputFileName, boolean fileMethod, Scanner txtReader){
        // Inisialisasi Scanner dan nilai y dari operasi IP dengan x tertentu
        double y = 0;
        Scanner sc = new Scanner(System.in);

        // baca derajat polinom
        int n = sc.nextInt();
        
        // buat matrix dan read nilai (x, y)
        Matrix m = new Matrix(n+1, 2);
        m.readMatrix(sc);

        // read nilai x yang ingin dicari
        double x = sc.nextDouble();
        
        // buat matrix baru untuk operasi Gauss
        Matrix IP = new Matrix(n+1, n+2);

        // loop 
        for(int i = 0; i < IP.getRow(); i++){
            for(int j = 0; j < IP.getCol(); j++){
                // set kolom terakhir dengan nilai y dari (x, y)
                if(j == IP.getCol() - 1)
                    IP.setELMT(i, j, m.ELMT(i, 1));
                
                // set kolom sisanya dengan nilai x^i
                else
                    IP.setELMT(i, j, powerOf(m.ELMT(i, 0), j));
            }
        }

        // Gauss operation
        SPL.Gauss(IP);

        // Solution for a0, a1, a2, ..., an;
        double[] solution = new double[IP.getCol()-1];
        for(int i = IP.getCol() - 2; i >= 0; i--){
            double sum = 0.0;
            for(int j = i + 1; j < IP.getCol() - 1; j++) 
                sum += IP.ELMT(i, j) * solution[j];
            solution[i] = (IP.ELMT(i, IP.getCol() - 1) - sum) / IP.ELMT(i, i);
        }   
        
        // find f(x)
        for(int i = 0; i < solution.length; i++)
            y += solution[i]*powerOf(x, i);

        // print output format
        for(int i = solution.length - 1; i > -1; i--){
            if(i == solution.length - 1)
                System.out.printf("\nf(x) = %.4fx^%d", solution[i], i);
            else if(solution[i] > 0 && i > 1)
                System.out.printf(" + %.4fx^%d", solution[i], i);
            else if(solution[i] < 0 && i > 1)
                System.out.printf(" %.4fx^%d", solution[i], i);
            else if(solution[i] > 0 && i == 1)
                System.out.printf(" + %.4fx", solution[i]);
            else if(solution[i] < 0 && i == 1)
                System.out.printf(" %.4fx", solution[i]);
            else if(solution[i] > 0 && i == 0)
                System.out.printf(" + %.4f, f(%.4f) = %.4f\n", solution[i], x, y);
            else if(solution[i] < 0 && i == 0)
                System.out.printf(" %.4f, f(%.4f) = %.4f\n", solution[i], x, y);
        }
    }

    // recursoin to find the power of a value
    static double powerOf(double value, int t){
        if(t == 0)
            return 1;
        else {
            if (t > 0) return powerOf(value, t-1)*value ;
            else return powerOf(value, t+1) * value ;
        }
    }


    public static void BicubicInterpolation(){
        Scanner sc = new Scanner(System.in);
        Matrix matrixx = new Matrix(16, 17) ;
        System.out.printf("Masukkan matrix 4x4 : \n") ;
        for (int i = 0 ; i < 16 ; i++) {
            matrixx.setELMT(i, 16, sc.nextDouble());
        }

        int baris = 0 ;
        int kolom = 0 ;

        for (int y = 0 ; y <= 1 ; y++) {
            for (int x = 0 ; x <= 1 ; x ++) {
                kolom = 0 ;
                for (int j = 0 ; j < 4 ; j++) {
                    for (int i = 0 ; i < 4 ; i++) {
                        matrixx.setELMT(baris, kolom, fungsi(i, j, x, y)); // 4 baris pertama fungsi biasa

                        if (i != 0) {
                            matrixx.setELMT(baris+4, kolom, fungsi(i-1, j, x, y) * i); // 4 baris pertama turunan x
                        }

                        if (j != 0) {
                            matrixx.setELMT(baris+8, kolom, fungsi(i, j-1, x, y) * j); // 4 baris pertama turunan y
                        }

                        matrixx.setELMT(baris+12, kolom, fungsi(i-1, j-1, x, y) * i * j); // 4 baris pertama turunan xy
                        kolom += 1 ;
                    }
                }
                baris += 1 ;
            }
        }
        // Gauss operation
        SPL.Gauss(matrixx);

        // Solution for a
        double[] solution = new double[matrixx.getCol()-1];
        for(int i = matrixx.getCol() - 2; i >= 0; i--){
            double sum = 0.0;
            for(int j = i + 1; j < matrixx.getCol() - 1; j++) 
                sum += matrixx.ELMT(i, j) * solution[j];
            solution[i] = (matrixx.ELMT(i, matrixx.getCol() - 1) - sum) / matrixx.ELMT(i, i);
        }   
        
        matrixx.displayMatrix();

        double x, y ;
        boolean status = true ; // true jika masih mau nginput
        while (status) {
            System.out.printf("Masukkan X : ") ;
            x = sc.nextDouble() ;
            System.out.printf("Masukkan Y : ") ;
            y = sc.nextDouble() ;
            Double total = 0.0 ;
            int idx = 0 ;
            for (int j = 0 ; j < 4 ; j++) {
                for (int i = 0 ; i < 4 ; i++) {
                    total += fungsi(i, j, x, y) * solution[idx] ;
                    idx++ ;
                }
            }
            System.out.printf("f(%.2f,%.2f) = %.4f\n" , x, y, total) ;
            System.out.printf("1. Lanjutkan \n2. Selesai\n") ;
            int check = sc.nextInt() ;
            if (check == 1) {
                status = true ;
            }
            else status = false ;
        }

    }

    static double fungsi (int i, int j, double x, double y) {
        double hasil = 0.0 ;
        hasil = powerOf((x), i) * powerOf(y, j) ;
        return hasil ;
    }

    public static void main (String[] args)
    {
        BicubicInterpolation();
    }
}