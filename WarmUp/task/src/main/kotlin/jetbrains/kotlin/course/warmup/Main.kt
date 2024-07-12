package jetbrains.kotlin.course.warmup

// You will use this function later
fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game: one chooses a word (a sequence of letters), " +
            "the other guesses it. In this version, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, with $secretExample as the hidden word, the BCDF guess will " +
            "give 1 full match (C) and 1 partial match (B)."

fun generateSecret() = "ABCD"

fun countPartialMatches(secret: String, guess: String): Int =
    countAllMatches(secret, guess) - countExactMatches(secret, guess)


fun countAllMatches(secret: String, guess: String): Int  =
    guess.toSet().sumOf { char ->
        minOf(secret.count { it == char }, guess.count { it == char })
    }


fun countExactMatches(secret: String, guess:String) = secret.filterIndexed {i, char -> char == guess[i]}.length

fun isComplete(secret: String, guess: String) = secret == guess

fun printRoundResults(secret: String, guess: String) =
    println("Your guess has ${countExactMatches(secret, guess)} full matches and ${countPartialMatches(secret, guess)} partial matches.")

fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int) =
    complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) =
    !complete && attempts > maxAttemptsCount

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int){
    var guess = ""
    var attempts = 0
    var complete: Boolean
    do {
        println("Please input your guess. It should be of length $wordLength.")
        guess = safeReadLine()
        complete = isComplete(secret, guess)
        attempts += 1
        printRoundResults(secret, guess)

    } while (!isWon(complete, attempts, maxAttemptsCount) && !isLost(complete, attempts, maxAttemptsCount))

    if (isLost(complete, attempts, maxAttemptsCount)) println("Sorry, you lost! :( My word is $secret") else println("Congratulations! You guessed it!")
}

fun main() {
    // Write your solution here
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val secret = generateSecret()
    println(getGameRules(wordLength, maxAttemptsCount, secretExample))
    playGame(secret, wordLength, maxAttemptsCount)
}
