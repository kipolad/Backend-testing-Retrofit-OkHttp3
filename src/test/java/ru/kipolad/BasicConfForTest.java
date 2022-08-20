/**
 * Created by Yulya Telysheva
 */
package ru.kipolad;

import com.github.javafaker.Faker;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import retrofit2.Response;
import ru.kipolad.api.ProductService;
import ru.kipolad.dto.Product;
import ru.kipolad.utils.RetrofitUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

abstract class BasicConfForTest {
    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    static int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);

    }
}
