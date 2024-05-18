package com.antonio.examen3evapmdm.ui.model

import java.io.Serializable

data class ProductoCarrito(
    var id:Int,
    var tipo:String,
    var modelo:String,
    var precio:String,
    var color:String
): Serializable
