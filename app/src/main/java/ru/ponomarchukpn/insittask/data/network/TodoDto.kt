package ru.ponomarchukpn.insittask.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TodoDto(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("status")
    @Expose
    val status: String
)