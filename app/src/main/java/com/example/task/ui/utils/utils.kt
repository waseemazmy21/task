package com.example.task.ui.utils

const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

val phone_regex: Map<String, String> = mapOf(
    "+20" to "^(10|11|12|15)\\d{8}\$",
    "+44" to "^(07\\d{9}|01\\d{9,10}|02\\d{9}|03\\d{9})\$"
)


fun validateEmail(email: String): Boolean {
    return email.matches(EMAIL_REGEX.toRegex())
}