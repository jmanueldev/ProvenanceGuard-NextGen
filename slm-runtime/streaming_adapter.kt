package com.provenanceguard.slm

fun streamLLM(prompt: String, onToken: (String) -> Unit) {
    // Mock streaming (replace with MLC)
    val words = prompt.split(" ")
    for (w in words) {
        Thread.sleep(50)
        onToken("$w ")
    }
}