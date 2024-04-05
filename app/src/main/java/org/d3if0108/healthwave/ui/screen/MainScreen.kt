package org.d3if0108.healthwave.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0108.healthwave.R
import org.d3if0108.healthwave.model.Gambar
import org.d3if0108.healthwave.navigation.Screen
import org.d3if0108.healthwave.ui.theme.HealthwaveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        ScreenContent(navController, Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(navController: NavHostController, modifier: Modifier) {

    val gambar = Gambar(R.drawable.gambar1)

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
    ){
       item {
           Box(
               modifier = Modifier
                   .width(150.dp)
                   .height(220.dp)
           ) {
               Column(
                   modifier = Modifier.fillMaxSize()
               ) {
                   Image(
                       painter = painterResource(gambar.imageResId),
                       contentDescription = stringResource(R.string.gambar),
                       contentScale = ContentScale.Crop,
                       modifier = Modifier.size(150.dp)
                   )
                   Button(
                       onClick = {navController.navigate(Screen.Bmr.route)},
                       modifier = Modifier
                           .align(Alignment.CenterHorizontally)
                           .fillMaxWidth(1f)
                           .padding(top = 15.dp),
                       contentPadding = PaddingValues(16.dp)
                   ) {
                       Text(
                           text = "BMR",
                           fontSize = 13.sp
                       )
                   }
               }
           }
       }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    HealthwaveTheme {
        MainScreen(rememberNavController())
    }
}