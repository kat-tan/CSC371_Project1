package com.example.csc371_kat_tan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csc371_kat_tan.ui.theme.Blue1
import com.example.csc371_kat_tan.ui.theme.FugazOne
import com.example.csc371_kat_tan.ui.theme.Pink1
import kotlinx.coroutines.delay
import androidx.core.content.edit


class MainActivity : ComponentActivity() {
    private var showSplash = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize SharedPreferences for the first launch check
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        // If it's the first launch, set showSplash to true to show splash
        if (isFirstLaunch) {
            // Set first launch flag to false immediately
            sharedPreferences.edit { putBoolean("isFirstLaunch", false) }
        }

        setContent {
            MaterialTheme {
                if (showSplash.value) {
                    SplashScreen {
                        showSplash.value = false  // Hide splash after the delay
                    }
                } else {
                    // Show main content after splash screen
                    StartUp(this)
                }
            }
        }
    }
}

// Screen to pop up at initial launch
@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // To keep track of whether the splash screen has been shown or not
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

    Log.d("SplashScreen", "isFirstLaunch: $isFirstLaunch")

    // If it's the first launch, show splash screen
    if (isFirstLaunch) {
        Log.d("SplashScreen", "Splash screen showing for first time")

        LaunchedEffect(Unit) {

            Log.d("SplashScreen", "Delay started")

            delay(2000)

            Log.d("SplashScreen", "Delay finished, calling onTimeout()")

            onTimeout() // After the delay, hide the splash screen

            // Set the flag to false after the first launch
            sharedPreferences.edit { putBoolean("isFirstLaunch", false) } }


        // Splash Screen UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Kathleen Tan Project 1", fontSize = 25.sp, fontFamily = FugazOne)
                Image(
                    painter = painterResource(id = R.drawable.splashicon), // Replace with your logo
                    contentDescription = "Badtz Maru",
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    } else {
        // If not the first launch, just proceed without the splash screen
        onTimeout()
    }
}


// Composable for the Welcome screen with buttons to navigate to Registration or Log In
@Composable
fun StartUp(context: Context) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.marubg), // Your image resource
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterEnd),// Ensure the image fills the container
            contentScale = ContentScale.Crop // This will ensure the image maintains aspect ratio
        )
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp).verticalScroll(
            rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {

        Text(
            modifier = Modifier.absolutePadding(top = 200.dp),
            text = "Welcome!",
            fontSize = 50.sp,
            fontFamily = FugazOne,
        )

        Column(
            modifier = Modifier.padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            // Button with an onClick to go to Registration
            Button(
                onClick = {
                    context.startActivity(Intent(context, Registration::class.java))
                },
                colors = ButtonDefaults.buttonColors(containerColor = Pink1),
                modifier = Modifier.padding(10.dp).width(200.dp).height(50.dp)

            ) {
                Text("Register", color = Color.Black, fontSize = 20.sp)
            }

            // onClick for Log In Button
            Button(
                onClick = {
                    context.startActivity(Intent(context, LogIn::class.java))
                },
                colors = ButtonDefaults.buttonColors(containerColor = Blue1),
                modifier = Modifier.padding(20.dp).width(200.dp).height(50.dp)

            ) {
                Text("Log In", color = Color.Black, fontSize = 20.sp)
            }
        }
    }


}



