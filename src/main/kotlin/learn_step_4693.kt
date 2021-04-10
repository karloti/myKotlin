fun main() = Array(readLine()!!.toInt()) { readLine()!!.toInt() }.minOf { it }.let(::println)
