fun main() {
    val charArray: CharArray = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
    var player = 'X'
    var result: String

    printTable(charArray)
    do {
        do {
            var successful = false
            print("Enter the coordinates: ")
            val coordinates = readLine()!!
            when {
                coordinates[0] !in '0'..'9' || coordinates[2] !in '0'..'9' -> println("You should enter numbers!")
                coordinates[0] !in '1'..'3' || coordinates[2] !in '1'..'3' -> println("Coordinates should be from 1 to 3!")
                else -> {
                    val row = coordinates[0].toString().toInt()
                    val col = coordinates[2].toString().toInt()
                    val index = (row - 1) * 3 + (col - 1)
                    if (charArray[index] != ' ')
                        println("This cell is occupied! Choose another one!")
                    else {
                        charArray[index] = player
                        if (player == 'X') player = 'O' else player = 'X'
                        printTable(charArray)
                        successful = true
                    }
                }
            }
        } while (!successful)
        result = checkWinner(charArray)
    } while (result == "Game not finished")
    println(result)
}

private fun checkWinner(charArray: CharArray): String {
    val x = 'X'
    val o = 'O'

    val row1 = charArrayOf(charArray[0], charArray[1], charArray[2])
    val row2 = charArrayOf(charArray[3], charArray[4], charArray[5])
    val row3 = charArrayOf(charArray[6], charArray[7], charArray[8])
    val column1 = charArrayOf(charArray[0], charArray[3], charArray[6])
    val column2 = charArrayOf(charArray[1], charArray[4], charArray[7])
    val column3 = charArrayOf(charArray[2], charArray[5], charArray[8])
    val diagonal1 = charArrayOf(charArray[0], charArray[4], charArray[8])
    val diagonal2 = charArrayOf(charArray[2], charArray[4], charArray[6])

    var cRow1X = 0
    var cRow2X = 0
    var cRow3X = 0
    var cCol1X = 0
    var cCol2X = 0
    var cCol3X = 0

    var cRow1O = 0
    var cRow2O = 0
    var cRow3O = 0
    var cCol1O = 0
    var cCol2O = 0
    var cCol3O = 0

    var cDiaX1 = 0
    var cDiaX2 = 0

    var cDiaO1 = 0
    var cDiaO2 = 0

    var emptyCell = 0

    var numX = 0
    var numO = 0

    for (i in row1) if (i == x) cRow1X++
    for (i in row1) if (i == o) cRow1O++

    for (i in row2) if (i == x) cRow2X++
    for (i in row2) if (i == o) cRow2O++

    for (i in row3) if (i == x) cRow3X++
    for (i in row3) if (i == o) cRow3O++

    for (i in column1) if (i == x) cCol1X++
    for (i in column1) if (i == o) cCol1O++

    for (i in column2) if (i == x) cCol2X++
    for (i in column2) if (i == o) cCol2O++

    for (i in column3) if (i == x) cCol3X++
    for (i in column3) if (i == o) cCol3O++

    for (i in diagonal1) if (i == x) cDiaX1++
    for (i in diagonal1) if (i == o) cDiaO1++

    for (i in diagonal2) if (i == x) cDiaX2++
    for (i in diagonal2) if (i == o) cDiaO2++

    for (i in charArray) if (i == ' ') emptyCell++

    for (i in charArray) if (i == x) numX++
    for (i in charArray) if (i == o) numO++

    return if (cRow1X == 3 || cRow2X == 3 || cRow3X == 3 || cCol1X == 3 || cCol2X == 3 || cCol3X == 3 || cDiaX1 == 3 || cDiaX2 == 3)
        "X wins" else
        if (cRow1O == 3 || cRow2O == 3 || cRow3O == 3 || cCol1O == 3 || cCol2O == 3 || cCol3O == 3 || cDiaO1 == 3 || cDiaO2 == 3)
            "O wins" else
            if (emptyCell == 0)
                "Draw" else
                "Game not finished"
}

private fun printTable(charArray: CharArray) {
    println("---------")
    println("| ${charArray[0]} ${charArray[1]} ${charArray[2]} |")
    println("| ${charArray[3]} ${charArray[4]} ${charArray[5]} |")
    println("| ${charArray[6]} ${charArray[7]} ${charArray[8]} |")
    println("---------")
}