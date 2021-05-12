package com.coolcats.f1follower.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Result
import com.coolcats.f1follower.util.myLog
import kotlinx.android.synthetic.main.driver_info_layout.*

class DriverFragment(private val results: Result) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.driver_info_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myLog(results.Driver.givenName)
        number_text.text = results.Driver.permanentNumber
        driver_name.text = "${results.Driver.givenName} ${results.Driver.familyName}"
        team_text.text = results.Constructor.name
        nationality_text.text = results.Driver.nationality
        dob_text.text = results.Driver.dateOfBirth
        if(results.FastestLap == null){
            fast_lap_text.text = "N/A"
            fast_info_text.text = "N/A"
        }
        else {
            fast_lap_text.text = results.FastestLap.Time.time
            fast_info_text.text = results.FastestLap.lap
        }
        position_info_text.text = results.position
        if(results.Time == null) {
            total_time_label.text = "Status:"
            total_time_text.text = results.status
        }
        else
            total_time_text.text = results.Time.time
    }

}