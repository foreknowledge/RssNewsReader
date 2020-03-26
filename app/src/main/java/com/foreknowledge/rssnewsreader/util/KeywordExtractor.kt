package com.foreknowledge.rssnewsreader.util

object KeywordExtractor {
    fun extract(description: String): List<String> =
        description
            .removeSpecialCharacter()
            .split(" ")
            .filter { it.length > 1 }
            .groupBy { it }
            .mapValues { it.value.size }
            .toSortedMap()
            .toList()
            .sortedByDescending { (_, count) -> count }
            .take(3)
            .toMap()
            .keys
            .toList()

    private fun String.removeSpecialCharacter() =
        this.replace("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]".toRegex(), "")
}