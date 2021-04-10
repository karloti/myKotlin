fun main() {
    val sum = readLine()!!.toInt()
    val (a, b, c) = readLine()!!.split(" ").map(String::toInt)
    var count = 1
    for (ai in 0..sum step a)
        for (bi in 0..(sum - ai) step b)
            for (ci in 0..(sum - ai - bi) step c)
                if (ai + bi + ci == sum) println("#${count++} -> ${ai / a}x$a + ${bi / b}x$b + ${ci / c}x$c = $sum")
}