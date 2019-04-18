package com.wayne.sunflower.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface PlantingDao {

    @Query("SELECT * FROM plantings")
    LiveData<List<Planting>> getPlantings();

    @Query("SELECT * FROM plantings WHERE planting_id = :plantingId")
    LiveData<Planting> getGardenPlanting(long plantingId);

    @Query("SELECT * FROM plantings WHERE plant_id = :plantId")
    LiveData<Planting> getGardenPlanting(String plantId);

    @Insert
    long insert(@NonNull Planting planting);

}
