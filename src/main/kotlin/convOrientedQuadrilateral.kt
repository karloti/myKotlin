/**
 *    Здравейте,
 *
 * изпращам решение на задачата:
 *
 * " Напишете подпрограма, която подрежда зададени като нейни
 *   аргументи точки A, B, C и D така, че ABCD да бъде положително
 *   ориентиран четириъгълник - предполага се че такъв съществува.
 *   Направете това с възможно най-малък брой размении по възможност кратко.
 *   Точките са зададени чрез координатите си, цели числа. "
 *   https://www.mnknowledge.com/
 *
 * Използвал съм метода на Греам.
 * Тъй като съм работил с множество,
 * хейпсорт и векторно произвидение за решаването на задачата,
 * сложността е:  О(n Log n)
 *
 * В случая избора на най-долна лява точка за начална са необходими
 * 3 проверки (сравнение координатите на точките) и след това за
 * сортировктана на векторите с начало нея са необходими още 3 проверки
 * (сравнение на векторното им произвидение) или общо 6!
 *
 * Автор: Калоян Караиванов 1.3.2021 г.
 */

import java.util.*
import kotlin.system.measureTimeMillis

data class Pixel(var name: String, val x: Int, val y: Int) : Comparable<Pixel> {
    override fun compareTo(other: Pixel) = if (y == other.y) x - other.x else y - other.y
}

data class Vector(val v: Pair<Pixel, Pixel>) : Comparable<Vector> {
    val x = v.second.x - v.first.x
    val y = v.second.y - v.first.y
    operator fun times(v: Vector) = y * v.x - x * v.y
    override fun compareTo(other: Vector) = this * other
}

fun Set<Pixel>.sort(): MutableList<Pixel> {  // Gream
    val min = minByOrNull { it }!!
    val queue = PriorityQueue((this - min).map { Vector(min to it) })
    val list = mutableListOf(min)
    while (queue.isNotEmpty()) list += queue.remove().v.second
    return list
}

fun pixel() = Pixel(('A'..'Z').random().toString(), (0..1000).random(), (0..1000).random())

// Banchev

fun pos(p: Pixel, q: Pixel, r: Pixel) = if ((q.x - p.x) * (r.y - p.y) > (r.x - p.x) * (q.y - p.y)) 1 else 0

fun MutableList<Pixel>.makeQ(): MutableList<Pixel> {
    fun swap(i: Int, j: Int) {
        val p = this[i]
        this[i] = this[j]
        this[j] = p
    }

    val s1 = pos(this[0], this[1], this[2])
    val s2 = pos(this[1], this[2], this[3])
    val p = s1 + s2 + pos(this[2], this[3], this[0]) + pos(this[3], this[0], this[1])
    when {
        p < 2 -> swap(0, 2)
        p == 2 -> swap(
            if (s1 == 1) 3 else 1,
            if (s2 == 1) 0 else 2,
        )
    }
    return this
}

// Fotev

fun n(p: Pixel, q: Pixel, r: Pixel) = (q.x - p.x) * (r.y - p.y) > (r.x - p.x) * (q.y - p.y)

fun MutableList<Pixel>.makeQ2(): MutableList<Pixel> {
    fun swap(i: Int, j: Int) {
        val p = this[i]
        this[i] = this[j]
        this[j] = p
    }
    if (n(this[0], this[1], this[2])) {
        if (!n(this[0], this[2], this[3])) {
            if (n(this[0], this[1], this[3])) {
                if (!n(this[1], this[2], this[3])) swap(2, 3)
            } else swap(0, 3)
        }
    } else {
        if (n(this[0], this[2], this[3])) {
            if (n(this[0], this[1], this[3])) {
                if (!n(this[1], this[2], this[3])) swap(1, 2)
            } else swap(0, 1)
        } else swap(0, 2)
    }
    return this
}

fun main() {
    println("тест за 100 000 000 четириъгълника ...")

/*
    measureTimeMillis {
        repeat(10_000_000) {
            val s = setOf(pixel(), pixel(), pixel(), pixel())
            s.sort()
        }
    }.let { println("Time in $it ms Gream") }
*/

    measureTimeMillis { // Fotev
        repeat(100_000_000) { MutableList(4) { pixel() }.makeQ2() }
    }.let { println("Time in $it ms Fotev") }

    measureTimeMillis { // Banchev
        repeat(100_000_000) { MutableList(4) { pixel() }.makeQ() }
    }.let { println("Time in $it ms Banchev") }
}