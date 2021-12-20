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

    public FormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        initListener();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initListener() {
        binding.btnSave.setOnClickListener(v -> {
                    save();
                    close();
                }
        );
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
}
