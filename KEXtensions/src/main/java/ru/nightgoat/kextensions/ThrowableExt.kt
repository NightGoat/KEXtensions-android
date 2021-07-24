package ru.nightgoat.kextensions

import ru.nightgoat.kextensions.utils.Kex

fun Throwable.getNameAndMessage() = "KEXception: $this, message: $message"

fun Throwable.log(message: String? = null, tag: String? = null) = Kex.loggE(
    message ?: this.getNameAndMessage(), tag, this
)