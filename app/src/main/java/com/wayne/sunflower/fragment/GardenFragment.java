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

import com.wayne.sunflower.R;
import com.wayne.sunflower.data.PlantAndPlanting;
import com.wayne.sunflower.data.Planting;
import com.wayne.sunflower.databinding.FragmentGardenBinding;
import com.wayne.sunflower.garden.GardenPlantingViewModel;
import com.wayne.sunflower.garden.GardenPlantingViewModelFactory;
import com.wayne.sunflower.utils.InjectorUtils;
import com.wayne.sunflower.utils.LogUtils;

import java.util.List;

/**
 * 花园
 */
public class GardenFragment extends Fragment {

    private FragmentGardenBinding mBinding;
    private GardenPlantingViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentGardenBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final GardenPlantingViewModelFactory factory = InjectorUtils.providerGardenPlantingViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(GardenPlantingViewModel.class);
        mViewModel.getPlantings().observe(this, new Observer<List<Planting>>() {
            @Override
            public void onChanged(@Nullable List<Planting> plantings) {
                LogUtils.i("----------------"+plantings.size());
                mBinding.setHasPlantings(plantings != null && !plantings.isEmpty());
            }
        });
        mViewModel.getPlantAndPlantings().observe(this, new Observer<List<PlantAndPlanting>>() {
            @Override
            public void onChanged(@Nullable List<PlantAndPlanting> plantAndPlantings) {
                //TODO
            }
        });
    }
}
