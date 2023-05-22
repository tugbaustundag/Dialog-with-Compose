package com.smality.dialogwithcompose
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.smality.dialogwithcompose.ui.theme.DialogWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DialogWithComposeTheme {
                Surface(color =Color(0xFF1A1A1A)) {
                    DialogMessageExample()
                }
            }
        }
    }
}

@Composable
fun DialogMessageExample() {

    val dialogStatus = remember {
        mutableStateOf(false)
    }
    val myContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UPLOAD FİLE buttonunu oluşturma ve renklendirme
        Button(onClick = { dialogStatus.value = true },
            border = BorderStroke(1.dp, Color(0xFFFF4B06)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B21))) {
            Text(text = "UPLOAD FİLE", color = Color.White)
        }

        if (dialogStatus.value){
            //Alert Dialog penceresini oluşturma
            AlertDialog(
                onDismissRequest = { dialogStatus.value = true },
                //Ünlem resmini ve başlığı oluşturup, tasarımda yerleştirme
                title = {
                    Column( modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        Image(painter = painterResource(R.drawable.warn), contentDescription = "")
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Upload Attention!", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold )
                    }
                },
                text = { Text(text = "Your file is over 3MB, this may harm your application", color = Color.Gray, fontSize = 16.sp)},
               //Alert Dialog arka plan rengini belirleme
                containerColor = Color.White,
                //Alert Dialog'un köşelerini yuvarlatılmış görünüm sağlama
                shape = RoundedCornerShape(35.dp),
                //Onaylama butonunu oluşturma
                confirmButton = {
                    Column( modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = {
                                dialogStatus.value = false
                                Toast.makeText(myContext,"Confirm button is clicked",Toast.LENGTH_SHORT).show()

                            },
                            border = BorderStroke(1.dp, Color(0xFFFF4B06)),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B21)),
                            modifier = Modifier.width(150.dp)
                        ) {
                            Text(text = "CONTİNUE",color = Color.White, fontSize = 16.sp)
                        }
                    }
                }/*,
                dismissButton = {
                    Button(
                        onClick = {

                            dialogStatus.value = false
                            Toast.makeText(myContext,"Dismiss button is clicked",Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text(text = "NO",color = Color.Green, fontSize = 18.sp)
                    }

                }*/)
        }
    }
}