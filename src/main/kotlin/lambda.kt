
fun Int.sum(other: Int) = this.plus(other)

var sum: Int.(Int) -> Int = { plus(it) + 5}

fun main() {
    sum = Int::plus
    println(2.sum(12))
}
