package com.excercise.androidtesting

open class Properties(
) {
    private var showSize: Int = 0
    open fun setShowSize(size: Int) {
        this.showSize = size
    }
    open fun getShowSize() = showSize
}