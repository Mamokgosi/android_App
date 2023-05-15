package com.example.precious

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.TextView
import com.example.precious.R.*

private lateinit var welcomeText: String
private lateinit var tvWelcome: TextView
private lateinit var userName: TextView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_dashboard)

        //Get text from Intent
        welcomeText ="Welcome "+ getIntent().getStringExtra("UserName").toString() + "!";
        tvWelcome = this.findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);
    }

}