package com.udacity.asteroidradar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R

class MyAdapter(private val dataSet: List<Asteroid>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    // for Listener
    private lateinit var mListener : MyClickListener

    interface MyClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: MyClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataSet[position]
        holder.codeName.text = currentItem.codename
        holder.closeApproachDate.text = currentItem.closeApproachDate
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(view: View, listener: MyClickListener) : RecyclerView.ViewHolder(view) {
        val codeName : TextView = view.findViewById(R.id.tv_codename)
        val closeApproachDate : TextView = view.findViewById(R.id.tv_closeApproachDate)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}