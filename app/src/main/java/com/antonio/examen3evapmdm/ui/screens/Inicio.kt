package com.antonio.examen3evapmdm.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.examen3evapmdm.ui.model.Producto
import com.antonio.examen3evapmdm.ui.model.ProductoCarrito
import com.antonio.examen3evapmdm.ui.navigation.Screens
import com.antonio.examen3evapmdm.ui.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio(navController: NavHostController, viewModelProducto: ProductoViewModel) {
    Scaffold(
        topBar = {
            MyTopBar(navController,viewModelProducto)
        },
        content = {padding ->
            Contenido(navController,viewModelProducto)
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController, viewModelProducto: ProductoViewModel) {
    val context= LocalContext.current
    TopAppBar(


        title = { Text("Comercio TEIS", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
        actions = {





            IconButton(onClick = {navController.navigate(route = Screens.ResumenCompras.route)}) {
                if(viewModelProducto.contadorProductos>0){
                    BadgedBox(badge = { Badge { Text(text = "${viewModelProducto.contadorProductos}") } }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AddShoppingCart,
                            contentDescription = "Carrito",
                            tint = Color.White
                        )
                    }
                }else{
                    Icon(
                        imageVector = Icons.Filled.AddShoppingCart,
                        contentDescription = "Carrito",
                        tint = Color.White
                    )
                }

            }





        },


        )
}




@Composable
fun Contenido(navController: NavHostController, viewModelProducto: ProductoViewModel) {
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        items(viewModelProducto.lista){
            ItemProductos(
                viewModelProducto=viewModelProducto,
                producto=it,
                navController=navController
            )
        }


    }
}

@Composable
fun ItemProductos(viewModelProducto: ProductoViewModel, producto: Producto, navController: NavHostController) {
    var context= LocalContext.current
    var isChecked by remember { mutableStateOf(producto.selecionado) }


    Card(border = BorderStroke(2.dp, Color.DarkGray),modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 8.dp)) {
        Row(modifier=Modifier.fillMaxWidth())
        {
            Column(modifier=Modifier.padding(10.dp)){
                Text(text = producto.tipo)
                Text(text = producto.modelo)
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)){
                Text(text = producto.precio)

            }
        }
        MyRadioButton(viewModelProducto,producto)
        Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Checkbox(checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    producto.selecionado = isChecked
                    viewModelProducto.set_ProductoCarrito(
                        ProductoCarrito(
                            producto.id,
                            producto.tipo,
                            producto.modelo,
                            producto.precio,
                            producto.color
                        )
                    )
                    if (isChecked) {
                        viewModelProducto.sumarProductos(producto.precio)
                        viewModelProducto.sumarUnidadeProductos()

                        viewModelProducto.listaComprar.add(viewModelProducto.productoCarrito)

                    } else {
                        viewModelProducto.restarProductos(producto.precio)
                        viewModelProducto.restarUnidadesProductos()
                        viewModelProducto.listaComprar.remove(viewModelProducto.productoCarrito)
                    }
                })
        }

    }

}

@Composable
fun MyRadioButton(viewModelProducto: ProductoViewModel, producto: Producto) {
    var colorElegido by remember { mutableStateOf(producto.color) }
    var listaColores = listOf<String>("Azul","Rojo","Verde")
    Row(modifier=Modifier.fillMaxWidth()){
        listaColores.forEach{item->
            RadioButton(selected = colorElegido == item,
                        onClick = {
                            colorElegido=item
                            viewModelProducto.set_Color(colorElegido)
                            producto.color=colorElegido



            })
            Text(text = item, modifier = Modifier.padding(top = 12.dp))
        }
    }
}
