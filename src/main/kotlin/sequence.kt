val sequence = sequence {
    repeat(Int.MAX_VALUE) { num ->
        repeat(num) { yield(num) }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val seq = sequence.take(n)

    println(seq.joinToString(" "))

}