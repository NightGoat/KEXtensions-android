package ru.nightgoat.kextensions.utils

import ru.nightgoat.kextensions.log

/**
 * Fork of Try class, slightly modified
 * @author bijukunjummen
 * @see <a href="https://bijukunjummen.medium.com/deriving-a-kotlin-try-type-c3a544411b5a">from Medium article</a>
 */
sealed class Try<T> {

    data class Success<T>(val data: T) : Try<T>()
    class Failure<T>(val throwable: Throwable) : Try<T>()

    /**
     * @throws Throwable
     */
    fun getOrThrow(): T {
        return when (this) {
            is Success -> this.data
            is Failure -> throw throwable
        }
    }

    fun getOrNull(): T? {
        return when (this) {
            is Success -> this.data
            is Failure -> null
        }
    }

    fun getOrDefault(doBlock: () -> T): T {
        return when (this) {
            is Success -> this.getOrThrow()
            is Failure -> doBlock()
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

    companion object {

        fun <T> of(tag: String? = null, tryBlock: () -> T): Try<T> {
            return try {
                Success(tryBlock())
            } catch (t: Throwable) {
                t.log(tag)
                Failure(t)
            }
        }
    }
}