package com.example.smartagriculturesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class flashwindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logintxt.setOnClickListener {
            startActivity(Intent(this,LogIn::class.java))



        }
        signupimg.setOnClickListener{
            startActivity(Intent(this,Signuppage::class.java))

        }
        arrowsignup.setOnClickListener{
            startActivity(Intent(this,Signuppage::class.java))
        }
    }


}