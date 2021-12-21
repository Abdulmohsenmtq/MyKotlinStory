package com.example.mykotlinstory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private var editTextUsername:EditText?= null
    private var editTextPassword:EditText?= null
    private var buttonLogin:Button?= null
    private var checkboxView:CheckBox?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        connectView()
        logon()
//        checkFields()
    }

    private fun connectView(){
        editTextUsername = findViewById(R.id.etUsername)
        editTextPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.btLogin)
        checkboxView = findViewById(R.id.checkbox)

    }
    private fun logon(){
        val arr:ArrayList<User> = ArrayList()
        arr.add(User("mohsen@test.com","1234"))
        arr.add(User("mohsen2@test.com","1234"))
        arr.add(User("mohsen3@test.com","1234"))
        buttonLogin?.setOnClickListener {
            val username = editTextUsername?.text.toString()
            val password = editTextPassword?.text.toString()
            val user = User(username, password)
            for (userArryay in arr) {
                if (userArryay.email == user.email && userArryay.password == user.password) {
//                    Toast.makeText(this, "Welcome ${user.email}", Toast.LENGTH_LONG).show()
                    finish()
                    val i = Intent(this,MainActivity::class.java)
                    i.putExtra("email",userArryay.email)
                    startActivity(i)
                    break
                } else {
                    Toast.makeText(this, "Chick your Entry", Toast.LENGTH_LONG).show()
                }
            }
            if (editTextUsername?.text?.isEmpty() == true){
                editTextUsername?.setError("Enter your email")
            } else if(editTextPassword?.text?.isEmpty() == true){
                editTextPassword?.setError("Enter your password")
            }
        }


    }
    private fun checkFields(){
                            buttonLogin?.setOnClickListener {
                if (editTextUsername?.text?.isEmpty() == true){
                    editTextUsername?.setError("Enter your email")
                } else if(editTextPassword?.text?.isEmpty() == true){
                    editTextPassword?.setError("Enter your password")
                }
            }
    }
}