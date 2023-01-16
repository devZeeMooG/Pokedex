package com.zeemoog.testpokeapi.data.network.entities

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)