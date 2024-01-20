@file:OptIn(ExperimentalMaterial3Api::class)

package com.meta.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.meta.littlelemon.ui.theme.PrimaryGreen


@Composable
fun Profile(context: Context,navHostController: NavHostController){

    val sharedPreferences = context.getSharedPreferences("little-lemon", Context.MODE_PRIVATE)

    val firstName = remember {
        mutableStateOf(sharedPreferences.getString("firstName", ""))
    }
    val lastName = remember {
        mutableStateOf(sharedPreferences.getString("lastName", ""))
    }
    val email = remember {
        mutableStateOf(sharedPreferences.getString("email", ""))
    }

    val scrollState = rememberScrollState()
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(Modifier.fillMaxWidth(0.6f)) {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "App logo")

                Text(text = "profile information",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall)
        }
                OutlinedTextField(
                    enabled = false,
                    readOnly = true,
                    value = firstName.value!!,
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = PrimaryGreen,
                        disabledLabelColor = PrimaryGreen
                    ),
                    modifier = Modifier.fillMaxWidth())
        
                OutlinedTextField(
                    enabled = false,
                    readOnly = true,
                    value = lastName.value!!,
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = PrimaryGreen,
                        disabledLabelColor = PrimaryGreen
                    ),
                    modifier = Modifier.fillMaxWidth())

                OutlinedTextField(
                    enabled = false,
                    readOnly = true,
                    value = email.value!!,
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = PrimaryGreen,
                        disabledLabelColor = PrimaryGreen
                    ),
                    modifier = Modifier.fillMaxWidth())
        
            Spacer(modifier = Modifier.size(40.dp))

            Button(onClick = {
                sharedPreferences.edit()
                    .clear()
                    .apply()

                navHostController.navigate(Onboarding.route){
                    popUpTo(Home.route){inclusive = true}
                    launchSingleTop = true
                }


            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)) {
                Text(text = "Log Out")
            }

        }


}