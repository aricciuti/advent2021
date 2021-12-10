import java.util.*

val openPairs = mapOf ('>' to '<', ')' to '(', ']' to '[', '}' to '{')
val completePoints = mapOf ('(' to 1, '[' to 2, '{' to 3, '<' to 4)

fun isClosing(char: Char): Boolean = ">}])".contains(char)

fun day10_2(): Long {
    val scores = mutableListOf<Long>()
    linesInFile("resources/day10.txt").forEachIndexed { index, line ->
        var score = 0L
        val stack = Stack<Char>()
        var inError = false
        line.forEachIndexed { idxChar, it ->
            if (!inError) {
                if (stack.isEmpty()) {
                    stack.push(it)
                }
                else {
                    if (isClosing(it)) {
                        if (stack.peek() != openPairs.get(it)) {
                            inError = true
                        } else {
                            stack.pop()
                        }
                    } else {
                        stack.push(it)
                    }
                }
                if (idxChar == line.length - 1) {
                    while (!stack.isEmpty()) {
                        val openChar = stack.pop()
                        score = (5 * score) + completePoints.get(openChar)!!
                    }
                    scores.add(score)
                }
            }
        }
    }
    return scores.sortedDescending()[scores.size / 2]
}
