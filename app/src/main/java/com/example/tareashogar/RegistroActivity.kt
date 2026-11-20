package com.example.tareashogar

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_registro)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.pantallaRegistro)
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
        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtContrasena = findViewById<EditText>(R.id.edtContrasena)
        val edtConfirmarContrasena =
            findViewById<EditText>(R.id.edtConfirmarContrasena)

        val rgOrganizacion = findViewById<RadioGroup>(R.id.rgOrganizacion)
        val chkTerminos = findViewById<CheckBox>(R.id.chkTerminos)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val txtIrLogin = findViewById<TextView>(R.id.txtIrLogin)

        // BOTON REGISTRAR
        btnRegistrar.setOnClickListener {

            val nombre = edtNombre.text.toString().trim()
            val correo = edtCorreo.text.toString().trim()
            val contrasena = edtContrasena.text.toString()
            val confirmarContrasena =
                edtConfirmarContrasena.text.toString()

            // VALIDAR NOMBRE
            if (nombre.isEmpty()) {
                edtNombre.error = "Ingresa tu nombre"
                edtNombre.requestFocus()
                return@setOnClickListener
            }

            // VALIDAR CORREO
            if (correo.isEmpty() ||
                !Patterns.EMAIL_ADDRESS.matcher(correo).matches()
            ) {
                edtCorreo.error = "Ingresa un correo válido"
                edtCorreo.requestFocus()
                return@setOnClickListener
            }

            // VALIDAR CONTRASEÑA
            if (contrasena.length < 6) {
                edtContrasena.error =
                    "La contraseña debe tener al menos 6 caracteres"
                edtContrasena.requestFocus()
                return@setOnClickListener
            }

            // CONFIRMAR CONTRASEÑA
            if (contrasena != confirmarContrasena) {
                edtConfirmarContrasena.error =
                    "Las contraseñas no coinciden"
                edtConfirmarContrasena.requestFocus()
                return@setOnClickListener
            }

            // VALIDAR ORGANIZACION
            if (rgOrganizacion.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Selecciona cómo organizas las tareas",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // VALIDAR TERMINOS
            if (!chkTerminos.isChecked) {
                Toast.makeText(
                    this,
                    "Debes aceptar los términos y condiciones",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // OBTENER ORGANIZACION SELECCIONADA
            val organizacionSeleccionada =
                when (rgOrganizacion.checkedRadioButtonId) {
                    R.id.rbIndividual -> "Individualmente"
                    R.id.rbPareja -> "En pareja"
                    R.id.rbFamilia -> "En familia"
                    else -> ""
                }

            // RESULTADO DE LAS VALIDACIONES
            Toast.makeText(
                this,
                "Datos validados correctamente. " +
                        "Organización: $organizacionSeleccionada. " +
                        "El registro aún no se guarda.",
                Toast.LENGTH_LONG
            ).show()
        }

        // VOLVER AL LOGIN
        txtIrLogin.setOnClickListener {
            finish()
        }
    }
    fun btnIrLoginOnClick(view: View)
    {
        val intent = Intent(this, ListaActivity::class.java)
        startActivity(intent)
    }
}
