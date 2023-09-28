import java.util.Scanner;

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

        double[] solution = new double[IP.getCol()-1];
        for(int i = IP.getCol() - 2; i >= 0; i--){
            double sum = 0.0;
            for(int j = i + 1; j < IP.getCol() - 1; j++) 
                sum += IP.ELMT(i, j) * solution[j];
            solution[i] = (IP.ELMT(i, IP.getCol() - 1) - sum) / IP.ELMT(i, i);
        }   
            
        for(int i = 0; i < solution.length; i++)
            y += solution[i]*powerOf(x, i);

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

    static double powerOf(double value, int t){
        if(t == 0)
            return 1;
        else
            return powerOf(value, t-1)*value;
    }
}