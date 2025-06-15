package com.app.spendr.presentation.widget

import android.content.Context
import android.util.Log
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.work.*
import java.time.Duration

class WidgetWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    companion object {

        private val uniqueWorkName = WidgetWorker::class.java.simpleName

        fun enqueue(context: Context,
                    instantUpdate: Boolean,
        ) {

            Log.d("Widget","worker")

            val manager = WorkManager.getInstance(context)

            if(instantUpdate){
                val requestBuilder = OneTimeWorkRequestBuilder<WidgetWorker>()
                manager.enqueue(requestBuilder.build())
            }else{
                val requestBuilder = PeriodicWorkRequestBuilder<WidgetWorker>(
                    Duration.ofHours(5)
                )
                manager.enqueueUniquePeriodicWork(
                    uniqueWorkName,
                    ExistingPeriodicWorkPolicy.KEEP,
                    requestBuilder.build()
                )
            }

        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueWorkName)
        }
    }

    override suspend fun doWork(): Result {
        Log.d("Widget","do work")

        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(MyAppWidget::class.java)
        return try {
            // Update state to indicate loading
            setWidgetState(glanceIds, SpendData.Loading)
            // Update state with new data
            setWidgetState(glanceIds, WidgetDataRepository(context).getWidgetSpendData())

            Result.success()
        } catch (e: Exception) {

            setWidgetState(glanceIds, SpendData.Unavailable(e.message.orEmpty()))
            if (runAttemptCount < 10) {
                // Exponential backoff strategy will avoid the request to repeat
                // too fast in case of failures.
                Result.retry()
            } else {
                Result.failure()
            }
        }

    }


    private suspend fun setWidgetState(glanceIds: List<GlanceId>, newState: SpendData) {
        glanceIds.forEach { glanceId ->
            updateAppWidgetState(
                context = context,
                definition = SpendDataStateDefinition,
                glanceId = glanceId,
                updateState = { newState }
            )
        }
        Log.d("Widget","Update: $newState")

        MyAppWidget().updateAll(context)
    }
}