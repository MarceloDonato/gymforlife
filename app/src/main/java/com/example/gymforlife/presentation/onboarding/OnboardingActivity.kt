package com.example.gymforlife.presentation.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gymforlife.R
import com.example.gymforlife.presentation.login.LoginActivity
import com.example.gymforlife.presentation.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        button_login.setOnClickListener{
            startActivity(LoginActivity.getStartIntent(this))
            finish()
        }

        button_cadastro.setOnClickListener{
            startActivity(RegisterActivity.getStartIntent(this))
            finish()
        }
    }
}