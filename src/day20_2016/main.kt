package day20_2016

data class IpRange(val start: Long, val end: Long)

fun findLowestAllowedIp(blockedRanges: List<String>): Long {
    // Parse and merge ranges in a functional way
    return blockedRanges
        .map { it.split("-").let { (start, end) -> IpRange(start.toLong(), end.toLong()) } }  // Parse input into IpRange
        .sortedBy { it.start }  // Sort by the start of the range
        .fold(listOf<IpRange>()) { acc, range ->
            if (acc.isEmpty() || acc.last().end < range.start - 1) acc + range  // No overlap, add new range
            else acc.dropLast(1) + IpRange(acc.last().start, maxOf(acc.last().end, range.end))  // Merge overlapping ranges
        }
        .firstOrNull { it.end < Long.MAX_VALUE }?.end?.plus(1)  // Find first unblocked IP
        ?: 0L  // If no ranges are blocking anything, 0 is allowed
}

fun main() {
    // Example of blocked IP ranges
    val blockedRanges = listOf("5-8", "0-2", "4-7")

    // Find the lowest unblocked IP
    val lowestUnblockedIp = findLowestAllowedIp(blockedRanges)
    println("The lowest allowed IP is: $lowestUnblockedIp")
}



// Samma lösning som ovan men mindre funktionell

fun findLowestAllowedIp2(blockedRanges: List<String>): Long {
    // Parse the input into a list of IpRange objects
    val ranges = blockedRanges
        .map { it.split("-") }
        .map { IpRange(it[0].toLong(), it[1].toLong()) }

    // Sort the ranges by their start value
    val sortedRanges = ranges.sortedBy { it.start }

    // Merge overlapping or adjacent ranges
    val mergedRanges = sortedRanges.fold(mutableListOf<IpRange>()) { acc, range ->
        if (acc.isEmpty() || acc.last().end < range.start - 1) {
            // No overlap or adjacency, add the current range
            acc.add(range)
        } else {
            // Overlap or adjacency, merge the current range with the last range in the list
            val last = acc.last()
            acc[acc.size - 1] = IpRange(last.start, maxOf(last.end, range.end))
        }
        acc
    }

    // The first allowed IP will be the first IP after the last merged range
    return if (mergedRanges.isEmpty() || mergedRanges[0].start > 0) {
        0L  // No blocked IPs, the lowest IP is 0
    } else {
        mergedRanges.firstOrNull { it.end < Long.MAX_VALUE }?.end?.plus(1)
            ?: (mergedRanges.last().end + 1)
    }
}

fun main2() {
    // Example of blocked IP ranges
    val blockedRanges = listOf("5-8", "0-2", "4-7")

    // Find the lowest unblocked IP
    val lowestUnblockedIp = findLowestAllowedIp2(blockedRanges)
    println("The lowest allowed IP is: $lowestUnblockedIp")
}
