package day_3

import java.io.File

fun getData() : MutableList<String> {
    var data = mutableListOf<String>()
    try{
        data = File("src/day3_2022/data_day3_josefin").readLines().toMutableList()
    }
    catch (e: Exception){
        println(e.message)
    }
    return data
}

// Dela upp varje rad (String) i två delar, hitta det tecken som förekommer i båda delarna och addera prioritetsnummer för det tecknet till en summa
fun solvePartOne(input: List<String>) : Int {
    return input
        .asSequence()
        .map { rucksack ->
            rucksack.take(rucksack.length / 2).toSet() intersect rucksack.drop(rucksack.length / 2).toSet()
        }
        .flatten()
        .sumOf { priorities[it] ?: 0 }  // Summerar prioriteringar
}

// Skapa grupper av tre rader, hitta det tecken som förekommer i alla tre och addera alla prioritetsvärden för dessa tecken till en summa
fun solvePartTwo(input: List<String>): Int {
    return input
        .asSequence()
        .chunked(3)
        .map { group ->
            group[0].toSet()
                .intersect(group[1].toSet())
                .intersect(group[2].toSet())
                .first()  // Hitta gemensam bokstav i alla 3 ransaksäckar
        }
        .sumOf { priorities[it] ?: 0 }  // Summera prioriteringar eller 0 om ingen gemensam bokstav hittas.
}

// skapar en map som "parar ihop" varje bokstav med ett nummervärde
val priorities = (('a'..'z').zip(1..26) + ('A'..'Z').zip(27..52)).toMap()

fun main(){
    println(solvePartOne(getData()))
    println(solvePartTwo(getData()))
}


// part 1, tailrec function
fun partOneOwnSolution(list : MutableList<String>) : Long {
    tailrec fun accSum(index : Int, sum : Long) : Long{
        if (index >= list.size) return sum
        val currentWord = list[index]
        val middle = list[index].length / 2
        val wordDivided = arrayOf(currentWord.substring(0, middle), currentWord.substring(middle))
        val commonChars = wordDivided[0].toSet().intersect(wordDivided[1].toSet())

        val priorityNum = priorities[commonChars.first()] ?: 0
        return accSum(index+1, sum + priorityNum)
    }
    return accSum(0, 0)
}