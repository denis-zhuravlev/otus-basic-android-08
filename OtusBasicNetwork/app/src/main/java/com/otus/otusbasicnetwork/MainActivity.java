package com.otus.otusbasicnetwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.otus.otusbasicnetwork.api.reponse.MovieJson;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<MovieItem> items = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getInstance().service.getMovies().enqueue(new Callback<List<MovieJson>>() {
            @Override
            public void onResponse(Call<List<MovieJson>> call, Response<List<MovieJson>> response) {
                if (response.isSuccessful()) {
                    items.clear();
                    for (MovieJson movieJson : response.body()) {
                        items.add(new MovieItem(movieJson));
                    }

                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<MovieJson>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MovieAdapter(items, LayoutInflater.from(this)));

        gsonSamples();
    }

    private void gsonSamples() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SampleObject.CustomSerialization.class, new CustomConverter())
                .serializeNulls()
                .create();
        String jsonString = gson.toJson(new SampleObject());
        Log.d("gson", jsonString);
        SampleObject sampleObject = gson.fromJson(jsonString, SampleObject.class);
        Log.d("gson", sampleObject.toString());
    }

    private static class SampleObject {
        public String name = "sampleName";
        public int age = 5;
        public List<String> nicknames = new ArrayList<>();
        public SampleNestedClass nested = new SampleNestedClass();
        public CustomSerialization customSerialization = new CustomSerialization(new Date(), 25);

        public SampleObject() {
            nicknames.add("abc");
            nicknames.add("someNick");
            nicknames.add("bcd");
        }

        public static class SampleNestedClass {
            public String surname = "S U R N A M E";

            @Override
            public String toString() {
                return "SampleNestedClass{" +
                        "surname='" + surname + '\'' +
                        '}';
            }
        }

        public static class CustomSerialization {
            Date date;
            Integer integer;

            public CustomSerialization(Date date, Integer integer) {
                this.date = date;
                this.integer = integer;
            }

            @Override
            public String toString() {
                return "CustomSerialization{" +
                        "date=" + date +
                        ", integer=" + integer +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "SampleObject{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", nicknames=" + nicknames +
                    ", nested=" + nested +
                    ", customSerialization=" + customSerialization +
                    '}';
        }
    }

    public class CustomConverter implements JsonSerializer<SampleObject.CustomSerialization>, JsonDeserializer<SampleObject.CustomSerialization> {
        public JsonElement serialize(SampleObject.CustomSerialization src, Type type,
                                     JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            object.addProperty("date", src.date.getTime());
            object.addProperty("integer", src.integer.toString());
            return object;
        }

        public SampleObject.CustomSerialization deserialize(JsonElement json, Type type,
                                                            JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            Date date = new Date(object.get("date").getAsLong());
            Integer integer = new Integer(object.get("integer").getAsString());
            return new SampleObject.CustomSerialization(date, integer);
        }

    }
}
