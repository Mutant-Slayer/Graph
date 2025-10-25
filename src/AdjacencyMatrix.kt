fun main() {
    val edgeList = listOf(
        listOf(1, 2),
        listOf(2, 3),
        listOf(3, 4),
        listOf(4, 2),
        listOf(1, 3)
    )
    val n = 5

    val adjacencyMatrix = List(n) { MutableList(n) { 0 } }

    for (i in 0 until edgeList.size) {
        val a = edgeList[i][0]
        val b = edgeList[i][1]
        adjacencyMatrix[a][b] = 1
        adjacencyMatrix[b][a] = 1
    }

    printAdjacencyMatrix(n - 1, adjacencyMatrix)
}

private fun printAdjacencyMatrix(n: Int, adjacencyMatrix: List<MutableList<Int>>) {

    /**
     * Graph Adjacency List Representation
     *
     * Node 1 => Neighbours : 2 3
     * Node 2 => Neighbours : 3
     * Node 3 => Neighbours : 4
     * Node 4 => Neighbours : 2
     */

    for (i in 1..n) {
        print("Node $i => Neighbours : ")
        for (j in 1..n) {
            if (adjacencyMatrix[i][j] == 1) {
                print("$j ")
            }
        }
        println()
    }
}