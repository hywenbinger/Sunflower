package com.wayne.sunflower.worker;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.SunflowerDatabase;
import com.wayne.sunflower.utils.Constants;
import com.wayne.sunflower.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * 获取植物目录信息，数据库创建时获取
 */
public class PlantDatabaseWorker extends Worker {

    public PlantDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream input = getApplicationContext().getAssets().open(Constants.PLANT_DATA_FILENAME);
            JsonReader reader = new JsonReader(new InputStreamReader(input));
            Type plantType = new TypeToken<List<Plant>>(){}.getType();
            List<Plant> plants = new Gson().fromJson(reader, plantType);
            insertDB(plants);
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure();
        }
    }

    private void insertDB(List<Plant> plants){
        SunflowerDatabase database = SunflowerDatabase.getInstance(getApplicationContext());
        database.getPlantDao().insertPlants(plants);
    }

}
