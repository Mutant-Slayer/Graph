fun main() {
    val edgeList = listOf(
        listOf(1, 2),
        listOf(2, 3),
        listOf(3, 4),
        listOf(4, 2),
        listOf(1, 3)
    )

//    size of edge list is m
//    TC :  O(m)
//
//    space complexity : O(E+V)

    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()

    for (i in 0 until edgeList.size) {
        val a = edgeList[i][0]
        val b = edgeList[i][1]
        adjacencyList.getOrPut(a) { mutableListOf() }.add(b)
        adjacencyList.getOrPut(b) { mutableListOf() }.add(a)
    }

    printAdjacencyList(adjacencyList)
}

private fun printAdjacencyList(adjacencyList: Map<Int, List<Int>>) {

    /**
     * Graph Representation
     *
     * Node 1 => Neighbours : 2 3
     * Node 2 => Neighbours : 1 3 4
     * Node 3 => Neighbours : 1 2 4
     * Node 4 => Neighbours : 2 3
     */

    // 1 node will run a for loop for n times
    // TC: O(n2)

    for (i in 1..adjacencyList.size) {
        print("Node $i => Neighbours : ")
        adjacencyList[i]?.forEach {
            print("$it ")
        }
        println()
    }
}