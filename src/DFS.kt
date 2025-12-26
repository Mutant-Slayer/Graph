fun main() {
    val edgeList = listOf(
        listOf(0, 2),
        listOf(3, 4),
        listOf(2, 3),
        listOf(3, 5),
        listOf(3, 1)
    )
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    convertToAdjacencyList(edgeList, adjacencyList)

    val visited = MutableList(adjacencyList.size) { 0 }

    dfsAlgo(0, adjacencyList, visited)
}

/**
 * Time Complexity = O(V+E)
 * So basically we are starting from source node and trying to cover all the elements in a single path,
 * after we reach dead end, we come back and go to another path, in a recursive way
 * Instead of using stack we are using recursion, which works in a similar way, LIFO
 * for source node 0 answer will be [0 2 3 4 5 1]
 */

private fun dfsAlgo(
    source: Int,
    adjacencyList: MutableMap<Int, MutableList<Int>>,
    visited: MutableList<Int>,
) {
    print("$source ")
    visited[source] = 1

    adjacencyList[source]?.forEach {
        if (visited[it] == 0) {
            dfsAlgo(it, adjacencyList, visited)
        }
    }
}

private fun convertToAdjacencyList(
    edgeList: List<List<Int>>,
    adjacencyList: MutableMap<Int, MutableList<Int>>
) {

    for (i in 0 until edgeList.size) {
        val a = edgeList[i][0]
        val b = edgeList[i][1]
        adjacencyList.getOrPut(a) { mutableListOf() }.add(b)
        adjacencyList.getOrPut(b) { mutableListOf() }.add(a)
    }
}