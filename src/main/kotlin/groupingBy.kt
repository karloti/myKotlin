// TODO: 15.2.2021 г.
fun main(args: Array<String>) {
    val words = "one two three four five six seven eight nine ten".split(' ')
    val words2 = "one three four five six seven eight nine ten".split(' ')

    val wordsGroup = words.groupingBy(String::length)
    println(wordsGroup.keyOf("one"))

    val wordsGroup2 = words.groupByTo( LinkedHashMap()) { it.length }
    wordsGroup2[3]?.add("Kar")
    println(wordsGroup2)


//    println("Counting first letters:")
//    println(frequenciesByFirstChar) // {o=1, t=3, f=2, s=2, e=1, n=1}

//    val moreWords = "eleven twelve".split(' ')
//    val moreFrequencies = moreWords.groupingBy { it.first() }.eachCountTo(frequenciesByFirstChar.toMutableMap())
//    println(moreFrequencies) // {o=1, t=4, f=2, s=2, e=2, n=1}
}