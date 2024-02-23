package com.example.currencyconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyConverter()

                }
            }
        }
    }
}


@Composable
fun CurrencyConverter(){
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf( "Meters") }
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oconversionFactor = remember { mutableStateOf(1.0) }
    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(text = "Unit Converter" , modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        , style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = inputValue, onValueChange = {inputValue=it
                                                              convertUnits()},modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            label = { Text(text = "Enter a value")})
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.Center , modifier = Modifier.fillMaxWidth()) {
            Box {
                Button(onClick = { iExpanded=true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                }
                DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Millimeters") },
                        onClick = {
                            iExpanded=false
                            inputUnit="Millimeters"
                            conversionFactor.value=0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded=false
                        inputUnit="Meters"
                        conversionFactor.value=1.0
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = {
                        iExpanded=false
                        inputUnit="Feets"
                        conversionFactor.value=0.3048
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Box {
                Button(onClick = {oExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)
                }
                DropdownMenu(expanded = oExpanded , onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        oExpanded=false
                        outputUnit="Millimeters"
                        oconversionFactor.value=0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded=false
                        outputUnit="Centimeters"
                        oconversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded=false
                        outputUnit="Meters"
                        oconversionFactor.value=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = {
                        oExpanded=false
                        outputUnit="Feets"
                        oconversionFactor.value=0.3048
                        convertUnits()
                    })
                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Result: ${outputValue} ${outputUnit}",modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        , style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    CurrencyConverter()
}