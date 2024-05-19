package com.antonio.examen3evapmdm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.antonio.examen3evapmdm.ui.screens.Inicio
import com.antonio.examen3evapmdm.ui.screens.ListaEmail

import com.antonio.examen3evapmdm.ui.screens.Menu
import com.antonio.examen3evapmdm.ui.screens.Registro
import com.antonio.examen3evapmdm.ui.screens.ResumenCompras

import com.antonio.examen3evapmdm.ui.viewmodel.LoginViewModel
import com.antonio.examen3evapmdm.ui.viewmodel.ProductoViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController() //linea para recordar NavController entre pantallas
    val viewModelLogin=remember{ LoginViewModel() } //linea para recordar viewModel entre pantallas
    val viewModelProducto=remember{ ProductoViewModel() } //linea para recordar viewModel entre pantallas
    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) {
            Menu(navController, viewModelLogin) }//Nombre del fichero .kt al que navegar

        composable(route = Screens.Inicio.route) {
            Inicio(navController,viewModelProducto) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.ResumenCompras.route) {
            ResumenCompras(navController,viewModelProducto) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.Registro.route) {
            Registro(navController,viewModelLogin) //Nombre de la función composable a la que navegar
        }
        composable(route = Screens.ListaEmail.route) {
            ListaEmail(navController,viewModelLogin) //Nombre de la función composable a la que navegar
        }



    }
}






