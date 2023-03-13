package com.dd.basiccompose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun SearchPreview() {
    Column {
        SearchBar(hint = "test") {
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBarNoClick("test") {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarNoClick(
    hint: String = "",
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
    onClick: (() -> Unit)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(200.dp))
            .background(backgroundColor)
            .clickable {
                onClick.invoke()
            }
            .padding(
                start = 14.dp,
                end = 12.dp,
                top = 10.dp,
                bottom = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = Icons.Filled.Search.name,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 12.dp)
        )
        Text(
            text = hint,
            style = MaterialTheme.typography.bodyLarge.copy(
                textColor
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String = "",
    hint: String = "",
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp),
    onTextChanged: ((String) -> Unit)
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = textColor
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(200.dp)
            ),
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                innerTextField = innerTextField,
                placeholder = { Text(hint) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = Icons.Filled.Search.name,
                        tint = textColor,
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(onClick = {
                            onTextChanged("")
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = Icons.Filled.Close.name,
                                tint = textColor,
                            )
                        }
                    }
                },
                singleLine = true,
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                visualTransformation = VisualTransformation.None,
                contentPadding = PaddingValues(
                    start = 14.dp,
                    end = 12.dp,
                    top = 10.dp,
                    bottom = 10.dp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = backgroundColor
                )
            )
        }
    )
}