package com.example.mixingstat.config

import com.example.mixingstat.presentation.viewmodel.home.HomeScreenViewModel
import com.example.mixingstat.screen.FakeHomeScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TestViewModelModule {
    @Binds
    abstract fun bindHomeScreenViewModel(fakeHomeScreenViewModel: FakeHomeScreenViewModel): HomeScreenViewModel
}