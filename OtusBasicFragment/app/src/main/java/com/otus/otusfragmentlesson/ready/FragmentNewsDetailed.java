package com.otus.otusfragmentlesson.ready;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.otus.otusfragmentlesson.R;

public class FragmentNewsDetailed extends Fragment {
    private static final String EXTRA_TEXT = "EXTRA_TEXT";
    //private String text = "default";

    public static FragmentNewsDetailed newInstance(String text) {
        FragmentNewsDetailed fragmentNewsDetailed = new FragmentNewsDetailed();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXT, text);
        fragmentNewsDetailed.setArguments(bundle);

        return fragmentNewsDetailed;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "onCreateView " + toString(), Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_news_detailed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            ((TextView) view.findViewById(R.id.newsTitleTv)).setText(getArguments().getString(EXTRA_TEXT, "..."));
        }
        //setRetainInstance(true);

        //((TextView) view.findViewById(R.id.newsTitleTv)).setText(text);

        //text = "notDefault";
    }

    public void setText(String text) {
        if (isAdded()) {
            ((TextView) getView().findViewById(R.id.newsTitleTv)).setText(text);
        }
    }
}
