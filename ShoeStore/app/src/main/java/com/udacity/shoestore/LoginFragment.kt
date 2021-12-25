package com.udacity.shoestore

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var email: String
    private lateinit var password: String

    private lateinit var binding: FragmentLoginBinding
    private val sharedViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        (activity)?.setTitle("title")

        binding.btnNewLogin.setOnClickListener { view: View ->
            getUserCredentials()
            if (isValidUser(email, password)) {
                sharedViewModel.changeStatus()
                view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
            hideKeyboard(context!!, view)
        }

        binding.btnExistingUserLogin.setOnClickListener { view: View ->
            if(sharedViewModel.isUserActive.value == true) {
                view.findNavController().navigate(R.id.action_loginFragment_to_instructionFragment)
            } else {
                Toast.makeText(context, "User is not logged in", Toast.LENGTH_SHORT).show()
            }
            hideKeyboard(context!!, view)
        }

        sharedViewModel.list

        return binding.root
    }

    /**
     * Login with this credentials below
     * email = user
     * password = password
     */
    private fun isValidUser(email: String, password: String): Boolean {
        if (!(email.equals("user") && password.equals("password"))) {
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun getUserCredentials() {
        email = binding.etEmail.text.toString()
        password = binding.etPassword.text.toString()
    }

    // Change the Title for the fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title="Login"
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
