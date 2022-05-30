package com.example.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvas.ui.theme.CanvasTheme

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            constraint()







        }
    }
}
@Composable
fun constraint() {
    var constraint = ConstraintSet {
        var greenbox = createRefFor("greenbox")
        var redbox = createRefFor("redbox")
        var purplebox = createRefFor("purplebox")
     constrain(greenbox) {

            start.linkTo(parent.start)
            top.linkTo(parent.top)
         width = Dimension.fillToConstraints
         height = Dimension.value(100.dp)
            




        }
        constrain(redbox) {

            width = Dimension.fillToConstraints
            height = Dimension.value(100.dp)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)


        }
        constrain(purplebox) {
            start.linkTo(greenbox.start)
            top.linkTo(greenbox.top)
            bottom.linkTo(redbox.bottom)
            end.linkTo(redbox.end)
            width = Dimension.value(100.dp)
            height = Dimension.matchParent
        }


        
    }
    ConstraintLayout(constraintSet = constraint, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenbox")
            .fillMaxWidth())
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .layoutId("redbox"))
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .layoutId("purplebox"))

    }
    
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun color(modifier: Modifier = Modifier) {
    var colors = remember {mutableStateOf(Color.Blue)}
    Box(modifier = modifier
        .background(colors.value)
        .clickable {
            colors.value = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(),
                1F)
        }) {


    }
}

@Composable
fun colum() {
    Column(modifier = Modifier
        .width(200.dp)
        .height(200.dp)
        .background(Color.Blue)
        .padding(top = 50.dp)
        .border(width = 20.dp, Color.Red)
        .padding(top = 50.dp)
        .border(width = 20.dp, Color.Green)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


    ) {
        Text(text = "Привет мир", fontSize = 15.sp, color = Color.Red, modifier = Modifier.offset(x = 20.dp, y = 50.dp))
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Пока мир", fontSize = 22.sp, color = Color.Red, modifier = Modifier.clickable {

             })

    }


}


@ExperimentalFoundationApi
@Composable
fun grid(contextex: Context) {
    var item  = (1..100 ).toList()
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(item.size){ Card(modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .padding(10.dp)
            .clickable {
                Toast
                    .makeText(contextex,
                        "Ты нажал на кнопку под номером  ${it}",
                        Toast.LENGTH_SHORT)
                    .show()
            },elevation = 8.dp) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${it}", fontSize = 18.sp)

            }}

       


        }
    }
}



@Composable
fun row(context: Context) {
    LazyRow{
     items((1..10).toList()) {
           Card(modifier = Modifier
               .width(200.dp)
               .height(200.dp)
               .padding(8.dp)
               .clickable {
                   Toast
                       .makeText(context,
                           "значение нажатое равно ${it.toString()} ",
                           Toast.LENGTH_SHORT)
                       .show()
               }
            ) {
               Column(
                   modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Box(modifier = Modifier.width(200.dp)) {
                       Image(painter = painterResource(id = R.drawable.test), contentDescription = "Batman" )
                       
                   }
                   Box(modifier = Modifier
                       .fillMaxSize()
                       .padding(8.dp)) {
                       Text(text = "Брюс Уейн", color = Color.Red)
                   }
               }

           }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CanvasTheme {
        Greeting("Android")
    }
}