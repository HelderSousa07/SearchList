package com.example.searchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillList();
        setupRecyclerView();
        setupSearchView();
    }

    private void setupSearchView() {
        androidx.appcompat.widget.SearchView searchView = findViewById(R.id.seatchView);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private void fillList() {
        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "One", "1"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Two", "2"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Three", "3"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Four", "4"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Five", "5"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Six", "6"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Seven", "7"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Eight", "8"));
        itemList.add(new Item(R.drawable.ic_search_black_24dp, "Nine", "9"));
    }

    private void setupRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ItemAdapter(itemList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}
