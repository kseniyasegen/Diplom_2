import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUserTest {

    LoginUser login = new LoginUser();
    RegisterUser registerUser = new RegisterUser();
    CommonFields commonFields = new CommonFields();
    DeleteUser deleteUser = new DeleteUser();

    @Test
    @DisplayName("Авторизация под существующим юзером")
    @Description("При авторизации под существующим юзером возвращается:\n" +
            "1) статус код 200,\n" +
            "2) success == true")
    public void loginUserValidCredsReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        login.loginUser(commonFields.email, commonFields.password);
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(true));
        //удаление юзера
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Авторизация под несуществующим юзером")
    @Description("При авторизации под несуществующим юзером возвращается:\n " +
            "1) статус код 401,\n " +
            "2) success == false, \n" +
            "3) текст сообщения email or password are incorrect")
    public void loginUserInvalidCredsReturnStatusCode401() {
        login.loginUser(commonFields.email, commonFields.password);
        //проверки
        assertEquals(401, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(false));
        assertTrue(CommonFields.response.path("message").equals("email or password are incorrect"));
    }
}
