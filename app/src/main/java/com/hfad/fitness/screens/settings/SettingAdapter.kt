package com.hfad.fitness.screens.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.fitness.databinding.ItemSettingBinding
import com.hfad.fitness.model.workouts.Workout

class SettingAdapter : RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {

    var settingList: List<Workout> = emptyList()

    class SettingViewHolder(
        val binding: ItemSettingBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingBinding.inflate(inflater, parent, false)

        return SettingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return settingList.size
    }
}