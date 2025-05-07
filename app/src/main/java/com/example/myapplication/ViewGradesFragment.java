package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewGradesFragment extends Fragment {
    private RecyclerView gradesRecyclerView;
    private GradeWithAssignmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_grades, container, false);

        gradesRecyclerView = view.findViewById(R.id.gradesRecyclerView);
        gradesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences prefs = requireContext().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE);
        int userId = prefs.getInt("userId", -1);
        if (userId == -1) {
            Toast.makeText(getContext(), "No user logged in.", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
            return view;
        }

        AppDatabase db = AppDatabase.getInstance(requireContext());
        User student = db.userDao().getUserById(userId);
        if (student == null || student.getRole() != User.Role.NORMAL || student.getStudentId() == null) {
            Toast.makeText(getContext(), "Invalid user or missing Student ID.", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
            return view;
        }

        String studentId = student.getStudentId();

        db.gradeDao().getGradesWithAssignmentsLive(studentId).observe(getViewLifecycleOwner(), new Observer<List<GradeWithAssignment>>() {
            @Override
            public void onChanged(List<GradeWithAssignment> grades) {
                adapter = new GradeWithAssignmentAdapter(grades);
                gradesRecyclerView.setAdapter(adapter);

                if (grades.isEmpty()) {
                    Toast.makeText(getContext(), "No grades found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}