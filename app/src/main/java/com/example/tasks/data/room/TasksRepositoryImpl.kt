package com.example.tasks.data.room

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.tasks.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val dao: TasksDao
) : TasksRepository{

    override fun getAllTasks(): Flow<UiState<List<Task>>> = flow{
        emit(UiState.LOADING)
        try {
            val result = dao.getTasks()
            emit(UiState.SUCCESS(result.toTask()))
        } catch (e: Exception){
            emit(UiState.ERROR(e))
        }
    }

    override fun insertTask(task: Task): Flow<UiState<Unit>> = flow{
        emit(UiState.LOADING)
        try {
            dao.insertTask(task.toTable())
            emit(UiState.SUCCESS(Unit))
        } catch (e: Exception){
            emit(UiState.ERROR(e))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun deleteTask(task: Task): Flow<UiState<Unit>> = flow{
        emit(UiState.LOADING)
        try {
            dao.deleteTask(task.toTable())
            emit(UiState.SUCCESS(Unit))
        } catch (e: Exception){
            emit(UiState.ERROR(e))
        }
    }

}