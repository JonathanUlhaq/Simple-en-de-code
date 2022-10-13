package com.belajar.encodedecode.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecScreenPreview() {
    ScreenLayout()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenLayout(
    viewModel:MainViewModel = viewModel(),
    modifier:Modifier = Modifier) {

    Scaffold(
        topBar = { TopBar()}
    ) {
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp, top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
                ){

            // Main Screen Layout
            MainScreen(value = viewModel.userInput,
                valueChange = {viewModel.updateUserInput(it)},
                onDecrypt = {viewModel.submitProccess()},
                onDone = {viewModel.submitProccess()})

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
                    ) {

               Column {
                   Text(text = "Encrypt")
                   HistoryProcessEncrypt(encrypt = viewModel.encodeHistory)
               }
                Spacer(modifier = Modifier.width(100.dp))
               Column() {
                   Text(text = "Decrypt")
                   HistoryProcessDecript(decrypt = viewModel.decodeHistory)
               }
            }
        }
    }

}

@Composable
fun HistoryProcessEncrypt(encrypt:MutableList<String>) {
    LazyColumn(
    ) {
        items(encrypt) {
            Text(text = it)
        }
    }
}

@Composable
fun TopBar (
    modifier:Modifier = Modifier
) {
    Text(text = "De/Encode VM",
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(top = 20.dp)
    )
}

@Composable
fun HistoryProcessDecript(decrypt:MutableList<String>) {
    LazyColumn(
    ) {
        items(decrypt) {
            Text(text = it)
        }
    }
}





@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    value:String,
    valueChange:(String) -> Unit,
    onDecrypt:() -> Unit,
    onDone:() -> Unit
) {
    // main layout
    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Apps Encode and Decode Base 64")
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = value,
            onValueChange = valueChange,
            keyboardActions = KeyboardActions(onDone = {onDone()} ),
            label = { Text(text = "Masukkan message en/decode")},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ))
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = onDecrypt) {
            Text(text = "En/decode Pesan")
        }

    }
}