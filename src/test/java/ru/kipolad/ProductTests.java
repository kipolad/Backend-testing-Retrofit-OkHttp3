package ru.kipolad;

import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import ru.kipolad.dto.Product;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ProductTests extends BasicConfForTest {

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    void createProduct() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();

        assert response.body() != null;
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @Test
    void modifyProduct() throws IOException {
        product.setId(id);
        Response<Product> response = productService.modifyProduct(product)
                .execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        assert response.body() != null;
        assertThat(response.body().getId(), equalTo(id));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));

    }

    @Test
    void getProduct() throws IOException {
        Response<Product> response = productService.getProductById(id)
                .execute();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));

        assert response.body() != null;
        assertThat(response.body().getId(), equalTo(id));
    }

    @Test
    void deleteProduct() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
