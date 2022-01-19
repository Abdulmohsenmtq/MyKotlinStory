package com.example.mykotlinstory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText


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

    // connecting views from activity_login layout to LoginActivity
    private fun connectView(){
        editTextUsername = findViewById(R.id.etUsername)
        editTextPassword = findViewById(R.id.etPassword)
        buttonLogin = findViewById(R.id.btLogin)
        checkboxView = findViewById(R.id.checkbox)

    }
    private fun logon(){
        // adding user
        val arr:ArrayList<User> = ArrayList()
        arr.add(User("mohsen@test.com","1234"))
        arr.add(User("mohsen2@test.com","1234"))
        arr.add(User("mohsen3@test.com","1234"))

        //set actions for login buttons
        buttonLogin?.setOnClickListener {
            val username = editTextUsername?.text.toString()
            val password = editTextPassword?.text.toString()
            val user = User(username, password)
           // this for lop is checking user and password
            for (userArryay in arr) {
                if (userArryay.email == user.email && userArryay.password == user.password) {
//                    Toast.makeText(this, "Welcome ${user.email}", Toast.LENGTH_LONG).show()
                    finish()
                    val i = Intent(this,MainActivity::class.java)
                    i.putExtra("email",userArryay.email)
                    startActivity(i)
                    break
                }
//                else
//                {
//                      Toast.makeText(this, "Chick your Entry", Toast.LENGTH_LONG).show()
//                }
            }
            // This condition will make error msg if there no entry from user
            if (editTextUsername?.text?.isEmpty() == true){
                editTextUsername?.error = "Enter your email"
            } else if(editTextPassword?.text?.isEmpty() == true){
                editTextPassword?.error = "Enter your password"
            }
        }


    }
//    private fun checkFields(){
//                            buttonLogin?.setOnClickListener {
//                if (editTextUsername?.text?.isEmpty() == true){
//                    editTextUsername?.setError("Enter your email")
//                } else if(editTextPassword?.text?.isEmpty() == true){
//                    editTextPassword?.setError("Enter your password")
//                }
//            }
//    }
}