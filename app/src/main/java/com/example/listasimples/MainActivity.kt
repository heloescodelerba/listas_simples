package com.example.listasimples

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etNovaTarefa = findViewById<EditText>(R.id.etnovatarefa)
        val btAdd = findViewById<Button>(R.id.btadd)
        val tvTitulo = findViewById<TextView>(R.id.tvtitulo)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            tvTitulo.isVisible = false
            etNovaTarefa.isVisible = true
            etNovaTarefa.isEnabled = true
            btAdd.isVisible = true
        }

        val lvTarefas = findViewById<ListView>(R.id.lvtarefas)
        val listaTarefas: ArrayList<String> = ArrayList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas)
        lvTarefas.adapter = adapter

        btAdd.setOnClickListener {
            if (etNovaTarefa.text.isNullOrEmpty()) {
                Toast.makeText(this, "Digite uma tarefa...", Toast.LENGTH_SHORT).show()
            } else {
                listaTarefas.add(etNovaTarefa.text.toString())
                adapter.notifyDataSetChanged()
                etNovaTarefa.setText("")
                etNovaTarefa.isVisible = false
                etNovaTarefa.isEnabled = false
                btAdd.isVisible = false
                tvTitulo.isVisible = true
            }
        }

        lvTarefas.setOnItemLongClickListener { _, _, position, _ ->
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atenção")
            alerta.setMessage("Quer mesmo excluir esse item?")
            alerta.setPositiveButton("Confirmar") { dialog, _ ->
                listaTarefas.removeAt(position)
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            alerta.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            alerta.create().show()
            true
        }
    }
}
