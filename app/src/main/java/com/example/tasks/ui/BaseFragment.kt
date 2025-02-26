package com.example.tasks.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tasks.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint

open class BaseFragment : Fragment() {

    protected val viewModel by viewModels<TasksViewModel>()

}