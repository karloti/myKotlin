data class Sample(val name: String, val age: Int) {
    override fun toString() = buildString {
        repeat(10) { append("" + (it + 1) + " ") }
    }
}
    fun main() {
    val names = listOf(
        """
        *12345
        *123456""".trimMargin("*"),
        """
        12345
        123456""".trimIndent(),
    )

    repeat(names.size) {
        println("Hello, ${names[it]}!")
        println("Your name is ${names[it].count()} long")
    }

        println(Sample("Karlo", 48))
    }