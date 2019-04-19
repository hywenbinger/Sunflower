package com.wayne.sunflower.garden;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wayne.sunflower.data.PlantAndPlantings;
import com.wayne.sunflower.data.Planting;
import com.wayne.sunflower.data.PlantingRepository;

import java.util.ArrayList;
import java.util.List;

public class PlantingListViewModel extends ViewModel {

    private LiveData<List<Planting>> mPlantings;
    private LiveData<List<PlantAndPlantings>> mPlantAndPlantings;

    public PlantingListViewModel(PlantingRepository plantingRepository) {
        mPlantings = plantingRepository.getPlantings();
        mPlantAndPlantings = plantingRepository.getPlantAndPlantings();
        mPlantAndPlantings = Transformations.map(plantingRepository.getPlantAndPlantings(),
                new Function<List<PlantAndPlantings>, List<PlantAndPlantings>>() {
            @Override
            public List<PlantAndPlantings> apply(List<PlantAndPlantings> items) {
                List<PlantAndPlantings> removeItems = new ArrayList<>();
                for (PlantAndPlantings item : items) {
                    if (item.getPlantings().isEmpty()) {
                        removeItems.add(item);
                    }
                }
                items.removeAll(removeItems);
                return items;
            }
        });
    }

    public LiveData<List<Planting>> getPlantings() {
        return mPlantings;
    }

    public LiveData<List<PlantAndPlantings>> getPlantAndPlantings() {
        return mPlantAndPlantings;
    }
}
