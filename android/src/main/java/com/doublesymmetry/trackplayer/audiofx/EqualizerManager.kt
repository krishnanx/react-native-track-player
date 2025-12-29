package com.doublesymmetry.kotlinaudio.audiofx

import android.media.audiofx.BassBoost
import android.media.audiofx.Equalizer
import android.util.Log

object EqualizerManager {

    private var equalizer: Equalizer? = null
    private var bassBoost: BassBoost? = null
    private var currentSessionId: Int? = null

    /* ---------------- INIT ---------------- */

    fun init(sessionId: Int) {
        if (currentSessionId == sessionId && equalizer != null) return

        release()

        try {
            equalizer = Equalizer(0, sessionId).apply {
                enabled = true
            }

            bassBoost = BassBoost(0, sessionId).apply {
                enabled = true
                setStrength(0)
            }

            currentSessionId = sessionId
            Log.d("EQ", "Initialized for session $sessionId")

        } catch (e: Exception) {
            Log.e("EQ", "EQ init failed", e)
        }
    }

    /* ---------------- ENABLE / DISABLE ---------------- */

    fun setEnabled(enabled: Boolean) {
        equalizer?.enabled = enabled
        bassBoost?.enabled = enabled
        Log.d("EQ", "EQ enabled = $enabled")
    }

    /* ---------------- BAND CONTROL ---------------- */

    fun setBandLevel(band: Short, level: Short) {
        equalizer?.setBandLevel(band, level)
    }

    fun getBandCount(): Short {
        return equalizer?.numberOfBands ?: 0
    }

    fun getBandRange(): ShortArray {
        return equalizer?.bandLevelRange ?: shortArrayOf(-1500, 1500)
    }

    /* ---------------- BASS BOOST ---------------- */

    fun setBass(strength: Short) {
        bassBoost?.setStrength(strength)
        Log.d("EQ", "BassBoost strength = $strength")
    }

    /* ---------------- RESET ---------------- */

    fun reset() {
        equalizer?.let {
            for (i in 0 until it.numberOfBands) {
                it.setBandLevel(i.toShort(), 0)
            }
        }
        bassBoost?.setStrength(0)
        Log.d("EQ", "EQ reset")
    }

    /* ---------------- RELEASE ---------------- */

    fun release() {
        equalizer?.release()
        bassBoost?.release()
        equalizer = null
        bassBoost = null
        currentSessionId = null
        Log.d("EQ", "EQ released")
    }

    fun getBandFrequencies(): IntArray {
        val eq = equalizer ?: return intArrayOf()
        val count = eq.numberOfBands
        val freqs = IntArray(count)
        for (i in 0 until count) {
            freqs[i] = (eq.getCenterFreq(i.toShort()) / 1000).toInt()
        }
        return freqs
    }   

}
