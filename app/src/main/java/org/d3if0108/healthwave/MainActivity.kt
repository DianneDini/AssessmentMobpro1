package org.d3if0108.healthwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.d3if0108.healthwave.model.Gambar
import org.d3if0108.healthwave.navigation.SetupNavGraph
import org.d3if0108.healthwave.ui.theme.HealthwaveTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthwaveTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph()
                }
            }
        }
    }

    private fun getData(): List<Gambar> {
        return listOf(
            Gambar(R.drawable.gambar1)
        )
    }
}

