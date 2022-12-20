package co.csadev.adventOfCode

fun <T> MutableList<T>.circular() = MutableCircularList(this)

class MutableCircularList<T>(private val list: MutableList<T>) : MutableList<T> by list {
    override fun get(index: Int) = list[index.mod(size)]
    override fun addAll(index: Int, elements: Collection<T>) = list.addAll(index.mod(size), elements)
    override fun add(index: Int, element: T) = list.add(index.mod(size), element)
    override fun listIterator(index: Int) = list.listIterator(index.mod(size))
    override fun removeAt(index: Int) = list.removeAt(index.mod(size))
    override fun set(index: Int, element: T) = list.set(index.mod(size), element)

    fun move(index: Int, distance: Int) = move(index, distance.toLong())
    fun move(index: Int, distance: Long) {
        removeAt(index).run {
            val newIndex = (index + distance).mod(size)
            add(newIndex, this)
        }
    }
}
