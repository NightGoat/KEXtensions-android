import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
* Shorter version of coroutine context swtiching to Default dispatcher
*/
suspend fun <T> doOnDefault(doFun: () -> T) = withContext(Dispatchers.Default) {
    doFun()
}

/**
* Shorter version of coroutine context swtiching to Main dispatcher
*/
suspend fun <T> doOnMain(doFun: () -> T) = withContext(Dispatchers.Main) {
    doFun()
}

/**
* Shorter version of coroutine context swtiching to IO dispatcher
*/
suspend fun <T> doOnIO(doFun: () -> T) = withContext(Dispatchers.IO) {
    doFun()
}

/**
* try default fun realisation
*/
fun <T : Any> tryOrDefault(defaultIfCatches: T, tryFunc: () -> T): T {
  return try {
    tryFunc()
    } catch (e: Exception) {
      //pls add logging here,
      //for example in android: Log.e ("tag", "tryOrDefault exception: ${e.message}", e)
      defaultIfCatches
    }
}

fun <T : Any> tryOrNull(tryFunc: () -> T): T? {
    return try {
        tryFunc()
    } catch (e: Exception) {
        //pls add logging here,
        //for example in android: Log.e ("tag", "tryOrNull exception: ${e.message}", e)
        null
    }
}

fun tryOrEmpty(tryFunc: () -> String) = tryOrDefault("") { tryFunc() }

fun tryOrNow(tryFunc: () -> Date) = tryOrDefault(Date()) { tryFunc() }
