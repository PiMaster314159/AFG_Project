package com.example.afgproject;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Fragment used for volunteer to edit their settings
 */
public class VolunteerSettings extends Fragment {
    ArrayList<ObjectMap> interestData;
    ArrayList<ObjectMap> skillData;
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
     * Once the holder is updated, update the corresponding data list such that when the holder is rerendered inside the scroll view, it can pull this data from the data list
     * @return command that toggles whether a settings widget holder is selected or not
     * <a href="https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data">How to alert recycler view adapter of data change</a>
     */
    public UniversalAdapter.OnItemClickListener toggleSelectedListener() {
                return myRvHolder -> {
                    ((SettingsRvHolder) myRvHolder).toggleSelected();
                    String holderName = myRvHolder.getName();
                    boolean holderSelected = ((SettingsRvHolder) myRvHolder).getSelected();
                    int holderPosition = myRvHolder.getLayoutPosition();
                    if(interestData.get(holderPosition).getValue("Widget Text").equals(holderName)){
                        interestData.get(holderPosition).changePair("isSelected", holderSelected);
                        System.out.println("Interest selected");
                        interestsLayout.getAdapter().notifyItemChanged(holderPosition);
                    } else { // if(skillData.get(holderPosition).getValue("Widget Text").equals(holderName)){
                        skillData.get(holderPosition).changePair("isSelected", holderSelected);
                        System.out.println("Skill selected");
                        skillsLayout.getAdapter().notifyItemChanged(holderPosition);
                    }
                };
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

        interestsLayout = createInterestsLayout();
        skillsLayout = createSkillsLayout();

        getChildFragmentManager().beginTransaction().replace(R.id.interests_recycler_view, interestsLayout).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.skills_recycler_view, skillsLayout).commit();
        this.zipCodeEdit = view.findViewById(R.id.zip_code_edit);
        this.maxDistEdit = view.findViewById(R.id.max_distance_edit);
        zipCodeEdit.setText(VolunteerData.getZipCode());
        maxDistEdit.setText(String.valueOf(VolunteerData.getMaxDistance()));
    }

    /**
     * Create interest layout which is populated by interest name data from string array
     * Determines whether interest name exists within the user's selected interests, determining whether it is selected to begin with or not
     * Initial/testing interest list from VolunteerMatch user profile configuration page (<a href="https://www.volunteermatch.org/s/auth/personalProfile#">LInk</a>)
     * @return Created UniversalRecycler object for interests
     */
    public UniversalRecycler createInterestsLayout() {
        interestData = new ArrayList<>();
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
     * Initial/testing skill list from VolunteerMatch user profile configuration page (<a href="https://www.volunteermatch.org/s/auth/personalProfile#">Link</a>)
     * @return Created UniversalRecycler object for skills
     */
    public UniversalRecycler createSkillsLayout() {
        skillData = new ArrayList<>();
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
     * <a href="https://stackoverflow.com/questions/5485807/android-pop-up-message">Thread discussing how to edit Alert Dialog objects</a>
     * Check if the provided data is valid. If not, tell the user what is invalid and return false.
     * Used to check if the user can move to the next page.
     * @return whether all provided data is valid
     */
    public boolean validData() throws IOException {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Invalid input")
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {})
                .setIcon(android.R.drawable.ic_dialog_alert);
        if(this.getMaxDistance() <= 0){
            alertDialog.setMessage("Please enter a valid maximum distance.");
            alertDialog.show();
            return false;
        }
        if(!Utils.isValidZipCode(this.getZipCode())){
            alertDialog.setMessage("Please enter a valid zip code.");
            alertDialog.show();
            return false;
        }
        return true;
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
        if(String.valueOf(maxDistEdit.getText()).equals("")) return 0;
        return Double.parseDouble(String.valueOf(maxDistEdit.getText()));
    }

    public ArrayList<ObjectMap> getInterestMap(){
        return interestData;
    }

    public ArrayList<ObjectMap> getSkillMap(){
        return skillData;
    }
}