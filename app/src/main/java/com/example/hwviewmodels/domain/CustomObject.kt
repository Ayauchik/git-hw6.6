package com.example.hwviewmodels.domain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hwviewmodels.ui.theme.CustomTheme


data class CustomObject(val id: Int, val name: String)

@Composable
fun CustomScreen(viewModel: CustomObjectViewModel) {

    Column() {
        val customObjects = remember {
            List(10) { index ->
                CustomObject(index, "Item ${index + 1}")
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(customObjects.size) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    backgroundColor = CustomTheme.colors.tertiary
                ) {
                    Text(
                        text = (item+1).toString(),
                        modifier = Modifier.padding(16.dp),
                        color = CustomTheme.colors.onTertiary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(text = viewModel.text) { viewModel.onTextChange(it) }

        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(onClick = {
            // TODO: Implement button logic
        })
    }
}

@Composable
fun CustomTextField(
    text: State<String>,
    onChanged: (String) -> Unit
) {

    TextField(
        value = text.value,
        onValueChange = onChanged,
        label = { Text("Enter your text") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = CustomTheme.colors.onPrimary,
            backgroundColor = CustomTheme.colors.primary
        )

    )
}

@Composable
fun CustomButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CustomTheme.colors.secondary,
            contentColor = CustomTheme.colors.onSecondary
        )
    ) {
        Text("Custom Button")
    }
}
