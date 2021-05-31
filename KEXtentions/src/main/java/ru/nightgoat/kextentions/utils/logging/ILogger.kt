package ru.nightgoat.kextentions.utils.logging

interface ILogger {
    fun loggE(message: String, tag: String?, e: Throwable? = null)
}