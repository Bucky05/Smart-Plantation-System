package com.example.smartagriculturesystem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_log_in.usernameEt
import kotlinx.android.synthetic.main.activity_log_in.passwordEt
import kotlinx.android.synthetic.main.activity_signuppage.*

class Signuppage : AppCompatActivity() {

    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog:ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var name =""
    private var username = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signuppage)

        //configure ActionBar
        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayShowHomeEnabled(true)

        //configure progress display
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account in...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        //get data
        email = emailEt.text.toString().trim()
        password = passwordEt.text.toString().trim()
//        name = nameEt.text.toString().trim()
//        username = usernameEt.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailEt.error = "Invalid email format"

        }
        else if (TextUtils.isEmpty(password))
        {

            passwordEt.error = "Please enter password"
        }
        else if (password.length < 6)
        {
            passwordEt.error = "Password must atleast 6 characters long"
        }
        else
        {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        progressDialog.show()

        //create account

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener{
                //signup success
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Account creathed with email $email",Toast.LENGTH_SHORT).show()

                //open home page
                startActivity(Intent(this,HomeWindow::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                //signup failed
                progressDialog.dismiss()
                Toast.makeText(this,"SignUp Failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }
}