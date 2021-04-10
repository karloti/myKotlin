fun main() {
    val n = readLine()!!
    val point = n.length / 2
    if (n.length % 2 == 0) {
        print(n.substring(0 until point - 1))
        print(n.substring(point + 1))
    } else {
        print(n.removeRange(point..point))
    }
}