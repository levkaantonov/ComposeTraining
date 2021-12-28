package levkaantonov.com.study.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import levkaantonov.com.study.composetraining.ui.theme.ComposeTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MediaQuery(Dimensions.Width lessThan 400.dp) {
//                Text(text = "I'm only show below a width of 400dp")
//            }

            Text(
                text = "I'm only show below a width of 400dp",
                modifier = Modifier
                    .background(Color.Green)
                    .mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        modifier = Modifier.size(300.dp)
                    )
            )
        }
    }
}