package com.antonio.examen3evapmdm.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.examen3evapmdm.ui.model.ProductoCarrito
import com.antonio.examen3evapmdm.ui.navigation.Screens
import com.antonio.examen3evapmdm.ui.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenCompras(navController: NavHostController, viewModelProducto: ProductoViewModel) {
    Scaffold(
        topBar = {
            MyTopBar2(navController, viewModelProducto)
        },
        content = { padding ->
            ElementosComprados(viewModelProducto)
        }
    )

}

@Composable
fun MyTopBar2(navController: NavHostController, viewModelProducto: ProductoViewModel) {
    val context = LocalContext.current
    TopAppBar(

        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = Screens.Inicio.route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Ir hacia atras",
                    tint = Color.White
                )
            }


        },
        title = { Text("Resumen de Compras", color = Color.White) },
        actions = {


        },


        )
}

@Composable
fun ElementosComprados(viewModelProducto: ProductoViewModel) {
    val context = LocalContext.current
    viewModelProducto.listaComprar.forEach { item->
        println(item.modelo)
    }
    Column {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 70.dp)
        ) {
            items(viewModelProducto.listaComprar) {
                ItemProductosComprados(
                    viewModel = viewModelProducto,
                    producto = it,
                    onItemSelected = {
                        Toast.makeText(context, it.tipo + " " + it.modelo, Toast.LENGTH_SHORT).show()
                    }
                )

            }


        }
        CalculoDelTotal(viewModelProducto)
        Spacer(modifier = Modifier.height(30.dp))
        BotonTramitarPedido(viewModelProducto, context)


    }
}


@Composable
fun ItemProductosComprados(
    viewModel: ProductoViewModel,
    producto: ProductoCarrito,
    onItemSelected: () -> Unit
) {
    Card(border = BorderStroke(2.dp, Color.DarkGray), modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 8.dp)
        .clickable { onItemSelected() }) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = producto.tipo)
                Text(text = producto.modelo)

            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {
                Text(text = producto.precio + "€", fontSize = 20.sp)


            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(10.dp)){
            Text(text = "Color: "+producto.color)
            
        }

    }

}

@Composable
fun CalculoDelTotal(viewModelProducto: ProductoViewModel) {
    Card(
        border = BorderStroke(2.dp, Color(255, 216, 20)), modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)

            .padding(horizontal = 5.dp, vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text(
                text = "TOTAL",
                modifier = Modifier
                    .weight(3f)
                    .padding(top = 5.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = viewModelProducto.sumaProductos.toString() + "€",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp, top = 5.dp), fontSize = 18.sp
            )
        }


    }
}

@Composable
fun BotonTramitarPedido(viewModelProducto: ProductoViewModel, context: Context) {
    Button(
        onClick = { Toast.makeText(context, "Pedido Tramitado", Toast.LENGTH_SHORT).show() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(Color(255, 216, 20)),
    ) {
        if (viewModelProducto.contadorProductos > 1) {
            Text(
                text = "Tramitar pedido (${viewModelProducto.contadorProductos} productos)",
                color = Color.White,
                fontSize = 18.sp
            )
        } else {
            Text(
                text = "Tramitar pedido (${viewModelProducto.contadorProductos} producto)",
                color = Color.White,
                fontSize = 18.sp
            )
        }

    }
}