package com.example.smartagriculturesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home_window.*
import kotlinx.android.synthetic.main.activity_signuppage.*
import kotlinx.android.synthetic.main.activity_signuppage.emailEt
import kotlinx.android.synthetic.main.plant_entry_dialog_box.view.*

class HomeWindow : AppCompatActivity() {
    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_window)

        val plants: ArrayList<String> = ArrayList()

        for (i in 1..7)
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
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.plant_entry_dialog_box,null)

            val mBuilder = AlertDialog.Builder(this).setView(mDialogView).setTitle("Register New Plant")

            val mAlertDialog = mBuilder.show()

            mDialogView.addPlantBtn.setOnClickListener{
                 val name = mDialogView.plant_nameET.text.toString()

                plants.add(name)
                Toast.makeText(this,"New Plant Added",Toast.LENGTH_SHORT).show()
                mAlertDialog.dismiss()
            }

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