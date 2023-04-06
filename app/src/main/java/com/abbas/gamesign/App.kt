package com.abbas.gamesign

import android.app.Application
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.abbas.gamesign.utils.font.FontChanger
import com.abbas.gamesign.utils.font.FontStyle
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor
import io.github.inflationx.viewpump.ViewPump
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var fontChanger: FontChanger

    override fun onCreate() {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        /**
         * init font
         */
        ViewPump.init(ViewPump.builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): InflateResult {
                val result = chain.proceed(chain.request())
                if (result.view is TextView) {
                    // Do something to result.view()
                    // You have access to result.context() and result.attrs()
                    val textView = result.view as TextView?

                    if (textView!!.typeface == null || !textView.typeface.isBold) {
                        textView.typeface = fontChanger.getFont(FontStyle.MEDIUM)
                    } else {
                        textView.typeface = fontChanger.getFont(FontStyle.BOLD)
                    }

                }
                return result
            }
        }).build())

        super.onCreate()
    }

}