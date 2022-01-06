package levkaantonov.com.study.composetraining

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import levkaantonov.com.study.composetraining.ui.theme.ComposeTrainingTheme

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTrainingTheme {
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )

                val lifeCycleOwner = LocalLifecycleOwner.current

                DisposableEffect(
                    key1 = lifeCycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_START) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                        }
                        lifeCycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifeCycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionState.permissions.forEach { permission ->
                        when (permission.permission) {
                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    permission.hasPermission -> {
                                        Text(text = "Record audio permission accepted")
                                    }
                                    permission.shouldShowRationale -> {
                                        Text(text = "Record audio is needed to access the mic")
                                    }
                                    !permission.hasPermission &&
                                            !permission.shouldShowRationale -> {
                                        Text(
                                            text = "Record audio permission was permanently denied." +
                                                    "You can enable it in the app settings"
                                        )
                                    }
                                }
                            }
                            Manifest.permission.CAMERA -> {
                                when {
                                    permission.hasPermission -> {
                                        Text(text = "Camera permission accepted")
                                    }
                                    permission.shouldShowRationale -> {
                                        Text(text = "Camera permission is needed to access the camera")
                                    }
                                    !permission.hasPermission &&
                                            !permission.shouldShowRationale -> {
                                        Text(
                                            text = "Camera permission was permanently denied." +
                                                    "You can enable it in the app settings"
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Text(text = "Record audio permission accepted")
                }
            }
        }
    }
}