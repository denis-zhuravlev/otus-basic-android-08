package com.otus.otusfragmentlesson.ready;

import android.content.Context;
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

public class FragmentNewsList extends Fragment {
    public String savedString = "default";

    private NewsClickListener listener = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "onCreateView " + savedString, Toast.LENGTH_SHORT).show();

        savedString = "changed";

        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.element1).setOnClickListener(v -> {
            if (listener != null) listener.onNewsClick(((TextView) v).getText().toString());
        });

        view.findViewById(R.id.element2).setOnClickListener(v -> {
            if (listener != null) listener.onNewsClick(((TextView) v).getText().toString());
        });

        view.findViewById(R.id.element3).setOnClickListener(v -> {
            if (listener != null) listener.onNewsClick(((TextView) v).getText().toString());
        });

        view.findViewById(R.id.element4).setOnClickListener(v -> {
            if (listener != null) listener.onNewsClick(((TextView) v).getText().toString());
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // Toast.makeText(getContext(), "onActivityCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        Toast.makeText(getContext(), "onCreate " + this.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Toast.makeText(getContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();

        //Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }


    public void setListener(NewsClickListener newsClickListener) {
        listener = newsClickListener;
    }

    public interface NewsClickListener {
        void onNewsClick(String newsText);
    }
}
