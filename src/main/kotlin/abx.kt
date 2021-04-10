fun main() {
/*    val a = 10
    val b = 20
    val x = 1*/
    val (a, b, x) = List(3) { readLine()!!.toInt() }

    println(x in a until b || x in b until a)

/*

    if (a == b)
        println(false)
    else
        if (a < b)
            if (x < b)
                if (a <= x)
                    println(true)
                else
                    println(false)
            else
                println(false)
        else
            if (x < a)
                if (b <= x)
                    println(true)
                else
                    println(false)
            else
                println(false)
*/
}
