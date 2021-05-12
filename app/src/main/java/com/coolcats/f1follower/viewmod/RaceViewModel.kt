package com.coolcats.f1follower.viewmod

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coolcats.f1follower.mod.Race
import com.coolcats.f1follower.network.RaceNetwork
import com.coolcats.f1follower.util.myLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RaceViewModel : ViewModel() {

    private val raceDisposable = CompositeDisposable()
    private val raceNetwork = RaceNetwork()
    val raceData = MutableLiveData<List<Race>>()

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