package com.antonio.examen3evapmdm.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.antonio.examen3evapmdm.ui.viewmodel.LoginViewModel

@Composable
fun ListaEmail(navController: NavHostController, viewModelLogin: LoginViewModel) {
    var context= LocalContext.current
    viewModelLogin.leerEmailArchivo(context)
    viewModelLogin.listaEmailLeidosFichero.forEach {
        println(it.email)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        items(viewModelLogin.listaEmailLeidosFichero){
            Text(text=it.email)
        }


    }

}