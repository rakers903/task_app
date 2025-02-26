package com.example.tasks.di

import com.example.tasks.data.login.LoginRepository
import com.example.tasks.data.login.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//    @Binds
//    abstract fun bindsLoginRepository(
//        loginRepositoryImpl: LoginRepositoryImpl
//    ): LoginRepository
//}