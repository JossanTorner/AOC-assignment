package day20_2016

import java.io.File

fun getData() : MutableList<String> {
    var data = mutableListOf<String>()
    try{
        data = File("src/day20_2016/data_day20_linn").readLines().toMutableList()
    }
    catch (e: Exception){
        println(e.message)
    }
    return data
}

// Bland olika ranges av IP av icke tillåtna IP-adressen, hitta den lägsta tillåtna
fun getLowestAllowedIp(data : MutableList<String>) : Long {
    val ranges = data.map { it.split('-').map { it.toLong() } }.map { it[0] to it[1] }.sortedBy { it.first }

    return ranges.fold(0L) { lowest, (start, end) ->
        if (lowest < start) return lowest
        maxOf(lowest, end + 1)
    }
}

fun main() {
    println(getLowestAllowedIp(getData()))
}


fun getSmallestAllowedIp(data : MutableList<String>) : Long {
    // skapar en lista av Pair<Long, Long>, sorterar den efter lägsta första numret
    val ranges = data.map { it.split('-').map { it.toLong() } }.map {it[0] to it[1]}.sortedBy { it.first }

    // skapar upp en initialt lägsta ip att jämföra med
    var lowest = 0L

    // går igenom alla nummer mellan start och end i ranges
    for(range in ranges){
        if (lowest < range.first){
            return lowest
        }
        lowest = maxOf(lowest, range.second + 1)
    }
    return lowest
}
