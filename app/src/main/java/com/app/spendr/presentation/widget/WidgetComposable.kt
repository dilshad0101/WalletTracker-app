package com.app.spendr.presentation.widget

import android.app.LauncherActivity
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Icon
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.material3.ColorProviders
import androidx.glance.text.FontFamily
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.app.spendr.MainActivity
import com.app.spendr.R


@Composable
fun WidgetComposable(
    size: DpSize,
    spendData: SpendData.Available
){
    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)

    val isDarkMode = (context.resources.configuration.uiMode and
            android.content.res.Configuration.UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES

    Column(
        modifier = GlanceModifier
            .padding(vertical = 13.dp, horizontal = 15.dp)
            .background(ImageProvider(
                if(!isDarkMode) R.drawable.rounded_bg_light else R.drawable.rounded_bg_dark)
            )
            .clickable(
                onClick = actionStartActivity<MainActivity>()
            )



    ) {
        Column {
            Text(
                text = spendData.currency+formatCurrencyCompact(spendData.monthlySpend),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    color = GlanceTheme.colors.onSurface,
                    fontFamily = FontFamily.SansSerif
                    )
            )
            Text(
                text = "Monthly Spend",
                style = TextStyle(
                    color = ColorProvider(Color.Gray),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif

                )
            )
        }
        Spacer(GlanceModifier.height(30.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Today",
                    style = TextStyle(
                        color = ColorProvider(Color.Gray),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = spendData.currency + formatCurrencyCompact(spendData.todaySpend),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = GlanceTheme.colors.onSurface
                        )
                )
            }
            Spacer(modifier = GlanceModifier.width(40.dp))

            Box(
                modifier = GlanceModifier
                    .clickable{}
                    .size(50.dp),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    provider = ImageProvider(
                        Icon.createWithResource(
                            LocalContext.current,
                            R.drawable.round_add_circle_24)),
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurface),
                    contentDescription = null,
                )
            }
        }
    }
}


object MyAppWidgetGlanceColorScheme {

    val colors = ColorProviders(
        light = lightColorScheme(
            onSurface = Color(0xF6161616),
            onSurfaceVariant = Color.Gray
        ),
        dark = darkColorScheme(
            onSurface = Color(0xF6F3F2F6),
            onSurfaceVariant = Color.Gray
        )
    )

}