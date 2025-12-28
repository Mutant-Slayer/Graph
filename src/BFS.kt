fun main() {
    val edgeList = listOf(
        listOf(0, 1),
        listOf(0, 2),
        listOf(0, 5),
        listOf(2, 3),
        listOf(3, 4)
    )
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    convertToAdjacencyList(edgeList, adjacencyList)
    val source = 2

    bfsAlgo(adjacencyList, source)
}

/***
 * So here we are using Queue, because we want to process neighbors first
 * we keep a visited array, as soon as we pop from queue we mark that node as visited
 * initially we will put source in queue and will run the loop till queue is not empty
 * And each time while marking a node as visited we will print that node which will be our BFS traversal
 *
 * so in this case answer would be [0 1 2 5 3 4]
 */

private fun bfsAlgo(
    adjacencyList: MutableMap<Int, MutableList<Int>>,
    source: Int,
) {
    val visitedList = MutableList(adjacencyList.size) { 0 }
    val queue = ArrayDeque<Int>()
    queue.add(source)

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        visitedList[current] = 1
        print("$current ")

        adjacencyList[current]?.forEach { neighbour ->
            if (visitedList[neighbour] == 0) {
                queue.add(neighbour)
            }
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