package com.coolcats.f1follower.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coolcats.f1follower.R
import kotlinx.android.synthetic.main.result_list_layout.*

class ResultsFragment(private val resultAdapter: ResultAdapter) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        results_recyclerview.adapter = resultAdapter
    }

}