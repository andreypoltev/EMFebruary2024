package com.andreypoltev.emfebruary2024.util

import com.andreypoltev.emfebruary2024.R

data class Tag(
    val tag: String,
    val stringResource: Int
)

object Tags {

    val showAll = Tag("show_all", R.string.show_all_tags)
    val face = Tag("face", R.string.face)
    val body = Tag("body", R.string.body)
    val suntan = Tag("suntan", R.string.suntan)
    val mask = Tag("mask", R.string.mask)

}