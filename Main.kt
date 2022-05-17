package tictactoe

fun main() {
    val g = List(3) { MutableList(3) { " " } }
    var actPlayer = "X"

    grid(g)

    while (true) {
        while (true) {
            print("Enter the coordinates: ")
            val coordinates = readln()
            var xy: List<Int>
            if (coordinates.matches("\\d \\d".toRegex())) {
                xy = coordinates.split(" ").map { it.toInt() }
                val x = xy.first()
                val y = xy.last()
                if (x !in 1..3 || y !in 1..3) {
                    println("Coordinates should be from 1 to 3!")
                    continue
                } else {
                    if (g[x - 1][y - 1] in listOf(" ", "_")) g[x - 1][y - 1] = actPlayer else {
                        println("This cell is occupied! Choose another one!")
                        continue
                    }
                }
            } else {
                println("You should enter numbers!")
                continue
            }
            break
        }

        grid(g)

        if (checkWin(g, actPlayer)) { println("$actPlayer wins"); break } else {
            val emptyCount = g.sumOf { it.count { i -> i in listOf(" ", "_") } }
            if (emptyCount == 0) { println("Draw"); break } else {
                actPlayer = if (actPlayer == "X") "O" else "X"
            }
        }
    }
}

fun grid(g: List<List<String>>) {
val borderUp = "---------\n"
var str = borderUp
for (i in g) {
    str += "| ${i.joinToString(" ")} |\n"
}
    str += "---------"
println(str)
}

fun checkWin(g: List<List<String>>, actPlayer: String): Boolean {
    for (i in 0..2) {
        if (actPlayer == g[i][0] && g[i][0] == g[i][1] && g[i][1] == g[i][2]) return true
        if (actPlayer == g[0][i] && g[0][i] == g[1][i] && g[1][i] == g[2][i]) return true
    }
    if (actPlayer == g[0][0] && g[0][0] == g[1][1] && g[1][1] == g[2][2]) return true
    if (actPlayer == g[0][2] && g[0][2] == g[1][1] && g[1][1] == g[2][0]) return true
    return false
}