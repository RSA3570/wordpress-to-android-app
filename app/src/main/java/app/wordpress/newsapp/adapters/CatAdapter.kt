package app.wordpress.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.wordpress.newsapp.R
import app.wordpress.newsapp.models.category.CatListItem

class CatAdapter(private val context: Context, private val catList: ArrayList<CatListItem>):
    RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    class CatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvCategoryName: TextView = itemView.findViewById(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_category_list, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catModel = catList[position]
        holder.tvCategoryName.text = catModel.name
    }

    override fun getItemCount(): Int {
        return catList.size
    }
}