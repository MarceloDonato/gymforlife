package com.example.gymforlife.presentation.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.gymforlife.R
import com.example.gymforlife.mechanism.extension.get
import com.example.gymforlife.mechanism.extension.toast
import com.example.gymforlife.presentation.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupListeners()
    }

    private fun setupListeners() {
        button_login_enter.setOnClickListener { validateFields() }
        button_start_register.setOnClickListener { startRegister() }
    }

    private fun startRegister() {
        startActivity(
            RegisterActivity.getStartIntent(
                this
            )
        )
        finish()
    }

    private fun validateFields() {
        val isValidateEmail = edit_text_email.get().isEmpty()
        val isValidatePassword = edit_text_password.get().isEmpty()

        when {
            isValidateEmail -> edit_text_email.error = getString(R.string.login_email_invalid)
            isValidatePassword -> edit_text_password.error =
                getString(R.string.login_password_invalid)
            else -> loginFirebase(edit_text_email.get(), edit_text_password.get())
        }
    }

    private fun loginFirebase(email: String, password: String) {
        loader.visibility = VISIBLE
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    loader.visibility = GONE
                    showSuccessLogin()
                } else {
                    loader.visibility = GONE
                    showErrorLogin()
                }
            }
            .addOnFailureListener {
                loader.visibility = GONE
                showErrorLogin()
            }
    }

    private fun showSuccessLogin() {
        toast(getString(R.string.login_message_success))
    }

    private fun showErrorLogin() {
        toast(getString(R.string.login_message_error))
    }

}