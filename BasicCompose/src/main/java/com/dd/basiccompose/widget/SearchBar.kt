/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * SearchBar.kt is part of Kizzy
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.dd.basiccompose.widget

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String = "",
    placeholder: String = "",
    onClose: () -> Unit = {},
    onTextChanged: ((String) -> Unit)
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
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
                placeholder = { Text(placeholder) },
                trailingIcon = {
                    IconButton(onClick = {
                        if (text.isNotEmpty())
                            onTextChanged("")
                        else
                            onClose()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close Icon"
                        )
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
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp)
                )
            )
        }
    )
}

@Preview
@Composable
fun Search() {
    SearchBar(
        text = "test"
    ) {
    }
}