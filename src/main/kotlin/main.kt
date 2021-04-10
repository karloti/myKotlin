fun getVolume(length: Int, width: Int = 1, height: Int = 1): Int {
    return length * width * height
}
fun main() {
    println(getVolume(1,5))
}