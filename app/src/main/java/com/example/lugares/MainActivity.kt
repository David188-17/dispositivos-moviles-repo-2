package com.example.lugares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.lugares.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.Principal

class MainActivity : AppCompatActivity() {
    //Definicion del objeto para hacer la autenticacion
   private lateinit var auth : FirebaseAuth
   private lateinit var binding : ActivityMainBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //se inicializa el objeto para manejar las vistas...
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       //se inicializa Firebase para usarce en el App
        // se asigna el objeto auth para hacer autenticacion
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth


//metodo para el login
        binding.btLogin.setOnClickListener{
            haceLogin()
        }
        binding.btRegister.setOnClickListener{
            haceRegister()
        }
    }

    private fun haceRegister() {
      //Recuperamos la informacion que ingreso el usuario...
        val email =binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()
       //se llama a la funcion para crear un usuario en Firebase(correo/contraseña)

        // se hace el registro
        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) {task ->
                var user: FirebaseUser? =null
                if (task.isSuccessful) {//si pudo crear el usuario
                    Log.d("creando usuario", "Registrado")
                    user = auth.currentUser// recupero la infor del usuario creado
                    actualiza(user)
                }else{
                    Log.d("creando usuario", "Fallo")


                }
                actualiza(user)
            }
    }

    private fun actualiza(user: FirebaseUser?) {
       // si hay un suario definido ... se pasa a la panatalla principal(Activity)
        if (user != null) {
         // se pasa a la otra pantalla
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }




// esto hara que una vez autenticao ... no pida mas a menos que se cierre la sesion
    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
                actualiza(usuario)

    }
    private fun haceLogin() {
       //recuperamos la informacion que ingreso el usuario
        val email =binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()
        // se hace el registro
        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this) {task ->

                var user: FirebaseUser? =null
                if (task.isSuccessful) {//si pudo crear el usuario
                    Log.d("autenticando", "autenticado")
                   user = auth.currentUser//recupero la info del usuario creado

                }else{
                    Log.d("autenticando", "fallo")

                }
                actualiza(user)
            }
    }
}