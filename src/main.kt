import java.io.File

fun main() {
    val data: List<String> = File("src/Data.txt").readLines()
    for (line in data) {
        println(line)
    }
}