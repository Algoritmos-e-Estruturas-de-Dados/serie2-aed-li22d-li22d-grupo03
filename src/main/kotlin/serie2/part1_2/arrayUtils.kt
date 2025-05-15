package serie2.part1_2
import kotlin.random.Random

fun minimum(maxHeap: Array<Int>, heapSize: Int): Int {
    var min = maxHeap[heapSize / 2]
        for (i in heapSize / 2 .. heapSize-1) {
            if (maxHeap[i] < min) {
                min = maxHeap[i]
            }
        }
        return min
}
