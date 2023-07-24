package ru.ponomarchukpn.insittask.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity

object TodoDiffCallback : DiffUtil.ItemCallback<TodoEntity>() {

    override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
        return oldItem == newItem
    }
}