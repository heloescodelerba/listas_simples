package com.example.listasimples

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etnovatarefa = findViewById<EditText>(R.id.etnovatarefa)
        val btadd = findViewById<Button>(R.id.btadd)
        val lvtarefas = findViewById<ListView>(R.id.lvtarefas)

        //aqui criamos a lista de Strings, inicialmente vazia
        val listaTarefas: ArrayList<String> = ArrayList()

        //para trabalhar com listas, precisamos de um adapter
        //um componente adicional do android para layout de listas
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas)

        //aqui o adapter do listview recebe o adapter que criamos
        lvtarefas.adapter = adapter

        btadd.setOnClickListener {
            if (etnovatarefa.text.isNullOrEmpty()) {
                Toast.makeText(this, "Digite uma tarefa...", Toast.LENGTH_SHORT).show()
            } else {
                listaTarefas.add(etnovatarefa.text.toString())
                //notificamos ao adaptar que tivemos alteração da lista
                //notificado, ele atualiza os novos elementos da lista na tela
                adapter.notifyDataSetChanged()
                etnovatarefa.setText("")
            }
        }
        lvtarefas.setOnItemClickListener { _, _, position, _ ->
            //aqui montamos a caixa de diálogo
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atenção!")
            alerta.setMessage("Quer Mesmo Excluir Esse Item?")
            alerta.setPositiveButton("Confirmar") { dialog, _ ->

                //caso o botão confirmar seja clicado, remover o item da lista
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

