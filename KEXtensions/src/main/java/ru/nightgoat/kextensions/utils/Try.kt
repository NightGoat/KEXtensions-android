package ru.nightgoat.kextensions.utils

import ru.nightgoat.kextensions.log

/**
 * Fork of Try class, slightly modified
 * @author bijukunjummen
 * @see <a href="https://bijukunjummen.medium.com/deriving-a-kotlin-try-type-c3a544411b5a">from Medium article</a>
 *
 * how to use: call val isOk = Try { 2 / 0 } will give you Failure, calling isOk.getOrNull() will give you null
 *
 * Same as kotlin runCatching method.
 */
sealed class Try<T> {

    data class Success<T>(val data: T) : Try<T>()
    class Failure<T>(val throwable: Throwable) : Try<T>()

    /**
     * @throws Throwable
     */
    fun getOrThrow(): T {
        return either {
            throw it
        }
    }

    fun getOrNull(): T? {
        return when (this) {
            is Success -> this.data
            is Failure -> null
        }
    }

    fun getOrDefault(doBlock: () -> T): T {
        return either {
            doBlock()
        }
    }

    private fun either(failureBlock: (Throwable) -> T): T {
        return when (this) {
            is Success -> this.data
            is Failure -> failureBlock(this.throwable)
        }
    }

    fun isSuccess() = this is Success
    fun isFail() = this is Failure

    fun <R> map(block: (T) -> R): Try<R> {
        return of {
            block(getOrThrow())
        }
    }

    fun <R> flatMap(tryBlock: (T) -> Try<R>): Try<R> {
        return try {
            tryBlock(getOrThrow())
        } catch (e: Throwable) {
            Failure(e)
        }
    }

    fun doIfSuccess(doBlock: (T) -> Unit): Try<T> {
        if (this is Success) {
            doBlock(this.getOrThrow())
        }
        return this
    }

    fun doIfFail(doBlock: (Throwable) -> Unit): Try<T> {
        if (this is Failure) {
            doBlock(throwable)
        }
        return this
    }

    fun toResult(): Result<T> {
        return when (this) {
            is Success -> Result.success(this.data)
            is Failure -> Result.failure(this.throwable)
        }
    }

    inline fun finally(doFun: (T?) -> Unit): Try<T> {
        when (this) {
            is Success -> doFun(this.data)
            else -> doFun(null)
        }
        return this
    }

    companion object {
        /**
         * Same as invoke
         */
        @Deprecated("Use invoke instead, just remove .of")
        inline fun <T> of(tag: String? = null, tryBlock: () -> T): Try<T> {
            return try {
                Success(tryBlock())
            } catch (t: Throwable) {
                t.log(tag)
                Failure(t)
            }
        }

        inline operator fun <T> invoke(
            tag: String? = null,
            finallyBlock: (T?) -> Unit = {},
            tryBlock: () -> T
        ): Try<T> {
            var result: T? = null
            return try {
                Success(tryBlock()).also {
                    result = it.data
                }
            } catch (t: Throwable) {
                t.log(tag)
                Failure(t)
            } finally {
                finallyBlock(result)
            }
        }
    }
}