fun main() {
    val n = readLine()!!.toInt()
    val grades = IntArray(4)
    repeat(n) { grades[readLine()!!.toInt() - 2]++ }
    println(grades.joinToString(" "))
}