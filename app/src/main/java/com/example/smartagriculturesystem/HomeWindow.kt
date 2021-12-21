package com.example.smartagriculturesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_window.*
import kotlinx.android.synthetic.main.activity_signuppage.*
import kotlinx.android.synthetic.main.activity_signuppage.emailEt

class HomeWindow : AppCompatActivity() {
    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_window)

        val plants: ArrayList<String> = ArrayList()

        for (i in 1..3)
        {
            plants.add("Tomato")
            plants.add("Chilly")
            plants.add("Cucumber")
        }

        recyclerView.layoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter(plants)
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //logout function
        logoutbtn.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }
        add_btn.setOnClickListener{
            plants.add
        }



    }

    private fun checkUser() {
        // check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null)
        {
            // user not null, user is logged in
            val email = firebaseUser.email.toString()
            emailTv.text = email
        }
        else
        {
            startActivity(Intent(this,LogIn::class.java))
            finish()
        }
    }
}