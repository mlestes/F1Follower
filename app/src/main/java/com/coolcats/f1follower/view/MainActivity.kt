package com.coolcats.f1follower.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Race
import com.coolcats.f1follower.mod.Result
import com.coolcats.f1follower.util.myLog
import com.coolcats.f1follower.viewmod.RaceViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: RaceViewModel by viewModels()
    private val raceAdapter = RaceAdapter(listOf(), { race: Race -> onRaceItemClicked(race) })
    private val resultAdapter =
        ResultAdapter(listOf(), { result: Result -> onResultItemClicked(result) })
    private val resultsFragment = ResultsFragment(resultAdapter)
    private lateinit var driverFragment: DriverFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.raceData.observe(this, { list ->
            raceAdapter.updateRaces(list)
        })

        viewModel.resultData.observe(this, { list ->
            resultAdapter.updateResults(list)
        })

        race_recyclerview.adapter = raceAdapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(race_recyclerview)

        submit_button.setOnClickListener {
            myLog("Pressed the button")
            val count = supportFragmentManager.backStackEntryCount
            for(i in 0 until count)
                supportFragmentManager.popBackStack()
            val year = year_input_text.text.toString()
            viewModel.getResultsFromServer(year)
        }
    }

    private fun onRaceItemClicked(race: Race) {

        myLog("Pressed: ${race.raceName}")
        viewModel.getResultsFromRace(race)
        viewModel.resultData.value?.get(0)?.Driver?.let { myLog(it.familyName) }
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.results_place_layout, resultsFragment)
            .addToBackStack(resultsFragment.tag)
            .commit()

    }

    private fun onResultItemClicked(result: Result) {

        driverFragment = DriverFragment(result)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.driver_place_layout, driverFragment)
            .addToBackStack(driverFragment.tag)
            .commit()

    }

}