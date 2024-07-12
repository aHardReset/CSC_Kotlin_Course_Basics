package jetbrains.kotlin.course.almost.done

fun trimPicture(picture:String) = picture.trimIndent()

fun applyBordersFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val pictureLines = picture.lines()
    val result = MutableList(pictureLines.size + 2){""}
    var line:String
    var endSeparator: String

    result[0] = "$borderSymbol".repeat(pictureWidth+4)
    result[pictureLines.size + 1] = "$borderSymbol".repeat(pictureWidth+4)
    for(idx in pictureLines.indices) {
        line = pictureLines[idx]
        endSeparator = "$separator".repeat(1 + pictureWidth - line.length)
        result[idx+1] = "$borderSymbol$separator$line$endSeparator$borderSymbol"
    }
    return result.joinToString(separator = System.lineSeparator())
}

fun applySquaredFilter(picture: String): String {
    val withBorder = applyBordersFilter(picture)
    val squared = StringBuilder()
    val withBorderLines = withBorder.lines()
    for (i in withBorderLines.indices){
        if (i < withBorderLines.size-1){
            squared.append(withBorderLines[i].repeat(2))
            squared.append(System.lineSeparator())
        }
    }
    squared.append(squared)
    squared.append(withBorderLines[withBorderLines.size - 1].repeat(2))
    return squared.toString()
}

fun applyFilter(picture: String, filterName: String): String {
    return when (filterName) {
        "borders" -> applyBordersFilter(picture)
        "squared" -> applySquaredFilter(picture)
        else -> error("Filer not supported")
    }
}

fun safeReadLine(): String = readlnOrNull() ?: error("Input is null")

fun chooseFilter(): String{
    var filter: String
    do {
        println("Please choose the filter: 'borders' or 'squared'")
        filter = safeReadLine()
    } while (filter != "borders" && filter != "squared")
    return filter
}

fun main() {
    // Uncomment this code on the last step of the game

    // photoshop()
}
