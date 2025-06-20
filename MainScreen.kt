package com.example.homelibrary.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homelibrary.Book
import com.example.homelibrary.BookViewModel

@Composable
fun MainScreen(
    viewModel: BookViewModel,
    onAddBook: () -> Unit,
    onEditBook: (Int) -> Unit
) {
    val books by viewModel.allBooks.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                // Для спрощення в цій версії пошук не оновлює список
                // Можна додати пошук у ViewModel за бажанням
            },
            label = { Text("Пошук книги") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(books) { book ->
                BookItem(book = book, onDelete = { viewModel.delete(it) }, onClick = { onEditBook(book.id) })
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        FloatingActionButton(onClick = onAddBook) {
            Icon(Icons.Default.Add, contentDescription = "Додати книгу")
        }
    }
}

@Composable
fun BookItem(book: Book, onDelete: (Book) -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = book.title, style = MaterialTheme.typography.h6)
                Text(text = "Автор: ${book.author}", style = MaterialTheme.typography.body2)
                Text(text = "Рік: ${book.year}", style = MaterialTheme.typography.body2)
            }
            IconButton(onClick = { onDelete(book) }) {
                Icon(Icons.Default.Delete, contentDescription = "Видалити книгу")
            }
        }
    }
}

