import static io.restassured.RestAssured.given;

public class GetOrders {

    public void getOrders() {
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .header("authorization", CommonFields.accessTokenAfterLogin)
                .when().log().all()
                .get(Endpoints.GET_USERS_ORDERS_URL);
    }

    public void getOrdersWithoutLogin() {
        CommonFields.response = given().log().all()
                .baseUri(Endpoints.URL)
                .header("Content-type", "application/json")
                .when().log().all()
                .get(Endpoints.GET_USERS_ORDERS_URL);
    }

}
