package com.saltserv.slaunches.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.saltserv.slaunches.R

@Composable
fun ErrorDialog(
    title: String,
    message: String
) {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true) }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = title)
                    },
                    text = {
                        Text(text = message)
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text(stringResource(id = R.string.ok))
                        }
                    },
                )
            }
        }
    }
}
