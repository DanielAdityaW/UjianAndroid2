package com.bcafbootcamp.ujianandroid2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcafbootcamp.ujianandroid2.Adapter.AdapterItem
import com.bcafbootcamp.ujianandroid2.Model.ModelItem
import com.bcafbootcamp.ujianandroid2.ViewModel.ViewModelItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelItem
    lateinit var ListItem : RecyclerView
    lateinit var addFB : FloatingActionButton
    lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addFB = findViewById(R.id.floatAddButton)

        addFB.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        val searchItem = findViewById<EditText>(R.id.searchBar)
        searchButton = findViewById(R.id.buttonSearch)

        ListItem = findViewById(R.id.listItem)
        ListItem.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(ViewModelItem::class.java)
        viewModel.allItem.observe(this) { itemList ->
            val adapter = AdapterItem(itemList, { selectedItem ->
                val intent = Intent(this, UpdateActivity::class.java)
                intent.putExtra("id", selectedItem.id)
                intent.putExtra("nama", selectedItem.nama)
                intent.putExtra("outstanding", selectedItem.outstanding)
                intent.putExtra("alamat", selectedItem.alamat)
                startActivity(intent)
            }, { selectedItem -> showDialog(selectedItem)})

            ListItem.adapter = adapter

            searchItem.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val searchText = s.toString()
                    searchButton.setOnClickListener {
                        val searchText = searchItem.text.toString()
                        adapter.filterList(itemList.filter { item ->
                            item.nama.contains(searchText, ignoreCase = true)
                        })
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }

    }

    fun showDialog(item: ModelItem){
        val dialogShow = LayoutInflater.from(this).inflate(R.layout.delete_dialogue, null)
        val dialog = AlertDialog.Builder(this).setView(dialogShow)
            .setNeutralButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss()}
            .setNegativeButton("Delete") { dialogInterface, _ -> viewModel.delete(item.id)
                Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()}
            .create()

        dialog.setOnShowListener {
            val neutralButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
            val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            neutralButton.setTextColor(Color.BLUE)
            negativeButton.setTextColor(Color.RED)
        }

        dialog.show()
    }
}