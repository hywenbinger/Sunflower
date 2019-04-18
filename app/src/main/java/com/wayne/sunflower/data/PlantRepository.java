package com.wayne.sunflower.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class PlantRepository {

    private static PlantRepository instance;
    private PlantDao mPlantDao;

    private PlantRepository(PlantDao gardenPlantingDao) {
        this.mPlantDao = gardenPlantingDao;
    }

    public static PlantRepository getInstance(PlantDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (PlantRepository.class) {
                if (instance == null) {
                    instance = new PlantRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    public LiveData<List<Plant>> getPlants() {
        return this.mPlantDao.getPlants();
    }

    public LiveData<Plant> getPlant(String plantId) {
        return this.mPlantDao.getPlant(plantId);
    }

}
