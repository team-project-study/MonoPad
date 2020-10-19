package com.project.monopad.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.monopad.databinding.ItemFilmographyMovieBinding
import com.project.monopad.model.network.response.MovieInfoResultResponse
import com.project.monopad.model.network.response.PersonDetailCreditsCastResponse
import com.project.monopad.util.DetailParsingUtil

class PersonFilmographyAdapter : RecyclerView.Adapter<PersonFilmographyAdapter.ViewHolder>() {

    private var filmographyList = ArrayList<PersonDetailCreditsCastResponse>()

    private var listener: ((id: Int) -> Unit)? = null

    fun setOnFilmoMovieClickListener(listener: (id: Int) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonFilmographyAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFilmographyMovieBinding = ItemFilmographyMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonFilmographyAdapter.ViewHolder, position: Int) {
        holder.bindView(filmographyList[position])
    }

    override fun getItemCount(): Int = filmographyList.size

    fun setFilmoList(list: List<PersonDetailCreditsCastResponse>){
        this.filmographyList = list as ArrayList<PersonDetailCreditsCastResponse>
    }

    inner class ViewHolder(private val binding: ItemFilmographyMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindView(movie: PersonDetailCreditsCastResponse){
            if (movie.character.isNotEmpty()) binding.tvFilmoMovieCharacter.text = "${movie.character} 역"

            binding.model = movie
            binding.ivFilmoMoviePoster.setOnClickListener {
                listener?.invoke(movie.id)
            }
        }
    }
}