package ru.ponomarchukpn.insittask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.ponomarchukpn.insittask.R
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.entity.TodoStatus

class TodoAdapter(
    private val onItemLongClick: (TodoEntity) -> Boolean
) : ListAdapter<TodoEntity, TodoAdapter.TodoViewHolder>(TodoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.description.text = item.description
        holder.status.text = when(item.status) {
            TodoStatus.OPEN -> "open"
            TodoStatus.DONE -> "done"
        }
        val colorResource = if (item.status == TodoStatus.OPEN) {
            R.color.app_blue
        } else {
            R.color.app_cyan
        }
        holder.itemLayout.setBackgroundResource(colorResource)
        holder.itemView.setOnLongClickListener {
            onItemLongClick.invoke(item)
        }
    }

    class TodoViewHolder(itemView: View) : ViewHolder(itemView) {
        val itemLayout: ConstraintLayout = itemView.findViewById(R.id.item_layout)
        val description: TextView = itemView.findViewById(R.id.item_description_content)
        val status: TextView = itemView.findViewById(R.id.item_status_content)
    }
}