package Main;
import java.util.Scanner;

public class Main{
    public static void main(String args[])
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Sistem Persamaan Linier, Determinan, dan Aplikasinya");
        System.out.println("====================================================");

        boolean exit = false;

        while(!exit){
            menu();
            System.out.println("====================================================");
            System.out.print("Input: ");
            int choice = myObj.nextInt();
            switch(choice){
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

                case 7:
                    System.exit(0);
            }
        }
    }

    public static void menu(){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi linier berganda");
        System.out.println("7. Keluar");
    }

    public static void submenu(){
        System.out.println("MENU");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
    }
}