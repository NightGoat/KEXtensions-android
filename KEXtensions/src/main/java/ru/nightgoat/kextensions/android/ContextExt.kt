package ru.nightgoat.kextensions.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.text.format.Formatter
import android.util.TypedValue
import androidx.annotation.RequiresApi
import ru.nightgoat.kextensions.log
import ru.nightgoat.kextensions.logIfNull
import ru.nightgoat.kextensions.orIfNull
import kotlin.system.exitProcess

@Suppress("DEPRECATION")
fun Context.getDeviceIp(): String {
    val wm = this.getSystemService(Context.WIFI_SERVICE) as WifiManager
    return Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
}

@SuppressLint("HardwareIds")
fun Context.getDeviceId(): String {
    return Settings.Secure.getString(contentResolver, "android_id")
}

private val outValue by lazy { TypedValue() }

fun Context.selectableItemBackgroundResId(): Int {
    theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
    return outValue.resourceId
}

fun Context.isWriteExternalStoragePermissionGranted(): Boolean {
    return getNotGrantedPermissions(listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)).isEmpty()
}

fun Context.getNotGrantedPermissions(neededPermissions: Collection<String>): List<String> {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        return emptyList()
    }
    return neededPermissions.filter { this.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
}

fun Context.restartApp() {
    val packageManager = packageManager
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    intent?.component?.let { componentName ->
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        this.startActivity(mainIntent)
        exitProcess(0)
    }
}

fun Context.openAnotherApp(packageName: String, tag: String = "openAnotherApp()") {
    packageManager.getLaunchIntentForPackage(packageName.trim())?.let { launchIntent ->
        try {
            startActivity(launchIntent)
        } catch (e: Exception) {
            e.log(tag = tag)
        }
    }.logIfNull("launch intent null!", tag)
}

fun Context.getApplicationName(
    packageName: String = this.packageName,
    tag: String = "getApplicationName(): "
): String {
    val pm = applicationContext.packageManager
    val applicationInfo: ApplicationInfo? = try {
        pm.getApplicationInfo(packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        e.log(message = "package manager null", tag)
        null
    }
    return applicationInfo?.let {
        pm.getApplicationLabel(applicationInfo) as String
    }.orIfNull {
        "(unknown)"
    }
}

fun Context.getAppVersion(packageName: String, withHash: Boolean = false): String? {
    return packageManager?.getPackageInfo(packageName, 0)?.versionName.let {
        if (withHash) {
            it
        } else {
            it?.split(".")?.dropLast(1)?.joinToString(".")
        }
    }
}

fun Context.getAppInfo(packageName: String = this.packageName, withHash: Boolean = true): String {
    return "${getApplicationName(packageName)} v${getAppVersion(packageName, withHash = withHash)}"
}

@Suppress("UNCHECKED_CAST")
fun <T> Context.getService(serviceName: String): T = this.getSystemService(serviceName) as T

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.M)
fun Context.isInternetAvailable(): Boolean {
    val cm: ConnectivityManager = this.getService(Context.CONNECTIVITY_SERVICE)
    val networkCapabilities = cm.activeNetwork ?: return false
    val activeNetwork = cm.getNetworkCapabilities(networkCapabilities) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        else -> false
    }
}