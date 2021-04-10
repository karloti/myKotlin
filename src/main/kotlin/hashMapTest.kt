import kotlin.system.measureTimeMillis

fun main() {
    val initialCapacity = 20_000_000
    val loadFactor = 0.75f

    val set: MutableSet<Int> = mutableSetOf()
    val hashSet: HashSet<Int> = HashSet((initialCapacity / loadFactor).toInt() + 1, loadFactor)
    val linkedHashSet: LinkedHashSet<Int> = LinkedHashSet((initialCapacity / loadFactor).toInt() + 1, loadFactor)

    print("TEST1 (set): Press enter ..")
    readLine()!!

    measureTimeMillis {
        for (i in 0 until initialCapacity) set.add(initialCapacity - i)
    }.let(::println)

    print("TEST2 (hashSet): Press enter ..")
    readLine()!!

    measureTimeMillis {
        for (i in 0 until initialCapacity) hashSet.add(initialCapacity - i)
    }.let(::println)

    print("TEST3 (linkedHashSet): Press enter ..")
    readLine()!!

    measureTimeMillis {
        for (i in 0 until initialCapacity) linkedHashSet.add(initialCapacity - i)
    }.let(::println)

    println("finished")
}