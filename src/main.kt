import java.io.File

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
        return diff (data, index + 1, diff(highestNumb(data[index]), lowestNumber(data[index])))
   }
    return diff(data, 0, 0)
}

//räkna ut max och min värde på varje rad.
//fun maxMin(data : List<String>) : Long{
//
//}


//För varje plats i lista med String, skapa en lista av ints och lägg i en lista
fun createIntLists(data : List<String>) : List<List<Int>>{
    val mainList = mutableListOf<List<Int>>()  // Use a mutable list
    for (line in data){
        val list : List<Int> = line.split(" ").map { it.toInt() }
        mainList.add(list)
    }
    return mainList
}

fun readFile() : List<String>{
    var data = mutableListOf<String>()
    try{
        data = File("src/Data.txt").readLines().toMutableList()
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
    createIntLists(readFile()).forEach(::println)
    val sum = sumDiff(createIntLists(readFile()))
    println(sum)
}