package com.example.remo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colors.background) {
                    //네이베이션 생성 
                    val navController = rememberNavController()
                    AppNavGraph(navController = navController)
                    // ViewModel 인스턴스를 생성
//                    val memoViewModel: MemoViewModel = viewModel()
//                    // MemoApp 컴포저블 함수를 호출
//                    MemoApp(memoViewModel = memoViewModel)
                }
            }
        }
    }
}