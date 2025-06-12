package com.example.codechallenge.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.codechallenge.CodeChallengeApplication
import com.example.codechallenge.MainViewModel
import com.example.codechallenge.databinding.SignUpFragmentBinding
import com.example.codechallenge.util.EventObserver
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {


    private val mainViewModel: MainViewModel by activityViewModels()

    private val signUpViewModel: SignUpViewModel by viewModels()

    private val startCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val photo = it.data?.extras?.get("data") as Bitmap?
                photo?.let { image ->
                    signUpViewModel.setAvatar(image)
                }
            }
        }

    private val checkCameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted)
                startCamera.launch(Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE))
            else
                view?.let {
                    Snackbar.make(
                        it,
                        "Camera permissions required to Add Avatar",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        CodeChallengeApplication.getAppComponent().signInComponent().create().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = this

        if (activity?.packageManager?.hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT
            ) == false
        ) {
            binding.image.visibility = View.GONE
        }
        setUpObserver()
        return binding.root
    }

    private fun setUpObserver() {
        signUpViewModel.navigateToConfirmation.observe(viewLifecycleOwner) {
            mainViewModel.setUserDetails(userDetails = it)
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToConfirmationFragment())
        }

        signUpViewModel.checkCameraPermission.observe(viewLifecycleOwner,EventObserver{
            if (it)
                checkPermissions()
        })
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera.launch(Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE))
        } else {
            checkCameraPermission.launch(Manifest.permission.CAMERA)
        }
    }
}
