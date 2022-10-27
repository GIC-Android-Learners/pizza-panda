package com.example.pizzapanda.presentation.admin

import android.net.Uri
import com.example.pizzapanda.domain.model.Menu

sealed class AdminEvent {
    data class InsertMenu(val menu: Menu, val photoUri: Uri? = null) : AdminEvent()
    data class UpdateMenu(val menu: Menu, val photoUri: Uri? = null) : AdminEvent()
    data class DeleteMenu(val menu: Menu) : AdminEvent()
}