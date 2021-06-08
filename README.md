[![](https://jitpack.io/v/NightGoat/KEXtentions.svg)](https://jitpack.io/#NightGoat/KEXtentions)
# KEXtentions
Kotlin and Android extentions library.

## About:
As Android developer, i use in my work Kotlin as main coding language, and Kotlin has a big killer feature - [extentions](https://kotlinlang.org/docs/extensions.html). Some extentions, such as [orIfNull](https://github.com/NightGoat/KEXtentions/blob/master/KEXtentions/src/main/java/ru/nightgoat/kextentions/OtherExt.kt) or [doOnDefault](https://github.com/NightGoat/KEXtentions/blob/master/KEXtentions/src/main/java/ru/nightgoat/kextentions/HighOrderFunctions.kt) I have been using many many times in alot of projects, and get kinda tired of copying this code in all projects, so this library was born.

##Examples:
Using this library does not requires knowledge or skill, this extentions will work as part of a kotlin language. For example:
You have liveData, and you want to get value from it, but your method requires non null values, you can use extention from [DoubleExt](https://github.com/NightGoat/KEXtentions/blob/master/KEXtentions/src/main/java/ru/nightgoat/kextentions/DoubleExt.kt) orZero():
```
val number = liveData {
	emit(2+2)
}

fun main {
    foo(number.orZero())
}

fun foo(bar: Int) {
   doStuff(bar)
}
```

##Logging
Some extentions does require logging, as they catching exceptions or catching null values from some places. Default Logger in this library is ordinary Android Log.e tool, but you can change it to any you want. Its already has Timber, so if you want to use it you need set it in your app starting point (Application or Activity) like this (ofcource you need to have Timber in your graddle dependencies):
```
Kex.setTimber()
```
If you want you can have your custom logger, you need to use ILogger interface from library and set your logger to Kex:
```
object MyCustomLogger: ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        \\Put your logging code here
    }
}

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Kex.setCustomLogger(MyCustomLogger)
    }
}
```

## How to add it:
* Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
* Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.nightgoat:kextentions:0.0.4'
	}
```
