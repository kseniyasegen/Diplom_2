import org.json.JSONObject;

import static io.restassured.RestAssured.given;


public class RegisterUser {

    public void registerUser(String email, String password, String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_USER_URL);
    }

    public void invalidRegisterUser(String email, String password, String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_USER_URL);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .post(Endpoints.CREATE_USER_URL);
    }

}
