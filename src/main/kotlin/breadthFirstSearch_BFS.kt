import java.util.*

class Dijkstra<T> {
    data class Node<T>(
        val name: T,
        val neighbours: MutableSet<T> = mutableSetOf(),
        var parrent: Node<T>? = null,
        var sum: Int? = null,
    )

    val nodes = mutableMapOf<T, Node<T>>()
    val edges = mutableMapOf<Pair<T, T>, Int>()


    operator fun set(t: Pair<T, T>, length: Int) {
        if (nodes[t.first]?.neighbours?.add(t.second) == null) nodes[t.first] = Node(t.first, mutableSetOf(t.second))
        edges[t] = length
        if (nodes[t.second]?.neighbours?.add(t.first) == null) nodes[t.second] = Node(t.second, mutableSetOf(t.first))
        edges[t.second to t.first] = length
    }

    operator fun set(t: T, sum: Int) {
        nodes[t]?.sum = sum
    }

    operator fun get(t: T) = nodes[t]
    operator fun get(t: Pair<T, T>) = edges[t]

    private fun initSumAndParents() {
        nodes.forEach {
            it.value.sum = null
            it.value.parrent = null
        }
    }

    fun pathOne(t: Pair<T, T>): List<Node<T>>? {
        val nodeFirst = nodes[t.first] ?: return null
        val nodeFinal = nodes[t.second] ?: return null
        if (nodeFirst.sum == 0 && nodeFinal.sum != null) return path(nodeFinal)
        initSumAndParents()
        val queue: PriorityQueue<Node<T>> = PriorityQueue(compareBy { it.sum!! })

        nodeFirst.sum = 0
        queue.add(nodeFirst)
        while (queue.isNotEmpty()) {
            queue.remove().run {
                println("${nodes[name]?.parrent?.name} -> $name")
                if (nodeFinal.sum == null || sum!! < nodeFinal.sum!!)
                    neighbours
                        .map { nodes[it]!! }
                        .filter { it.sum == null || it.sum!! > sum!! + edges[name to it.name]!! }
                        .forEach {
                            if (it.sum == null) {
                                it.sum = sum!! + edges[name to it.name]!!
                                it.parrent = this
                                queue.add(it)
                            } else {
                                it.sum = sum!! + edges[name to it.name]!!
                                it.parrent = this
                            }
                        }
            }
        }
        return path(nodeFinal)
    }

    fun pathAny(t: T) {
        initSumAndParents()
        val queue: PriorityQueue<Node<T>> = PriorityQueue(compareBy { it.sum!! })
        nodes[t]!!.sum = 0
        queue.add(nodes[t])
        while (queue.isNotEmpty()) {
            queue.remove().run {
                neighbours
                    .map { nodes[it]!! }
                    .filter { it.sum == null || it.sum!! > sum!! + edges[name to it.name]!! }
                    .forEach {
                        if (it.sum == null) {
                            it.sum = sum!! + edges[name to it.name]!!
                            it.parrent = this
                            queue.add(it)
                        } else {
                            it.sum = sum!! + edges[name to it.name]!!
                            it.parrent = this
                        }
                    }
            }
        }
    }

    private fun path(nodeFinal: Node<T>): List<Node<T>> {
        var node: Node<T>? = nodeFinal
        val path = mutableListOf<Node<T>>()
        while (node != null) {
            path.add(node)
            node = node.parrent
        }
        return path.reversed()
    }
}

fun main() {
    val g = Dijkstra<Char>()

    g['E' to 'G'] = 15
    g['G' to 'F'] = 3
    g['F' to 'B'] = 10
    g['B' to 'A'] = 7
    g['A' to 'C'] = 15
    g['C' to 'E'] = 40
    g['D' to 'E'] = 28
    g['D' to 'F'] = 20
    g['E' to 'F'] = 10
    g['D' to 'B'] = 5
    g['D' to 'A'] = 10
    g['D' to 'C'] = 9

    g.pathAny('E')
    g.nodes.values.sortedBy { it.sum ?: 0 }.forEach { println("${it.name}, sum = ${it.sum}") }

//    val result = g.pathOne('E' to 'G')!!
//    println(result.map { it.name })
//    println(result.last().sum)
//    g.nodes.values.sortedBy { it.name }.forEach { println(it.name + " " + it.sum) }
}
