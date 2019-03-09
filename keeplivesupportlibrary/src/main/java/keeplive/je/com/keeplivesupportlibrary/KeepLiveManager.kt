package keeplive.je.com.keeplivesupportlibrary

import android.content.Context
import android.content.Intent
import android.os.Build
import keeplive.je.com.keeplivesupportlibrary.two.JobHandlerService
import keeplive.je.com.keeplivesupportlibrary.two.LocalService
import keeplive.je.com.keeplivesupportlibrary.two.RemoteService


object KeepLiveManager {

    fun init(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            openJobService(context)
        } else {
            openTwoService(context)
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            context.startService(Intent(context, NotifyJavaService::class.java))
        }

    }

    private fun openTwoService(context: Context) {
        context.startService(Intent(context, LocalService::class.java))
        context.startService(Intent(context, RemoteService::class.java))
    }

    private fun openJobService(context: Context) {

        val intent = Intent()
        intent.setClass(context, JobHandlerService::class.java)
        context.startService(intent)

    }

}