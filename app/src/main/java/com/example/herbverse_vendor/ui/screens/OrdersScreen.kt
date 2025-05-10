package com.example.herbverse_vendor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(navController: NavController) {
    val orderStatusTabs = listOf("All", "Pending", "Processing", "Completed", "Canceled")
    var selectedTabIndex by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Orders") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Status tabs
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp
            ) {
                orderStatusTabs.forEachIndexed { index, status ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(status) }
                    )
                }
            }

            // Sample orders
            val orders = remember {
                listOf(
                    Order(
                        id = "#1245",
                        customerName = "Edenmir",
                        productName = "Echinacea Plant",
                        status = OrderStatus.NEW,
                        date = "May 10, 2025"
                    ),
                    Order(
                        id = "#1244",
                        customerName = "Edenmir",
                        productName = "Echinacea Plant",
                        status = OrderStatus.CONFIRMED,
                        date = "May 9, 2025"
                    )
                )
            }
            
            // Filter orders based on selected tab
            val filteredOrders = when (selectedTabIndex) {
                0 -> orders
                1 -> orders.filter { it.status == OrderStatus.NEW }
                2 -> orders.filter { it.status == OrderStatus.PROCESSING }
                3 -> orders.filter { it.status == OrderStatus.CONFIRMED }
                4 -> orders.filter { it.status == OrderStatus.SHIPPED }
                else -> orders
            }

            if (filteredOrders.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No orders found")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredOrders) { order ->
                        OrderItem(order = order)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { /* TODO: Show order details */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = order.id,
                    style = MaterialTheme.typography.titleMedium
                )
                
                Text(
                    text = order.productName,
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Text(
                    text = order.customerName,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                OrderStatusChip(status = order.status)
            }
        }
    }
}

@Composable
fun OrderStatusChip(status: OrderStatus) {
    val (backgroundColor, textColor) = when (status) {
        OrderStatus.NEW -> Pair(Color(0xFFE3F2FD), Color(0xFF1976D2))
        OrderStatus.PROCESSING -> Pair(Color(0xFFE0F7FA), Color(0xFF0097A7))
        OrderStatus.CONFIRMED -> Pair(Color(0xFFE8F5E9), Color(0xFF388E3C))
        OrderStatus.SHIPPED -> Pair(Color(0xFFF3E5F5), Color(0xFF7B1FA2))
        OrderStatus.CANCELED -> Pair(Color(0xFFFFEBEE), Color(0xFFC62828))
    }

    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status.toString(),
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

data class Order(
    val id: String,
    val customerName: String,
    val productName: String,
    val status: OrderStatus,
    val date: String
)

enum class OrderStatus {
    NEW,
    PROCESSING,
    CONFIRMED,
    SHIPPED,
    CANCELED;

    override fun toString(): String {
        return when (this) {
            NEW -> "New"
            PROCESSING -> "Processing"
            CONFIRMED -> "Confirmed"
            SHIPPED -> "Shipped"
            CANCELED -> "Canceled"
        }
    }
}