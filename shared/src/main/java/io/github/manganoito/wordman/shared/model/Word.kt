package io.github.manganoito.wordman.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey
    val id: Int,
    val value: String,
)
