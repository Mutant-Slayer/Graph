import java.util.*

fun main() {
    val edgeList = listOf(
        listOf(0, 1, 4),
        listOf(0, 2, 8),
        listOf(1, 4, 6),
        listOf(2, 3, 2),
        listOf(3, 4, 10)
    )
    val adjacencyList = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    convertToAdjacencyList(edgeList, adjacencyList)

    val distance = MutableList(adjacencyList.size) { Int.MAX_VALUE }
    val source = 2
    algo(source, distance, adjacencyList)

    /**
     * Distance from source 0 => [0 4 8 10 10 ]
     *
     * Distance from source 2 => [8 12 0 2 12 ]
     */

    distance.forEach {
        print("$it ")
    }
}

private fun convertToAdjacencyList(
    edgeList: List<List<Int>>,
    adjacencyList: MutableMap<Int, MutableList<Pair<Int, Int>>>
) {

    for (i in 0 until edgeList.size) {
        val a = edgeList[i][0]
        val b = edgeList[i][1]
        val weight = edgeList[i][2]
        adjacencyList.getOrPut(a) { mutableListOf() }.add(Pair(b, weight))
        adjacencyList.getOrPut(b) { mutableListOf() }.add(Pair(a, weight))
    }
}

private fun algo(source: Int, distance: MutableList<Int>, adjacencyList: MutableMap<Int, MutableList<Pair<Int, Int>>>) {

    /**
     * Step-by-Step Implementation
     *
     * Set dist[source]=0 and all other distances as infinity.
     * Push the source node into the min heap as a pair <node, distance> → i.e., <source, 0>.
     * Pop the top element (node with the smallest distance) from the min heap.
     * For each adjacent neighbor of the current node:
     * Calculate the distance using the formula:
     * dist[v] = dist[u] + weight[u][v]
     *     If this new distance is shorter than the current dist[v], update it.
     *     Push the updated pair <v, dist[v]> into the min heap
     * Repeat step 3 until the min heap is empty.
     * Return the distance array, which holds the shortest distance from the source to all nodes.
     */

    val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { pair -> pair.second })

    minHeap.add(Pair(source, 0))
    distance[source] = 0

    while (minHeap.isNotEmpty()) {
        val curr = minHeap.poll()
        val element = curr.first
        val weight = curr.second

        adjacencyList[element]?.forEach { pair ->
            if (pair.second + weight < distance[pair.first]) {
                distance[pair.first] = pair.second + weight
                minHeap.add(Pair(pair.first, pair.second + weight))
            }
        }
    }
}