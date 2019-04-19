package com.wayne.sunflower.utils;

import android.content.Context;

import com.wayne.sunflower.SunflowerDatabase;
import com.wayne.sunflower.data.PlantRepository;
import com.wayne.sunflower.data.PlantingRepository;
import com.wayne.sunflower.detail.PlantDetailViewModelFactory;
import com.wayne.sunflower.garden.PlantingListViewModelFactory;
import com.wayne.sunflower.list.PlantListViewModelFactory;

public class InjectorUtils {

    private static PlantRepository getPlantRepository(Context context){
        return PlantRepository.getInstance(SunflowerDatabase.getInstance(context.getApplicationContext()).getPlantDao());
    }

    private static PlantingRepository getPlantingRepository(Context context){
        return PlantingRepository.getInstance(SunflowerDatabase.getInstance(context.getApplicationContext()).getPlantingDao());
    }

    public static PlantListViewModelFactory providerPlantListViewModelFactory(Context context){
        return new PlantListViewModelFactory(getPlantRepository(context));
    }

    public static PlantDetailViewModelFactory providerPlantDetailViewModelFactory(Context context, String plantId){
        return new PlantDetailViewModelFactory(getPlantRepository(context), getPlantingRepository(context), plantId);
    }

    public static PlantingListViewModelFactory providerGardenPlantingViewModelFactory(Context context){
        return new PlantingListViewModelFactory(getPlantingRepository(context));
    }

}
