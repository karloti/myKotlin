import kotlin.system.measureTimeMillis

class QuickSort(private val a: IntArray) {
    private var tmp: Int = 0
    private fun swap(i: Int, j: Int) {
        tmp = a[i]; a[i] = a[j]; a[j] = tmp
    }

    init {
        sort(0, a.lastIndex)
    }

    private fun partition(lowIndex: Int, highIndex: Int): Int {
        var pivotIndex = (lowIndex + highIndex) / 2
        pivotIndex = when {
            a[lowIndex] <= a[highIndex] == a[highIndex] <= a[pivotIndex] -> highIndex
            a[lowIndex] <= a[pivotIndex] == a[pivotIndex] <= a[highIndex] -> pivotIndex
            else -> lowIndex
        }
        val pivot = a[pivotIndex]

        swap(pivotIndex, highIndex)
        pivotIndex = lowIndex
        for (i in lowIndex until highIndex)
            if (a[i] < pivot) swap(i, pivotIndex++)
        swap(highIndex, pivotIndex)

        return pivotIndex
    }

    private fun sort(lowIndex: Int, highIndex: Int) {
        if (lowIndex < highIndex) {
            val pivotIndex = partition(lowIndex, highIndex)
            sort(lowIndex, pivotIndex - 1)
            sort(pivotIndex + 1, highIndex)
        }
    }
}

fun main() {
    val size = 100_000_000
    val a1 = IntArray(size) { (1..size).random() / (1..10).random() }
    val a2 = a1.clone()

    println(a1.joinToString(" ", "a1 = [", "]", 20, " ... of ${a1.size - 20}") { "%d".format(it) })

    println("\nStart Internal!")
    measureTimeMillis {
        a1.sort()
    }.let {
        println(a1.joinToString(" ", "a1 = [", "]", 20, " ... of ${a1.size - 20}") { "%d".format(it) })
        println("Finished $it ms")
    }

    println("\nStart Karlo!")
    measureTimeMillis {
        QuickSort(a2)
    }.let {
        println(a2.joinToString(" ", "a2 = [", "]", 20, " ... of ${a2.size - 20}") { "%d".format(it) })
        println("Finished $it ms")
    }

    println("\nEquality: " + a1.contentEquals(a2))
}