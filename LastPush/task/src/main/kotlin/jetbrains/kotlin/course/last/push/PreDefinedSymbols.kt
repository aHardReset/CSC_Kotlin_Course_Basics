@file:Suppress("MayBeConst")

package jetbrains.kotlin.course.last.push

val separator = ' '
val newLineSymbol = "\n"

fun getPatternWidth(pattern: String) = pattern.lines().maxOfOrNull { it.length } ?: 0

fun getPatternByName(name: String) = allPatternsMap[name]

fun allPatterns() = allPatternsMap.keys
