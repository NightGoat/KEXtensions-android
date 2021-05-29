import java.util.*

/**
* With that you can write something like this: Date() + Date()
*/
operator fun Date.plus(other: Date): Date = Date(this.time + other.time)
operator fun Date.minus(other: Date): Date = Date(this.time - other.time)

fun Date?.orNow() = this ?: Date()