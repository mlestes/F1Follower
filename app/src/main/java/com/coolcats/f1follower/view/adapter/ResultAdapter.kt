package com.coolcats.f1follower.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Result
import kotlinx.android.synthetic.main.result_item_layout.view.*

class ResultAdapter(private var resultsList: List<Result>, private var raceResultDelegate: RaceResultDelegate) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    inner class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view)

    interface RaceResultDelegate{
        fun onRaceResultSelected(results: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.result_item_layout, parent, false)
        return ResultViewHolder(itemView)
    }

    fun updateResults(resultsList: List<Result>) {
        this.resultsList = resultsList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        resultsList[position].let {
            holder.itemView.apply {
                driver_name.text = "${it.Driver.givenName} ${it.Driver.familyName}"
                team_name.text = it.Constructor.name
                position_text.text = it.position
            }
            holder.itemView.setOnClickListener { raceResultDelegate.onRaceResultSelected(resultsList[position])}
        }
    }

    override fun getItemCount(): Int = resultsList.size


}