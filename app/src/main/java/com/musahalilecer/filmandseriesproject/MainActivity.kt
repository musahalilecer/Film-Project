package com.musahalilecer.filmandseriesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.musahalilecer.filmandseriesproject.adapter.RecyclerViewAdapter
import com.musahalilecer.filmandseriesproject.databinding.ActivityMainBinding
import com.musahalilecer.filmandseriesproject.model.FilmModel
import com.musahalilecer.filmandseriesproject.service.FilmApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener  {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://api.tvmaze.com/"
    private var filmModel: ArrayList<FilmModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    private var compositeDisposable: CompositeDisposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        loadData()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerFilmView.layoutManager = layoutManager

    }
    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(FilmApi::class.java)

        compositeDisposable?.add(retrofit.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse))


    }
    private fun handleResponse(filmList: List<FilmModel>){

        try {
            filmModel = ArrayList(filmList)

            filmModel?.let {
                recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                binding.recyclerFilmView.adapter = recyclerViewAdapter
            }
        }catch (e: Exception){
            println(e)
        }


    }



    override fun onItemClick(filmModel: FilmModel) {
        Toast.makeText(this@MainActivity,"Clicked: ${filmModel.name}",Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}