import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterUserTest {

    RegisterUser registerUser = new RegisterUser();
    CommonFields commonFields = new CommonFields();
    DeleteUser deleteUser = new DeleteUser();

    @Test
    @DisplayName("Регистрация уникального юзера")
    @Description("При регистрации уникального юзера возвращается: \n" +
            "1) статус код 200\n" +
            "2) success == true\n")
    public void registerNewUserReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.response.prettyPrint();
        assertEquals(200, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("success").equals(true));
        //удаление юзера
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        deleteUser.deleteUser();
    }


    @Test
    @DisplayName("Регистрация существующего юзера")
    @Description("При регистрации существующего юзера возвращается: \n" +
            "1) статус код 403, \n" +
            "2) текст сообщения User already exists")
    public void registerSameUserReturnStatusCode403() {
        registerUser.invalidRegisterUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.response.prettyPrint();
        assertEquals(403, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("message").equals("User already exists"));
    }

    @Test
    @DisplayName("Регистрация юзера без email")
    @Description("При регистрации юзера без email возвращается: \n" +
            "1) статус код 403, \n" +
            "2) текст сообщения Email, password and name are required fields")
    public void registerNewUserWithoutEmailReturnStatusCode403() {
        registerUser.registerUser("", commonFields.password, commonFields.name);
        CommonFields.response.prettyPrint();
        assertEquals(403, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("message").equals("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Регистрация юзера без password")
    @Description("При регистрации юзера без password возвращается: \n" +
            "1) статус код 403, \n" +
            "2) текст сообщения Email, password and name are required fields")
    public void registerNewUserWithoutPasswordReturnStatusCode403() {
        registerUser.registerUser(commonFields.email, "", commonFields.name);
        CommonFields.response.prettyPrint();
        assertEquals(403, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("message").equals("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Регистрация юзера без name")
    @Description("При регистрации юзера без name возвращается: \n" +
            "1) статус код 403, \n" +
            "2) текст сообщения Email, password and name are required fields")
    public void registerNewUserWithoutNameReturnStatusCode403() {
        registerUser.registerUser(commonFields.email, commonFields.password, "");
        CommonFields.response.prettyPrint();
        assertEquals(403, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("message").equals("Email, password and name are required fields"));
    }


}
