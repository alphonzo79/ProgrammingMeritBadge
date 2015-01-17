import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by joe on 1/14/15.
 */
public class Main {
    private static final int MAX_RANGE = 100;
    private static final int ALLOWED_ATTEMPTS = 10;

    private static int attemptCount;
    private static int currentGuess;
    private static int maxEdge;
    private static int minEdge;

    public static void main(String[] args) {
        boolean keepPlaying = true;

        Random rand = new Random(System.currentTimeMillis());

        while(keepPlaying) {
            resetTrackers();

            System.out.println("\n\nThink of a number between 1 and " + MAX_RANGE + ". I will try to guess it in fewer than " + ALLOWED_ATTEMPTS + " attempts.");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Ready? I'm going to start guessing.");

            boolean correct = false;
            while(!correct) {
                attemptCount++;
                currentGuess = rand.nextInt(maxEdge);
                //We can determine the max for the random number, but not the min. Guard against a guess that is less
                // than our minimum by going right between the two;
                if(currentGuess <= minEdge) {
                    currentGuess = (minEdge + maxEdge) / 2;
                }

                System.out.println("Is your number " + currentGuess + "? (Y or N)");
                correct = readYesNoInput();
                if(!correct) {
                    System.out.println("Is my number too high or too low? (H or L)");
                    if(readHighOrLowInput()) {
                        maxEdge = currentGuess;
                    } else {
                        minEdge = currentGuess;
                    }
                }

                if(attemptCount == MAX_RANGE) {
                    break;
                }
            }

            if(attemptCount <= ALLOWED_ATTEMPTS) { //we got the answer within our target number of attempts
                System.out.println("I Win! I guessed your number in " + attemptCount + " attempts!");
            } else if(correct) { //we got the right number, but not in the target number of attempts
                System.out.println("Bummer, you win. It took me " + attemptCount + " guesses to get your number");
            } else { //We have guessed every single number in the range and still haven't guess it
                System.out.println("I think you cheated. I have guessed every number in the valid range!");
            }

            System.out.println("Do you want to play again?");
            keepPlaying = readYesNoInput();
        }

        System.out.println("Thanks for playing, that was fun");

        System.exit(0);
    }

    private static void resetTrackers() {
        attemptCount = 0;
        currentGuess = -1;
        maxEdge = MAX_RANGE + 1;
        minEdge = -1;
    }

    private static boolean readYesNoInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String answer = br.readLine();
            if(answer.equals("Y") || answer.equals("y")) {
                return true;
            } else if(answer.equals("N") || answer.equals("n")) {
                return false;
            } else {
                System.out.println("Please use 'Y' for Yes or 'N' for No");
                return readYesNoInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean readHighOrLowInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String answer = br.readLine();
            if(answer.equals("H") || answer.equals("h")) {
                return true;
            } else if(answer.equals("L") || answer.equals("l")) {
                return false;
            } else {
                System.out.println("Please use 'H' for Too High or 'L' for Too Low");
                return readHighOrLowInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
