package com.musahalilecer.filmandseriesproject.service

import com.musahalilecer.filmandseriesproject.model.FilmModel
import io.reactivex.Observable
import retrofit2.http.GET

interface FilmApi {
    @GET("search/shows?q=war")
    fun getData(): Observable<List<FilmModel>>
}

// search/shows?q=war