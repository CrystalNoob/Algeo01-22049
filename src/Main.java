import java.util.Scanner;
import java.io.File;

public class Main{
    static Scanner sc;
    public static void main(String args[]){
        // Decalaration
        String choice, subchoice, inputChoice;
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
                                    SPL.SPLGauss(getFileNameToOutput(), false, null);
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGauss(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        e.getStackTrace();
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
                                    SPL.SPLGaussJordan(getFileNameToOutput(), false, null);
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGaussJordan(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        e.getStackTrace();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;

                        // Matriks balikan
                        case "3":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){

                                // Manual input
                                case "1":
                                    SPL.SPLInverse();

                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGauss(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        e.getStackTrace();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;
                        
                        // Cramer
                        case "4":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){

                                // Manual input
                                case "1":
                                    SPL.SPLCramer();
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGauss(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        e.getStackTrace();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;
                        default:
                            wrongInput();
                        }
                    break;

                // Determinan
                case "2":
                    clear();
                    submenuDet();
                    header();
                    System.out.print("Input: ");
                    subchoice = sc.next();
                    switch(subchoice){
                        
                        // Reduksi baris
                        case "1":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){

                                // Manual input
                                case "1":
                                    System.out.printf("Determinan:\n%f\n", Matrix.DetReduksiBaris(Matrix.readDet()));
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGauss(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        e.getStackTrace();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;

                        // Ekspansi kofaktor
                        case "2":
                            inputPrompt();
                            inputChoice = sc.next();
                            switch(inputChoice){

                                // Manual input
                                case "1":
                                    System.out.printf("Determinan:\n%f\n", Matrix.DetEkspansiKofaktor(Matrix.readDet()));
                                    break;

                                // Read from file
                                case "2":
                                    try{
                                        txt = new File(getFileNameToInput());
                                        Scanner txtReader = new Scanner(txt);
                                        SPL.SPLGauss(getFileNameToOutput(), true, txtReader);
                                    }
                                    catch(Exception e){
                                        e.getStackTrace();
                                    }
                                    break;
                                default:
                                    wrongInput();
                            }
                            break;
                    }
                    break;

                // Inverse
                case "3":
                    break;

                // Interpolasi Polinomial
                case "4":
                    break;

                // Interpolasi Bicubic Spline
                case "5":
                    break;

                // Regresi Linear Berganda
                case "6":
                    break;

                // Exit
                case "7":
                    clear();
                    System.exit(0);
                    break;

                // Invalid input
                default:
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
        System.out.println("2. Metode OBE");
        System.out.println("3. Metode adjoin");
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

    // Input file name getter
    static String getFileNameToInput(){
        sc = new Scanner(System.in);
        System.out.print("Input file name: ");
        return "../test" + sc.nextLine() + ".txt";
    }

    // Output file name getter
    static String getFileNameToOutput(){
		sc = new Scanner(System.in);
		System.out.print("Solution file name: ");
		return "../test/" + sc.nextLine() + ".txt";
	}
}