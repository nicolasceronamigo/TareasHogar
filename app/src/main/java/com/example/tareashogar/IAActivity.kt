package com.example.tareashogar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IAActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_ia)

        // CONFIGURAR MARGENES DE PANTALLA
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.pantallaIA)
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

        // CONECTAR COMPONENTES
        val edtPreguntaIA =
            findViewById<EditText>(R.id.edtPreguntaIA)

        val btnPreguntarIA =
            findViewById<Button>(R.id.btnPreguntarIA)

        val txtRespuestaIA =
            findViewById<TextView>(R.id.txtRespuestaIA)

        // BOTON PREGUNTAR
        btnPreguntarIA.setOnClickListener {

            val pregunta = edtPreguntaIA.text.toString().trim()

            // VALIDAR PREGUNTA
            if (pregunta.isEmpty()) {

                edtPreguntaIA.error = "Escribe tu pregunta"
                edtPreguntaIA.requestFocus()

                return@setOnClickListener
            }

            // MOSTRAR RESPUESTA DE EJEMPLO
            txtRespuestaIA.text =
                getString(R.string.ia_respuesta_ejemplo)
        }
    }
}
