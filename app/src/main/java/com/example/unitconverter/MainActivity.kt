package com.example.unitconverter

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Unitconverter()
                }
            }
        }
    }
}

@Composable
fun Unitconverter() {
    var inputunit by remember{ mutableStateOf("Centimeters") }
    var outputunit by remember{ mutableStateOf("Meters") }
    var inputvalue by remember{ mutableStateOf("") }
    var outputvalue by remember{ mutableStateOf("") }
    var iexpanded by remember{ mutableStateOf(false) }
    var oexpanded by remember{ mutableStateOf(false) }
    var conversionfactor = remember{ mutableStateOf(1.00) }
    var oconversionfactor = remember{ mutableStateOf(1.00) }

    val CustomTextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )

    fun convertunits(){
        val inputvaluedouble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (inputvaluedouble * conversionfactor.value * 100.0 / oconversionfactor.value).roundToInt() / 100.0
        outputvalue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter",style = CustomTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue, onValueChange = {
            inputvalue = it
            convertunits()
        }
            ,label = {Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = {iexpanded = true}) {
                    Text(text = inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                    DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centimeters")},onClick = {
                            iexpanded = false
                            inputunit = "Centimeters"
                            conversionfactor.value = 0.01
                            convertunits()
                        })
                        DropdownMenuItem(text = { Text(text = "meters")},onClick = {
                            iexpanded = false
                            inputunit = "meters"
                            conversionfactor.value = 1.0
                            convertunits()
                        })
                        DropdownMenuItem(text = { Text(text = "Feet")},onClick = {
                            iexpanded = false
                            inputunit = "Feet"
                            conversionfactor.value = 0.3048
                            convertunits()
                        })
                        DropdownMenuItem(text = { Text(text = "Milimeters")}, onClick = {
                            iexpanded = false
                            inputunit = "Milimeters"
                            conversionfactor.value = 0.001
                            convertunits()
                        })
                        
                    }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oexpanded = true }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = { oexpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")},onClick = {
                        oexpanded = false
                        outputunit = "Centimeters"
                        oconversionfactor.value = 0.01
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "meters")},onClick = {
                        oexpanded = false
                        outputunit = "meters"
                        oconversionfactor.value = 1.00
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")},onClick = {
                        oexpanded = false
                        outputunit = "Feet"
                        oconversionfactor.value = 0.3048
                        convertunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters")}, onClick = {
                        oexpanded = false
                        outputunit = "Milimeters"
                        oconversionfactor.value = 0.001
                        convertunits()
                    })

                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputvalue $outputunit",style = CustomTextStyle)

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        Unitconverter()
    }
}
