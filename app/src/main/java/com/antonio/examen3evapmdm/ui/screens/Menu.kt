package com.antonio.examen3evapmdm.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.antonio.examen3evapmdm.ui.navigation.Screens
import com.antonio.examen3evapmdm.ui.viewmodel.LoginViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController, viewModelLogin: LoginViewModel) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = viewModelLogin.usuario,
            onValueChange = { viewModelLogin.ObtenerUsuario(it) },
            label={
                Text(text = "Usuario")
            },
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Email)
            )

        TextField(value = viewModelLogin.password,
            onValueChange = { viewModelLogin.ObtenerPassword(it) },
            label={
                Text(text = "Contraseña")
            },
            visualTransformation =
                if(viewModelLogin.PasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Password)

            )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 60.dp)
                .clickable {
                    viewModelLogin.verOcultarPassword()
                },
            text = if(viewModelLogin.PasswordVisible) "Ocultar contraseña" else "Ver contraseña",

        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(modifier = Modifier
            .align(Alignment.End)
            .padding(end = 60.dp), onClick = {
            viewModelLogin.ValidarDatos()
            if (viewModelLogin.banderaAceso) {
                navController.navigate(route = Screens.Inicio.route)

            } else {
                showToast2(string = "Datos invalidos", context = context)
            }
        }) {
            Text(text = "Entrar")

        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 60.dp)
                .clickable {
                    navController.navigate(route = Screens.Registro.route)
                }, text = "Registrarse",
        )

    }
}

fun showToast2(string: String, context: Context) {

    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()

}