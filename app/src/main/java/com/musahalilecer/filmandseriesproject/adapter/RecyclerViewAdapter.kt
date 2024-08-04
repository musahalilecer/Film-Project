package com.musahalilecer.filmandseriesproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.musahalilecer.filmandseriesproject.databinding.RecyclerFilmViewBinding
import com.musahalilecer.filmandseriesproject.model.FilmModel

class RecyclerViewAdapter(val filmlist: ArrayList<FilmModel>,  private val listener: Listener ): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    interface Listener{
        fun onItemClick(filmModel: FilmModel)
    }



    class ViewHolder(val binding: RecyclerFilmViewBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerFilmViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filmlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClick(filmlist.get(position))
        }
        holder.binding.textName.text = filmlist.get(position).name
        holder.binding.textLanguage.text = filmlist.get(position).language
    }

}

