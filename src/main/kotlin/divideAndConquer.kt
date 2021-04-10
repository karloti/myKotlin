fun calc_sum(array: IntArray, left: Int, right: Int): Int {
// the sum of zero elements is 0
    if (left == right) return 0

// the sum of one-element sub-array is the element
    if (left == right - 1) return array[left]

// the index of the middle element to divide the array into two sub-arrays
    val middle = (left + right) / 2

// the sum of elements in the left subarray
    val left_sum = calc_sum(array, left, middle)

// the sum of elements in the right subarray
    val right_sum = calc_sum(array, middle, right)

// the sum of elements in the array
    return left_sum + right_sum
}

fun main() {
    val array = IntArray(10) { println(it);it }
    println(array.sum())
    println(calc_sum(array, 0, array.size))
}