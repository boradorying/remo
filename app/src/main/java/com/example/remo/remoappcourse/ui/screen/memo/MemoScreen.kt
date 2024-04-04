package com.example.remo.remoappcourse.ui.screen.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoScreen(
    state: MemoState,
    onEvent: (MemoEvent) -> Unit
) {
    Scaffold(
        topBar ={
            CenterAlignedTopAppBar(title ={},
                navigationIcon = {
                    IconButton(
                        onClick = {onEvent(MemoEvent.NavigateBack)}
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription ="navigate back" )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { onEvent(MemoEvent.DeleteMemo) }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "delete")
                    }
                }
            )
        } 
    ) { pading ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pading)
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            OutlinedTextField(
                value = state.title,
                onValueChange = {
                    onEvent(MemoEvent.TitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                }
            )
            OutlinedTextField(
                value = state.content,
                onValueChange = {
                    onEvent(MemoEvent.ContentChagne(it))
                },
                placeholder = {
                    Text(text = "Content")
                }

            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        onEvent(MemoEvent.Save)
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}