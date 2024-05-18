package com.antonio.examen3evapmdm.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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


}