package com.example.tommyho_shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tommyho_shoppinglistapp.ui.theme.TommyHoShoppingListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TommyHoShoppingListAppTheme {
                shoppingList()
            }
        }
    }
}

@Composable
fun shoppingList(){
    val itemList = remember { mutableStateListOf<String>() }
    val itemQuantity = remember{ mutableStateListOf<Float>()}
    val checkedItems = remember {mutableStateListOf<Boolean>()}
    var currentText by remember{mutableStateOf("")}
    var quantity by remember{mutableStateOf("")}
    val pattern = remember { Regex("^\\d+\$") }
    Column() {
        TextField(
            value = currentText,
            onValueChange = { currentText = it },
            label = { Text("Enter Item") })
        TextField(
            value = quantity,
            onValueChange = {
                if (pattern.matches(it)) {
                    quantity = it
                }},
            label = { Text("Enter Quantity") })
        if(currentText.isNotEmpty() && quantity.isNotEmpty()) {
            Button(onClick = {
                itemList.add(currentText); currentText = ""; itemQuantity.add(quantity.toFloat()); quantity = ""; checkedItems.add(
                false
            )
            }) {
                Text("Add Item to List")
            }
        }
        LazyColumn() {
            items(itemList.size) { index ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedItems[index],
                        onCheckedChange = { checkedItems[index] = it })
                    Text(text = itemList[index] + " ")
                    Text(text = itemQuantity[index].toString())
                    if(checkedItems[index]){
                        Text(text = "   Item has been bought")
                    }
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TommyHoShoppingListAppTheme {
    }
}