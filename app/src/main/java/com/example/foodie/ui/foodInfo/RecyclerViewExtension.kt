package com.example.foodie.ui.foodInfo

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.setDivider(@DrawableRes drawableRes: Int, orientation: Int) {
    val divider = DividerItemDecoration(this.context, orientation)
    val drawable = ContextCompat.getDrawable(this.context, drawableRes)
    drawable?.let {
        divider.setDrawable(it)
        addItemDecoration(divider)
    }
}