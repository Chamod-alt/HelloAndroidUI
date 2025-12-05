
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu // Placeholder symbol for Top Bar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Define the primary colors matching the Improved UI
val PrimaryTeal = Color(0xFF00ADB5)
val ImprovedUIGreen = Color(0xFF6DD55C)
val LightBackground = Color(0xFFE8F6F6)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {

                Scaffold(
                    topBar = {
                        ImprovedUITopBar()
                    }
                ) { innerPadding ->
                    AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// --- TOP BAR Component with Icon and Teal Color ---
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ImprovedUITopBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // ICON: Using Android Logo as placeholder for the symbol icon
                Icon(
                    painter = painterResource(id = R.drawable.ic_androidchat),
                    contentDescription = "App Icon",
                    //tint = Color.White,
                    modifier = Modifier.size(36.dp).padding(end = 8.dp)
                )
                Text(
                    text = "HelloAndroid",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryTeal // Teal color
        )
    )
}

// --- MAIN CONTENT Component ---
@Composable
fun AppContent(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    // State to hold the user input (for "Enter your name")
    var username by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LightBackground) // Light background color
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // ---------- IMAGE (Android Logo - Tinted Green) ----------
        Image(
            painter = painterResource(id = R.drawable.ic_android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 10.dp)
        )

        // ---------- TITLE ----------
        Text(
            text = "Welcome to",
            color = Color.DarkGray,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Android Development!",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // ---------- CARD (Container for Input and Buttons) ----------
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White // White card background
            ),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ---------- 1. Input Field ("Enter your name") ----------
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Enter your name") },
                    modifier = Modifier.fillMaxWidth(),
                    // Use primary teal color for the input field border/label
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryTeal,
                        focusedLabelColor = PrimaryTeal
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ---------- 2. BUTTON ("Click Me") ----------
                Button(
                    onClick = {
                        showToast(context, "Hello $username!")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryTeal, // Teal color
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Click Me", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ---------- 3. BUTTON ("Go to Next Page") ----------
                Button(
                    onClick = {
                        // Ensure you have a 'SecondActivity' class in your project
                        // and it's registered in AndroidManifest.xml
                        val intent = Intent(context, SecondActivity::class.java)
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryTeal, // Teal color
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Go to Next Page", fontSize = 16.sp)
                }
            }
        }
    }
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyApplicationTheme {
        Column {
            ImprovedUITopBar()
            AppContent()
        }
    }
}







