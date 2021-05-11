package com.coolcats.f1follower.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Race
import kotlinx.android.synthetic.main.race_item_layout.view.*

class RaceAdapter(private var raceList: List<Race>, var clickListener: (Race) -> Unit) :
    RecyclerView.Adapter<RaceAdapter.RaceViewHolder>() {

    inner class RaceViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.race_item_layout, parent, false)
        return RaceViewHolder(itemView)
    }

    fun updateRaces(raceList: List<Race>) {
        this.raceList = raceList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RaceViewHolder, position: Int) {
        raceList[position].let {
            holder.itemView.apply {
                race_name_text.text = it.raceName
                circuit_text.text = it.Circuit.circuitName
                date_text.text = it.date
            }
            holder.itemView.setOnClickListener { clickListener(raceList[position]) }
        }

    }

    override fun getItemCount(): Int = raceList.size


}