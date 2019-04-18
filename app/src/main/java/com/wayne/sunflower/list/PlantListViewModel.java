package com.wayne.sunflower.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.data.PlantRepository;

import java.util.List;

public class PlantListViewModel extends ViewModel {

    private PlantRepository mPlantRepository;

    private LiveData<List<Plant>> mPlantList;

    public PlantListViewModel(PlantRepository plantRepository) {
        mPlantRepository = plantRepository;
        mPlantList = mPlantRepository.getPlants();
    }

    public LiveData<List<Plant>> getPlantList(){
        if (mPlantList == null) {
            mPlantList = new MutableLiveData<>();
        }
        return mPlantList;
    }

}
