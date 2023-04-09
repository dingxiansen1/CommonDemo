package com.dd.basiccompose.theme

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.dd.common.utils.JsonUtils
import kotlinx.serialization.SerializationException
import java.io.InputStream
import java.io.OutputStream

const val Theme_State = "Theme_State"

val Context.themeState by dataStore(Theme_State, serializer = ThemeStateSerializer)

object ThemeStateSerializer : Serializer<ThemeState> {
    override val defaultValue = ThemeState()

    override suspend fun readFrom(input: InputStream): ThemeState {
        try {
            return JsonUtils.JSON.decodeFromString(
                ThemeState.serializer(), input.readBytes().decodeToString()
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException("Unable to read UserPrefs", serialization)
        }
    }

    override suspend fun writeTo(t: ThemeState, output: OutputStream) {
        output.write(JsonUtils.JSON.encodeToString(ThemeState.serializer(), t).encodeToByteArray())
    }
}