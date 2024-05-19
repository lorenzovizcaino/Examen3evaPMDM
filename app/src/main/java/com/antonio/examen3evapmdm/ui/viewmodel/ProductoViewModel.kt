package com.antonio.examen3evapmdm.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.antonio.examen3evapmdm.ui.model.Producto
import com.antonio.examen3evapmdm.ui.model.ProductoCarrito
import java.text.DecimalFormat

class ProductoViewModel {

    var lista= mutableListOf<Producto>(

        Producto(1,"Camisa","Vietnan","34.90","Rojo",false),
        Producto(2,"Camisa","Roma","24.90","Rojo",false),
        Producto(3,"Camisa","Camboya","35.90","Rojo",false),
        Producto(4,"Camisa","Hawai","134.90","Rojo",false),
        Producto(5,"Camisa","Estambul","4.90","Rojo",false),
        Producto(6,"Camisa","Paris","54.90","Rojo",false),
        Producto(7,"Pantalon","Paris","54.90","Rojo",false),
        Producto(8,"Pantalon","Roma","24.90","Rojo",false),
        Producto(9,"Pantalon","Camboya","35.90","Rojo",false),
        Producto(10,"Pantalon","Hawai","134.90","Rojo",false),
        Producto(11,"Pantalon","Estambul","4.90","Rojo",false),
        Producto(12,"Pantalon","Paris","54.90","Rojo",false),
    )
        private set

    var listaComprar= mutableListOf<ProductoCarrito>()
        private set

    var id by mutableStateOf(0)
        private set

    var tipo by mutableStateOf("")
        private set

    var modelo by mutableStateOf("")
        private set

    var precio by mutableStateOf("")
        private set

    var color by  mutableStateOf("Rojo")
        private set

    var selecionado by mutableStateOf(false)
        private set

    var producto by mutableStateOf(Producto(0,"","","","rojo",false))
        private set

    var productoCarrito by mutableStateOf(ProductoCarrito(0,"","","",""))
        private set

    var sumaProductos by mutableStateOf(0.0)
        private set

    var contadorProductos by mutableStateOf(0)
        private set

    var format= DecimalFormat("#,###.00")
        private set

    var isCheckedScafold by mutableStateOf(false)




    fun set_Producto(producto: Producto){
        this.producto=producto
    }

    fun set_ProductoCarrito(productoCarrito: ProductoCarrito){
        this.productoCarrito=productoCarrito
    }

    fun set_id(id:Int){
        this.id=id
    }
    fun set_tipo(tipo:String){
        this.tipo=tipo
    }

    fun set_modelo(modelo:String){
        this.modelo=modelo
    }

    fun set_precio(precio:String){
        this.precio=precio
    }

    fun set_selecionado(selecionado:Boolean){
        this.selecionado=selecionado
    }

    fun set_Color(color:String){
        this.color=color
    }

    fun sumarProductos(precio: String) {
        var precioDouble=precio.toDouble()
        sumaProductos+=precioDouble
    }

    fun restarProductos(precio: String) {
        var precioDouble=precio.toDouble()
        sumaProductos-=precioDouble
    }

    fun sumarUnidadeProductos() {
        contadorProductos++
    }

    fun restarUnidadesProductos() {
        contadorProductos--
    }

    fun getTotalFormateado(cantidad:Double): String {
        return format.format(cantidad)
    }

    fun set_ContadorProductos(contadorProductos:Int){
        this.contadorProductos=contadorProductos
    }
    fun set_SumaProductos(sumaProductos:Double){
        this.sumaProductos=sumaProductos
    }

}