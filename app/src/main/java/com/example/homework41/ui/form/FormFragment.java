package com.example.homework41.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homework41.R;
import com.example.homework41.databinding.FragmentFormBinding;
import com.example.homework41.ui.App;

public class FormFragment extends Fragment {
    private FragmentFormBinding binding;
    private Bundle bundle = new Bundle();

    public FormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        chekIsEdit();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void chekIsEdit() {
        if (getArguments() != null) {
            binding.etTask.setText(getArguments().getString("editText"));
            binding.btnSave.setOnClickListener(view -> {
                edit();
                close();
            });
        } else {
            binding.btnSave.setOnClickListener(view -> {
                save();
                close();
            });
        }

    }

    private void close() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigateUp();
    }

    private void save() {
        Bundle bundle = new Bundle();
        String text = binding.etTask.getText().toString();
        FormModel model = new FormModel(text);
        App.db.noteDao().insertAllNote(model);
        bundle.putString("text", text);
        getParentFragmentManager().setFragmentResult("key", bundle);
    }

    private void edit() {
        String text = binding.etTask.getText().toString();
        int id = getArguments().getInt("ids");
        FormModel model = new FormModel(id, text);
        App.db.noteDao().upDateNote(model);
        bundle.putString("editText", text);
        bundle.putInt("ids", id);
        getParentFragmentManager().setFragmentResult("editNote", bundle);
    }
}
