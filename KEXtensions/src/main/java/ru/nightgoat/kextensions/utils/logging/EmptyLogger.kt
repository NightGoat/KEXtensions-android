package ru.nightgoat.kextensions.utils.logging

import io.github.nightgoat.kexcore.utils.logging.ILogger

object EmptyLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) = Unit
    override fun loggD(message: String, tag: String?) = Unit
}