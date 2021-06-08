package ru.nightgoat.kextentions

fun Throwable.getNameAndMessage() = "KEXception: $this, message: $message"