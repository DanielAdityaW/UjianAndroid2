package com.bcafbootcamp.ujianandroid2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bcafbootcamp.ujianandroid2.Model.ModelItem
import com.bcafbootcamp.ujianandroid2.ViewModel.ViewModelItem

class UpdateActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelItem
    lateinit var updateNama: EditText
    lateinit var updateOst: EditText
    lateinit var updateAlamat: EditText
    lateinit var buttonUpdate: Button
    private var itemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)

        viewModel = ViewModelProvider(this).get(ViewModelItem::class.java)

        updateNama = findViewById(R.id.formNamaUpdate)
        updateOst = findViewById(R.id.formOutstandingUpdate)
        updateAlamat = findViewById(R.id.formAlamatUpdate)
        buttonUpdate = findViewById(R.id.buttonInputUpdate)


        itemId = intent.getIntExtra("id", 0)
        val itemNama = intent.getStringExtra("nama")
        val itemOutstanding = intent.getStringExtra("outstanding")
        val itemAlamat = intent.getStringExtra("alamat")

        updateNama.setText(itemNama)
        updateOst.setText(itemOutstanding)
        updateAlamat.setText(itemAlamat)

        buttonUpdate.setOnClickListener {
            val nama = updateNama.text.toString()
            val ost = updateOst.text.toString()
            val alamat = updateAlamat.text.toString()

            val item = ModelItem(id = itemId, nama = nama, outstanding = ost, alamat = alamat)
            viewModel.update(item)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
