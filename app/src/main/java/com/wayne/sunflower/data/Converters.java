package com.wayne.sunflower.data;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;

/**
 * Type converters to allow Room to reference complex data types.
 * RoomDatabase除了必须添加@Database注解也可以添加@TypeConverter注解。
 * 用于提供一个把自定义类转化为一个Room能够持久化的已知类型的，
 * 比如我们想持久化日期的实例，可以用如下代码写一个TypeConverter去存储相等的Unix时间戳在数据库中。
 */
public class Converters {

    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
