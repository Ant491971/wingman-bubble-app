package com.bubbletask.wingmanbubble.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bubbletask.wingmanbubble.service.BubbleService
import com.bubbletask.wingmanbubble.util.PermissionHelper

@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    val permissionHelper = remember { PermissionHelper(context) }
    var hasOverlayPermission by remember { mutableStateOf(permissionHelper.canDrawOverlays()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Wingman Bubble Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "System Overlay Permission")
            if (hasOverlayPermission) {
                Text(text = "Granted", color = MaterialTheme.colorScheme.primary)
            } else {
                Button(onClick = {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
                    context.startActivity(intent)
                }) {
                    Text("Grant")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val intent = Intent(context, BubbleService::class.java)
                context.startService(intent)
            },
            enabled = hasOverlayPermission
        ) {
            Text("Start Wingman Bubble")
        }

        Button(
            onClick = {
                val intent = Intent(context, BubbleService::class.java)
                context.stopService(intent)
            }
        ) {
            Text("Stop Wingman Bubble")
        }
    }
}