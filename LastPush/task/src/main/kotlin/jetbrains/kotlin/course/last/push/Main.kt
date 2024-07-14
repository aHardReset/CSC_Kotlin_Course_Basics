package jetbrains.kotlin.course.last.push


// You will use this function later
fun getPattern(): String {
    println(
        "Do you want to use a pre-defined pattern or a custom one? " +
                "Please input 'yes' for a pre-defined pattern or 'no' for a custom one"
    )
    do {
        when (safeReadLine()) {
            "yes" -> {
                return choosePattern()
            }
            "no" -> {
                println("Please, input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

// You will use this function later
fun choosePattern(): String {
    do {
        println("Please choose a pattern. The possible options: ${allPatterns().joinToString(", ")}")
        val name = safeReadLine()
        val pattern = getPatternByName(name)
        pattern?.let {
            return@choosePattern pattern
        }
    } while (true)
}

fun getPatternHeight(pattern: String) = pattern.lines().size

fun fillPatternRow(patternRow: String, patternWidth: Int): String {
    check(patternRow.length <= patternWidth) { "Not a valid row" }
    val filled = StringBuilder(patternRow)
    while (filled.length < patternWidth) {
        filled.append(separator)
    }
    return filled.toString()
}

fun repeatHorizontally(pattern: String, repeats: Int, patternWidth: Int): String {
    val result = StringBuilder()
    val patternLines = pattern.lines()
    for (i in patternLines.indices){
        result.append(fillPatternRow(patternLines[i], patternWidth).repeat(repeats))
        if (i < patternLines.size - 1) result.append(newLineSymbol)
    }
    return result.toString()
}

fun repeatHorizontally2(pattern: String, repeats: Int, patternWidth: Int): String {
    val result = StringBuilder()
    val patternLines = pattern.lines()
    for (i in patternLines.indices){
        result.append(fillPatternRow(patternLines[i], patternWidth).repeat(repeats))
        if (i < patternLines.size - 1) result.append("\r\n")
    }
    return result.toString()
}

fun repeatHorizontallyWithGaps(pattern: String, n: Int, shifted: Boolean = false):String {
    val patternLines = pattern.lines()
    val patternWidth = getPatternWidth(pattern)
    val result = StringBuilder()
    for (i in patternLines.indices) {
        for (j in 0 until n) {
            if (shifted){
                if(j%2 == 0){
                    result.append(" ".repeat(patternWidth))
                } else {
                    result.append(fillPatternRow(patternLines[i], patternWidth))
                }
            }else{
                if(j%2 == 0){
                    result.append(fillPatternRow(patternLines[i], patternWidth))

                } else {
                    result.append(" ".repeat(patternWidth))
                }
            }
        }
        if (i < patternLines.size - 1) result.append(newLineSymbol)
    }
    return result.toString()
}

fun repeatHorizontallyWithGaps2(pattern: String, n: Int, shifted: Boolean = false):String {
    val patternLines = pattern.lines()
    val patternWidth = getPatternWidth(pattern)
    val result = StringBuilder()
    for (i in patternLines.indices) {
        for (j in 0 until n) {
            if (shifted){
                if(j%2 == 0){
                    result.append(" ".repeat(patternWidth))
                } else {
                    result.append(fillPatternRow(patternLines[i], patternWidth))
                }
            }else{
                if(j%2 == 0){
                    result.append(fillPatternRow(patternLines[i], patternWidth))

                } else {
                    result.append(" ".repeat(patternWidth))
                }
            }
        }
        if (i < patternLines.size - 1) result.append("\r\n")
    }
    return result.toString()
}

fun dropTopFromLine(line: String, newWidth: Int, patternHeight: Int, patternWidth: Int): String {
    return if (patternHeight > 1){
        line.split(newLineSymbol).drop(1).joinToString(newLineSymbol)
    } else {
        line
    }
}

fun canvasGenerator(pattern: String, width: Int, height: Int): String {
    val patternWidth = getPatternWidth(pattern)
    val repeatedPattern = repeatHorizontally(pattern, width, patternWidth)
    val result = StringBuilder()
    val patternWithoutTopLine = dropTopFromLine(repeatedPattern, patternWidth, pattern.lines().size, patternWidth)
    for (i in 0 until height) {
        if (i == 0) result.append(repeatedPattern) else result.append(patternWithoutTopLine)
        result.append(newLineSymbol)
    }
    return result.toString()
}

fun canvasGenerator2(pattern: String, width: Int, height: Int): String {
    val patternWidth = getPatternWidth(pattern)
    val repeatedPattern = repeatHorizontally2(pattern, width, patternWidth)
    val result = StringBuilder()
    val patternWithoutTopLine = dropTopFromLine(repeatedPattern, patternWidth, pattern.lines().size, patternWidth)
    for (i in 0 until height) {
        if (i == 0) result.append(repeatedPattern) else result.append(patternWithoutTopLine)
        result.append("\r\n")
    }
    return result.toString()
}

fun canvasWithGapsGenerator(pattern: String, width: Int, height: Int): String {
    val result = StringBuilder()
    if (width == 1){
        for (i in 0 until height) {
            result.append(repeatHorizontallyWithGaps(pattern, width))
            result.append(newLineSymbol)
        }
    } else{
        for (i in 0 until height) {
            if (i % 2 == 0){
                result.append(repeatHorizontallyWithGaps(pattern, width))
            }
            else{
                result.append(repeatHorizontallyWithGaps(pattern, width, true))
            }
            if (i < height - 1) result.append(newLineSymbol)
        }
    }
    return  result.toString()
}

fun canvasWithGapsGenerator2(pattern: String, width: Int, height: Int): String {
    val result = StringBuilder()
    if (width == 1){
        for (i in 0 until height) {
            result.append(repeatHorizontallyWithGaps2(pattern, width))
            result.append("\r\n")
        }
    } else{
        for (i in 0 until height) {
            if (i % 2 == 0){
                result.append(repeatHorizontallyWithGaps2(pattern, width))
            }
            else{
                result.append(repeatHorizontallyWithGaps2(pattern, width, true))
            }
            result.append("\r\n")
        }
    }
    return  result.toString()
}

// You will use this function later
fun chooseGenerator(): String {
    var toContinue = true
    var generator = ""
    println("Please choose the generator: 'canvas' or 'canvasGaps'.")
    do {
        when (val input = safeReadLine()) {
            "canvas", "canvasGaps" -> {
                toContinue = false
                generator = input
            }
            else -> println("Please, input 'canvas' or 'canvasGaps'")
        }
    } while (toContinue)
    return generator
}

fun applyGenerator(pattern: String, generatorName: String, width: Int, height: Int): String {
    check((generatorName == "canvas" || generatorName == "canvasGaps")) { "Not a valid generator" }
    val trimmedPattern = pattern.trimIndent().replace(System.lineSeparator(), newLineSymbol)
    return if (generatorName == "canvas") canvasGenerator2(trimmedPattern, width, height) else canvasWithGapsGenerator2(trimmedPattern, width, height)
}

// You will use this function later
fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

fun main() {
    // Uncomment this code on the last step of the game

    val pattern = getPattern()
    val generatorName = chooseGenerator()
    println("Please input the width of the resulting picture:")
    val width = safeReadLine().toInt()
    println("Please input the height of the resulting picture:")
    val height = safeReadLine().toInt()

    println("The pattern:$newLineSymbol${pattern.trimIndent()}")

    println("The generated image:")
    println(applyGenerator(pattern, generatorName, width, height))
}
