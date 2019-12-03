package com.example.animales.view


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.animales.R
import com.example.animales.databinding.FragmentDetailsBinding
import com.example.animales.model.Animal
import com.example.animales.model.AnimalsPalette
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    var animal: Animal? = null
    private lateinit var dataBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailsFragmentArgs.fromBundle(it).animal
        }

        /*context?.let {
            dataBinding.animalImage.loadImage(animal?.imageUrl, getProgressDrawable(it))
        }*/

        dataBinding.animal = animal

        setupBackgroundColor(animal?.imageUrl)

    }

    private fun setupBackgroundColor(url: String?) {

        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate() { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                           //dataBinding.animalLayout.setBackgroundColor(intColor)
                            dataBinding.pallet= AnimalsPalette(intColor)

                        }
                }
            })
    }
}
