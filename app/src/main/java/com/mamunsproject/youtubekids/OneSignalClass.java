package com.mamunsproject.youtubekids;

import android.app.Application;

import com.onesignal.OneSignal;

public class OneSignalClass extends Application {


        private static final String ONESIGNAL_APP_ID = "3b8bd0fd-1376-4471-9e8d-113c41d5d06e";

        @Override
        public void onCreate() {
            super.onCreate();

            // Enable verbose OneSignal logging to debug issues if needed.
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

            // OneSignal Initialization
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ONESIGNAL_APP_ID);

        }
}
