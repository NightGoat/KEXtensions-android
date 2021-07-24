package ru.nightgoat.kextensions.utils.logging

object EmptyLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) = Unit
}