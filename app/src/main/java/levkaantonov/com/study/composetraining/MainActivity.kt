package levkaantonov.com.study.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import levkaantonov.com.study.composetraining.ui.theme.ComposeTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()
            Scaffold(scaffoldState = scaffoldState) {
                var counter by remember {
                    mutableStateOf(0)
                }

                if (counter % 5 == 0 && counter > 0) {
                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                        scaffoldState.snackbarHostState.showSnackbar(counter.toString())
                    }
                }

                if (counter % 5 == 0)
                    Button(onClick = { counter++ }) {
                        Text(text = "Click me: ${counter}")
                    }
            }
        }
    }
}