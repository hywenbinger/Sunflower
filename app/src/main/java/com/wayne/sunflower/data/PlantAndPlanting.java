package com.wayne.sunflower.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class PlantAndPlanting {

    @Embedded
    private Plant plant;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    private List<Planting> plantings;


    public PlantAndPlanting() {
        this.plantings = new ArrayList<>();
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public List<Planting> getPlantings() {
        return plantings;
    }

    public void setPlantings(List<Planting> plantings) {
        this.plantings = plantings;
    }
}
