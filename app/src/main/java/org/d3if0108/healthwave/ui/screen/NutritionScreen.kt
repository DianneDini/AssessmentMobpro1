package org.d3if0108.healthwave.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import org.d3if0108.healthwave.ui.theme.HealthwaveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.nutrisi))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        NutritionContent(Modifier.padding(padding))
    }
}

@Composable
fun NutritionContent(modifier: Modifier) {

    val gambar = Gambar(R.drawable.gambar2)

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        item {
            Image(
                painter = painterResource(gambar.imageResId),
                contentDescription = stringResource(R.string.gambar),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 350.dp, height = 225.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.teks1),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.teks2),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.teks3),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.teks4),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.teks5),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.teks6),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NutritionScreenPreview() {
    HealthwaveTheme {
        NutritionScreen(rememberNavController())
    }
}
