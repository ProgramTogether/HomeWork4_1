package com.example.homework41.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homework41.R;
import com.example.homework41.databinding.FragmentHomeBinding;
import com.example.homework41.ui.App;
import com.example.homework41.ui.form.FormFragment;
import com.example.homework41.ui.form.FormModel;

public class HomeFragment extends Fragment implements TaskAdapter.EditNoteItem {
    private FragmentHomeBinding binding;
    private TaskAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new TaskAdapter(getContext(), this);
        adapter.setListener(position -> showAlert(position));
        loadNote();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        setFragmentListener();
        intiRv();
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                FormModel model = new FormModel(result.getString("text"));
                adapter.addText(model);
            }
        });
            getParentFragmentManager().setFragmentResultListener("editNote", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                FormModel model = new FormModel(result.getInt("ids"), result.getString("editText"));
                adapter.editNote(model);
            }
        });
    }

    private void showAlert(int position) {
        new AlertDialog.Builder(requireActivity())
                .setTitle("Удалить заметку?")
                .setNegativeButton("Нет", null)
                .setPositiveButton("да", (dialogInterface, i) -> {
                    adapter.removeNote(position);
                }).show();

    }

    private void loadNote() {
        adapter.setList(App.db.noteDao().getAllNote());
    }

    private void intiRv() {
        binding.rvTask.setAdapter(adapter);
    }

    private void initListeners() {
        binding.btnAction.setOnClickListener(v -> {
            openFragment();
        });
    }

    private void openFragment() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigate(R.id.formFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void editClil(int position) {
        FormModel model = adapter.getList().get(position);
        Bundle bundle = new Bundle();
        bundle.putString("editText", model.getText());
        bundle.putInt("ids", model.getId());
        FormFragment formFragment = new FormFragment();
        formFragment.setArguments(bundle);
        openFragment();
    }
}