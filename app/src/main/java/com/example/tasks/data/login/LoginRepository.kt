package com.example.tasks.data.login

import android.content.Context
import android.security.identity.CredentialDataRequest
import androidx.credentials.Credential
import com.example.tasks.utils.UiState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun handleLogin(context: Context) : Flow<UiState<FirebaseUser>>

    suspend fun handleLogout(): Flow<UiState<Unit>>

}