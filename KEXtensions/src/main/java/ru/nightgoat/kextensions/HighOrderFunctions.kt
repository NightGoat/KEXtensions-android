package ru.nightgoat.kextensions

import io.github.nightgoat.kexcore.tryOrDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Shorter version of coroutine context switching to Default dispatcher
 */
suspend fun <T> doOnDefault(doFun: suspend () -> T) = withContext(Dispatchers.Default) {
    doFun()
}

/**
 * Shorter version of coroutine context switching to Main dispatcher
 */
suspend fun <T> doOnMain(doFun: suspend () -> T) = withContext(Dispatchers.Main) {
    doFun()
}

/**
 * Shorter version of coroutine context switching to IO dispatcher
 */
suspend fun <T> doOnIO(doFun: suspend () -> T) = withContext(Dispatchers.IO) {
    doFun()
}


fun tryOrNow(tag: String = "tryOrNow(): ", tryFunc: () -> Date) =
    tryOrDefault(Date(), tag) { tryFunc() }