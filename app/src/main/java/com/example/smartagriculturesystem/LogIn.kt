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

class LogIn : AppCompatActivity() {

    private lateinit var actionBar: ActionBar
    private lateinit var progressDialog:ProgressDialog
    private lateinit var firebaseAuth:FirebaseAuth
    private var email = ""
    private var password =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        actionBar = supportActionBar!!
        actionBar.title = "Login"

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        //send to signup page
        signupbtn.setOnClickListener {
            startActivity(Intent(this,Signuppage::class.java))
        }
        //begin login
        updateInfo.setOnClickListener{
            // validate before loggin in
                validateData()
        }

    }

    private fun validateData() {
        //getting data
        email = usernameEt.text.toString().trim()
        password = passwordEt.text.toString().trim()

        //validatig data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            //invalid email format
            usernameEt.error = "Invalid email format"
        }
        else if(TextUtils.isEmpty(password))
        {
            passwordEt.error = "Please enter password"
        }
        else {
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener{
                //login success
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                //get user info
                Toast.makeText(this,"LoggedIn as $email", Toast.LENGTH_SHORT).show()


                startActivity(Intent(this, HomeWindow::class.java))
                finish()
            }
            .addOnFailureListener { e->
                //login failed
                progressDialog.dismiss()
                //get user info

                val firebaseUser = firebaseAuth.currentUser
                //val email = firebaseUser!!.email
                Toast.makeText(this, "Login failed due to ${e.message}",Toast.LENGTH_SHORT).show()


            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null)
        {
            //user is already logged in
            startActivity(Intent(this, HomeWindow::class.java))
            finish()
        }
    }
}