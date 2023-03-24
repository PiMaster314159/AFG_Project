package com.example.afgproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class organizationCreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_create_event);

        //fromHours dropdown
        Spinner fromHoursDropdownSpinner=findViewById(R.id.fromHoursDropdown);
        ArrayAdapter<CharSequence> fromHoursAdapter =ArrayAdapter.createFromResource(this, R.array.hoursDropdown, android.R.layout.simple_spinner_item);
        fromHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fromHoursDropdownSpinner.setAdapter(fromHoursAdapter);

        //fromMinutes dropdown
        Spinner fromMinutesDropdownSpinner=findViewById(R.id.fromMinutesDropdown);
        ArrayAdapter<CharSequence> fromMinutesAdapter =ArrayAdapter.createFromResource(this, R.array.minutesDropdown, android.R.layout.simple_spinner_item);
        fromMinutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fromMinutesDropdownSpinner.setAdapter(fromMinutesAdapter);

        //fromAmPm dropdown
        Spinner fromAmPmDropdownSpinner=findViewById(R.id.fromAmPmDropdown);
        ArrayAdapter<CharSequence> fromAmPmAdapter =ArrayAdapter.createFromResource(this, R.array.AmPmDropdown, android.R.layout.simple_spinner_item);
        fromAmPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        fromAmPmDropdownSpinner.setAdapter(fromAmPmAdapter);

        //toHours dropdown
        Spinner toHoursDropdownSpinner=findViewById(R.id.toHoursDropdown);
        ArrayAdapter<CharSequence> toHoursAdapter =ArrayAdapter.createFromResource(this, R.array.hoursDropdown, android.R.layout.simple_spinner_item);
        toHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        toHoursDropdownSpinner.setAdapter(toHoursAdapter);

        //toMinutes dropdown
        Spinner toMinutesDropdownSpinner=findViewById(R.id.toMinutesDropdown);
        ArrayAdapter<CharSequence> toMinutesAdapter =ArrayAdapter.createFromResource(this, R.array.minutesDropdown, android.R.layout.simple_spinner_item);
        toMinutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        toMinutesDropdownSpinner.setAdapter(toMinutesAdapter);

        //toAmPm dropdown
        Spinner toAmPmDropdownSpinner=findViewById(R.id.toAmPmDropdown);
        ArrayAdapter<CharSequence> toAmPmAdapter =ArrayAdapter.createFromResource(this, R.array.AmPmDropdown, android.R.layout.simple_spinner_item);
        toAmPmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        toAmPmDropdownSpinner.setAdapter(toAmPmAdapter);

       //skills required dropdown
        Spinner skillsRequiredDropdownSpinner=findViewById(R.id.skillsRequiredDropdown);
        ArrayAdapter<CharSequence> skillsRequiredAdapter =ArrayAdapter.createFromResource(this, R.array.skillsRequiredDropdown, android.R.layout.simple_spinner_item);
        skillsRequiredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        skillsRequiredDropdownSpinner.setAdapter(skillsRequiredAdapter);

        //interest fields dropdown
        Spinner interestFieldsDropdownSpinner=findViewById(R.id.interestFieldsDropdown);
        ArrayAdapter<CharSequence> interestFieldsAdapter =ArrayAdapter.createFromResource(this, R.array.interestFieldsDropdown, android.R.layout.simple_spinner_item);
        interestFieldsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
       interestFieldsDropdownSpinner.setAdapter(interestFieldsAdapter);

        //event type dropdown
        Spinner eventTypeDropdownSpinner=findViewById(R.id.eventTypeDropdown);
        ArrayAdapter<CharSequence> eventTypeAdapter =ArrayAdapter.createFromResource(this, R.array.eventTypeDropdown, android.R.layout.simple_spinner_item);
       eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        eventTypeDropdownSpinner.setAdapter(eventTypeAdapter);
    }




}