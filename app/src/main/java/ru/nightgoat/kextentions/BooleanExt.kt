fun Boolean?.orTrue(): Boolean = this ?: true

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean.takeIfTrue() = this.takeIf { it }

fun Boolean.takeIfFalse() = this.takeIf { !it }
