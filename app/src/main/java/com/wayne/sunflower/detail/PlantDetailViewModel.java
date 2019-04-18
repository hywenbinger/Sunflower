package com.wayne.sunflower.detail;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.data.PlantRepository;
import com.wayne.sunflower.data.Planting;
import com.wayne.sunflower.data.PlantingRepository;
import com.wayne.sunflower.utils.LogUtils;


public class PlantDetailViewModel extends ViewModel {

    private PlantRepository mPlantRepository;
    private PlantingRepository mPlantingRepository;
    private LiveData<Plant> mPlant;
    private String mPlantId;
    private LiveData<Boolean> mIsPlanted;

    public PlantDetailViewModel(PlantRepository plantRepository, PlantingRepository plantingRepository, String plantId) {
        mPlantRepository = plantRepository;
        mPlantingRepository = plantingRepository;
        mPlantId = plantId;
        mPlant = mPlantRepository.getPlant(plantId);
        mIsPlanted = Transformations.map(plantingRepository.getPlanting(plantId), new Function<Planting, Boolean>() {
            @Override
            public Boolean apply(Planting it) {
                return it != null;
            }
        });
    }

    public LiveData<Plant> getPlant(){
        return mPlant;
    }

    public LiveData<Boolean> isPlanted(){
        return mIsPlanted;
    }

    /**
     * 种植植物
     */
    public void addPlantToGarden(){
        mPlantingRepository.insert(new Planting(mPlantId, null, null));
    }
}
