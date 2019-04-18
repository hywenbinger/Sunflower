package com.wayne.sunflower.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wayne.sunflower.R;
import com.wayne.sunflower.SunflowerDatabase;
import com.wayne.sunflower.data.Plant;
import com.wayne.sunflower.data.PlantRepository;
import com.wayne.sunflower.data.PlantingRepository;
import com.wayne.sunflower.databinding.FragmentPlantDetailBinding;
import com.wayne.sunflower.detail.PlantDetailViewModel;
import com.wayne.sunflower.detail.PlantDetailViewModelFactory;
import com.wayne.sunflower.utils.InjectorUtils;

/**
 * 植物详情
 */
public class PlantDetailFragment extends Fragment {

    private FragmentPlantDetailBinding mBinding;
    private PlantDetailViewModel mViewModel;
    private String mShareText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentPlantDetailBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addPlantToGarden();
                Snackbar.make(v, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show();
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        final String plantId = bundle.getString("plantId");
        final PlantDetailViewModelFactory factory = InjectorUtils.providerPlantDetailViewModelFactory(getContext(), plantId);
        mViewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);//observing changes of LiveData in this binding
        mViewModel.getPlant().observe(this, new Observer<Plant>() {
            @Override
            public void onChanged(@Nullable Plant plant) {
                mShareText = getString(R.string.share_text_plant, plant.getName());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                        .setText(this.mShareText)
                        .setType("text/plain")
                        .createChooserIntent();
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                startActivity(shareIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
