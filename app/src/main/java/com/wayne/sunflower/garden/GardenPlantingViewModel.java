package com.wayne.sunflower.garden;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.wayne.sunflower.data.PlantAndPlanting;
import com.wayne.sunflower.data.Planting;
import com.wayne.sunflower.data.PlantingRepository;

import java.util.List;

public class GardenPlantingViewModel extends ViewModel {

    private PlantingRepository mPlantingRepository;
    private LiveData<List<Planting>> mPlantings;
    private LiveData<List<PlantAndPlanting>> mPlantAndPlantings;

    public GardenPlantingViewModel(PlantingRepository plantingRepository) {
        mPlantingRepository = plantingRepository;
        mPlantings = plantingRepository.getPlantings();
        mPlantAndPlantings = plantingRepository.getPlantAndPlantings();
    }

    public LiveData<List<Planting>> getPlantings() {
        return mPlantings;
    }

    public LiveData<List<PlantAndPlanting>> getPlantAndPlantings() {
        return mPlantAndPlantings;
    }
}
