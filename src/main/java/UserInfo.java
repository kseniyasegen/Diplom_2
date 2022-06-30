import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UserInfo {

    public void getUserInfo() {
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", CommonFields.accessTokenAfterLogin)
                .when().log().all()
                .get(Endpoints.UPDATE_USER_URL);
    }

    public void updateUserInfo(String newEmail, String newName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", newEmail);
        jsonObject.put("name", newName);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", CommonFields.accessTokenAfterLogin)
                .body(jsonObject.toMap())
                .when().log().all()
                .patch(Endpoints.UPDATE_USER_URL);
    }

    public void updateUserInfoWithoutLogin(String newEmail, String newName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", newEmail);
        jsonObject.put("name", newName);
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .body(jsonObject.toMap())
                .when().log().all()
                .patch(Endpoints.UPDATE_USER_URL);
    }

}
