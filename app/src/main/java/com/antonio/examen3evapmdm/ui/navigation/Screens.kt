package com.antonio.examen3evapmdm.ui.navigation

sealed class Screens(val route:String){
    object Menu: Screens("initial_screen")//info a aparecer en pantalla


    object Inicio: Screens("Contadores")//info a aparecer en pantalla
    object Registro: Screens("Registro")//info a aparecer en pantalla
    object ResumenCompras: Screens("Resumen Compras")//info a aparecer en pantalla
}