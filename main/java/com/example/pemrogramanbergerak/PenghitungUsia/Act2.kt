package com.example.pemrogramanbergerak.PenghitungUsia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.pemrogramanbergerak.R

class act2 : AppCompatActivity() {

    private lateinit var tampilkanumur: TextView
    private lateinit var isinama: TextView
    private lateinit var isitanggal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act2)

        isinama = findViewById(R.id.isinama)
        isitanggal = findViewById(R.id.isitanggal)
        tampilkanumur = findViewById(R.id.tampilkanumur)

        // Ambil hasil umur dari intent
        val ageResult = intent.getStringExtra("ageResult")
        tampilkanumur.text = ageResult
        val name = intent.getStringExtra("name")
        isinama.text = name
        val tgl = intent.getStringExtra("tgl")
        isitanggal.text = tgl

    }
}