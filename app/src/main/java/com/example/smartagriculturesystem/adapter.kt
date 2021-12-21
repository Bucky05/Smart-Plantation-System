package com.example.smartagriculturesystem



import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.single_plant.view.*

class adapter(val topics:ArrayList<String>): RecyclerView.Adapter<adapter.ViewHolder>() {
    override fun getItemCount()=topics.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.plantname.plant_name.text = topics[position]
        holder.itemView.setOnClickListener{ view : View -> Unit
            val context = holder.black_background.context
            val intent = Intent(context,ProfileActivity::class.java)
            intent.putExtra("plantName",topics[position])
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_plant,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val plantname : CardView = itemView.findViewById(R.id.singleplant)
        val black_background : ImageView = itemView.findViewById(R.id.black_background)
    }
}