package com.example.servicenovigrad;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class employee_edit_working_hours extends AppCompatActivity {
    private FirebaseUser current_user;
    private DatabaseReference Ref;
    private static employeeAccount current_user_object;
    private Button discard;
    private Button update;
    private TextView startTime1, startTime2, startTime3, endTime1, endTime2, endTime3;
    private int startTime1_hour, startTime1_min, startTime2_hour, startTime2_min, startTime3_hour, startTime3_min;
    private int endTime1_hour, endTime1_min, endTime2_hour, endTime2_min, endTime3_hour, endTime3_min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_working_hours);
        startTime1 = findViewById(R.id.edit_start_time_1);
        startTime2 = findViewById(R.id.edit_start_time_2);
        startTime3 = findViewById(R.id.edit_start_time_3);

        endTime1 = findViewById(R.id.edit_end_time_1);
        endTime2 = findViewById(R.id.edit_end_time_2);
        endTime3 = findViewById(R.id.edit_end_time_3);
        getUser();
        startTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        employee_edit_working_hours.this,

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                startTime1_hour = hour;
                                startTime1_min = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0, startTime1_hour, startTime1_min);

                                startTime1.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );

                timePickerDialog.updateTime(startTime1_hour, startTime1_min);
                timePickerDialog.show();
            }
        });

        startTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        employee_edit_working_hours.this,

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                startTime2_hour = hour;
                                startTime2_min = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0, startTime2_hour, startTime2_min);

                                startTime2.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(startTime2_hour, startTime2_min);
                timePickerDialog.show();
            }
        });

        startTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        employee_edit_working_hours.this,

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                startTime3_hour = hour;
                                startTime3_min = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0, startTime3_hour, startTime3_min);

                                startTime3.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(startTime3_hour, startTime3_min);
                timePickerDialog.show();
            }
        });

        endTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        employee_edit_working_hours.this,

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                endTime1_hour = hour;
                                endTime1_min = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0, endTime1_hour, endTime1_min);

                                endTime1.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(endTime1_hour, endTime1_min);
                timePickerDialog.show();
            }
        });

        endTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        employee_edit_working_hours.this,

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                endTime2_hour = hour;
                                endTime2_min = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0, endTime2_hour, endTime2_min);

                                endTime2.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(endTime2_hour, endTime2_min);
                timePickerDialog.show();
            }
        });

        endTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        employee_edit_working_hours.this,

                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                endTime3_hour = hour;
                                endTime3_min = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0, endTime3_hour, endTime3_min);

                                endTime3.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(endTime3_hour, endTime3_min);
                timePickerDialog.show();
            }
        });
        discard = (Button)findViewById(R.id.edit_cancel);
        discard.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           finish();
                                           startActivity(new Intent(getApplicationContext(), EmployeHome.class));
                                       }
                                   }
        );
        update = (Button)findViewById(R.id.edit_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String branchstart1 = startTime1.getText().toString();
                final String branchend1 = endTime1.getText().toString();
                final String branchstart2 = startTime2.getText().toString();
                final String branchend2 = endTime2.getText().toString();
                final String branchstart3 = startTime3.getText().toString();
                final String branchend3 = endTime3.getText().toString();
                if(branchstart1.equals("Edit time")||branchstart2.equals("Edit time")||branchstart3.equals("Edit time")||branchend1.equals("Edit time")||branchend2.equals("Edit time")||branchend3.equals("Edit time")){
                    Toast.makeText(employee_edit_working_hours.this, "Working hours incomplete", Toast.LENGTH_LONG).show();
                } else {
                    current_user_object.setStart1(branchstart1);
                    current_user_object.setEnd1(branchend1);
                    current_user_object.setStart2(branchstart2);
                    current_user_object.setEnd2(branchend2);
                    current_user_object.setStart3(branchstart3);
                    current_user_object.setEnd3(branchend3);
                    Ref.setValue(current_user_object);
                    Toast.makeText(employee_edit_working_hours.this, "Working hours updated successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void getUser(){
        current_user = FirebaseAuth.getInstance().getCurrentUser();
        Ref = FirebaseDatabase.getInstance().getReference("MyUsers").child(current_user.getUid());
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                current_user_object = snapshot.getValue(employeeAccount.class);
                startTime1.setText(current_user_object.getStart1());
                endTime1.setText(current_user_object.getEnd1());
                startTime2.setText(current_user_object.getStart2());
                endTime2.setText(current_user_object.getEnd2());
                startTime3.setText(current_user_object.getStart3());
                endTime3.setText(current_user_object.getEnd3());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

