package com.wayne.sunflower.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Objects;

@Entity(tableName = "plantings",
        foreignKeys = {@ForeignKey(entity = Plant.class, parentColumns = {"id"}, childColumns = {"plant_id"})},
        indices = {@Index("plant_id")})
public class Planting {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "planting_id")
    private long plantingId = 0L;

    @ColumnInfo(name = "plant_id")
    private String plantId;

    @ColumnInfo(name = "plant_date")
    private Calendar plantDate;

    @ColumnInfo(name = "last_watering_date")
    private Calendar lastWateringDate;

    public Planting(String plantId, Calendar plantDate, Calendar lastWateringDate) {
        this.plantId = plantId;
        this.plantDate = plantDate == null ? Calendar.getInstance() : plantDate;
        this.lastWateringDate = lastWateringDate == null ? Calendar.getInstance() : lastWateringDate;
    }

    public String getPlantId() {
        return plantId;
    }

    public Calendar getPlantDate() {
        return plantDate;
    }

    public Calendar getLastWateringDate() {
        return lastWateringDate;
    }

    public long getPlantingId() {
        return plantingId;
    }

    public void setPlantingId(long plantingId) {
        this.plantingId = plantingId;
    }

    @Override
    public String toString() {
        return "Planting{" +
                "plantingId=" + plantingId +
                ", plantId='" + plantId + '\'' +
                ", plantDate=" + plantDate +
                ", lastWateringDate=" + lastWateringDate +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Planting target = (Planting) obj;

        return this.plantId.equals(target.plantId)
                && this.plantDate.equals(this.plantDate)
                && this.lastWateringDate.equals(this.lastWateringDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantingId);
    }

    @Override
    protected Object clone() {
        return new Planting(plantId, plantDate, lastWateringDate);
    }

}
