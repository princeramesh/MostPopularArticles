package com.ramesh.app.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ramesh.app.data.entities.Article;
import com.ramesh.app.domain.ArticlesUseCase;

import java.util.List;

public class ArticlesViewModel extends ViewModel {

    private ArticlesUseCase articlesUseCase;


    ArticlesViewModel(ArticlesUseCase articlesUseCase) {
        this.articlesUseCase = articlesUseCase;
    }

    public LiveData<List<Article>> loadArticles(int days) {
        return articlesUseCase.getArticles(days);
    }

}
