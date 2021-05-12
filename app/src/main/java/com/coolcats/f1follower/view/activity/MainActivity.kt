package com.coolcats.f1follower.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Race
import com.coolcats.f1follower.util.myLog
import com.coolcats.f1follower.view.adapter.RaceAdapter
import com.coolcats.f1follower.view.fragment.ResultsFragment
import com.coolcats.f1follower.viewmod.RaceViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RaceAdapter.RaceDelegate {

    private val viewModel: RaceViewModel by viewModels()
    private val raceAdapter = RaceAdapter(listOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.raceData.observe(this, { raceList ->
            raceAdapter.updateRaces(raceList)
        })

        race_recyclerview.adapter = raceAdapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(race_recyclerview)

        submit_button.setOnClickListener {
            myLog("Pressed the button")
            val count = supportFragmentManager.backStackEntryCount
            for (i in 0 until count)
                supportFragmentManager.popBackStack()
            val year = year_input_text.text.toString()
            viewModel.getResultsFromServer(year)
        }
    }

    override fun onRaceSelected(race: Race) {
        myLog("RaceResults size: ${race.Results.size}")
        val resultsFragment = ResultsFragment(race.Results)
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

}