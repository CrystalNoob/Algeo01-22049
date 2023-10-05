import java.util.Scanner;
import java.io.File;

public class Main{
    static Scanner sc;
    public static void main(String args[]){
        // Decalaration
        String choice, subchoice, inputChoice, outputChoice;
        File txt;
        sc = new Scanner(System.in);
        System.out.println("Sistem Persamaan Linier, Determinan, dan Aplikasinya");
        header();

        // Infinite loop
        while(true){
            menu();
            choice = sc.next();
            clear();

            switch(choice){

                // SPL
                case "1":
                    clear();
                    submenuSPL();
                    subchoice = sc.next();
                    switch(subchoice){
                        
                        // Gauss
                        case "1":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){
                                
                                // Manual input
                                case "1":
                                    System.out.println("Output a file? (y/n)");
                                    outputChoice = sc.next();
                                    switch(outputChoice){
                                        case "y":
                                            SPL.SPLGauss(getFileNameToOutput(), false, null);
                                            break;
                                        case "n":
                                            SPL.SPLGauss(null, false, null);
                                            break;
                                        default:
                                            wrongInput();
                                    }
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGauss(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        fileNotFound();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;

                        // Gauss-Jordan
                        case "2":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){
                                
                                // Manual input
                                case "1":
                                    System.out.println("Output a file? (y/n)");
                                    outputChoice = sc.next();
                                    switch(outputChoice){
                                        case "y":
                                            SPL.SPLGaussJordan(getFileNameToOutput(), false, null);
                                            break;
                                        case "n":
                                            SPL.SPLGaussJordan(null, false, null);
                                            break;
                                        default:
                                            wrongInput();
                                    }
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGaussJordan(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        fileNotFound();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;

                        // Inverse
                        case "3":
                            SPL.SPLInverse();
                            break;
                        
                        // Cramer
                        case "4":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){
                                
                                // Manual input
                                case "1":
                                    System.out.println("Output a file? (y/n)");
                                    outputChoice = sc.next();
                                    switch(outputChoice){
                                        case "y":
                                            SPL.SPLCramer(getFileNameToOutput(), false, null);
                                            break;
                                        case "n":
                                            SPL.SPLCramer(null, false, null);
                                            break;
                                        default:
                                            wrongInput();
                                    }
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLCramer(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        fileNotFound();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;
                    }
                    break;

                // Determinan
                case "2":
                    clear();
                    submenuDet();
                    subchoice = sc.next();
                    Matrix detM;
                    switch(subchoice){
                        
                        // Reduksi Baris
                        case "1":
                            detM = Matrix.readDet();
                            System.out.printf("%f\n", Matrix.DetReduksiBaris(detM));
                            break;

                        // Ekspansi Kofaktor
                        case "2":
                            detM = Matrix.readDet();
                            System.out.printf("%f\n", Matrix.DetEkspansiKofaktor(detM));
                            break;
                        }
                    break;

                // Inverse
                case "3":
                    clear();
                    submenuInverse();
                    subchoice = sc.next();
                    System.out.print("Masukan banyaknya baris & kolom (1 value): ");
                    int n = sc.nextInt();
                    System.out.println();
                    Matrix invM = new Matrix(n, n);
                    invM.readMatrix(sc);
                    switch(subchoice){
                        // Gauss-Jordan
                        case "1":
                            if(Matrix.DetReduksiBaris(invM) == 0){
                                invM = Matrix.InverseGaussJordan(invM);
                                invM.displayMatrix();
                            }
                            else
                                System.out.println("Matriks idak memiliki balikan");
                            break;
                        // Adjoint
                        case "2":
                            if(Matrix.DetReduksiBaris(invM) == 0){
                                invM = Matrix.InverseUsingAdjoint(invM);
                                invM.displayMatrix();
                            }
                            else
                                System.out.println("Matriks idak memiliki balikan");
                            break;
                    }
                    break;

                // Interpolasi Polinomial
                case "4":
                    clear();
                    Interpolasi.InterpolasiPolinom();
                    break;

                // Interpolasi Bicubic Spline
                case "5":
                    clear();
                    Interpolasi.BicubicInterpolation() ;
                    break;

                // Regresi Linear Berganda
                case "6":
                    clear();
                    Regresi.RegresiLinear();
                    System.out.printf("\n\n");
                    break;

                // Exit
                case "7":
                    clear();
                    System.exit(0);
                    break;

                // Invalid input
                default:
                    clear();
                    wrongInput();
            }
        }
    }

    // Writing Template
    static void header(){
        System.out.println("=====================================================");
    }
    
    static void inputHere(){
        System.out.print("Input: ");
    }

    static void wrongInput(){
        System.out.println("Incorrect input, please try again!");
    }

    static void menu(){
        System.out.println("====================     MENU    ====================");
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
        header();
        inputHere();
    }

    static void submenuSPL(){
        System.out.println("==================     SUB MENU    ==================");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        header();
        inputHere();
    }

    static void submenuDet(){
        System.out.println("==================     SUB MENU    ==================");
        System.out.println("1. Metode reduksi baris");
        System.out.println("2. Metode ekspansi kofaktor");
        header();
        inputHere();
    }

    static void submenuInverse(){
        System.out.println("==================     SUB MENU    ==================");
        System.out.println("1. Metode Gauss-Jordan");
        System.out.println("2. Metode adjoin");
        header();
        inputHere();
    }

    static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void inputPrompt(){
        System.out.println("==================      INPUT      ==================");
        System.out.println("1. Manual input");
        System.out.println("2. Read from file");
        header();
        inputHere();
    }

    static void fileNotFound(){
        System.out.println("File not found!");
    }

    // Input file name getter
    static String getFileNameToInput(){
        sc = new Scanner(System.in);
        System.out.print("Input file name: ");
        return "../test/" + sc.nextLine() + ".txt";
    }

    // Output file name getter
    static String getFileNameToOutput(){
		sc = new Scanner(System.in);
		System.out.print("Solution file name: ");
		return "../test/" + sc.nextLine() + ".txt";
	}
}