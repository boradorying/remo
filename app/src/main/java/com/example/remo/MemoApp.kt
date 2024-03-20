package com.example.remo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MemoApp(memoViewModel: MemoViewModel = viewModel()) {
    val memos = memoViewModel.getMemos()
    var text by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Memo Title") }
        )
        TextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Memo Content") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            memoViewModel.addMemo(text, content)
            text = "" // Reset title field
            content = "" // Reset content field
        }) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(memos) { memo ->
                Text("${memo.title}: ${memo.content}")
            }
        }
    }
}