package day2_2017.txt

import java.io.File

//AoC 2017 day 2.

// PART 1
fun highestNumb (list : List<Int>) : Int {
    return list.max()
}

// PART 1
fun lowestNumber(list : List<Int>) : Int {
    return list.min()
}

// PART 1
fun diff(max : Int, min : Int) : Int {
    return max - min
}


// PART 1
fun sumDiff(data : List<List<Int>>) : Int {
    tailrec fun diff (data : List<List<Int>>, index : Int, sum : Int) : Int {
       if(index == data.size) return sum
        return diff (data, index + 1, sum + diff(highestNumb(data[index]), lowestNumber(data[index])))
   }
    return diff(data, 0, 0)
}

// PART 2
fun sumDividedAllLists(data : List<List<Int>>) : Int {
    tailrec fun divList(data: List<List<Int>>, index: Int, sum: Int): Int {
        if (index == data.size) return sum
        return divList(data, index + 1, sum + getDivisionValue(data[index]))
    }
    return divList(data, 0, 0)
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
    catch (e: NumberFormatException){
        println("Not correct data in file")
    }
    catch (e: Exception){
        println(e.message)
    }
    return data
}

fun main() {
    println(sumDiff(createIntLists(readFile())))
    val sum = sumDividedAllLists(createIntLists(readFile()))
    println(sum)
}

