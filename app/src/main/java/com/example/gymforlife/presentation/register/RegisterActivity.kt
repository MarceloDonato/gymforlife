package com.example.gymforlife.presentation.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gymforlife.R
import com.example.gymforlife.presentation.home.HomeActivity
import com.example.gymforlife.presentation.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button.setOnClickListener {
            performeRegister()
        }

        already_has_a_count.setOnClickListener {
            startActivity(LoginActivity.getStartIntent(this))
            finish()
        }
    }

    private fun performeRegister() {
        val email = edit_text_email_cadastro.text.toString()
        val password = edit_text_password_cadastro.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "please enter text in email/password", Toast.LENGTH_SHORT).show()
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            edit_text_email_cadastro.text.toString(),
            edit_text_password_cadastro.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                    startActivity(HomeActivity.getStartIntent(this))
                    finish()

                } else {
                    Toast.makeText(this, "Failed to created user with uid:", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to created user with uid:", Toast.LENGTH_SHORT).show()
            }

    }
}
