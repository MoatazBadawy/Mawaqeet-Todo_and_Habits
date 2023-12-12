package com.moataz.mawaqeet.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.moataz.mawaqeet.components.notification.HabitAlertMorningNotification
import com.moataz.mawaqeet.components.notification.HabitAlertNightNotification
import com.suddenh4x.ratingdialog.AppRating
import com.suddenh4x.ratingdialog.preferences.RatingThreshold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
        initView(window)
        initNotification()
        displayAppRating()
        inAppUpdate()
    }

    private fun initView(window: Window): Window {
        // make the status bar white with black icons
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        // make the app support only arabic "Right to left"
        // even if the language of the device on english or others
        ViewCompat.setLayoutDirection(window.decorView, ViewCompat.LAYOUT_DIRECTION_RTL)
        return window
    }

    private fun initNotification() {
        HabitAlertMorningNotification().setupHabitAlertMorningNotification(this)
        HabitAlertNightNotification().setupHabitAlertNightNotification(this)
    }

    private fun displayAppRating() {
        AppRating.Builder(this)
            .setMinimumLaunchTimes(3)
            .setMinimumDays(3)
            .useGoogleInAppReview()
            .setMinimumLaunchTimesToShowAgain(10)
            .setMinimumDaysToShowAgain(7)
            .setRatingThreshold(RatingThreshold.FOUR)
            .showIfMeetsConditions()
    }

    private fun inAppUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                (appUpdateInfo.clientVersionStalenessDays() ?: -1) >= 3 &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    0,
                )
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode != RESULT_OK) {
                Log.e("MY_APP", "Update flow failed! Result code: $resultCode")
                inAppUpdate()
            }
        }
    }
}
