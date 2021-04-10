/*
import java.util.*

class QuickSort(private val a: Array<Int>) {
    private var tmp: Int = 0
    private fun swap(i: Int, j: Int) {
        tmp = a[i]; a[i] = a[j]; a[j] = tmp
    }

    init {
        sort(0, a.lastIndex)
    }

    private fun partition(lowIndex: Int, highIndex: Int): Int {
        val middleIndex = (lowIndex + highIndex) / 2
        var pivotIndex = when {
            a[lowIndex] <= a[highIndex] == a[highIndex] <= a[middleIndex] -> highIndex
            a[lowIndex] <= a[middleIndex] == a[middleIndex] <= a[highIndex] -> middleIndex
            else -> lowIndex
        }
        val pivot = a[pivotIndex]
        swap(pivotIndex, highIndex)
        pivotIndex = lowIndex
        for (j in lowIndex until highIndex) if (a[j] < pivot) swap(j, pivotIndex++)
        swap(pivotIndex, highIndex)
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

fun quickSort(arr: Array<Int>): Array<Int> {
    QuickSort(arr)
    return arr
}


fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = quickSort(arr)

    println(result.joinToString(" "))
}
*/
