package com.example.afgproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Fragment used for volunteer to edit their settings
 */
public class VolunteerSettings extends Fragment {

    UniversalRecycler interestsLayout;
    UniversalRecycler skillsLayout;

    EditText zipCodeEdit;
    EditText maxDistEdit;

    /**
     * Empty structure for fragment construction/instantiation
     */
    public VolunteerSettings(){

    }

    /**
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**
     * Create an onClick listener command that toggles whether a settings widget holder is selected or not
     * @return command that toggles whether a settings widget holder is selected or not
     */
    public UniversalAdapter.OnItemClickListener toggleSelectedListener() {
        return myRvHolder -> ((SettingsRvHolder) myRvHolder).toggleSelected();
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
     * @return inflated fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_volunteer_settings, container, false);
    }

    /**
     * Create skills and interest recycler lists and replace fragment holders with UniversalRecyclers
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UniversalAdapter.OnItemClickListener toggleSelectedListener = toggleSelectedListener();

        interestsLayout = createInterestsLayout();
        skillsLayout = createSkillsLayout();

        getChildFragmentManager().beginTransaction().replace(R.id.interests_recycler_view, interestsLayout).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.skills_recycler_view, skillsLayout).commit();
        this.zipCodeEdit = (EditText) view.findViewById(R.id.zip_code_edit);
        this.maxDistEdit = (EditText) view.findViewById(R.id.max_distance_edit);
        zipCodeEdit.setText(VolunteerData.getZipCode());
        maxDistEdit.setText(String.valueOf(VolunteerData.getMaxDistance()));
    }

    /**
     * Create interest layout which is populated by interest name data from string array
     * Determines whether interest name exists within the user's selected interests, determining whether it is selected to begin with or not
     * Initial/testing interest list from VolunteerMatch user profile configuration page (https://www.volunteermatch.org/s/auth/personalProfile#)
     * @return Created UniversalRecycler object for interests
     */
    public UniversalRecycler createInterestsLayout() {
        ArrayList<ObjectMap> interestData = new ArrayList<>();
        ArrayList<String> volunteerInterests = VolunteerData.getInterests();
        String[] interestArray = getResources().getStringArray(R.array.volunteer_interest_names);
        for(String interest : interestArray){
            if(volunteerInterests.contains(interest)){
                interestData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{interest, true}));
            } else {
                interestData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{interest, false}));
            }
        }
        return new UniversalRecycler(R.id.interests_recycler_view, UniversalRecycler.LayoutManagerType.GRID_LAYOUT_MANAGER, interestData, UniversalAdapter.HolderType.USER_SETTINGS, toggleSelectedListener());
    }

    /**
     * Create skill layout which is populated by skill name data from string array
     * Determines whether skill name exists within the user's selected skills, determining whether it is selected to begin with or not
     * Initial/testing skill list from VolunteerMatch user profile configuration page (https://www.volunteermatch.org/s/auth/personalProfile#)
     * @return Created UniversalRecycler object for skills
     */
    public UniversalRecycler createSkillsLayout() {
        ArrayList<ObjectMap> skillData = new ArrayList<>();
        ArrayList<String> volunteerSkills = VolunteerData.getSkills();
        String[] skillArray = getResources().getStringArray(R.array.volunteer_skill_names);
        for(String skill : skillArray){
            if(volunteerSkills.contains(skill)){
                skillData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{skill, true}));
            } else {
                skillData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{skill, false}));
            }
        }
        return new UniversalRecycler(R.id.skills_recycler_view, UniversalRecycler.LayoutManagerType.GRID_LAYOUT_MANAGER, skillData, UniversalAdapter.HolderType.USER_SETTINGS, toggleSelectedListener());
    }

    /**
     * @return List of all interest holders in UniversalRecycler
     */
    public ArrayList<UniversalHolder> getAllInterests(){
        return interestsLayout.getAdapter().getHolderList();
    }

    /**
     * @return List of all skill holders in UniversalRecycler
     */
    public ArrayList<UniversalHolder> getAllSkills(){
        return interestsLayout.getAdapter().getHolderList();
    }

    /**
     * @return zip code specified by user
     */
    public String getZipCode(){
        return String.valueOf(zipCodeEdit.getText());
    }

    /**
     * @return max distance for organization/opportunity recommendation by the user
     */
    public double getMaxDistance(){
        return Double.parseDouble(String.valueOf(maxDistEdit.getText()));
    }
}