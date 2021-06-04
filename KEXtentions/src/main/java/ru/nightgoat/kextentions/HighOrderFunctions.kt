import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.nightgoat.kextentions.getNameAndMessage
import ru.nightgoat.kextentions.utils.Kex
import java.util.*

/**
 * Shorter version of coroutine context switching to Default dispatcher
 */
suspend fun <T> doOnDefault(doFun: () -> T) = withContext(Dispatchers.Default) {
    doFun()
}

/**
 * Shorter version of coroutine context switching to Main dispatcher
 */
suspend fun <T> doOnMain(doFun: () -> T) = withContext(Dispatchers.Main) {
    doFun()
}

/**
 * Shorter version of coroutine context switching to IO dispatcher
 */
suspend fun <T> doOnIO(doFun: () -> T) = withContext(Dispatchers.IO) {
    doFun()
}

/**
 * try default fun realisation
 */
fun <T : Any> tryOrDefault(defaultIfCatches: T, tag: String = "tryOrDefault(): ", tryFunc: () -> T): T {
    return try {
        tryFunc()
    } catch (e: Exception) {
        Kex.loggE(tag = tag, message = e.getNameAndMessage(), e = e)
        defaultIfCatches
    }
}

fun <T : Any> tryOrNull(tag: String = "tryOrNull(): ", tryFunc: () -> T): T? {
    return try {
        tryFunc()
    } catch (e: Exception) {
        Kex.loggE(tag = tag, message = e.getNameAndMessage(), e = e)
        null
    }
}

fun tryOrEmpty(tag: String = "tryOrEmpty(): ", tryFunc: () -> String) =
    tryOrDefault("", tag) { tryFunc() }

fun tryOrNow(tag: String = "tryOrNow(): ", tryFunc: () -> Date) =
    tryOrDefault(Date(), tag) { tryFunc() }