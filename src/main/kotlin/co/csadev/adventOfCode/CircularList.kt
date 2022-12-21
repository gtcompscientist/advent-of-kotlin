package co.csadev.adventOfCode

fun <T> MutableList<T>.circular() = MutableCircularList(this)

class MutableCircularList<T>(private val list: MutableList<T>) : MutableList<T> by list {
    override fun get(index: Int) = list[index.sized]
    override fun addAll(index: Int, elements: Collection<T>) = list.addAll(index.sized, elements)
    override fun add(index: Int, element: T) = list.add(index.sized, element)
    override fun listIterator(index: Int) = list.listIterator(index.sized)
    override fun removeAt(index: Int) = list.removeAt(index.sized)
    override fun set(index: Int, element: T) = list.set(index.sized, element)

    fun move(index: Int, distance: Int) = move(index, distance.toLong())
    fun move(index: Int, distance: Long) = add((index + distance).mod(size - 1), removeAt(index))

    private val Int.sized: Int
        get() = mod(size)

    private val Long.sized: Int
        get() = mod(size)
}
