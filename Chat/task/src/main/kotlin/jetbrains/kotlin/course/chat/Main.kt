package jetbrains.kotlin.course.chat

fun main() {
    // Write your solution here
    println("Hello! I'm glad to meet you, let me get to know you better! What is your name?")
    val userName = readlnOrNull()
    println("Nice to meet you, $userName! My name is Kotlin Bot! I am a young programming language created in 2010. How old are you?")
    val userAge = readlnOrNull()
    println("$userAge is great! I hope you successfully complete this course! Anyone can learn programming at any age!")
}
