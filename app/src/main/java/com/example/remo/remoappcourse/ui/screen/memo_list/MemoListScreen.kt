package com.example.remo.remoappcourse.ui.screen.memo_list
//@file:OptIn(ExperimentalMaterial3Api::class)
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.remo.remoappcourse.domain.model.Memo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoListScereen(
    memoList : List<Memo>,
    onMemoClick:(Memo)->Unit,
    onAddMemoClick:()->Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddMemoClick)
            {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription ="add memo" )
            }
        }
    ) { padding ->
        LazyColumn (
            contentPadding = PaddingValues(
                start =20.dp,
                end = 20.dp,
                top = 15.dp + padding.calculateTopPadding(),
                bottom = 15.dp + padding.calculateBottomPadding()
            )
        ){
            item {
                Text(
                    text = "Memos",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(memoList){ memo->
                ListItem (
                    headlineText = {
                        Text(
                            text = memo.title,
                            style = MaterialTheme.typography.titleMedium)
                                   },
                    supportingText = {
                        Text(
                            text = memo.content,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    modifier = Modifier.clickable (onClick ={
                        onMemoClick(memo)
                    }
                    )
                )
            }
        }

    }
}


