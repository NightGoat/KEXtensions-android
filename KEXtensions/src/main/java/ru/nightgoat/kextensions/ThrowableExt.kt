package ru.nightgoat.kextensions

fun Throwable.getNameAndMessage() = "KEXception: $this, message: $message"