import java.util.Scanner;
public class Main{
    static Scanner sc;
    public static void main(String args[]){
        int choice, subchoice;
        sc = new Scanner(System.in);
        System.out.println("Sistem Persamaan Linier, Determinan, dan Aplikasinya");
        header();

        while(true){
            menu();
            System.out.print("Input: ");
            choice = sc.nextInt();
            clear();


            switch(choice){
                case 1:
                    clear();
                    submenu();
                    System.out.print("Input: ");
                    subchoice = sc.nextInt();
                    switch(subchoice){
                        case 1:
                            SPL.SPLGauss();
                            break;
                        case 2:
                            System.out.println("sub2 success");
                            break;
                        case 3:
                            System.out.println("sub3 success");
                            break;
                        case 4:
                            System.out.println("sub4 success");
                            break;
                        default:
                            wrongInput();
                    }
                    break;
                case 2:
                    clear();
                    submenu();
                    header();
                    System.out.print("Input: ");
                    subchoice = sc.nextInt();
                    switch(subchoice){
                        case 1:
                            System.out.println("sub1 success");
                            break;
                        case 2:
                            System.out.println("sub2 success");
                            break;
                        case 3:
                            System.out.println("sub3 success");
                            break;
                        case 4:
                            System.out.println("sub4 success");
                            break;
                        default:
                            wrongInput();
                    }
                    break;
                case 3:
                    clear();
                    submenu();
                    header();
                    System.out.print("Input: ");
                    subchoice = sc.nextInt();
                    switch(subchoice){
                        case 1:
                            System.out.println("sub1 success");
                            break;
                        case 2:
                            System.out.println("sub2 success");
                            break;
                        case 3:
                            System.out.println("sub3 success");
                            break;
                        case 4:
                            System.out.println("sub4 success");
                            break;
                        default:
                            wrongInput();
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    wrongInput();
            }
        }
    }

    static void header(){
        System.out.println("=====================================================");
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
    }

    static void submenu(){
        System.out.println("==================     SUB MENU    ==================");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        header();
    }

    static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void prompt(){
        
    }
}