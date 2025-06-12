package com.example.codechallenge.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.codechallenge.CodeChallengeApplication
import com.example.codechallenge.MainViewModel
import com.example.codechallenge.databinding.ConfirmationFragmentBinding

class ConfirmationFragment : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        CodeChallengeApplication.getAppComponent().signInComponent().create().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding = ConfirmationFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = mainViewModel
        return binding.root
    }

}
