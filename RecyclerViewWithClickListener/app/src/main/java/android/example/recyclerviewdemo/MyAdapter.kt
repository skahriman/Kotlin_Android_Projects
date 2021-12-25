package android.example.recyclerviewdemo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataSet: MutableList<String>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // for Listener
    private lateinit var mListener : MyClickListener

    interface MyClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: MyClickListener) {
        mListener = listener
    }
    // for Listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        //added mListener
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class ViewHolder(view: View, listener: MyClickListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.textView)

            //added this
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}