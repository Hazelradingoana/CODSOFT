package src.main.java.numberGame;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static int Guess;
    private static int number;
    private static int score;




    public static void main(String args[]){
        System.out.println("Welcome to the number guessing Game!!! ");
        run();
        
    }

    public static int RandomNumber(){
        
    Random rand = new Random(); 
    int int_random =  rand.nextInt(10); 
    System.out.println(int_random);
    return int_random;
        
    }

    public static int  userInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Guess the random number between (0-9)");
        int guess = scanner.nextInt();
        return guess;
        
    }

    public static void checkGuess(){
               
        if (Guess  > number ){
            System.out.println("Your guess is to high ");
        }else if(Guess  < number ){
        System.out.println("Your guess is to low ");
        }
    }
    
    public static void run(){



        number = RandomNumber();
        int guessesNumber = 5;
        score =0;
        while( guessesNumber>0){
            Guess = userInput();
            checkGuess();
            guessesNumber--;
            score++;
            if (Guess== number){
                System.out.println("Congratulations you got it right!! ");
                System.out.println("You won after " + score + " tries! ");
                break;
            }

            if (guessesNumber !=0){
                System.out.println("Guesses left: " + guessesNumber);
            }

            else {
                System.out.println("No more Guesses left.");
                System.out.println("The number was: " + number);
                break;
            }

            
            
        }
    }

}




