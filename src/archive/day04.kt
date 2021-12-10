import java.io.File
import java.util.*

data class BoardNumber(var number: Int) {
    var drawn = false
}

private fun markBoard(board: Array<Array<BoardNumber>>, number: Int) {
    for (row in 0 until 5) {
        for (col in 0 until 5) {
            if (board[row][col].number == number) {
                board[row][col].drawn = true
            }
        }
    }
}

private fun isWinBoard(board: Array<Array<BoardNumber>>): Boolean {
    for (row in 0 until 5) {
        var winLine = true
        for (col in 0 until 5) {
            if (! board[row][col].drawn) {
                winLine = false
            }
        }
        if (winLine) { return true }
    }
    for (col in 0 until 5) {
        var winCol = true
        for (row in 0 until 5) {
            if (!board[row][col].drawn) {
                winCol = false
            }
        }
        if (winCol) { return true }
    }
    return false
}

private fun getScoreWinBoard(board: Array<Array<BoardNumber>>): Int {
    var score = 0
    for (row in 0 until 5) {
        for (col in 0 until 5) {
            score += if (board[row][col].drawn) 0 else board[row][col].number
        }
    }
    return score
}

fun day04_1(): Int {
    val input = Scanner( File("resources/day04.txt"))
    val numbersDrawn = input.nextLine().split(",").map { it.toInt() }
    val boards = mutableListOf< Array<Array<BoardNumber>>>()
    input.nextLine()

    while (input.hasNext()) {
        val board = Array(5) { Array(5){BoardNumber(0)} }
        for (row in 0 until 5) {
            for (col in 0 until 5) {
                board[row][col].number = input.nextInt()
            }
            input.nextLine()
        }
        boards.add(board)
    }
    numbersDrawn.forEach {number -> boards.forEach{board ->
        markBoard(board, number)
        if (isWinBoard(board)) {
            return getScoreWinBoard(board) * number;
        }
    } }
    return -1
}

fun day04_2(): Int {
    val input = Scanner( File("resources/day04.txt"))
    val numbersDrawn = input.nextLine().split(",").map { it.toInt() }
    val boards = mutableListOf< Array<Array<BoardNumber>>>()
    input.nextLine()

    while (input.hasNext()) {
        val board = Array(5) { Array(5){BoardNumber(0)} }
        for (row in 0 until 5) {
            for (col in 0 until 5) {
                board[row][col].number = input.nextInt()
            }
            input.nextLine()
        }
        boards.add(board)
    }
    numbersDrawn.forEach {number ->
        val boardsIterator = boards.iterator()
        while (boardsIterator.hasNext()) {
            val board = boardsIterator.next()
            markBoard(board, number)
            if (isWinBoard(board)) {
                if (boards.size == 1) {
                    return getScoreWinBoard(board) * number;
                }
                boardsIterator.remove()
            }
        }
    }
    return -1
}
