package com.wayne.sunflower.detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.wayne.sunflower.data.PlantRepository;

public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private PlantRepository mPlantRepository;
    private String mPlantId;

    public PlantDetailViewModelFactory(PlantRepository plantRepository, String plantId) {
        super();
        mPlantRepository = plantRepository;
        mPlantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(mPlantRepository, mPlantId);
    }
}
