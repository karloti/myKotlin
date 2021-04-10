fun main() {
    val n = readLine()!!.toInt()
    List(n) { readLine()!!.toInt() }
        .chunked(n / 2)
        .map { it.sum() }
        .let { if (it[0]>it[1]) "YES" else "NO" }
        .let(::println)
}