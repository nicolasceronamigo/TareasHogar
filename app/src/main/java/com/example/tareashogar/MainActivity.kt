package com.example.tareashogar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        // CONFIGURAR MARGENES DE PANTALLA
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // CONECTAR TEXTO DE REGISTRO
        val txtPreguntaRegistro =
            findViewById<TextView>(R.id.txtPreguntaRegistro)

        // NAVEGAR HACIA REGISTRO
        txtPreguntaRegistro.setOnClickListener {

            val intent = Intent(
                this,
                RegistroActivity::class.java
            )

            startActivity(intent)
        }
    }
    fun btnIngresarOnClick(view: View)
    {
        val intent = Intent(this, ListaActivity::class.java)
        startActivity(intent)
    }
    fun btnRegistrarOnClick(view: View)
    {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}
