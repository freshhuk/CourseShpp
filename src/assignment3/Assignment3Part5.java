package assignment3;

import java.util.Random;

public class Assignment3Part5 {

    public static void main(String[] args) {
        //We run our method that will launch our game
        playBernoulliCasino();
    }

    public static void playBernoulliCasino() {
        //variables for counting how much money was earned and how many games were played
        int totalEarned = 0;
        int gamesPlayed = 0;

        //The cycle continues until the lucky winner earns 20 dollars or more.
        while (totalEarned < 20) {
            //We launch our method that simulates a game, namely tossing a coin and further results
            int earnings = playGame();
            //Calculates the amount we have earned
            totalEarned += earnings;
            //Add 1 which means we have played one game
            gamesPlayed++;


            System.out.println("This game, you earned $" + earnings);//We display how much we earned in this game
            System.out.println("Your total is $" + totalEarned);//We show how much we earned in total
        }

        //We deduce that the lucky player earned 20 dollars and how many games did he do it for
        System.out.println("It took " + gamesPlayed + " games to earn $20");
    }

    //A method that simulates one round of a coin toss game
    public static int playGame() {
        Random random = new Random();
        int winnings = 1;  // Sweaty puts $1 on the table

        while (true) {
            //Simulating a coin toss
            //If the result is true, then it is an eagle
            boolean isHeads = random.nextBoolean();

            if (isHeads) {
                winnings *= 2;  // Sweaty adds exactly the same amount to the amount on the table
            } else {
                return winnings;  // Everything on the table goes to the lucky person
            }
        }
    }
}
