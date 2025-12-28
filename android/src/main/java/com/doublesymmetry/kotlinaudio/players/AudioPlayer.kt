package com.doublesymmetry.kotlinaudio.players

import android.content.Context
import com.doublesymmetry.kotlinaudio.models.BufferConfig
import com.doublesymmetry.kotlinaudio.models.CacheConfig
import com.doublesymmetry.kotlinaudio.models.PlayerConfig
import com.doublesymmetry.kotlinaudio.models.PlayerOptions
import androidx.media3.exoplayer.analytics.AnalyticsListener
import android.util.Log
import com.doublesymmetry.kotlinaudio.audiofx.EqualizerManager

open class AudioPlayer(context: Context, playerConfig: PlayerOptions = PlayerOptions()): BaseAudioPlayer(context, playerConfig)