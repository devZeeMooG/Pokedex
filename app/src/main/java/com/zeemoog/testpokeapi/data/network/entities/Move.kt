package com.zeemoog.testpokeapi.data.network.entities

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)