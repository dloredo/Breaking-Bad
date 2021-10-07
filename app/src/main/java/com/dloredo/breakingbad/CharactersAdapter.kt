package com.dloredo.breakingbad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dloredo.breakingbad.databinding.ItemCharacterBinding

class CharactersAdapter(private var characters: MutableList<Character>):RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    private lateinit var mContext: Context

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val binding = ItemCharacterBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_character,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]

        with(holder.binding){
            name.text = character.name
            nickname.text = character.nickname

            Glide.with(mContext)
                .load(character.img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(img)

            when(character.status){
                "Alive"-> imgStatus.setImageResource(R.drawable.a)
                "Deceased"-> imgStatus.setImageResource(R.drawable.d)
                else -> imgStatus.setImageResource(R.drawable.u)
            }
        }
    }

    override fun getItemCount(): Int = characters.size

    fun setItems(characters: MutableList<Character>){
        this.characters = characters
        notifyDataSetChanged()
    }
}