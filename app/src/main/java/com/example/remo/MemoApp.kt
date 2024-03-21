package com.example.remo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview(showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=400,height=600,unit=dp,dpi=420"
)
fun MemoApp(memoViewModel: MemoViewModel = viewModel()) {
    val memos by memoViewModel.memos.collectAsState() //새로운 상태를 memos에 위임해서 최신상태 반영하도록
    var text by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Title") }
        )
        TextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Content") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            memoViewModel.addMemo(text, content)
            text = ""
            content = ""
        }) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(memos) { memo ->
                MemoItem(memo)
            }
        }
    }
}
@Composable
fun MemoItem(memo: Memo) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)){
        Column(modifier = Modifier.padding(8.dp)){
        Text(text = memo.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = memo.content)
        }
    }
}
