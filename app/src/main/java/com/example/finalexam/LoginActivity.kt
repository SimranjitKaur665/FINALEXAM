package com.example.finalexam

class LoginActivity {
}
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalandroid.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        // Check if the user is already signed in
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            redirectToMainPage()
        }

        // Handle sign-in button click
        sign_in_button.setOnClickListener {
            val email = email_edit_text.text.toString()
            val password = password_edit_text.text.toString()
            signIn(email, password)
        }

        // Handle sign-up button click
        sign_up_button.setOnClickListener {
            val email = email_edit_text.text.toString()
            val password = password_edit_text.text.toString()
            signUp(email, password)
        }
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, redirect to main page
                    redirectToMainPage()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success, redirect to main page
                    redirectToMainPage()
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun redirectToMainPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}