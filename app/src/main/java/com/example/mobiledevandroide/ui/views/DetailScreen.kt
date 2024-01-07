import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mobiledevandroide.data.model.ReceiptModel
import com.example.mobiledevandroide.ui.components.detailscreen.DateField
import com.example.mobiledevandroide.ui.components.detailscreen.EditableTextField
import com.example.mobiledevandroide.ui.components.scaffold.BottomBar
import com.example.mobiledevandroide.ui.components.scaffold.TopBar
import com.example.mobiledevandroide.viewModels.ReceiptDetailViewModel

@Composable
fun DetailsScreen(
    receiptId: String,
    navController: NavController,
    receiptDetailViewModel: ReceiptDetailViewModel = hiltViewModel(),
) {
    val receiptDetailState by receiptDetailViewModel.receiptModelDetail.collectAsState()

    var editedReceiptModel by remember { mutableStateOf<ReceiptModel?>(null) }

    LaunchedEffect(receiptId) {
        if (receiptId.isNotBlank()) {
            receiptDetailViewModel.loadReceiptDetail(receiptId)
        }
    }
    var totalAmount by remember { mutableStateOf<String?>(null) }
    var currency by remember { mutableStateOf<String?>(null) }
    var date by remember { mutableStateOf<String?>(null) }
    var paymentType by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(receiptDetailState) {
        if (receiptDetailState != null) {
            editedReceiptModel = receiptDetailState
            totalAmount = editedReceiptModel?.totalAmount
            currency = editedReceiptModel?.currency
            paymentType = editedReceiptModel?.paymentType
        }
    }

    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (val detail = receiptDetailState) {
                is ReceiptModel -> {
                    Image(
                        bitmap = detail.receiptImage.asImageBitmap(),
                        contentDescription = "contentDescription",
                        modifier = Modifier
                            .size(400.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                    )
                    Text(text = "Receipt ID: ${detail.id}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))

                    totalAmount?.let {
                        EditableTextField("Total Bedrag", it, KeyboardType.Number) {
                            totalAmount = it
                        }
                    }

                    currency?.let {
                        EditableTextField("Valuta", it, KeyboardType.Text) {
                            currency = it
                        }
                    }
                    paymentType?.let{
                        EditableTextField("betalingsmethode", it, KeyboardType.Text  ){
                            paymentType = it
                        }
                    }


                    DateField(
                        detail.date,
                        onValueChange = { selectedDate -> date = selectedDate },
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Row {
                        Button(
                            onClick = {
                                val updatedReceipt = editedReceiptModel?.copy(
                                    totalAmount = totalAmount ?: "",
                                    currency = currency ?: "",
                                    date = date ?: "",
                                )

                                if (updatedReceipt != null) {
                                    receiptDetailViewModel.saveReceiptDetail(
                                        updatedReceipt,
                                        receiptId
                                    )
                                }
                            },
                        ) {
                            Text(text = "Opslaan")
                        }
                        Button(onClick = { receiptDetailViewModel.deleteReceipt(receiptId) }) {
                            Text(text = "Verwijderen")
                        }
                    }

                }

                null -> {
                    CircularProgressIndicator()
                }

                else -> {
                    Text(text = "Error: Unable to load receipt details", fontSize = 20.sp)
                }
            }
        }
    }
}







