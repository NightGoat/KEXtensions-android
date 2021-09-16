package ru.nightgoat.kextensions.utils.logging

object EmptyLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) = Unit
    override fun loggD(message: String, tag: String?) = Unit
}