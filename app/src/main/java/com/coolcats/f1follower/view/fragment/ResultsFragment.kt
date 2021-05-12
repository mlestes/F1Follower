package com.coolcats.f1follower.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.coolcats.f1follower.R
import com.coolcats.f1follower.util.myLog
import com.coolcats.f1follower.mod.Result
import com.coolcats.f1follower.view.adapter.ResultAdapter
import kotlinx.android.synthetic.main.result_list_layout.*

class ResultsFragment(private val resultsList: List<Result>) : Fragment(),
    ResultAdapter.RaceResultDelegate {

    private val adapter: ResultAdapter = ResultAdapter(listOf(), this)
    private lateinit var driverFragment: DriverFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        results_recyclerview.adapter = adapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(results_recyclerview)

    }

    override fun onRaceResultSelected(results: Result) {
        myLog(results.Driver.familyName)
        driverFragment = DriverFragment(results)
        requireActivity().supportFragmentManager.beginTransaction()
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter.updateResults(resultsList)
    }

}