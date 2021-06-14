package ru.nightgoat.kextensions.utils.logging

interface ILogger {
    fun loggE(message: String, tag: String?, e: Throwable? = null)
}