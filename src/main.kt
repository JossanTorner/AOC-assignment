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
        return divList(data, index + 1, sum + div(data[index]))
    }
    return divList(data, 0, 0)
}


// För varje element (behövs index), kolla om vardera element efter det är jämnt delbart, i så fall skicka vidare talet av divisionen
// om elementet ej är delbart med nästa element, jämför med nästa (behövs index för nuvarande jämförelse)



fun div(data : List<Int>) : Int{
    tailrec fun loop(data : List<Int>, currentNumber : Int, indexComp : Int) : Int {
        if(currentNumber == data.size - 1) return 0
        if (indexComp == data.size) return loop(data, currentNumber + 1, 0)
        if(data[currentNumber] % data[indexComp] == 0) return data[currentNumber] / data[indexComp]
        return loop(data, currentNumber, indexComp +1)
    }
    return loop(data, 0, 0)
}










fun readFile() : List<String>{
    var data = mutableListOf<String>()
    try{
        data = File("src/data.txt").readLines().toMutableList()
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

    val sum = sumDividedIndex(createIntLists(readFile()))
    println(sum)
}

// fun division(list: List<Int>): Int {
//    tailrec fun findNumbers(list: List<Int>, sum : Int, index: Int, currentComparator : Int): Int {
//        if (index >= list.size - 1) return sum
//        if (currentComparator == list.size) return findNumbers(list, sum, index + 1, index + 2)
//
//        return if (list[index] % list[currentComparator] == 0) {
//            list[index] / list[currentComparator]
//        }
//        else findNumbers(list, sum, index, currentComparator + 1)
//    }
//    return findNumbers(list, 0, 0, 1)
//}