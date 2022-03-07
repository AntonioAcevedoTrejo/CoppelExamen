package com.example.coppelexamen.ui.fragment.marvel_list

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.example.coppelexamen.R
import com.example.coppelexamen.data.model.Character
import com.example.coppelexamen.databinding.FragmentItemBinding
import kotlinx.coroutines.withContext
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList

class MarvelRecyclerViewAdapter() : RecyclerView.Adapter<MarvelRecyclerViewAdapter.ViewHolder>() {

    private var marvelAllCharacter:List<Character> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       /* val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_list, parent, false)
        return ViewHolder(view)*/
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = marvelAllCharacter[position]
        holder.idView.text = item.id.toString()
        holder.nameView.text = item.name
        holder.imageView.let {
            Glide.with(holder.imageView)
                .load(getImage(item.thumbnail+"."+item.thumbnailExt))
                .into(it)
        }
       /* holder.imageView.load(item.thumbnail+"."+item.thumbnailExt){
            crossfade(true)
            placeholder(R.drawable.ic_100tb)
            transformations(CircleCropTransformation())
        }
                .load(item.thumbnail+"."+item.thumbnailExt)
        */
        var urlImage=item.thumbnail+"."+item.thumbnailExt
        Log.i("TAGS","imagen:"+urlImage)
    }

    override fun getItemCount(): Int = marvelAllCharacter.size


    fun getImage(urlImage: String): URL? {
        var urlImage = urlImage
        var url: URL? = null
        urlImage = urlImage.replace("http", "https")
        try {
            url = URL(urlImage)
            return url
        } catch (e: MalformedURLException) {
            Log.d("Error", e.toString())
        }
        return url
    }

    fun setData(marvelCharacter:List<Character>?){
        marvelAllCharacter = marvelCharacter!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.txtId
        val nameView: TextView = binding.txtName
        val imageView:ImageView = binding.imgThumbnail

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

}