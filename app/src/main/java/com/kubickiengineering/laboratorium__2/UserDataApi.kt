package com.kubickiengineering.laboratorium__2

import io.reactivex.Single
import retrofit2.http.GET

interface UserDataApi {

    @GET("people/1/")
    fun postUser(): Single<PersonSW>
}

