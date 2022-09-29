package com.example.pizzapanda.domain.storage.util

sealed class Directory (val path: String){
    object Image: Directory("images")
}
