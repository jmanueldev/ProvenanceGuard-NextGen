package com.provenanceguard.slm

import ai.mlc.mlcchat.ChatModule

class MLCEngine {

    private lateinit var chat: ChatModule

    fun init(modelPath: String) {
        chat = ChatModule(modelPath)
    }

    fun generate(prompt: String, onToken: (String) -> Unit) {
        chat.generate(prompt) { token ->
            onToken(token)
        }
    }
}
fun streamGemma(engine: MLCEngine, prompt: String, cb: (String) -> Unit) {
    engine.generate(prompt) { token ->
        cb(token)
    }
}