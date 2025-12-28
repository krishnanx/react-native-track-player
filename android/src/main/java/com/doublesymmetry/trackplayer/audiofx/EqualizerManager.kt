package com.doublesymmetry.kotlinaudio.audiofx

import android.media.audiofx.Equalizer
import android.util.Log

object EqualizerManager {

    private var equalizer: Equalizer? = null

    fun init(sessionId: Int) {
        if (equalizer != null) return

        try {
            equalizer = Equalizer(0, sessionId).apply {
                enabled = true
            }
            Log.d("EQ", "Equalizer initialized with session $sessionId")
        } catch (e: Exception) {
            Log.e("EQ", "EQ init failed", e)
        }
    }

    fun setBandLevel(band: Short, level: Short) {
        equalizer?.setBandLevel(band, level)
    }

    fun setBass(level: Short) {
        equalizer?.let {
            if (it.numberOfBands > 0) {
                // first band is usually bass
                it.setBandLevel(0, level)
                Log.d("EQ", "Bass band set to $level") // <-- ADD THIS
            }
        }
    }

    fun reset() {
        equalizer?.let {
            for (i in 0 until it.numberOfBands) {
                it.setBandLevel(i.toShort(), 0)
                Log.d("EQ", "it is now reseted") // <-- ADD THIS
            }
        }
    }

    fun release() {
        equalizer?.release()
        equalizer = null
    }
}
