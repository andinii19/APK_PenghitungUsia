package com.example.pemrogramanbergerak.PenghitungUsia

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.example.pemrogramanbergerak.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class act1 : AppCompatActivity() {

    private lateinit var pilihtanggal: Button
    private lateinit var nama: EditText
    private lateinit var hitungumur: Button

    private val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1)

        pilihtanggal = findViewById(R.id.pilihtanggal)
        nama = findViewById(R.id.nama)
        hitungumur = findViewById(R.id.hitungumur)

        pilihtanggal.setOnClickListener {
            showDatePickerDialog()
        }

        hitungumur.setOnClickListener {
            calculateAgeAndNavigate()
        }
    }
    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                updateDateInView()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
    private fun updateDateInView() {
        val formattanggal = "dd/MM/yyyy" // format tanggal yang diinginkan
        val sdf = SimpleDateFormat(formattanggal, Locale.getDefault())
        pilihtanggal.setText(sdf.format(calendar.time))
    }
    private fun calculateAgeAndNavigate() {
        val tglskrg = Calendar.getInstance()
        val tglygdipilih = calendar

        var tahun = tglskrg.get(Calendar.YEAR) - tglygdipilih.get(Calendar.YEAR)
        var bulan = tglskrg.get(Calendar.MONTH) - tglygdipilih.get(Calendar.MONTH)
        var hari = tglskrg.get(Calendar.DAY_OF_MONTH) - tglygdipilih.get(Calendar.DAY_OF_MONTH)

        if (hari < 0) {
            val lastMonthMaxDays = tglskrg.getActualMaximum(Calendar.DAY_OF_MONTH)
            hari += lastMonthMaxDays
            bulan--
        }

        // Adjust the result if months are negative
        if (bulan < 0) {
            bulan += 12
            tahun--
        }

        val hasilumur = "$tahun tahun, $bulan bulan, $hari hari"
        val name = nama.text.toString()
        val tgl = pilihtanggal.text.toString()

        // Pindah ke Activity baru untuk menampilkan hasil umur
        val intent = Intent(this, act2::class.java)
        intent.putExtra("ageResult", hasilumur)
        intent.putExtra("name", name)
        intent.putExtra("tgl", tgl)
        startActivity(intent)
    }
}