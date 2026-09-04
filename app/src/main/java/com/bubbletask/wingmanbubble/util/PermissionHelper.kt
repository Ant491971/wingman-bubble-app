package com.bubbletask.wingmanbubble.util

import android.content.Context
import android.provider.Settings

class PermissionHelper(private val context: Context) {
    fun canDrawOverlays(): Boolean = Settings.canDrawOverlays(context)
}