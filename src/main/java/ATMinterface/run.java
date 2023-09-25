package ATMinterface;

import java.util.Scanner;

public class run {
    private static int AccountBalance =0;
    private static int Amount;

    public static void main(String args[]){
        GetInput();
    }

    public static void GetInput() {
        Scanner scanner = new Scanner(System.in);
        int command;

        do {
            System.out.println("1.Withdrawing ");
            System.out.println("2.Depositing ");
            System.out.println("3.Check balance ");
            System.out.println("4.Cancel ");
            System.out.println("What would you like to do (1 - 4):");
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    System.out.print("How much would you like to withdraw? ");
                    int amount = scanner.nextInt();
                    withdrawing(amount);
                    break;
                case 2:
                    System.out.print("How much would you like to deposit? ");
                    amount = scanner.nextInt();
                    depositing(amount);
                    break;
                case 3:
                    CheckBalance();
                    break;
                case 4:
                    System.out.println("Transaction cancelled.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1 - 4.");
            }
        } while (command != 4);
    }



    public static int withdrawing(int Amount){

        if (Amount > AccountBalance){
            System.out.println("You have insufficient Balance ");
        } else{
            AccountBalance -= Amount;
            System.out.println("You've withdrawn R"+Amount+".00");
        }
        return AccountBalance;


    }

    public static int depositing(int Amount){
        AccountBalance += Amount;
        System.out.println("You've deposited R"+Amount+".00");

        return AccountBalance;
    }

    public static void CheckBalance(){
        System.out.println("Your Availble funds are R"+AccountBalance+".00");

    }


}

