package com.example.tasks.data.room

import com.example.tasks.utils.UiState
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getAllTasks(): Flow<UiState<List<Task>>>

    fun insertTask(task: Task): Flow<UiState<Unit>>

    fun deleteTask(task: Task): Flow<UiState<Unit>>

}