package com.company;
import java.util.InputMismatchException;
import java.util.Scanner;
public class HW2 {
    private static int maxIteration=0;
    private static double[] coefficients = new double[4];


    public static void main(String [] args) {

        int choice;
        do {
                try {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("1-Enter Polynomial\n2-Evaluate Polynomial\n3-Find Zero of Function\n4-Exit");
                    System.out.print("\n\tEnter your choice: ");
                    Scanner kb = new Scanner(System.in);
                    choice = kb.nextInt();
                    switch (choice) {
                        case 1:
                            case1();
                            break;
                        case 2:
                            case2();
                            break;
                        case 3:
                            case3();
                            break;
                        case 4:
                            case4();
                            break;
                        default:
                            System.out.println("Please enter a number from 1 to 4 to choose from the menu");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: please try again and enter a number");
                }
        }while (true);
    }



        private static void case1(){
            System.out.println("Your choice (1) to enter polynomial coefficients");
            input();
            System.out.println();
        }




        private static void case2(){
            int n;
            Scanner kb = new Scanner(System.in);
            System.out.println("Your choice (2) to evaluate polynomial");
            do {
                System.out.println("How many points to evaluate: ");
                n = kb.nextInt();
                if (n < 1)
                    System.out.println("number of points cannot be Zero or less");
            }while (n < 1);
            for (int i = 0; i < n; i++) {
                System.out.println("Value of x: ");
                double x = kb.nextDouble();
                double px = evalFun(x);
                System.out.printf("P(%.4f) = %.4f\n", x, px);
            }
            System.out.println();
        }





        private static void case3(){
            Scanner kb = new Scanner(System.in);
            System.out.println("Your choice (3) to find zero of polynomial function");
            System.out.println("Enter approximate solution: ");
            double Xn = kb.nextDouble();
            System.out.println("Enter accuracy: ");
            double accuracy = kb.nextDouble();
            System.out.println("Enter the maximum number of iterations: ");
            maxIteration = kb.nextInt();
            newtonRaphsonMethod(Xn, accuracy);
        }





        private static void case4(){
            System.out.println("Your choice (4) to exit\nThanks for using my program !");
            System.exit(0);
        }





    private static void input() {
        boolean error;
        do {
            try {
                Scanner kb = new Scanner(System.in);
                for (int i = coefficients.length - 1; i >= 0; i--) {
                    System.out.printf("Enter coefficient a(%d)", i);
                    coefficients[i] = kb.nextDouble();
                }
                System.out.print("P(X) = ");
                for (int i = coefficients.length - 1; i >= 0; i--) {
                    if (coefficients[i] > 0) {
                        if (i == 0)
                            System.out.printf("+%.2f", coefficients[i]);
                        else if (i == 1)
                            System.out.printf("+%.2f.X", coefficients[i]);
                        else
                            System.out.printf("+%.2f.X^%d", coefficients[i], i);
                    } else if (coefficients[i] < 0) {
                        if (i == 0)
                            System.out.printf("%.2f", coefficients[i]);
                        else if (i == 1)
                            System.out.printf("%.2f.X", coefficients[i]);
                        else
                            System.out.printf("%.2f.X^%d", coefficients[i], i);
                    }

                }
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: the coefficients must be numbers");
                error = true;
            }
        } while (error);
    }




    private static double evalFun(double x){
        return ((coefficients[3] * Math.pow(x , 3)) + (coefficients[2] * Math.pow(x , 2)) +(coefficients[1] * Math.pow(x , 1)) + (coefficients[0] * Math.pow(x , 0)));
        }




    private static double evalF_un(double x) {
        return ((3 * coefficients[3] * Math.pow(x, 2)) + 2 * (coefficients[2] * Math.pow(x, 1)) + 1 * (coefficients[1]));
    }




    private static void newtonRaphsonMethod(double Xn, double accuracy){
        double[] X = new double[maxIteration];
        X[0]= Xn;
        int i = 0;
        for (; i<(X.length-2); i++) {
            double fxn = evalFun(X[i]);
            double f_xn = evalF_un(X[i]);
            X[i+1] = X[i] - (fxn/f_xn);
            if (Math.abs(X[i+1] - X[i])<= accuracy){
                System.out.printf("Zero around the point (x=%.3f) = %.7f)\n", Xn, X[i+1]);
                System.out.printf("Zero has been found after %d iterations.\n", i+1);
                break;
            }
        }
        if (Math.abs(X[i+1] - X[i])> accuracy){
            System.out.printf("No solution found with given accuracy (%.5f) around %.3f, after %d iterations.\n", accuracy,Xn,i+2);
        }

    }
}
