package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	    final double RESIDENTIAL_PRICE_0_6000 = 0.18; //SR
        final double RESIDENTIAL_PRICE_OVER_6000 = 0.30; //SR
        final double COMMERCIAL_PRICE_0_6000 = 0.20; //SR
        final double COMMERCIAL_PRICE_OVER_6000 = 0.30; //SR
        final double AGRICULTURAL_PRICE_0_6000 = 0.16; //SR
        final double AGRICULTURAL_PRICE_OVER_6000 = 0.20; //SR
        final double GOVERNMENTAL_PRICE= 0.32; //SR
        final double INDUSTRIAL_PRICE= 0.18; //SR
        final double PRIVATE_EDUCATIONAL_MEDICAL_PRICE = 0.18; //SR
        int counter = 1;
        double power = 0;
        int current = 0;
        int voltage = 0;
       double totalPower = 0;
       double totalCost = 0;
       int category = 0;
       double cost = 0;
       double maximumCost = 0 ;
       double minimumCost = Double.MAX_VALUE;
        Scanner kb = new Scanner(System.in);
        while (true) {
            System.out.printf("Recipient #%d information:\n", counter);
            System.out.println("Insert current > ");
            current = kb.nextInt();
            System.out.println("Insert voltage > ");
            voltage = kb.nextInt();
            if (voltage == -1 || current == -1)
                break;
            power = ((current * voltage)/1000);
            System.out.println("Enter Consumption Category: ");
            System.out.println("1 = Residential \n2 = commercial \n3 = Agricultural & Charities \n4 = Government \n5 = Industrial \n6 = Private Educational and Medical");
            category = kb.nextInt();
            switch (category) {
                case 1:
                    if (power <= 6000) {
                        cost = (power * RESIDENTIAL_PRICE_0_6000);
                        break;
                    } else {
                        cost = ((6000 * RESIDENTIAL_PRICE_0_6000) + ((power - 6000) * RESIDENTIAL_PRICE_OVER_6000));
                        break;
                    }
                case 2:
                    if (power <= 6000) {
                        cost = (power * COMMERCIAL_PRICE_0_6000);
                        break;
                    } else {
                        cost = ((6000 * COMMERCIAL_PRICE_0_6000) + ((power - 6000) * COMMERCIAL_PRICE_OVER_6000));
                        break;
                    }
                case 3:
                    if (power <= 6000) {
                        cost = (power * AGRICULTURAL_PRICE_0_6000);
                        break;
                    } else {
                        cost = ((6000 * AGRICULTURAL_PRICE_0_6000) + ((power - 6000) * AGRICULTURAL_PRICE_OVER_6000));
                        break;
                    }
                case 4:
                    cost = power * GOVERNMENTAL_PRICE;
                    break;
                case 5:
                    cost = power * INDUSTRIAL_PRICE;
                    break;
                case 6:
                    cost = power * PRIVATE_EDUCATIONAL_MEDICAL_PRICE;
                    break;
                default:
                    System.out.println("Incorrect Category ! program will stop now !");
                    System.out.println("---------------------------------");
                    System.exit(0);
            }
            if (cost > maximumCost)
                maximumCost = cost;
            if (cost < minimumCost)
                minimumCost = cost;
            totalPower += power;
            totalCost += cost;
            System.out.printf("power = %.2f Kilo Watts \nCost = %.2f SR\n\n", power , cost);
                    counter++;
         }
        if (totalPower == 0 || totalCost == 0 )
            System.out.println("No recipient information is inserted!");
        else
        System.out.printf("total Power = %.2f Kilo Watts \nTotal Cost = %.2f SR \nMaximum cost = %.2f SR \nMinimum cost = %.2f SR", totalPower, totalCost, maximumCost, minimumCost);

        }
    }

