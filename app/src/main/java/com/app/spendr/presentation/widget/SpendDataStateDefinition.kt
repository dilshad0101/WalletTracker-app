package com.app.spendr.presentation.widget

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.io.OutputStream

object SpendDataStateDefinition : GlanceStateDefinition<SpendData> {

    private const val DATA_STORE_FILENAME = "spendData"

    private val Context.datastore by dataStore(DATA_STORE_FILENAME, SpendDataSerializer)

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<SpendData> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }

    object SpendDataSerializer : Serializer<SpendData> {
        override val defaultValue = SpendData.Unavailable("no place found")

        override suspend fun readFrom(input: InputStream): SpendData = try {
            Json.decodeFromString(
                SpendData.serializer(),
                input.readBytes().decodeToString()
            )

        } catch (exception: Error) {
            throw CorruptionException("Could not read data: ${exception.message}")
        }

        override suspend fun writeTo(t: SpendData, output: OutputStream) {
            output.use {
                it.write(
                    Json.encodeToString(SpendData.serializer(), t).encodeToByteArray()
                )
            }
        }
    }
}