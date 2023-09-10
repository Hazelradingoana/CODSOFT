package src.main.java.StudentGradeCalculator;

import java.util.Scanner;

public class Calculator {

    public static void main(String[] args)  {
       int AveragePercentage =  StudentInput();
       StudentGrade(AveragePercentage);
        
    }


    public static int StudentInput(){

        Scanner scanner = new Scanner(System.in);
        int PS,LS,Acc,Math,Eng,cat;

        System.out.println("Please enter the obtained marks (Out of 100) for the following subjects: ");
        do {
            System.out.print("Physical Science: ");
            PS = scanner.nextInt();
        } while (PS < 0 || PS > 100); 
        
        do {
            System.out.print("Life Science: ");
            LS = scanner.nextInt();
        } while (LS < 0 || LS > 100); 
        do {
            System.out.print("Accounting: ");
            Acc = scanner.nextInt();
        } while (Acc < 0 || Acc > 100);
        
        do {
            System.out.print("Mathematics: ");
            Math = scanner.nextInt();
        } while (Math < 0 || Math > 100);
        do {
            System.out.print("English: ");
            Eng = scanner.nextInt();
        } while (Eng < 0 || Eng > 100);
        
        do {
            System.out.print("Computer Application Technology: ");
            cat = scanner.nextInt();
        } while (cat < 0 || cat > 100);

        System.out.println("\nYour results are as follows: ");
        System.out.println("1.Physical Science: "+PS);
        System.out.println("2.Life Science: "+LS);
        System.out.println("3. Accounting: "+Acc);
        System.out.println("4.Mathematics: "+Math);
        System.out.println("5.English: "+Eng);
        System.out.println("6.Computer Application Technology: "+cat);


        int Marks = PS +LS  + Acc + Math + Eng +cat; 
        System.out.println("Your overall Mark is "+Marks+ "out of 600 for the 6 subjects");       
        int AveragePercentage = (Marks * 100) / 600;
        System.out.println("Your Average Percentage is "+AveragePercentage);
        scanner.close();       
        return AveragePercentage;


    }

    public static void StudentGrade(int AveragePercentage){

        if(AveragePercentage >=90){
            System.out.println("You got A+");
        }else if (AveragePercentage >=80){
            System.out.println("You got A");

        }else if (AveragePercentage >=70){
            System.out.println("You got B");

        }else if (AveragePercentage >=60){
            System.out.println("You got C");

        }else if (AveragePercentage >=50){
            System.out.println("You got D");

        }else{
            System.out.println("You FAILED");
        
        }
    }
    
}
