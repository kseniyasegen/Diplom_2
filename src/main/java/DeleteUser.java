import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    public void deleteUser() {
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", CommonFields.accessTokenAfterRegister)
                .when().log().all()
                .delete(Endpoints.DELETE_USER_URL);
    }

}
