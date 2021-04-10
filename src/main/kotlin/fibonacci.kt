fun fib(n: Int, numbers: IntArray = IntArray(n + 1) { -1 }): Int {

//    if (numbers[n] != -1) return numbers[n]

    println("n=$n numbers[$n]=${numbers[n]}")

    if (n < 2)
        numbers[n] = n
    else
        numbers[n] = fib(n - 1, numbers) + fib(n - 2, numbers)

    return numbers[n]
}

tailrec fun fibrec(n: Int, a: Int = 0, b: Int = 1):Int = if (n == 2) a + b else fibrec(n - 1, b, a + b)

fun main() {
    println(fib(6))
}