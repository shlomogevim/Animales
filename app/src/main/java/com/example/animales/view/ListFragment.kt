package com.example.animales.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.animales.R
import com.example.animales.model.Animal
import com.example.animales.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    lateinit var viewModel:ListViewModel
    private val listAdapter=AnimalListAdapter(arrayListOf())

    private val animalListDataObserver=Observer<List<Animal>>{list->
        list?.let {
            animalList.visibility= VISIBLE
            listAdapter.updateAnimalList(it)
        }

    }
    private val loadingDataObserver=Observer<Boolean>{isLoading->
        loadingView.visibility=if (isLoading) VISIBLE else GONE
        if (isLoading){
            listError.visibility= GONE
            animalList.visibility= GONE
        }

    }
    private val errorDataObserver=Observer<Boolean>{isError->
        listError.visibility=if (isError) VISIBLE else GONE
        if (isError){
            loadingView.visibility= GONE
            animalList.visibility= GONE
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel= ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.animals.observe(this,animalListDataObserver)
        viewModel.loading.observe(this,loadingDataObserver)
        viewModel.loadError.observe(this,errorDataObserver)
        viewModel.refresh()

        animalList.apply {
            layoutManager=GridLayoutManager (context,2)
            adapter=listAdapter
        }
        refreshLayout.setOnRefreshListener {
            animalList.visibility= GONE
            listError.visibility= GONE
            loadingView.visibility= VISIBLE
            //viewModel.refresh()
            viewModel.hardRefresh()
            refreshLayout.isRefreshing=false
        }

    }




}
