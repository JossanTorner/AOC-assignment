package day_3

import java.io.File

fun getData() : MutableList<String> {
    var data = mutableListOf<String>()
    try{
        data = File("src/day3_2022/data_day_3_linn").readLines().toMutableList()
    }
    catch (e: Exception){
        println(e.message)
    }
    return data
}

// tailrec fun jag gjort själv (ej till 100%)
fun getSum(list : MutableList<String>) : Long {
    tailrec fun accSum(index : Int, sum : Long) : Long{
        if (index >= list.size) return sum
        val currentWord = list[index]
        val middle = list[index].length / 2
        val wordDivided = arrayOf(currentWord.substring(0, middle), currentWord.substring(middle))
        val commonChars = wordDivided[0].toSet().intersect(wordDivided[1].toSet())

        val priorityNum = commonChars.sumOf {
            when (it) {
                in 'a'..'z' -> (it.code - 'a'.code + 1).toLong()
                in 'A'..'Z' -> (it.code - 'A'.code + 27).toLong()
                else -> 0
            }
        }
        return accSum(index+1, sum + priorityNum)
    }
    return accSum(0, 0)
}

fun solvePart1(input: List<String>) : Int {
    return input
        .asSequence()  // Förbättrar prestanda för stora datamängder
        .map { rucksack ->
            rucksack.take(rucksack.length / 2).toSet() intersect rucksack.drop(rucksack.length / 2).toSet()
        }
        .flatten()  // Plattar ut setet till en lista
        .sumOf { priorities[it] ?: 0 }  // Summerar prioriteringar, eller 0 om inte funnen
}

// skapar en map som "parar ihop" varje bpkstav med ett nummervärde vilket sedan hämtas i solvepart1functional.
val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()

fun printResult() {
    val result = File("src/day3_2022/data_day_3_linn")
        .readLines()  // Läser filen som en lista av rader
        .filter { it.isNotEmpty() }  // Tar bort tomma rader
        .let(::solvePart1)  // Kör lösningen på listan
    println("Sum of priorities: $result")
}

fun main(){

    println(getSum(getData()))

    printResult()

    println(solvePart1(getData()))
}