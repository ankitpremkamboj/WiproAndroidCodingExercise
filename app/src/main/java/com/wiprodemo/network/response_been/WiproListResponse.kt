package com.wiprodemo.network.response_been

data class WiproListResponse(
    val rows: List<WiproListRow>,
    val title: String
)

data class WiproListRow(
    val description: String,
    val imageHref: String,
    val title: String
)