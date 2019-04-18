package com.wayne.sunflower.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.data.PlantRepository;


public class PlantDetailViewModel extends ViewModel {

    private PlantRepository mPlantRepository;

    private LiveData<Plant> mPlant;

    public PlantDetailViewModel(PlantRepository plantRepository, String plantId) {
        mPlantRepository = plantRepository;
        mPlant = mPlantRepository.getPlant(plantId);
    }

    public LiveData<Plant> getPlant(){
        return mPlant;
    }

}
