package com.wayne.sunflower.data;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PlantAndPlantingsViewModel extends ViewModel {

    public ObservableField<String> waterDateString;
    public ObservableInt wateringInterval;
    public ObservableField<String> imageUrl;
    public ObservableField<String> plantName;
    public ObservableField<String> plantDateString;

    public PlantAndPlantingsViewModel(@NonNull PlantAndPlantings plantings) {
        final Plant plant = plantings.getPlant();
        final Planting gardenPlanting = plantings.getPlantings().get(0);
        final DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        this.waterDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getLastWateringDate().getTime()));
        this.wateringInterval = new ObservableInt(plant.getWateringInterval());
        this.imageUrl = new ObservableField<>(plant.getImageUrl());
        this.plantName = new ObservableField<>(plant.getName());
        this.plantDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getPlantDate().getTime()));
    }

}
