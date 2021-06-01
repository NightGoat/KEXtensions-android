import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.text.format.Formatter
import android.util.TypedValue
import ru.nightgoat.kextentions.utils.Kex
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
            Kex.loggE(tag, "exception: ${e.message}", e)
        }
    }.logIfNull("launch intent null!", tag)
}

fun Context.getApplicationName(packageName: String = this.packageName, tag: String = "getApplicationName(): "): String {
    val pm = applicationContext.packageManager
    val applicationInfo: ApplicationInfo? = try {
        pm.getApplicationInfo(packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        Kex.loggE("package manager null", tag, e)
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
