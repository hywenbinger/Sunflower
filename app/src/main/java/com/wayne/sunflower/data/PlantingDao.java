package com.wayne.sunflower.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface PlantingDao {

    @Query("SELECT * FROM plantings")
    LiveData<List<Planting>> getPlantings();

    @Query("SELECT * FROM plantings WHERE planting_id = :plantingId")
    LiveData<Planting> getPlanting(long plantingId);

    @Query("SELECT * FROM plantings WHERE plant_id = :plantId")
    LiveData<Planting> getPlanting(String plantId);

    /**
     * This query will tell Room to query both the [Plant] and [GardenPlanting] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM plants")
    LiveData<List<PlantAndPlantings>> getPlantAndPlantings();

    @Insert
    long insert(@NonNull Planting planting);

}
