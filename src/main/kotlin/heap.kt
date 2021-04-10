import java.util.*

data class Data(val name: String = "", val egn: Long = 0) {
    companion object : Comparator<Data> {
        override fun compare(o1: Data?, o2: Data?) = (o2!!.egn - o1!!.egn).toInt()
    }
}

fun main() {
    val heap = PriorityQueue<Pair<String, Int>>(compareBy { it.second })
    heap.add("Jack" to 10)
    heap.add("Alex" to 5)
    heap.add("Katherin" to 3)
    heap.add("Watsons" to 100)
    heap.add("Jason" to 4)
    heap.add("Hector" to 5)

    heap.forEachIndexed { index, pair -> print("index = ${index}");println(" pair = ${pair}")}
    println("heap = ${heap.remove()}")
    heap.forEachIndexed { index, pair -> print("index = ${index}");println(" pair = ${pair}")}
    println("heap = ${heap.remove()}")
    heap.forEachIndexed { index, pair -> print("index = ${index}");println(" pair = ${pair}")}
    println("heap = ${heap.remove()}")
    heap.forEachIndexed { index, pair -> print("index = ${index}");println(" pair = ${pair}")}
    println("heap = ${heap.remove()}")
    heap.forEachIndexed { index, pair -> print("index = ${index}");println(" pair = ${pair}")}
    println("heap = ${heap.remove()}")

    while (!heap.isEmpty()) {
        println(heap.remove())
    }

    val other = PriorityQueue(Data)
    other.add(Data("Koko", 7304138080))
    other.add(Data("Viki", 6304138080))
    println()
    other.forEach { println(it) }

}