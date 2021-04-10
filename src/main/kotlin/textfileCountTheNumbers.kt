import java.io.File

fun main() {
    File("\\words_with_numbers.txt")
        .readLines()
        .count { Regex("\\d+").matches(it) }
        .let(::println)
}