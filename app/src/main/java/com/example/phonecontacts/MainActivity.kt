package com.example.phonecontacts

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.phonecontacts.databinding.ActivityMainBinding
import com.example.phonecontacts.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    @SuppressLint("Recycle", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}