package com.application.cloudchatapp.extension

fun androidx.appcompat.widget.SearchView.queryChanged(listener: (String?) -> Unit) {
    this.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText)
            return false
        }

    })

}
