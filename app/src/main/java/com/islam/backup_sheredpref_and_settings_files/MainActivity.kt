package com.islam.backup_sheredpref_and_settings_files

import android.app.backup.SharedPreferencesBackupHelper
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPref = getSharedPreferences("myPref",Context.MODE_PRIVATE)
            val editor =sharedPref.edit()
            SharedFiles(editor = editor, sharedPreferences = sharedPref)
        }
    }
}


@Composable
fun SharedFiles(editor: SharedPreferences.Editor,sharedPreferences: SharedPreferences) {
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value =name.value, onValueChange ={name.value =it}, placeholder = { Text(text = "Name")})
        TextField(value =age.value, onValueChange ={age.value =it}, placeholder = { Text(text = "Age")})
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Button(onClick = {
                editor.apply{
                    putString("name", name.value)
                    putString("age", age.value)
                    apply()
                }
            }) {
                Text(text = "Save")
            }
            Button(onClick = {
                name.value = sharedPreferences.getString("name",null).toString()
                age.value = sharedPreferences.getString("age",null).toString()
            }) {
                Text(text = "Read")
            }
        }
        Text(text = "Name is ${name.value}")
        Text(text = "Age is ${age.value}")
    }
}


@Preview(showBackground = true)
@Composable
fun SharedFilesPreview() {
}