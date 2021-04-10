data class Customer(
    val name: String = "none",
    val age: Int = 0,
    val gender: String = "",
    val occupation: String = "",
    val salary: Double = 0.0,
)

data class Company(
    val name: String,
    val customers: MutableList<Customer> = mutableListOf(),
) {
    fun custOcumation(occupation: String) = customers.filter { it.occupation == occupation }.forEach(::println)
    fun printCustomers() = customers.forEach { println(it) }
    fun maxSalary() {
        val maxSalary = customers.maxOf { it.salary }
        customers.filter { it.salary == maxSalary }.forEach { println(it) }
    }
}

fun main() {
    var choice = ""
    println("Въведете име на фирма: ")
    val company = Company(readLine()!!)

    println("Здравейте")
    println("Добре дошли във фирма ${company.name}")
    println()
    while (choice != "0") {
        println("Изберете някой от следните възможности:")
        println("1. добавяне на служител")
        println("2. списък със служители по дадена длъжност")
        println("3. служител с най-голяма заплата")
        println("4. отпечатване на цялата фирма")
        println("0. изход")
        println("Въведете: ")
        choice = readLine()!!
        when (choice) {
            "1" -> {
                println("име на служител")
                val name = readLine()!!
                println("възраст")
                val age = readLine()!!.toInt()
                println("пол")
                val gender = readLine()!!
                println("длъжност")
                val occupation = readLine()!!
                println("месечно възнаграждение")
                val salary = readLine()!!.toDouble()
                company.customers += Customer(name,age, gender, occupation, salary)
            }
            "2" -> {
                println("Въведете длъжност: ")
                company.custOcumation(readLine()!!)
            }
            "3" -> {
                println("Служител с максимална заплата:")
                company.maxSalary()

            }
            "4" -> {
                println("фирма ${company.name} има следните служители:")
                company.printCustomers()
            }
        }
        println()
    }
    println("Приятен ден/нощ!")
}