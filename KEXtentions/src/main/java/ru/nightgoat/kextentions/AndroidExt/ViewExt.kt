import android.view.View

fun View.setVisible(visibility: Boolean = true) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun View.setVisibleGone(isGone: Boolean = true) {
    setVisible(!isGone)
}

fun View.setInvisible(invisible: Boolean = true) {
    visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}
