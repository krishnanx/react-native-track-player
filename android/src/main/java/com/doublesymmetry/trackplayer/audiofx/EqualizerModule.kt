package com.doublesymmetry.trackplayer.audiofx

import com.facebook.react.bridge.*
import com.doublesymmetry.kotlinaudio.audiofx.EqualizerManager

class EqualizerModule(
    reactContext: ReactApplicationContext
) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        const val NAME = "AudioEqualizer"
    }

    override fun getName(): String = NAME

    @ReactMethod
    fun setBass(level: Int) {
        EqualizerManager.setBass(level.toShort())
    }

    @ReactMethod
    fun reset() {
        EqualizerManager.release()
    }
}
