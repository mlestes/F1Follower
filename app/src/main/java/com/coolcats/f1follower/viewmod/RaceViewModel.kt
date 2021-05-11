package com.coolcats.f1follower.viewmod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coolcats.f1follower.mod.Race
import com.coolcats.f1follower.mod.Result
import com.coolcats.f1follower.network.RaceNetwork
import com.coolcats.f1follower.util.myLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RaceViewModel : ViewModel() {

    private val raceDisposable = CompositeDisposable()
    private val raceNetwork = RaceNetwork()
    val raceData = MutableLiveData<List<Race>>()
    val resultData = MutableLiveData<List<Result>>()
    val driverData = MutableLiveData<Result>()

    fun getResultsFromRace(race: Race) {
//        resultData.postValue(race.Results)
        resultData.value = listOf()
        resultData.value = race.Results
    }

    fun getDriverFromResult(result: Result) {
//        driverData.postValue(result)
        driverData.value = result
    }

    fun getResultsFromServer(year: String) {

        raceDisposable.add(
            raceNetwork.getResults(year)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    raceData.postValue(response.MRData.RaceTable.Races)
                    raceDisposable.clear()
                }, { throwable ->
                    raceDisposable.clear()
                    myLog(throwable.toString())
                })
        )
    }

    override fun onCleared() {
        raceDisposable.clear()
        super.onCleared()
    }

}