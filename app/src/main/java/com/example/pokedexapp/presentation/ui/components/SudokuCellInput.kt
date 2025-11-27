package com.example.pokedexapp.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuCellInput(
    value: String,
    onValueChange: (String) -> Unit,
    isInitial: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier,
) {
    val textColor =
        when {
            isInitial -> Color.Black
            isError -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.primary
        }

    Box(
        modifier =
            modifier
                .aspectRatio(1f)
                .border(0.5.dp, Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        TextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= 1 && (newValue.isEmpty() || newValue.matches(Regex("[1-9]")))) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier.fillMaxSize(),
            readOnly = isInitial,
            textStyle =
                MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = if (isInitial) FontWeight.Bold else FontWeight.Normal,
                    color = textColor,
                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}
