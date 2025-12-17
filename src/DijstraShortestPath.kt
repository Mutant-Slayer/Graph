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

    val distance = MutableList<Pair<Int, Int>>(adjacencyList.size) { Pair(Int.MAX_VALUE, -1) }
    val source = 0

    algo(source = source, distance = distance, adjacencyList = adjacencyList)

    /**
     * So here we have got the distance array with its parent, we simply iterate over it backwards and find the whole path
     */

    val destination = 3
    var sourceNode = distance[destination].second
    val path = mutableListOf<Int>()
    path.add(destination)

    while (sourceNode != source) {
        path.add(sourceNode)
        sourceNode = distance[sourceNode].second
    }
    path.add(source)

    path.reverse()

    print("Shortest path between $source and $destination is ")
    path.forEach {
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

private fun algo(
    source: Int,
    distance: MutableList<Pair<Int, Int>>,
    adjacencyList: MutableMap<Int, MutableList<Pair<Int, Int>>>
) {

    /**
     * For finding the shortest path, we use the same logic as finding the distance,
     * its just that instead of using a distance array, we keep a Pair(distance, parent) array
     * basically we not only store shortest distance but also the parent through which one reached that shortest distance
     * so by backtracking that parent we can find the shortest distance for any destination node from a particular source
     */

    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { pair -> pair.second })

    pq.add(Pair(source, 0))
    distance[source] = Pair(0, source)

    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        val element = curr.first
        val weight = curr.second

        adjacencyList[element]?.forEach { neighbour ->
            if (distance[neighbour.first].first > weight + neighbour.second) {
                distance[neighbour.first] = Pair(weight + neighbour.second, element)
                pq.add(Pair(neighbour.first, weight + neighbour.second))
            }
        }
    }
}