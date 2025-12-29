class EqualizerModule(
    reactContext: ReactApplicationContext
) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        const val NAME = "AudioEqualizer"
    }

    override fun getName(): String = NAME

    /* ---------------- ENABLE / DISABLE ---------------- */

    @ReactMethod
    fun setEnabled(enabled: Boolean) {
        EqualizerManager.setEnabled(enabled)
    }

    /* ---------------- BAND CONTROL ---------------- */

    @ReactMethod
    fun setBandLevel(band: Int, level: Int) {
        EqualizerManager.setBandLevel(
            band.toShort(),
            level.toShort()
        )
    }

    @ReactMethod
    fun getBandCount(promise: Promise) {
        promise.resolve(EqualizerManager.getBandCount().toInt())
    }

    @ReactMethod
    fun getBandRange(promise: Promise) {
        val range = EqualizerManager.getBandRange()
        val arr = Arguments.createArray()
        arr.pushInt(range[0].toInt())
        arr.pushInt(range[1].toInt())
        promise.resolve(arr)
    }

    /* ---------------- BASS ---------------- */

    @ReactMethod
    fun setBass(level: Int) {
        val strength = (level * 10).coerceIn(0, 1000)
        EqualizerManager.setBass(strength.toShort())
    }

    /* ---------------- RESET ---------------- */

    @ReactMethod
    fun reset() {
        EqualizerManager.reset()
    }
}
