package com.coolcats.f1follower.network

import com.coolcats.f1follower.mod.QueryResults
import com.coolcats.f1follower.util.APPENDED_QUERY
import com.coolcats.f1follower.util.BASE_URL
import com.coolcats.f1follower.util.DEFAULT_QUERY
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RaceNetwork {

    private val resultsService = createRetrofit().create(ResultsService::class.java)

    private fun createRetrofit():
            Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    //desired output -> List<Race>
    //weird JSON formatting: QueryResults -> MRData -> RaceTable -> List<Race>
    fun getCurrentResults(): Single<QueryResults> = resultsService.getCurrentResults()
    fun getYearResults(year: String): Single<QueryResults> = resultsService.getYearResults(year)

    fun getResults(year: String): Single<QueryResults> {
        if (year.isNotEmpty())
            return getYearResults(year)
        else
            return getCurrentResults()
    }

    interface ResultsService {
        @GET(DEFAULT_QUERY)
        fun getCurrentResults(): Single<QueryResults>

        @GET("{year}/$APPENDED_QUERY")
        fun getYearResults(@Path("year") year: String): Single<QueryResults>
    }
}