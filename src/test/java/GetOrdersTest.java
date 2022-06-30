import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import lombok.extern.java.Log;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetOrdersTest {

    GetOrders getOrders = new GetOrders();
    RegisterUser registerUser = new RegisterUser();
    LoginUser loginUser = new LoginUser();
    CommonFields commonFields = new CommonFields();
    MakeOrder makeOrder = new MakeOrder();
    GetIngredients getIngredients = new GetIngredients();
    DeleteUser deleteUser = new DeleteUser();

    @Test
    @DisplayName("Запрос списка заказов под авторизованным пользователем")
    @Description("Запрос списка заказов под авторизованным пользователем возвращается:\n" +
            " 1) статус код 200,\n" +
            " 2) true в поле success")
    public void getOrdersLoginUserReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        CommonFields.accessTokenAfterLogin = CommonFields.response.path("accessToken");
        getIngredients.getIngredients();
        CommonFields.ingredient1 = CommonFields.response.path("data[0]._id");
        CommonFields.ingredient2 = CommonFields.response.path("data[1]._id");
        makeOrder.makeOrderWithIngredients();
        getOrders.getOrders();
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(true));
        //удаление юзера
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Запрос списка заказов под неавторизованным пользователем")
    @Description("Запрос списка заказов под неавторизованным пользователем возвращается:\n" +
            " 1) статус код 401,\n" +
            " 2) false в поле success")
    public void getOrdersWithoutLoginUserReturnStatusCode401() {
        getOrders.getOrdersWithoutLogin();
        //проверки
        assertEquals(401, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(false));
    }
}
