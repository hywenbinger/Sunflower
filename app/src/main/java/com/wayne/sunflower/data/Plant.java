package com.wayne.sunflower.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

@Entity(tableName = "plants")
public class Plant {

    @Ignore
    private final int DEFAULT_WATERING_INTERVAL = 7;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String plantId;

    @NonNull
    private String name;

    @NonNull
    private String description;

    private int growZoneNumber;

    private int wateringInterval;

    @NonNull
    private String imageUrl;

    public Plant(@NonNull String plantId, @NonNull String name, @NonNull String description,
                 int growZoneNumber, int wateringInterval, @NonNull String imageUrl) {
        this.plantId = plantId;
        this.name = name;
        this.description = description;
        this.growZoneNumber = growZoneNumber;
        this.wateringInterval = wateringInterval > 0 ? wateringInterval : DEFAULT_WATERING_INTERVAL;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getPlantId() {
        return plantId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "plantId='" + plantId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", growZoneNumber=" + growZoneNumber +
                ", wateringInterval=" + wateringInterval +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Plant
                && this.plantId.equals(((Plant) obj).plantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantId);
    }

    @Override
    protected Object clone() {
        return new Plant(plantId, name, description, growZoneNumber, wateringInterval, imageUrl);
    }

}
