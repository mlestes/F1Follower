package com.coolcats.f1follower.mod

data class FastestLap(
    val AverageSpeed: AverageSpeed,
    val Time: Time,
    val lap: String,
    val rank: String
)