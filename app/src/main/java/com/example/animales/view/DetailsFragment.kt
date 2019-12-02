package com.example.animales.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.animales.R
import com.example.animales.model.Animal
import com.example.animales.util.getProgressDrawable
import com.example.animales.util.loadImage
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    var animal : Animal?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal=DetailsFragmentArgs.fromBundle(it).animal
        }
        animalImage.loadImage(animal?.imageUrl, getProgressDrawable(context!!))
        animalName.text=animal?.name
        animalLocation.text=animal?.location
        animalLifeSpane.text=animal?.lifeSpane
        animalDieat.text=animal?.dieat


    }

}
