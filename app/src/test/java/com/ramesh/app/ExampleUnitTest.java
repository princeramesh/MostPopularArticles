package com.ramesh.app;

import com.ramesh.app.data.entities.ArticlesResponse;
import com.ramesh.app.data.services.ArticlesService;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import retrofit2.Retrofit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public ArticlesService articlesService;
    public TestObserver testObserver = new TestObserver<ArticlesResponse>();
    public Retrofit retrofit;

    public TestUtils testUtils;

    @Before
    public void before() {
        testUtils = new TestUtils();
        retrofit = testUtils.buildRetrofit();
        articlesService = retrofit.create(ArticlesService.class);
    }

    @Test
    public void testResponse() {
        articlesService.getArticles(TestConstants.API_KEY).subscribe(testObserver);
        testObserver.assertValue(new Predicate() {
            @Override
            public boolean test(Object t) throws Exception {
                ArticlesResponse response = testUtils.getArticles();
                ArticlesResponse testedResponse = (ArticlesResponse) t;
                String json = new Gson().toJson(t);
                return testedResponse.getCopyright().equals(response.getCopyright());
            }
        });
    }
}