package com.example.animales.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.animales.R
import com.example.animales.model.Animal
import com.example.animales.util.getProgressDrawable
import com.example.animales.util.loadImage
import kotlinx.android.synthetic.main.item_layout.view.*

class AnimalListAdapter( private val animalList:ArrayList<Animal>):
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList:List<Animal>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_layout,parent,false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount()=animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animalName.text=animalList[position].name
        holder.view.animalImage.loadImage(animalList[position]
            .imageUrl, getProgressDrawable(holder.view.context))
        holder.view.animalLayout.setOnClickListener {
            val action=ListFragmentDirections.
                actionDetails(animalList[position])
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    class AnimalViewHolder(val view: View):RecyclerView.ViewHolder(view)

}