package nl.bhat.muskan.newsreaderstudent636130.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView
import androidx.cardview.widget.CardView
import nl.bhat.muskan.newsreaderstudent636130.GetResults.ResultsDTO
import nl.bhat.muskan.newsreaderstudent636130.R
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.DetailActivityView
import nl.bhat.muskan.newsreaderstudent636130.ViewsActivities.webView

class AdapterResults(var context: Context, private val items: List<ResultsDTO>) :
    RecyclerView.Adapter<AdapterResults.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_item, null, false)
        return ViewHolder(view)

    }



    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.mtime?.text = "Published at: " + items[position].PublishDate
        holder.mheading.text = items[position].Title
        holder.mcontent.text = items[position].Summary
        Glide.with(context).load(items[position].Image).into(holder.imageView)

        Log.d("deatil view", "on click listener")
        holder.cardView.setOnClickListener {
            val intent = Intent(context, DetailActivityView::class.java)
            intent.putExtra("Url", items[position].Url)
            intent.putExtra("Date", items[position].PublishDate)
            intent.putExtra("Title", items[position].Title)
            intent.putExtra("Summary", items[position].Summary)
            intent.putExtra("Image", items[position].Image)
            context.startActivity(intent)
            Log.d("WORKS", "on click listener") ///

            /*val intent = Intent(context, webView::class.java)
           intent.putExtra("Url", items[position].Url)
           context.startActivity(intent)
           Log.d("WORKS", "on click listener") */
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mheading: TextView
        var mcontent: TextView
        lateinit var mtime: TextView
        var cardView: CardView
        var imageView: ImageView

        init {
            mheading = itemView.findViewById(R.id.mainheading)
            mcontent = itemView.findViewById(R.id.content)
            mtime = itemView.findViewById(R.id.time)
            imageView = itemView.findViewById(R.id.imageView)
            cardView = itemView.findViewById(R.id.cardview)
        }
    }
}