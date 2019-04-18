package com.wayne.sunflower.list;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.wayne.sunflower.data.PlantRepository;

public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private PlantRepository mPlantRepository;

    public PlantListViewModelFactory(@NonNull PlantRepository repository) {
        super();
        this.mPlantRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantListViewModel(mPlantRepository);
    }
}
