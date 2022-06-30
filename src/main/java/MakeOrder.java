import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class MakeOrder {

    public void makeOrderWithIngredients() {
        JSONArray ingredients = new JSONArray();
        ingredients.put(CommonFields.ingredient1);
        ingredients.put(CommonFields.ingredient2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ingredients", ingredients);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_ORDER_URL);
    }

    public void makeOrderWithoutIngredients() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ingredients", "");
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_ORDER_URL);
    }

    public void makeOrderWithInvalidIngredients() {
        JSONArray ingredients = new JSONArray();
        ingredients.put("85465jkdgf9854fdg");
        ingredients.put("85465jk67dgf9854fdg");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ingredients", ingredients);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_ORDER_URL);
    }
}
