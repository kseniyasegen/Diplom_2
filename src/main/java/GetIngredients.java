import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class GetIngredients {

    public void getIngredients() {
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .when().log().all()
                .get(Endpoints.GET_INGREDIENTS_URL);
    }

}
