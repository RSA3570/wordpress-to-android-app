package app.wordpress.newsapp.utils

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import org.jsoup.nodes.Document
import java.lang.Exception

class WebUtils {
    companion object{
        fun docToBetterHTML(doc: Document, c: Context): String {
            try {
                doc.select("img[src]").removeAttr("width")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                doc.select("a[href]").removeAttr("style")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                doc.select("div").removeAttr("style")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                doc.select("video").removeAttr("width").removeAttr("height")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                doc.select("iframe").attr("width", "100%")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            var rtl: String
            val currentapiVersion = Build.VERSION.SDK_INT
            val config = c.resources.configuration
            rtl =
                if (currentapiVersion >= Build.VERSION_CODES.JELLY_BEAN_MR1 && config.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                    "direction:LTR; unicode-bidi:embed;"
                } else {
                    ""
                }
            rtl = "direction:LTR; unicode-bidi:embed;"
            val style = "<style>" +
                    "img{" +
                    "max-width: 100%; " +
                    "width: auto; height: auto;" +
                    "}" +
                    "video{" +
                    "max-width: 100%; " +
                    "width: auto; height: auto;" +
                    "}" +
                    "p{" +
                    "max-width: 100%; " +
                    "width:auto; " +
                    "height: auto;" +
                    "}" +
                    "@font-face {" +  // "font-family: 'Currents-Light-Sans';" +
                    "font-style: normal;" +
                    "font-weight: normal;" +  //  "src: local('sans-serif-light'), url('file:///android_asset/fonts/Roboto-Light.ttf') format('truetype');" +
                    "src: url('file:///assets/times_new_roman.ttf') format('truetype');" +
                    "} body p {  " +  //  "font-family: 'Currents-Light-Sans', serif;"+
                    "} body {  " +
                    "max-width: 100% !important;" +  // "font-family: 'Currents-Light-Sans', serif;" +
                    "margin: 0;" +
                    "padding: 0;" +
                    rtl +
                    "}" +
                    "</style>"
            val header = doc.head()
            header.append(style)
            Log.e("rtl-", rtl)
            return doc.toString()
        }
    }
}