package com.sosauce.nekobites.utils

import android.content.Context

val Context.appVersion
    get() = packageManager.getPackageInfo(packageName, 0).versionName