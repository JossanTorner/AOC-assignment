package day2_2017.txt

import java.io.File

//AoC 2017 day 2.
//For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.
// What is the checksum for the spreadsheet in your puzzle input?

// PART 1
fun sumDifference(data : List<List<Int>>) : Int {
    tailrec fun loop (data : List<List<Int>>, index : Int, sum : Int) : Int {
       if(index == data.size) return sum
        return loop(data, index + 1, sum + (data[index].max() - data[index].min()))
   }
    return loop(data, 0, 0)
}

//-----------------------------------------------------------------------------------------------------------------
// PART 2
//What is the sum of division result of each row's two evenly divided numbers?

fun getSumPartTwo(data : List<List<Int>>) : Int {
    tailrec fun loop(data: List<List<Int>>, index: Int, sum: Int): Int {
        if (index == data.size) return sum
        return loop(data, index + 1, sum + getDivisionValue(data[index]))
    }
    return loop(data, 0, 0)
}

// PART 2
fun getDivisionValue(data: List<Int>) : Int{
    tailrec fun loop(data: List<Int>, currentIndex : Int, compareIndex: Int) : Int {
        if (currentIndex == data.size - 1) return 0
        else if (compareIndex == data.size) return loop(data, currentIndex + 1, 0)

        if (data[currentIndex] % data[compareIndex] == 0 && compareIndex != currentIndex){
            return data[currentIndex] / data[compareIndex]
        }
        return loop(data, currentIndex, compareIndex + 1)
    }
    return loop(data, 0, 1)
}

//För varje plats i lista med String, skapa en lista av ints och lägg i en lista
fun createIntLists(data : List<String>) : List<List<Int>>{
    val mainList = mutableListOf<List<Int>>()
    for (line in data){
        val list : List<Int> = line.split(" ").map { it.toInt() }
        mainList.add(list)
    }
    return mainList
}

fun readFile() : List<String>{
    var data = mutableListOf<String>()
    try{
        data = File("src/day2_2017/txt/data_day2_josefin.txt").readLines().toMutableList()
    }
    catch (e: Exception){
        println(e.message)
    }
    return data
}

fun main() {
    println(sumDifference(createIntLists(readFile())))
    val sum = getSumPartTwo(createIntLists(readFile()))
    println(sum)
}

