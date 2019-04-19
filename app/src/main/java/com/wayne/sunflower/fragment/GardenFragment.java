package com.wayne.sunflower.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wayne.sunflower.data.PlantAndPlantings;
import com.wayne.sunflower.data.Planting;
import com.wayne.sunflower.databinding.FragmentGardenBinding;
import com.wayne.sunflower.garden.PlantingListAdapter;
import com.wayne.sunflower.garden.PlantingListViewModel;
import com.wayne.sunflower.garden.PlantingListViewModelFactory;
import com.wayne.sunflower.utils.InjectorUtils;

import java.util.List;

/**
 * 花园
 */
public class GardenFragment extends Fragment {

    private FragmentGardenBinding mBinding;
    private PlantingListViewModel mViewModel;
    private PlantingListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentGardenBinding.inflate(inflater, container, false);
        mAdapter = new PlantingListAdapter();
        mBinding.plantListView.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final PlantingListViewModelFactory factory = InjectorUtils.providerGardenPlantingViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(PlantingListViewModel.class);
        mViewModel.getPlantings().observe(this, new Observer<List<Planting>>() {
            @Override
            public void onChanged(@Nullable List<Planting> plantings) {
                mBinding.setHasPlantings(plantings != null && !plantings.isEmpty());
            }
        });
        mViewModel.getPlantAndPlantings().observe(this, new Observer<List<PlantAndPlantings>>() {
            @Override
            public void onChanged(@Nullable List<PlantAndPlantings> plantAndPlantings) {
                if (plantAndPlantings != null && !plantAndPlantings.isEmpty()) {
                    mAdapter.submitList(plantAndPlantings);
                }
            }
        });
    }
}
