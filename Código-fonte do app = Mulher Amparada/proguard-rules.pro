-keep class android.webkit.** { *; }
-keep class android.net.http.** { *; }

-keep class **.MainActivity { *; }

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
