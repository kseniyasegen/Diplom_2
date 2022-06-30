import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class LoginUser {

    public void loginUser(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.LOGIN_USER_URL);
    }

}
