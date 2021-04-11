import kotlin.system.measureTimeMillis

class QuickSort(private val a: IntArray) {
    private var tmp: Int = 0
    private fun swap(i: Int, j: Int) {
        tmp = a[i]; a[i] = a[j]; a[j] = tmp
    }

    init {
        sort(0, a.lastIndex)
    }

    private fun partition(lowIndex: Int, highIndex: Int): Pair<Int, Int> {
        val middleIndex = (lowIndex + highIndex) / 2
        val pivotIndex = when {
            a[lowIndex] <= a[highIndex] == a[highIndex] <= a[middleIndex] -> highIndex
            a[lowIndex] <= a[middleIndex] == a[middleIndex] <= a[highIndex] -> middleIndex
            else -> lowIndex
        }
        val pivot = a[pivotIndex]
        var pivotLowIndex = lowIndex
        var pivotHighIndex = highIndex

        for (i in lowIndex until pivotIndex)
            if (a[i] < pivot) swap(i, pivotLowIndex++)
        swap(pivotLowIndex, pivotIndex)

        for (i in pivotHighIndex downTo pivotLowIndex + 1)
            if (a[i] > pivot) swap(i, pivotHighIndex--)

        swap(pivotLowIndex, pivotHighIndex)
        val index = pivotLowIndex
        pivotLowIndex = pivotHighIndex
        for (i in pivotHighIndex - 1 downTo index)
            if (a[i] == pivot) swap(i, --pivotLowIndex)

        return pivotLowIndex to pivotHighIndex
    }

    private fun sort(lowIndex: Int, highIndex: Int) {
        if (lowIndex < highIndex) {
            val (pivotLowIndex, pivotHighIndex) = partition(lowIndex, highIndex)
            sort(lowIndex, pivotLowIndex - 1)
            sort(pivotHighIndex + 1, highIndex)
        }
    }
}

fun main() {
    val size = 30_000_000
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

    println("\nStart Karlo Second!")
    measureTimeMillis {
        QuickSort(a2)
    }.let {
        println(a2.joinToString(" ", "a2 = [", "]", 20, " ... of ${a2.size - 20}") { "%d".format(it) })
        println("Finished $it ms")
    }

    println(a1.contentEquals(a2))
}