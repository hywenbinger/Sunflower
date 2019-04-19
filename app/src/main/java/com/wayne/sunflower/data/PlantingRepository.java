package com.wayne.sunflower.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class PlantingRepository {

    private static PlantingRepository instance;
    private PlantingDao mPlantingDao;

    private PlantingRepository(PlantingDao plantingDao) {
        this.mPlantingDao = plantingDao;
    }

    public static PlantingRepository getInstance(PlantingDao plantingDao) {
        if (instance == null) {
            synchronized (PlantingRepository.class) {
                if (instance == null) {
                    instance = new PlantingRepository(plantingDao);
                }
            }
        }
        return instance;
    }

    public long insert(Planting planting){
        return mPlantingDao.insert(planting);
    }

    public LiveData<Planting> getPlanting(long plantingId){
        return mPlantingDao.getPlanting(plantingId);
    }

    public LiveData<Planting> getPlanting(String plantId){
        return mPlantingDao.getPlanting(plantId);
    }

    public LiveData<List<Planting>> getPlantings(){
        return mPlantingDao.getPlantings();
    }

    public LiveData<List<PlantAndPlantings>> getPlantAndPlantings(){
        return mPlantingDao.getPlantAndPlantings();
    }

}
