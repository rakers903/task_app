package com.example.tasks.di

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.GetCredentialRequest
import androidx.room.Room
import com.example.tasks.OAUTH_ID
import com.example.tasks.data.login.LoginRepository
import com.example.tasks.data.login.LoginRepositoryImpl
import com.example.tasks.data.room.TasksDao
import com.example.tasks.data.room.TasksDatabase
import com.example.tasks.data.room.TasksRepository
import com.example.tasks.data.room.TasksRepositoryImpl
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesGoogleId(): GetGoogleIdOption =
        GetGoogleIdOption.Builder()
            .setServerClientId(OAUTH_ID)
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .build()

    @Provides
    @Singleton
    fun providesCredentialRequest(
        googleIdOption: GetGoogleIdOption
    ): GetCredentialRequest = GetCredentialRequest
        .Builder()
        .addCredentialOption(googleIdOption)
        .build()

    @Provides
    @Singleton
    fun providesClearCredentialsState(): ClearCredentialStateRequest =
        ClearCredentialStateRequest()

    @Provides
    @Singleton
    fun providesAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun providesLogin(
        auth: FirebaseAuth,
        credentialRequest: GetCredentialRequest,
        clearCredentialStateRequest: ClearCredentialStateRequest
    ) : LoginRepository = LoginRepositoryImpl(auth, credentialRequest, clearCredentialStateRequest)

    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun providesTaskDatabase(
        @ApplicationContext context: Context
    ): TasksDatabase =
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            "tasks-db"
        ).build()

    @Provides
    @Singleton
    fun providesDao(
        tasksDatabase: TasksDatabase
    ): TasksDao = tasksDatabase.getTasksDao()

    @Provides
    @Singleton
    fun providesTasksRepository(
        dao: TasksDao
    ): TasksRepository = TasksRepositoryImpl(dao)

}