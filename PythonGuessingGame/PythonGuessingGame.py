#!
#PythonGuessingGame.py
#Author: Joe rowley

#Python3

import datetime
import random

def main():
    MAX_RANGE = 100
    ALLOWED_ATTEMPTS = 10

    keepPlaying = True
    print('\n\nLet\'s play a game. You think of a number between 0 and ' + str(MAX_RANGE))

    random.seed(datetime.datetime.now().timestamp())

    while(keepPlaying):
        attemptCount, currentGuess, maxEdge, minEdge = initializeTrackers(MAX_RANGE, 0)

        input("\n\nThink of a number between 0 and " + str(MAX_RANGE)+ " and hit Enter when you're ready" + " ")

        guessCorrect = False
        while(guessCorrect is False):
            attemptCount += 1
            currentGuess = random.randint(minEdge, maxEdge)
            guessCorrect = getYesNoAnswer("My guess is " + str(currentGuess) + " ")

            if(guessCorrect is False):
                if(getHighLowResponse()):
                    #the randint is inclusive on both ends. So we need to subtract one from the current to avoid getting it again
                    maxEdge = currentGuess - 1
                else:
                    #the randint is inclusive on both ends. So we need to add one to the current to avoid getting it again
                    minEdge = currentGuess + 1

            if(attemptCount > MAX_RANGE):
                break

        if(attemptCount <= ALLOWED_ATTEMPTS): #We got the answer within the target number of attempts
            print("I win! I guess your number in " + str(attemptCount)+ " attempts" + " ")
        elif(guessCorrect): #We got the answer, but not in the allowed attempts
            print("Bummer, you win. It took me " + str(attemptCount) + " guesses to get your number" + " ")
        else: #We have guessed every number in the range. Something went wrong
            print("I think you cheated or made a mistake. I have guessed every number in the valid range" + " ")

        keepPlaying = getYesNoAnswer("Do you want to play again?" + " ")

    print("That was fun. Thanks for playing" + " ")


def initializeTrackers(maxRange, minRange):
    return [0, -1, maxRange, minRange]

def getYesNoAnswer(questionText):
    answer = input(questionText + " (Y or N)" + " ")
    if(answer is "Y" or answer is "y"):
        return True
    if(answer is "N" or answer is "n"):
        return False
    else:
        print("Please answer with Y or N" + " ")
        return getYesNoAnswer(questionText)

def getHighLowResponse():
    answer = input("Is my number too high or too low? (H or L)" + " ")
    if(answer is "H" or answer is "h"):
        return True
    elif(answer is "L" or answer is "l"):
        return False
    else:
        print("Please answer with H for Too High or L for Too Low" + " ")
        return getHighLowResponse()


if __name__ == '__main__':
    main()