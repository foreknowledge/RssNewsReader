package com.foreknowledge.rssnewsreader.util

object KeywordExtractor {
    fun extract(description: String): List<String> {
        val wordCountMap =  description
            .removeSpecialCharacter()
            .split(" ")
            .filter { it.length > 1 }
            .groupBy { it }
            .mapValues { it.value.size }
            .toSortedMap()
            .toList()
            .sortedByDescending { (_, count) -> count }
            .toMap()

        val keywords = mutableListOf<String>()
        for ((i, word) in wordCountMap.keys.withIndex()) {
            if (i >= 3) break
            keywords.add(word)
        }

        return keywords
    }

    private fun String.removeSpecialCharacter()
            = this.replace("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]".toRegex(), "")
}