package com.cristian.uberclonecliente.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.cristian.uberclonecliente.databinding.ActivityRegisterBinding
import com.cristian.uberclonecliente.providers.AuthProvider

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authProvider = AuthProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.botonGoToLogin.setOnClickListener { goToLogin() }
        binding.botonRegistroRegistro.setOnClickListener { register() }

    }

    private fun register(){
        val nombre = binding.textoNombre.text.toString()
        val apellido = binding.textoApellido.text.toString()
        val telefono = binding.textoTelefono.text.toString()
        val email = binding.textoEmailRegistro.text.toString()
        val password = binding.textoPasswordRegistro.text.toString()
        val confirmpassword = binding.textoPasswordConfirmRegistro.text.toString()

        if (isValidForm(nombre, apellido, telefono, email, password, confirmpassword)){
            authProvider.register(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@RegisterActivity, "Registro fallido ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                    Log.d("FIREBASE", "Error ${it.exception.toString()}")
                }
            }
        }
    }

    private fun isValidForm(
        nombre: String,
        apellido: String,
        telefono: String,
        email: String,
        password: String,
        confirmpassword: String) :Boolean{

        if(nombre.isEmpty()){
            Toast.makeText(this, "Debes ingresar tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if(apellido.isEmpty()){
            Toast.makeText(this, "Debes ingresar tu apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if(telefono.isEmpty()){
            Toast.makeText(this, "Debes ingresar tu telefono", Toast.LENGTH_SHORT).show()
            return false
        }

        if(email.isEmpty()){
            Toast.makeText(this, "Debes ingresar tu email", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.isEmpty()){
            Toast.makeText(this, "Debes ingresar tu contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if(confirmpassword.isEmpty()){
            Toast.makeText(this, "Debes confirmar la contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password != confirmpassword){
            Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length <6){
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show()
        }
        return true
    }

    private fun goToLogin(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}