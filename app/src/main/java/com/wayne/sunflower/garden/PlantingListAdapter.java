package com.wayne.sunflower.garden;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wayne.sunflower.R;
import com.wayne.sunflower.data.PlantAndPlantings;
import com.wayne.sunflower.data.PlantAndPlantingsViewModel;
import com.wayne.sunflower.databinding.ListPlantingItemBinding;
import com.wayne.sunflower.utils.Constants;

import androidx.navigation.Navigation;

public class PlantingListAdapter extends ListAdapter<PlantAndPlantings, PlantingListAdapter.ViewHolder> {

    public PlantingListAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PlantingListAdapter.ViewHolder(ListPlantingItemBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final PlantAndPlantings plantings = getItem(position);
        viewHolder.itemView.setTag(plantings);
        final ItemClickListener itemClickListener = new ItemClickListener(plantings.getPlant().getPlantId());
        viewHolder.bind(itemClickListener, plantings);
    }

    class ItemClickListener implements View.OnClickListener{

        private String plantId;

        public ItemClickListener(String plantId) {
            this.plantId = plantId;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.ACTION_KEY, plantId);
            Navigation.findNavController(v).navigate(R.id.action_garden_fragment_to_plant_detail_fragment, bundle);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ListPlantingItemBinding binding;

        public ViewHolder(@NonNull ListPlantingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(View.OnClickListener listener, PlantAndPlantings plantings) {
            binding.setViewModel(new PlantAndPlantingsViewModel(plantings));
            binding.setClickListener(listener);
            binding.executePendingBindings();
        }
    }

    static DiffUtil.ItemCallback diffCallback = new DiffUtil.ItemCallback<PlantAndPlantings>() {

        @Override
        public boolean areItemsTheSame(@NonNull PlantAndPlantings oldItem,
                                       @NonNull PlantAndPlantings newItem) {
            return oldItem.getPlant().getPlantId().equals(newItem.getPlant().getPlantId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlantAndPlantings oldItem,
                                          @NonNull PlantAndPlantings newItem) {
            return oldItem.equals(newItem);
        }
    };
}
