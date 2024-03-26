package com.example.remo

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true,
    showSystemUi = true,
    device = "spec:shape=Normal,width=400,height=600,unit=dp,dpi=420"
)
fun MemoApp(memoViewModel: MemoViewModel = viewModel(),navController:NavController?= null) {
    val memos by memoViewModel.memos.collectAsState() //새로운 상태를 memos에 위임해서 최신상태 반영하도록
    var text by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var showMessage = remember { SnackbarHostState()}

    Scaffold(
        snackbarHost = {SnackbarHost(showMessage) }
    ){paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
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
            if (text.isBlank() || content.isBlank()){
                memoViewModel.viewModelScope.launch{
                    showMessage.showSnackbar("제목과 내용을 입력해주세요") }
            }else{
            memoViewModel.addMemo(text, content)
            text = ""
            content = "" }
        }) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(memos) { memo ->
                MemoItem(memo= memo , onClick = {selectedMemo ->
                navController?.navigate("memoDetail/${selectedMemo.id}")
            })
        }
    }
}
    }
    }
@Composable
fun MemoItem(memo: Memo,onClick:(Memo)->Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick(memo) }
            .fillMaxWidth()
            .padding(4.dp))

    {
        Column(modifier = Modifier.padding(8.dp)){
        Text(text = memo.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = memo.content)
        }
    }
}

@Composable
fun MemoDetail(memoViewModel:MemoViewModel = viewModel(),
                memoId : Int,
                navController: NavController){
    val memo = memoViewModel.memos.collectAsState().value.find { it.id == memoId }
    if(memo==null){
        Log.d("MemoDetail","$memoId")
    }else {
        Log.d("MemoDetail", "Memo ID: $memoId, Memo: ${memo?.title}, ${memo?.content}")
    }
    var title by rememberSaveable {
        mutableStateOf(memo?.title?:"")
    }
    var content by rememberSaveable {
        mutableStateOf(memo?.content?:"")
    }


    Column(modifier=Modifier.padding(16.dp)){
        TextField(value = title , onValueChange = { title = it},
            label = { Text("제목")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = content, onValueChange ={content= it},
            label = { Text("내용")},
            modifier = Modifier.height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            memoViewModel.updateMemo(memoId,title,content)
            navController.popBackStack()
        }){
            Text("저장")
        }
    }
}
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "memoList") {
        composable(route = "memoList") { MemoApp(navController = navController) }
        composable(
            route = "memoDetail/{memoId}",
            arguments = listOf(navArgument("memoId") { type = NavType.IntType })
        ) { backStackEntry ->
            MemoDetail(
                memoId = backStackEntry.arguments?.getInt("memoId") ?: 0,
                navController = navController
            )
        }
    }
}

