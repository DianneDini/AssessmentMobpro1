package org.d3if0108.healthwave.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0108.healthwave.R
import org.d3if0108.healthwave.navigation.Screen
import org.d3if0108.healthwave.ui.theme.HealthwaveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmrScreen(navController: NavHostController) {
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
                    Text(text = stringResource(id = R.string.bmr_screen))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.Nutrition.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = stringResource(R.string.nutrisi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
            )
        },
    ) { padding ->
        BmrContent(navController, Modifier.padding(padding))
    }
}

@SuppressLint("StringFormatMatches")
@Composable
fun BmrContent(navController: NavHostController, modifier: Modifier) {

    var usia by rememberSaveable { mutableStateOf("") }
    var usiaError by rememberSaveable { mutableStateOf(false) }

    var tinggi by rememberSaveable { mutableStateOf("") }
    var tinggiError by rememberSaveable { mutableStateOf(false) }

    var berat by rememberSaveable { mutableStateOf("") }
    var beratError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(id = R.string.lakilaki),
        stringResource(id = R.string.perempuan),
    )
    var gender by rememberSaveable { mutableStateOf<String?>(null) }
    var genderError by rememberSaveable { mutableStateOf(false) }
    var genderEmpty by rememberSaveable { mutableStateOf(false) }

    val aktivitasFisikOptions = listOf(
        stringResource(id = R.string.tidak_pernah),
        stringResource(id = R.string.jarang_berolahraga),
        stringResource(id = R.string.cukup_sering),
        stringResource(id = R.string.sering_berolahraga),
        stringResource(id = R.string.sangat_sering),
    )
    var aktivitasFisik by rememberSaveable { mutableStateOf<String?>(null) }
    var aktivitasFisikError by rememberSaveable { mutableStateOf(false) }
    var aktivitasFisikEmpty by rememberSaveable { mutableStateOf(false) }

    var totalBmrHasil by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    var inputValid by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            Text(
                text = stringResource(id = R.string.bmr_intro),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            )
            // TextField untuk usia
            OutlinedTextField(
                value = usia,
                onValueChange = { usia = it },
                label = { Text(text = stringResource(R.string.usia)) },
                isError = usiaError,
                trailingIcon = { IconPicker(usiaError, "") },
                supportingText = { ErrorHint(usiaError) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 19.dp)
            )
            // RadioButton untuk gender
            Row (
                modifier = Modifier
                    .border(width = 1.dp,
                        color = if (genderEmpty) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                radioOptions.forEach { text ->
                    GenderOption(
                        label = text,
                        isSelected = gender == text,
                        isError = genderError,
                        onClick = { gender = text },
                        modifier = Modifier
                            .selectable(
                                selected = gender == text,
                                onClick = { gender = text },
                                role = Role.RadioButton
                            )
                            .weight(2f)
                            .padding(20.dp)
                    )
                }
            }
            if (genderEmpty) {
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.salah_input),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            // TextField untuk tinggi
            OutlinedTextField(
                value = tinggi,
                onValueChange = { tinggi = it },
                label = { Text(text = stringResource(R.string.tinggi_badan)) },
                isError = tinggiError,
                trailingIcon = { IconPicker(tinggiError, "cm") },
                supportingText = { ErrorHint(tinggiError) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 13.dp, top = 27.dp)
            )
            // TextField untuk berat
            OutlinedTextField(
                value = berat,
                onValueChange = { berat = it },
                label = { Text(text = stringResource(R.string.berat_badan)) },
                isError = beratError,
                trailingIcon = { IconPicker(beratError, "kg") },
                supportingText = { ErrorHint(beratError) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 17.dp)
            )

            // RadioButton untuk aktivitas fisik
            Column (
                modifier = Modifier
                    .border(width = 1.dp,
                        color = if (aktivitasFisikEmpty) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                aktivitasFisikOptions.forEach { text ->
                    AktivitasFisikOptions(
                        label = text,
                        isSelected = aktivitasFisik == text,
                        isError = aktivitasFisikError,
                        onClick = { aktivitasFisik = text },
                        modifier = Modifier
                            .selectable(
                                selected = aktivitasFisik == text,
                                onClick = { aktivitasFisik = text },
                                role = Role.RadioButton
                            )
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                    )
                }
            }
            if (aktivitasFisikEmpty) {
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.salah_input),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Button(
                onClick = {

                    usiaError = (usia == "" || usia == "0")
                    tinggiError = (tinggi == "" || tinggi == "0")
                    beratError = (berat == "" || berat == "0")
                    genderError = (gender == null)
                    aktivitasFisikError = (aktivitasFisik == null)
                    genderEmpty = (gender == null)
                    aktivitasFisikEmpty = (aktivitasFisik == null)

                    if (usiaError || tinggiError || beratError || genderError || aktivitasFisikError || gender == null || aktivitasFisik == null) return@Button

                    val tinggiValue = tinggi.toFloatOrNull()
                    val beratValue = berat.toIntOrNull()
                    val usiaValue = usia.toIntOrNull()

                    if (beratValue != null && tinggiValue != null && usiaValue != null) {
                        val bmr = if (gender == context.getString(R.string.lakilaki)) {
                            66 + (13.7 * beratValue) + (5 * tinggiValue) - (6.8 * usiaValue)
                        } else {
                            655 + (9.6 * beratValue) + (1.8 * tinggiValue) - (4.7 * usiaValue)
                        }

                        val faktorAktivitas = when (aktivitasFisik) {
                            context.getString(R.string.tidak_pernah) -> 1.2f
                            context.getString(R.string.jarang_berolahraga) -> 1.375f
                            context.getString(R.string.cukup_sering) -> 1.55f
                            context.getString(R.string.sering_berolahraga) -> 1.725f
                            context.getString(R.string.sangat_sering) -> 1.9f
                            else -> 1.0f
                        }

                        val totalBmr = bmr * faktorAktivitas

                        if (beratValue != null && tinggiValue != null && usiaValue != null) {
                            // hitung total bmr
                            totalBmrHasil = context.getString(R.string.total_bmr_hasil, totalBmr)
                            inputValid = true
                        } else {
                            inputValid = false
                        }
                    }
                },
                modifier = Modifier.padding(top = 70.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }

            Text(
                text = totalBmrHasil,
                modifier = Modifier.padding(top = 50.dp),
            )

            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template,
                            usia, gender, tinggi, berat, totalBmrHasil)
                    )
                },
                modifier = Modifier.padding(top = 20.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                enabled = inputValid // button aktif ketika input dan hasil valid
            ) {
                Text(text = stringResource(R.string.bagikan))
            }

        }
    }
}


@Composable
fun GenderOption(label: String, isSelected: Boolean, isError: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
            )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun AktivitasFisikOptions(label: String, isSelected: Boolean, isError: Boolean, onClick: () -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 20.dp)
        )
    }

}

@Composable
fun  IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.salah_input))
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun BmrScreenPreview() {
    HealthwaveTheme {
        BmrScreen(rememberNavController())
    }
}



