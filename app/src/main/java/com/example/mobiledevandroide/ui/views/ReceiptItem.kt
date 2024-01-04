package com.example.mobiledevandroide.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiledevandroide.data.model.ReceiptModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

@Composable
fun ReceiptItem(receiptModel: ReceiptModel, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(receiptModel.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Receipt ID: ${receiptModel.id}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(text = "Total Amount:${receiptModel.currency} ${receiptModel.totalAmount} ")
            val formattedDate = convertDateFormat(receiptModel.date)
            Text(
                text = "Date: $formattedDate",
            )
            Text(text = "Payment Type: ${receiptModel.paymentType}")
        }
    }
}

private fun convertDateFormat(originalDate: String): String {
    val originalFormatter = DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        .optionalStart()
        .appendOffset("+HH:MM", "Z")
        .optionalEnd()
        .toFormatter()

    val targetFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val localDateTime = LocalDateTime.parse(originalDate, originalFormatter)
    return localDateTime.format(targetFormatter)
}
