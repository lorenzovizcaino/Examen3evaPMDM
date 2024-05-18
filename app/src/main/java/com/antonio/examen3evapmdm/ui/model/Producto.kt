package com.antonio.examen3evapmdm.ui.model

import java.io.Serializable

data class Producto(
    var id:Int,
    var tipo:String,
    var modelo:String,
    var precio:String,
    var color:String,
    var selecionado:Boolean

):Serializable
