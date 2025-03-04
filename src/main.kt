import java.io.File
import java.util.IntSummaryStatistics


//AoC 2017 day 2.


fun highestNumb (list : List<Int>) : Int {
    return list.max()
}

fun lowestNumber(list : List<Int>) : Int {
    return list.min()
}

fun diff(max : Int, min : Int) : Int {
    return max - min
}


//Summera diff mellan alla min + max values
fun sumDiff(data : List<List<Int>>) : Int {
    tailrec fun diff (data : List<List<Int>>, index : Int, sum : Int) : Int {
       if(index == data.size) return sum
        return diff (data, index + 1, sum + diff(highestNumb(data[index]), lowestNumber(data[index])))
   }
    return diff(data, 0, 0)
}


//För varje plats i lista med String, skapa en lista av ints och lägg i en lista
fun createIntLists(data : List<String>) : List<List<Int>>{
    val mainList = mutableListOf<List<Int>>()  // Use a mutable list
    for (line in data){
        val list : List<Int> = line.split(" ").map { it.toInt() }
        mainList.add(list)
    }
    return mainList
}

fun sumDividedIndex(data : List<List<Int>>) : Int {
   tailrec fun divList(data: List<List<Int>>, index: Int, sum: Int): Int {
        if (index == data.size) return sum
        return divList(data, index + 1, sum + div2(data[index]))
    }
    return divList(data, 0, 0)
}

fun div(data : List<Int>) : Int{
    tailrec fun loop(data : List<Int>, currentNumber : Int, indexComp : Int) : Int {
        if(currentNumber == data.size - 1) return 0

        if (indexComp == data.size) return loop(data, currentNumber + 1, 0)

        if(data[currentNumber] % data[indexComp] == 0) return data[currentNumber] / data[indexComp]

        return loop(data, currentNumber, indexComp +1)
    }
    return loop(data, 0, 1)
}

fun div2(data: List<Int>): Int {
    tailrec fun loop(data: List<Int>, currentNumber: Int, indexComp: Int): Int {
        if (currentNumber == data.size - 1) return 0

        if (indexComp == data.size) return loop(data, currentNumber + 1, currentNumber + 2)

        if (data[currentNumber] % data[indexComp] == 0) return data[currentNumber] / data[indexComp]

        return loop(data, currentNumber, indexComp + 1)
    }
    return loop(data, 0, 1)
}


//funkar ej, zip with next parar bara ihop bredvidliggande siffror
fun divisionValue(data : List<Int>) : Int {
    return data.zipWithNext()
        .firstOrNull { (current, next) -> next % current == 0}?.let { (current, next) -> next / current } ?: 0
}

val sumEvenNumbers: (List<Int>) -> Int = { list ->
    list.filter { it % 2 == 0 }.sum()
}

//funkar
fun sumDividedAllLists(data : List<List<Int>>) : Int {
    tailrec fun divList(data: List<List<Int>>, index: Int, sum: Int): Int {
        if (index == data.size) return sum
        return divList(data, index + 1, sum + getDivisionValueRecursive(data[index]))
    }
    return divList(data, 0, 0)
}

//funkar
fun getDivisionValue(data: List<Int>) : Int{
    for (currentNumberIndex in data.indices){
        for (comparisonNumberIndex in data.indices){
            if (currentNumberIndex != comparisonNumberIndex && data[currentNumberIndex] % data[comparisonNumberIndex] == 0){
                return data[currentNumberIndex] / data[comparisonNumberIndex]
            }
        }
    }
    return 0
}

//funkar
fun getDivisionValueRecursive(data: List<Int>) : Int{
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


fun readFile() : List<String>{
    var data = mutableListOf<String>()
    try{
        data = File("src/data3.txt").readLines().toMutableList()
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
//    createIntLists(readFile()).forEach(::println)
//    val sum = sumDiff(createIntLists(readFile()))
//    println(sum)

    val sum = sumDividedAllLists(createIntLists(readFile()))
    println(sum)
    val list = listOf(4, 8, 2, 3)
    val shouldgive2 = getDivisionValue(list)
    println(shouldgive2)
}

