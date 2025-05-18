package serie2.part1_2

class IntArrayList(private val capacity: Int) : Iterable<Int> {
    private val data = Array(capacity) { 0 }
    private var start = 0
    private var size = 0
    private var globalAdd = 0

    fun append(x:Int):Boolean {
        if (capacity==size) return false
        val lastindex = (start + size) % capacity
        data[lastindex] = x + globalAdd
        size++
        return true
    }

    fun get(n:Int):Int?  {
        if (n < 0 || n >= size) return null
        val index = (start + n) % capacity
        return data[index] + + globalAdd
    }

    fun addToAll(x:Int)   {
        globalAdd += x
    }

    fun remove():Boolean {
        if (size == 0) return false
        start = (start + 1) % capacity
        size--
        return true
    }

    override fun iterator(): Iterator<Int> { // Opcional
        return object : Iterator<Int> {
            var i = 0
            override fun hasNext(): Boolean = i < size
            override fun next(): Int {
                val index = (start + i) % capacity
                i++
                return data[index] + globalAdd
            }
        }
    }
}

