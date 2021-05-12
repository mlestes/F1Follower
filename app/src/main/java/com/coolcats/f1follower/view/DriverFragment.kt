package com.coolcats.f1follower.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Result
import com.coolcats.f1follower.util.myLog
import kotlinx.android.synthetic.main.driver_info_layout.*

class DriverFragment(private val result: Result) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.driver_info_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myLog(result.Driver.givenName)
        number_text.text = result.Driver.permanentNumber
        driver_name.text = "${result.Driver.givenName} ${result.Driver.familyName}"
        team_text.text = result.Constructor.name
        nationality_text.text = result.Driver.nationality
        dob_text.text = result.Driver.dateOfBirth
        if(result.FastestLap == null){
            fast_lap_text.text = "N/A"
            fast_info_text.text = "N/A"
        }
        else {
            fast_lap_text.text = result.FastestLap.Time.time
            fast_info_text.text = result.FastestLap.lap
        }
        position_info_text.text = result.position
        if(result.Time == null) {
            total_time_label.text = "Status:"
            total_time_text.text = result.status
        }
        else
            total_time_text.text = result.Time.time
    }

}