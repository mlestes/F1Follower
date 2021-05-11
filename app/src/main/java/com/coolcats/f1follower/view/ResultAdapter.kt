package com.coolcats.f1follower.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolcats.f1follower.R
import com.coolcats.f1follower.mod.Result
import com.coolcats.f1follower.util.myLog
import kotlinx.android.synthetic.main.result_item_layout.view.*

class ResultAdapter(private var resultsList: List<Result>, var clickListener: (Result) -> Unit) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    inner class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view)

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
            holder.itemView.setOnClickListener { clickListener(resultsList[position]) }
//            myLog("Pressed ${resultsList[position].Driver.familyName}")
        }
    }

    override fun getItemCount(): Int = resultsList.size


}