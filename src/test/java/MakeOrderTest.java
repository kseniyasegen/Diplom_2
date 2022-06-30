import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.*;

public class MakeOrderTest {

    RegisterUser registerUser = new RegisterUser();
    CommonFields commonFields = new CommonFields();
    DeleteUser deleteUser = new DeleteUser();
    LoginUser loginUser = new LoginUser();
    UserInfo userInfo = new UserInfo();
    GetIngredients getIngredients = new GetIngredients();
    MakeOrder makeOrder = new MakeOrder();

    @Test
    @DisplayName("Заказ с авторизацией и ингредиентами")
    @Description("Заказ с авторизацией и ингредиентами возвращает:\n " +
            "1) статус код 200,\n " +
            "2) success == true")
    public void mareOrderWithLoginAndIngredientsReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        getIngredients.getIngredients();
        CommonFields.ingredient1 = CommonFields.response.path("data[0]._id");
        CommonFields.ingredient2 = CommonFields.response.path("data[1]._id");
        makeOrder.makeOrderWithIngredients();
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(true));
        //удаление юзера
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Заказ с авторизацией без ингредиентов")
    @Description("Заказ с авторизацией без ингредиентов возвращает: \n" +
            "1) статус код 400, \n" +
            "2) success == false")
    public void mareOrderWithLoginAndWithoutIngredientsReturnStatusCode400() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        makeOrder.makeOrderWithoutIngredients();
        //проверки
        assertEquals(400, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(false));
        //удаление юзера
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Заказ с авторизацией и с неверным хешем ингредиентов")
    @Description("Заказ с авторизацией с неверным хешем ингредиентов возвращает: \n" +
            "1) статус код 500")
    public void mareOrderWithLoginAndWithInvalidIngredientsReturnStatusCode500() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        makeOrder.makeOrderWithInvalidIngredients();
        //проверки
        assertEquals(500, CommonFields.response.statusCode());
        //удаление юзера
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Заказ без авторизации с ингредиентами")
    @Description("Заказ без авторизации с ингредиентами возвращает: \n" +
            "1) статус код 200, \n" +
            "2) success == true")
    public void mareOrderWithoutLoginAndWithIngredientsReturnStatusCode200() {
        getIngredients.getIngredients();
        CommonFields.ingredient1 = CommonFields.response.path("data[0]._id");
        CommonFields.ingredient2 = CommonFields.response.path("data[1]._id");
        makeOrder.makeOrderWithIngredients();
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(true));
    }

    @Test
    @DisplayName("Заказ с авторизацией без ингредиентов")
    @Description("Заказ с авторизацией без ингредиентов возвращает: \n" +
            "1) статус код 400, \n" +
            "2) success == false")
    public void mareOrderWithoutLoginAndWithoutIngredientsReturnStatusCode400() {
        makeOrder.makeOrderWithoutIngredients();
        //проверки
        assertEquals(400, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(false));
    }

    @Test
    @DisplayName("Заказ без авторизации с неверным хешем ингредиентов")
    @Description("Заказ без авторизации с неверным хешем ингредиентов возвращает: \n" +
            "1) статус код 500")
    public void mareOrderWithoutLoginAndWithInvalidIngredientsReturnStatusCode500() {
        makeOrder.makeOrderWithInvalidIngredients();
        //проверки
        assertEquals(500, CommonFields.response.statusCode());
    }

}
