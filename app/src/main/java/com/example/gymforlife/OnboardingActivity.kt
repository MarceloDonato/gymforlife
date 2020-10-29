package com.example.gymforlife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            startActivity(MainActivity.getStartIntent(this))
            finish()
        }
    }
}