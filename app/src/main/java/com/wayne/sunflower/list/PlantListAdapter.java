package com.wayne.sunflower.list;

import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.wayne.sunflower.R;
import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.databinding.ListPlantItemBinding;
import com.wayne.sunflower.utils.Constants;
import com.wayne.sunflower.utils.LogUtils;

import androidx.navigation.Navigation;

public class PlantListAdapter extends ListAdapter<Plant, PlantListAdapter.ViewHolder> {

    public PlantListAdapter(){
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(ListPlantItemBinding.inflate(
                LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Plant plant = getItem(i);
        final ItemClickListener itemClickListener = new ItemClickListener(plant.getPlantId());
        viewHolder.bind(plant, itemClickListener);
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
            Navigation.findNavController(v).navigate(R.id.action_plant_list_fragment_to_plant_detail_fragment, bundle);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ListPlantItemBinding binding;

        public ViewHolder(@NonNull ListPlantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Plant item, View.OnClickListener listener) {
            binding.setPlant(item);
            binding.setClickListener(listener);
            binding.executePendingBindings();
        }
    }

    static DiffUtil.ItemCallback diffCallback = new DiffUtil.ItemCallback<Plant>() {

        @Override
        public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return oldItem.getPlantId().equals(newItem.getPlantId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return oldItem == newItem;
        }
    };

}
