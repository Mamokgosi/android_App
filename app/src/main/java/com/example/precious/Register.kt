package com.example.precious

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent;
import android.view.View;
import android.widget.Button
import android.widget.EditText;
import android.widget.TextView
import android.widget.Toast
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
private lateinit var etUsername: EditText
private lateinit var etPassword: EditText
private lateinit var etButton: Button

class Register : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)

        etButton = findViewById<Button>(R.id.btnRegister)

        this.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        etButton.setOnClickListener {
            registerUser()
         //val Name =etUsername.getText().toString();
            //Toast.makeText(this, " registered successfully", Toast.LENGTH_SHORT).show();
        }


    }

    private fun registerUser()
    {
   val userName: String = etUsername.getText().toString().trim()
   val password: String = etPassword.getText().toString().trim()

    if (userName.isEmpty()) {
        etUsername.error ="Username is required"
        etUsername.requestFocus()
        return         }
    else if (password.isEmpty()) {
        etPassword.error = "Password is required"
        etPassword.requestFocus()
        return
    }
        val call: Call<ResponseBody> = RetrofitClient
            .getInstance()
            .api
            .createUser(User(userName, password))
         call.enqueue(object : Callback<ResponseBody>
         {

             override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                 var s = ""

                 try {
                     s = response.body()!!.string()

                 } catch (e: IOException) {
                     e.printStackTrace()

                 }
                 if (s == "SUCCESS") {
                     Toast.makeText(
                         this@Register,
                         "Successfully registered. Please login",
                         Toast.LENGTH_LONG
                     ).show()
                     startActivity(Intent(this@Register, Login::class.java))
                 } else {
                     Toast.makeText(this@Register, "User already exists!", Toast.LENGTH_LONG)
                         .show()
                 }
             }

             override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                 Toast.makeText(this@Register, t.message, Toast.LENGTH_LONG).show()
             }
         })


    }

}
