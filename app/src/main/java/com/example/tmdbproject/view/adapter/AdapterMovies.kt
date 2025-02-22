package com.example.tmdbproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbproject.R
import com.example.tmdbproject.core.Constants
import com.example.tmdbproject.model.MoviesModel

class AdapterMovies(
    private val context: Context,
    var listMovies: List<MoviesModel>
): RecyclerView.Adapter<AdapterMovies.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val movieCardView = itemView.findViewById(R.id.cvMovies) as CardView
        val moviePosterImage = itemView.findViewById(R.id.moviesImage) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movies = listMovies[position]
        Glide
            .with(context)
            .load("${Constants.BASE_URL_IMAGE}${movies.posterPath}")
            .apply(RequestOptions().override(Constants.WIDTH, Constants.HEIGHT,))
            .into(holder.moviePosterImage)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

}
