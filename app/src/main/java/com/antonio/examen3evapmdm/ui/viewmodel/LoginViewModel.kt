package com.antonio.examen3evapmdm.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.antonio.examen3evapmdm.ui.model.Email
import com.antonio.examen3evapmdm.ui.model.Producto
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class LoginViewModel: ViewModel() {

    var usuario by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var password2 by mutableStateOf("")
        private set

    var banderaAceso by mutableStateOf(false)
        private set



    var banderaRegistro by mutableStateOf(false)
        private set

    var PasswordVisible by mutableStateOf(false)
        private set

    var email by mutableStateOf(Email(""))
        private set

    val nombreArchivo="email4.dat"

    var listaEmailLeidosFichero = mutableListOf<Email>()
        private set

    var listaEmail= mutableListOf<Email>(

    )
        private set

    fun set_Email(email:Email){
        this.email=email
    }





    fun ObtenerUsuario(user:String){
        this.usuario=user
    }

    fun ObtenerPassword(pass:String){
        this.password=pass
    }
    fun ObtenerPassword2(pass2:String){
        this.password2=pass2
    }


    fun ValidarDatos(){

        var booluser=false
        var boolpass=false
        if(usuario.isNotEmpty()) booluser=true

        if(password.isNotEmpty()) {
            if(password.length>=6) boolpass=true
        }
        if(booluser && boolpass){
           banderaAceso=true
        }

    }

    fun ValidarRegistro(){
        ValidarDatos()
        if(banderaAceso){
            if (password==password2) banderaRegistro=true
        }

    }

    fun verOcultarPassword(){
        PasswordVisible=!PasswordVisible
    }

    fun showToast(string: String, context: Context) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }



    //DESDE AQUI PARA GUARDAR EN FICHERO

    fun guardarListaEnFichero(context: Context) {
        var archivo = File(context.filesDir, nombreArchivo)
        if(!archivo.exists()){
            val objectOutputStream = ObjectOutputStream(FileOutputStream(archivo))
            var contador = 0
            listaEmail.forEach { item ->
                //contador++

                var email = Email(
                    item.email
                )

                // Serializar objeto
                serializarObjeto(email, objectOutputStream)
            }
            objectOutputStream.close()
        }

    }

    fun guardarEmailEnFichero(context: Context,email: Email){
        try{
            var archivo = File(context.filesDir, nombreArchivo)

            val objectOutputStream = object : ObjectOutputStream(FileOutputStream(archivo,true)) {
                override fun writeStreamHeader() {}  //para no sobreescribir la cabecera del archivo
            }
            serializarObjeto(email, objectOutputStream)
            objectOutputStream.close()
            println("Objeto agregado correctamente al archivo.")
        } catch (ex: IOException) {
            println("Error al escribir el objeto en el archivo: ${ex.message}")
        }

    }

    fun serializarObjeto(objeto: Email, objectOutputStream: ObjectOutputStream) {
        objectOutputStream.writeObject(objeto)
    }

    fun leerEmailArchivo(context: Context): MutableList<Email> {
        var archivo = File(context.filesDir, nombreArchivo)
        listaEmailLeidosFichero.clear()
        listaEmailLeidosFichero = deserializarObjeto(archivo)
        listaEmailLeidosFichero.sortBy { it.email }
        return listaEmailLeidosFichero
    }

    fun deserializarObjeto(archivo: File): MutableList<Email> {
        try {
            val objectInputStream = ObjectInputStream(FileInputStream(archivo))

            while (true) {
                try {
                    val email = objectInputStream.readObject()
                    if (email is Email) {
                        listaEmailLeidosFichero.add(email)

                    } else {
                        break;
                    }

                } catch (ex: EOFException) {
                    break
                }
            }

            objectInputStream.close()
        } catch (ex: IOException) {
            println("Error al leer el archivo: ${ex.message}")
        } catch (ex: ClassNotFoundException) {
            println("Clase no encontrada: ${ex.message}")
        }

        return listaEmailLeidosFichero
    }

    //HASTA AQUI PARA GUARDAR EN FICHERO






}