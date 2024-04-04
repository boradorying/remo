package com.example.remo.remoappcourse.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remo.remoappcourse.ui.screen.memo.MemoScreen
import com.example.remo.remoappcourse.ui.screen.memo.MemoViewModel
import com.example.remo.remoappcourse.ui.screen.memo_list.MemoListScereen
import com.example.remo.remoappcourse.ui.screen.memo_list.MemoListViewModel
import com.example.remo.remoappcourse.ui.theme.MemoAppCourseTheme
import com.example.remo.remoappcourse.ui.util.Route
import com.example.remo.remoappcourse.ui.util.UiEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoAppCourseTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "") {
                    composable(route = Route.memoList) {
                        val viewModel = hiltViewModel<MemoListViewModel>()
                        val memoList by viewModel.memoList.collectAsStateWithLifecycle()

                        MemoListScereen(
                            memoList = memoList,
                            onMemoClick = {
                                navController.navigate(
                                    Route.memo.replace(
                                        "{id}",it.id.toString()
                                    )
                                )
                            },
                            onAddMemoClick = {
                                navController.navigate(Route.memo)
                            }
                        )
                    }
                    composable(route = Route.memo){
                        val viewModel = hiltViewModel<MemoViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()
                        
                        LaunchedEffect(key1 = true) {
                            viewModel.event.collect{event->
                                when(event){
                                    is UiEvent.NavigateBack ->{
                                        navController.popBackStack()
                                    }
                                else -> Unit
                            }
                        }
                        }
                        MemoScreen(state = state, onEvent = viewModel::onEvent )
                    }
                }
            }
        }
    }
}
