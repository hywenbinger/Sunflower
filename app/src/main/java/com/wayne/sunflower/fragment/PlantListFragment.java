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

import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.SunflowerDatabase;
import com.wayne.sunflower.databinding.FragmentPlantListBinding;
import com.wayne.sunflower.list.PlantListAdapter;
import com.wayne.sunflower.list.PlantListViewModel;
import com.wayne.sunflower.list.PlantListViewModelFactory;
import com.wayne.sunflower.data.PlantRepository;
import com.wayne.sunflower.utils.LogUtils;

import java.util.List;

/**
 * 植物目录
 */
public class PlantListFragment extends Fragment {

    private FragmentPlantListBinding mBinding;
    private PlantListAdapter mAdapter;
    private PlantListViewModel mViewModel;
    private PlantListViewModelFactory mFactory;
    private PlantRepository mRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentPlantListBinding.inflate(inflater, container, false);
        mAdapter = new PlantListAdapter();
        mBinding.plantListView.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRepository = PlantRepository.getInstance(SunflowerDatabase.getInstance(getContext().getApplicationContext()).getPlantDao());
        mFactory = new PlantListViewModelFactory(mRepository);
        mViewModel = ViewModelProviders.of(this, mFactory).get(PlantListViewModel.class);
        mViewModel.getPlantList().observe(this, new Observer<List<Plant>>() {
            @Override
            public void onChanged(@Nullable List<Plant> plantList) {
                mAdapter.submitList(plantList);
            }
        });
    }
}
