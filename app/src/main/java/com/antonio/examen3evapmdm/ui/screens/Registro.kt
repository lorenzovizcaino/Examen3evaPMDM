package com.antonio.examen3evapmdm.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavHostController
import com.antonio.examen3evapmdm.ui.navigation.Screens
import com.antonio.examen3evapmdm.ui.viewmodel.LoginViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(navController: NavHostController, viewModelLogin: LoginViewModel) {
    var context= LocalContext.current
    Column(modifier = Modifier.padding(top = 180.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
        TextField(value = viewModelLogin.usuario,
            onValueChange = { viewModelLogin.ObtenerUsuario(it) },
            label={
                Text(text = "Usuario")
            },
            keyboardOptions = KeyboardOptions(keyboardType= KeyboardType.Email)
        )
        //myTextField(number = viewModel.usuario, function ={viewModel.ObtenerUsuario(it)} , operando ="Usuario" )

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

        //myTextFieldPass(number = viewModel.password, function ={viewModel.ObtenerPassword(it)} , operando ="Contraseña",viewModel.PasswordVisible )
        Text(modifier = Modifier
            .align(Alignment.End)
            .padding(end = 50.dp).clickable {
                      viewModelLogin.verOcultarPassword()
            },
            text = if(viewModelLogin.PasswordVisible) "Ocultar contraseña" else "Ver contraseña",

        )
        TextField(value = viewModelLogin.password2,
            onValueChange = { viewModelLogin.ObtenerPassword2(it) },
            label={
                Text(text = "Repite contraseña")
            },
            visualTransformation =
            if(viewModelLogin.PasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Password)

        )
        //myTextFieldPass(number = viewModel.password2, function ={viewModel.ObtenerPassword2(it)} , operando ="Repite contraseña", viewModel.PasswordVisible )
        Button(modifier = Modifier
            .align(Alignment.End)
            .padding(end = 50.dp),onClick = {
            viewModelLogin.ValidarRegistro()
            if(viewModelLogin.banderaRegistro){
                showToast2(string = "Registro realizado correctamente", context = context)
                navController.navigate(route= Screens.Menu.route)
            }else{
                showToast2(string = "Datos incorrectos, Registro no realizado", context = context)
            }

        }) {
            Text(text = "Registrar")
        }
        
    }
}


