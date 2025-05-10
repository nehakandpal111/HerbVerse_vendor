package com.example.herbverse_vendor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.herbverse_vendor.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("My Products") },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            // Products list with images
            ProductCardWithButtons(
                name = "Aloe Vera",
                price = "$2.00"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProductCardWithButtons(
                name = "Lavender Oil",
                price = "$5.00"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Orders section
            Text(
                text = "Orders",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Order items
            OrderItem(
                id = "#1245",
                productName = "Echinacea Plant",
                status = "New",
                customer = "Edenmir is left"
            )

            Spacer(modifier = Modifier.height(8.dp))

            OrderItem(
                id = "#1244",
                productName = "Echinacea Plant",
                status = "Confirmed",
                customer = "Edenmir lmg be"
            )
        }
    }
}

@Composable
fun ProductCardWithButtons(
    name: String,
    price: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product image - plant icon in a green circle
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0F2F1)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Spa,
                    contentDescription = null,
                    tint = Color(0xFF00796B),
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Column {
                Button(
                    onClick = { /* TODO: Edit */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE0F2F1),
                        contentColor = Color(0xFF00796B)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("Edit")
                }

                Button(
                    onClick = { /* TODO: Delete */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFEBEE),
                        contentColor = Color(0xFFC62828)
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
fun OrderItem(
    id: String,
    productName: String,
    status: String,
    customer: String
) {
    val statusColor = when (status) {
        "New" -> Color(0xFF4CAF50)
        "Confirmed" -> Color(0xFF2196F3)
        "Shipped" -> Color(0xFF9C27B0)
        else -> Color(0xFF9E9E9E)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = id,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = productName,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = customer,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8F5E9)
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.align(Alignment.Top)
            ) {
                Text(
                    text = status,
                    color = statusColor,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}