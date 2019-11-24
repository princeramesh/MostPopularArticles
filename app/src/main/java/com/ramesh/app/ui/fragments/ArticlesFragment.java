package com.ramesh.app.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ramesh.app.R;
import com.ramesh.app.data.entities.Article;
import com.ramesh.app.interfaces.ItemClickListener;
import com.ramesh.app.ui.MainActivity;
import com.ramesh.app.ui.adapters.ArticlesAdapter;
import com.ramesh.app.viewmodel.ArticlesViewModel;
import com.ramesh.app.viewmodel.ViewModelFactory;
import com.ramesh.app.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ArticlesFragment extends Fragment implements ItemClickListener<Article> {

    private static final String TAG="ArticlesFragment";
    private ArticlesViewModel mViewModel;
    @BindView(R.id.articles_recycler)
    public RecyclerView articlesRecyclerView;

    @Inject
    public ArticlesAdapter adapter;

    @Inject
    public ViewModelFactory viewModelFactory;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    Spinner spinner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.articles_fragment, container, false);
        ButterKnife.bind(this, view);

        initViews();
        setupToolbar();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(ArticlesViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();

        loadData( 7);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        spinner = (Spinner) item.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:

                        loadData( 1);
                        break;

                    case 1:
                        loadData( 7);
                        break;

                    case 2:
                        loadData( 30);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (spinner != null)
            spinner.setSelection(1);


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        adapter.setItemClickListenr(this);
        articlesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articlesRecyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void loadData( int days) {

        Log.i(TAG,"Fetching Data, Days: "+days);
        mViewModel.loadArticles(days).observe(this, new Observer<List<Article>>() {

            @Override
            public void onChanged(@Nullable List<Article> articles) {
                Log.i(TAG,"Received Data size: "+articles.size());
                adapter.setArticles(articles);
            }
        });
    }

    @Override
    public void onItemClick(int position, Article model) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.ARTICLE, model);
        ((MainActivity) getActivity()).replaceCurrentFragment(bundle, new ArticlesDetailsFragment());
    }
}
