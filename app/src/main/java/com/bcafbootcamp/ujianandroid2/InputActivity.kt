package com.bcafbootcamp.ujianandroid2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bcafbootcamp.ujianandroid2.Model.ModelItem
import com.bcafbootcamp.ujianandroid2.ViewModel.ViewModelItem

class InputActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelItem
    lateinit var inputNama : EditText
    lateinit var inputOst : EditText
    lateinit var inputAlamat : EditText
    lateinit var buttonInput : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_input)

        viewModel = ViewModelProvider(this).get(ViewModelItem::class.java)

        inputNama = findViewById(R.id.formNama)
        inputOst = findViewById(R.id.formOutstanding)
        inputAlamat = findViewById(R.id.formAlamat)
        buttonInput = findViewById(R.id.buttonInput)

        buttonInput.setOnClickListener{
            val nama = inputNama.text.toString()
            val ost = inputOst.text.toString()
            val alamat = inputAlamat.text.toString()

            if (nama.isEmpty() || ost.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                val item = ModelItem(nama = nama, outstanding = ost, alamat = alamat)
                viewModel.insert(item)
                finish()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}