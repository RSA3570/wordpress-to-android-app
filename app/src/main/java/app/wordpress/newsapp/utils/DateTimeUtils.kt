package app.wordpress.newsapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {
    companion object{
        fun getFormattedDate(oldDate: String?): String? {
            try {
                val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse(oldDate)
                val dt1 = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                return dt1.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return null
        }
    }
}