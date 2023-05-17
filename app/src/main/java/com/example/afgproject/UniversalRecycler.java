package com.example.afgproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * The universal recycler is a recycler view fragment object that can take in a variety of view holders, which are populated using the ObjectMap class
 */
public class UniversalRecycler extends Fragment {

    protected RecyclerView rv;
    protected RecyclerView.LayoutManager layoutManager;
    protected UniversalAdapter UniversalAdapter;
    protected ArrayList<ObjectMap> dataSource;
    protected int replaceId;
    UniversalAdapter.HolderType holderType;

    /**
     * Enum for determining the layout of the recycler view
     */
    public enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER_HORIZONTAL,
        LINEAR_LAYOUT_MANAGER_Vertical
    }

    /**
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Empty constructor for fragment instantiation/construction
     */
    public UniversalRecycler(){

    }

    /**
     * Create a Universal Recycler object
     * @param replaceId - Id of the view which this fragment replaces
     * @param layoutManagerType - Enum value of the layout view of the recycler
     * @param data - ObjectMap data which is used to populate the RvHolders
     * @param holderType - Type of holder used within this Recycler View
     * @param onItemClickListener - onClick listener determining the commands conducted when a holder is clicked
     */
    public UniversalRecycler(int replaceId, LayoutManagerType layoutManagerType, ArrayList<ObjectMap> data, UniversalAdapter.HolderType holderType, UniversalAdapter.OnItemClickListener onItemClickListener){
        this.replaceId = replaceId;
        dataSource = data;
        switch (layoutManagerType){
            case GRID_LAYOUT_MANAGER:
                layoutManager = new GridLayoutManager(getContext(), 3);
                break;
            case LINEAR_LAYOUT_MANAGER_HORIZONTAL:
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                break;
            case LINEAR_LAYOUT_MANAGER_Vertical:
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                break;
        }
        this.holderType = holderType;
        this.UniversalAdapter = new UniversalAdapter(dataSource, holderType, onItemClickListener);
        UniversalAdapter.setOnItemClickListener(onItemClickListener);
    }

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return View of the Universal Recycler fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_universal_recycler, container, false);
    }

    /**
     * Tie recycler view to fragment, set manager and adapter, which subsequently populates with the specified data
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = requireView().findViewById(R.id.recyclerView);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(UniversalAdapter);
    }

    /**
     * Get the adapter object tied to this recycler view
     * @return adapter object tied to this recycler view
     */
    public UniversalAdapter getAdapter(){
        return UniversalAdapter;
    }
}