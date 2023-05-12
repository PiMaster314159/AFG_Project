package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class VolunteerPreferences extends AppCompatActivity {
    RecyclerTest interestsLayout;
    RecyclerTest skillsLayout;

    EditText zipCodeEdit;
    EditText maxDistEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);

        MyAdapter.OnItemClickListener toggleSelectedListener = toggleSelectedListener();

        interestsLayout = interestsLayout();
        skillsLayout = skillsLayout();

        getSupportFragmentManager().beginTransaction().replace(R.id.interests_recycler_view, interestsLayout).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.skills_recycler_view, skillsLayout).commit();
        this.zipCodeEdit = (EditText) findViewById(R.id.zip_code_edit);
        this.maxDistEdit = (EditText) findViewById(R.id.max_distance_edit);
        zipCodeEdit.setText(VolunteerSharedData.getZipCode());
        maxDistEdit.setText(String.valueOf(VolunteerSharedData.getMaxDistance()));

        Button b = (Button) findViewById(R.id.button_test);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ok");
                saveData();
            }
        });
    }

    public MyAdapter.OnItemClickListener toggleSelectedListener() {
        return myRvHolder -> ((UserPreferenceHolder) myRvHolder).toggleSelected();
    }

    public RecyclerTest interestsLayout() {
        ArrayList<ObjectMap> interestData = new ArrayList<>();
        ArrayList<String> volunteerInterests = VolunteerSharedData.getInterests();
        String[] interestArray = getResources().getStringArray(R.array.volunteer_interest_names);
        for(String interest : interestArray){
            if(volunteerInterests.contains(interest)){
                interestData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{interest, true}));
            } else {
                interestData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{interest, false}));
            }
        }
        return new RecyclerTest(R.id.interests_recycler_view, RecyclerTest.LayoutManagerType.GRID_LAYOUT_MANAGER, interestData, MyAdapter.HolderType.USER_SETTINGS, toggleSelectedListener());
    }

    public RecyclerTest skillsLayout() {
        ArrayList<ObjectMap> skillData = new ArrayList<>();
        ArrayList<String> volunteerSkills = VolunteerSharedData.getSkills();
        String[] skillArray = getResources().getStringArray(R.array.volunteer_skill_names);
        for(String skill : skillArray){
            if(volunteerSkills.contains(skill)){
                skillData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{skill, true}));
            } else {
                skillData.add(new ObjectMap(new String[]{"Widget Text", "isSelected"}, new Object[]{skill, false}));
            }
        }
        return new RecyclerTest(R.id.skills_recycler_view, RecyclerTest.LayoutManagerType.GRID_LAYOUT_MANAGER, skillData, MyAdapter.HolderType.USER_SETTINGS, toggleSelectedListener());
    }

    public void saveData(){
        ArrayList<MyRvHolder> totalInterests = interestsLayout.getAdapter().getHolderList();
        ArrayList<String> selectedInterests = new ArrayList<>();
        for(MyRvHolder holder : totalInterests)
            if(((UserPreferenceHolder) holder).getSelected())
                selectedInterests.add(holder.getName());
        VolunteerSharedData.putInterests(selectedInterests);

        ArrayList<MyRvHolder> totalSkills = skillsLayout.getAdapter().getHolderList();
        ArrayList<String> selectedSkills = new ArrayList<>();
        for(MyRvHolder holder : totalSkills)
            if(((UserPreferenceHolder) holder).getSelected())
                selectedSkills.add(holder.getName());
        VolunteerSharedData.putSkills(selectedSkills);

        VolunteerSharedData.putZipCode(String.valueOf(zipCodeEdit.getText()));
        VolunteerSharedData.putMaxDistance(Double.parseDouble(String.valueOf(maxDistEdit.getText())));
    }
}