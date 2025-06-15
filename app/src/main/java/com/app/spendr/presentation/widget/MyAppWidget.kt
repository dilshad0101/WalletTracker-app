package com.app.spendr.presentation.widget

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.util.Log
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.LocalSize
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.layout.Box

class MyAppWidget: GlanceAppWidget() {

    override val sizeMode = SizeMode.Exact
    override val stateDefinition = SpendDataStateDefinition

    override fun onCompositionError(
        context: Context,
        glanceId: GlanceId,
        appWidgetId: Int,
        throwable: Throwable
    ) {
        throwable.printStackTrace()
        Log.d("Widget Error 714",throwable.cause.toString())
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val dataState = currentState<SpendData>()

            GlanceTheme(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                GlanceTheme.colors
            else
                    MyAppWidgetGlanceColorScheme.colors
            ) {
                when(dataState){
                    is SpendData.Available-> {

                        WidgetComposable(
                            size = LocalSize.current,
                            spendData = dataState
                        )
                    }else -> {
                        Box {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


class MyAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()
}





