package com.wayne.sunflower;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.wayne.sunflower.data.Converters;
import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.data.PlantDao;
import com.wayne.sunflower.data.Planting;
import com.wayne.sunflower.data.PlantingDao;
import com.wayne.sunflower.utils.Constants;
import com.wayne.sunflower.utils.LogUtils;
import com.wayne.sunflower.worker.PlantDatabaseWorker;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

@Database(entities = {Plant.class, Planting.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class SunflowerDatabase extends RoomDatabase {

    public abstract PlantDao getPlantDao();

    public abstract PlantingDao getPlantingDao();

    private static volatile SunflowerDatabase instance;

    public static SunflowerDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (SunflowerDatabase.class) {
                instance = buildDatabase(context);
            }
        }
        return instance;
    }

    private static SunflowerDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, SunflowerDatabase.class, Constants.DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        LogUtils.i("sunflower db created");
                        WorkManager.getInstance().enqueue(OneTimeWorkRequest.from(PlantDatabaseWorker.class));
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        LogUtils.i("sunflower db opened");
                    }
                })
                .allowMainThreadQueries()
                .build();
    }

}
