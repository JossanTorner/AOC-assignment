package day20_2016

import java.io.File

data class IpRange(val start: Long, val end: Long)

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

fun findLowestAllowedIp(blockedRanges: List<String>): Long {
    return blockedRanges
        .map { it.split("-").let { (start, end) -> IpRange(start.toLong(), end.toLong()) } }  //Parsar input (listan med data) till IP-range objekt
        .sortedBy { it.start }  //Sorteras baserat på start-range
        .fold(listOf<IpRange>()) { acc, range ->                                 //Bygga upp en ny lista med optimerade, icke-överlappande ranges
            if (acc.isEmpty() || acc.last().end < range.start - 1) acc + range  // Lägg till intervallet om det inte överlappar med föregående intervall
            else acc.dropLast(1) + IpRange(acc.last().start, maxOf(acc.last().end, range.end))  // Om överlapp, tas sista värder i acc bort, och skapar ett nytt större intervall och läggs till i listan.
        }
        .firstOrNull { it.end < Long.MAX_VALUE }?.end?.plus(1)  // Hitta första IP-intervallet som inte blockerar något, slutet < MAX_VALUE.Om sånt hittas, return IP efter slutet på intervallen, alltså +1.
        ?: 0L  // If no ranges are blocking anything, 0 is allowed
}

fun main() {
    println(findLowestAllowedIp(getData()))
}
