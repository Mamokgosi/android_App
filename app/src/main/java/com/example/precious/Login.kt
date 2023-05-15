package com.example.precious

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private lateinit var etUsername: EditText
private lateinit var etPassword: EditText

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.findViewById<TextView>(R.id.tvRegisterLink).setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)

        val etButton = findViewById<Button>(R.id.btnLogin)

        etButton.setOnClickListener {
            login(etUsername, etPassword)
        }

    }

    fun login(Name: EditText, Pass: EditText) {
        val userName: String = Name.getText().toString().trim()
        val password: String = Pass.getText().toString().trim()

        val call: Call<ResponseBody> = RetrofitClient
            .getInstance()
            .api
            .checkUser(User(userName, password))
        if (userName.isEmpty()) {
            Name.error = "Username is required"
            Name.requestFocus()
            return
        } else if (password.isEmpty()) {
            Pass.error = "Password is required"
            Pass.requestFocus()
            return
        }
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s == userName) {
                    val intent = Intent(this@Login,Dashboard::class.java)
                    intent.putExtra("UserName",s)

                    Toast.makeText(
                        this@Login,
                        "Successfully login",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "User does not exists!", Toast.LENGTH_LONG)
                        .show()

                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@Login, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}





