package com.example.tareashogar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AgregarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_agregar)

        // CONFIGURAR MÁRGENES DE PANTALLA
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.pantallaAgregar)
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
        val spnZona = findViewById<Spinner>(R.id.spnZona)
        val edtNuevaZona = findViewById<EditText>(R.id.edtNuevaZona)

        val spnCategoria = findViewById<Spinner>(R.id.spnCategoria)

        val spnTarea = findViewById<Spinner>(R.id.spnTarea)
        val edtTareaPersonalizada = findViewById<EditText>(R.id.edtTareaPersonalizada)

        val edtFrecuenciaCantidad = findViewById<EditText>(R.id.edtFrecuenciaCantidad)
        val spnUnidadFrecuencia = findViewById<Spinner>(R.id.spnUnidadFrecuencia)

        val spnResponsable = findViewById<Spinner>(R.id.spnResponsable)
        val swRecordatorio = findViewById<Switch>(R.id.swRecordatorio)

        val rgPrioridad = findViewById<RadioGroup>(R.id.rgPrioridad)

        val btnGuardarTarea = findViewById<Button>(R.id.btnGuardarTarea)
        val txtCancelarAgregar = findViewById<TextView>(R.id.txtCancelarAgregar)

        // BOTÓN GUARDAR
        btnGuardarTarea.setOnClickListener {
            val zonaSeleccionada = spnZona.selectedItem.toString()
            val nuevaZona = edtNuevaZona.text.toString().trim()

            val categoriaSeleccionada = spnCategoria.selectedItem.toString()

            val tareaSeleccionada = spnTarea.selectedItem.toString()
            val tareaPersonalizada = edtTareaPersonalizada.text.toString().trim()

            val frecuenciaCantidad = edtFrecuenciaCantidad.text.toString().trim()
            val unidadFrecuencia = spnUnidadFrecuencia.selectedItem.toString()

            val responsableSeleccionado = spnResponsable.selectedItem.toString()
            val recordatorioActivo = swRecordatorio.isChecked

            // VALIDAR ZONA
            if (zonaSeleccionada == "Selecciona una zona") {
                Toast.makeText(
                    this,
                    "Selecciona una zona",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (zonaSeleccionada == "Otro espacio" && nuevaZona.isEmpty()) {
                edtNuevaZona.error = "Escribe una nueva zona"
                edtNuevaZona.requestFocus()
                return@setOnClickListener
            }

            // VALIDAR CATEGORÍA
            if (categoriaSeleccionada == "Selecciona una categoría") {
                Toast.makeText(
                    this,
                    "Selecciona una categoría",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // VALIDAR TAREA
            if (tareaSeleccionada == "Selecciona una tarea" && tareaPersonalizada.isEmpty()) {
                edtTareaPersonalizada.error = "Selecciona o escribe una tarea"
                edtTareaPersonalizada.requestFocus()
                return@setOnClickListener
            }

            // VALIDAR FRECUENCIA
            if (frecuenciaCantidad.isEmpty()) {
                edtFrecuenciaCantidad.error = "Ingresa una frecuencia"
                edtFrecuenciaCantidad.requestFocus()
                return@setOnClickListener
            }

            val cantidad = frecuenciaCantidad.toIntOrNull()
            if (cantidad == null || cantidad <= 0) {
                edtFrecuenciaCantidad.error = "Ingresa un número válido"
                edtFrecuenciaCantidad.requestFocus()
                return@setOnClickListener
            }

            // VALIDAR PRIORIDAD
            if (rgPrioridad.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Selecciona una prioridad",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val prioridadSeleccionada = when (rgPrioridad.checkedRadioButtonId) {
                R.id.rbPrioridadBaja -> "Baja"
                R.id.rbPrioridadMedia -> "Media"
                R.id.rbPrioridadAlta -> "Alta"
                else -> ""
            }

            val zonaFinal = if (zonaSeleccionada == "Otro espacio") {
                nuevaZona
            } else {
                zonaSeleccionada
            }

            val tareaFinal = if (tareaPersonalizada.isNotEmpty()) {
                tareaPersonalizada
            } else {
                tareaSeleccionada
            }

            // RESULTADO
            Toast.makeText(
                this,
                "Tarea validada correctamente:\n" +
                        "Zona: $zonaFinal\n" +
                        "Categoría: $categoriaSeleccionada\n" +
                        "Tarea: $tareaFinal\n" +
                        "Frecuencia: $cantidad $unidadFrecuencia\n" +
                        "Responsable: $responsableSeleccionado\n" +
                        "Prioridad: $prioridadSeleccionada\n" +
                        "Recordatorio: ${if (recordatorioActivo) "Sí" else "No"}\n\n" +
                        "Aún no se guarda en base de datos.",
                Toast.LENGTH_LONG
            ).show()
        }

        // CANCELAR
        txtCancelarAgregar.setOnClickListener {
            finish()
        }
    }
}