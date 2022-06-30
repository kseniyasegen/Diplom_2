import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserInfoTest {

    LoginUser loginUser = new LoginUser();
    RegisterUser registerUser = new RegisterUser();
    CommonFields commonFields = new CommonFields();
    UserInfo userInfo = new UserInfo();
    DeleteUser deleteUser = new DeleteUser();


    @Test
    @DisplayName("Обновление email и name авторизованного юзера")
    @Description("При обновлении email и name возвращается: \n" +
            "1) статус код 200, \n" +
            "2) новые email и name")
    public void changeEmailAndNameReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        CommonFields.accessTokenAfterLogin = CommonFields.response.path("accessToken");
        userInfo.updateUserInfo("NEW" + commonFields.email, "NEW" + commonFields.name);
        userInfo.getUserInfo();
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        CommonFields.newName = CommonFields.response.path("user.name");
        assertEquals("NEW" + commonFields.name, CommonFields.newName);
        CommonFields.newEmail = CommonFields.response.path("user.email");
        assertEquals(CommonFields.newEmail, ("NEW" + commonFields.email).toLowerCase());
        //удаление юзера
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Обновление email авторизованного юзера")
    @Description("При обновлении email  возвращается: \n" +
            "1) статус код 200, \n" +
            "2) новый email")
    public void changeEmailReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        CommonFields.accessTokenAfterLogin = CommonFields.response.path("accessToken");
        userInfo.updateUserInfo("NEW" + commonFields.email, commonFields.name);
        userInfo.getUserInfo();
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        CommonFields.newEmail = CommonFields.response.path("user.email");
        assertEquals(CommonFields.newEmail.toLowerCase(), ("NEW" + commonFields.email).toLowerCase());
        //удаление юзера
        deleteUser.deleteUser();
    }

    @Test
    @DisplayName("Обновление name авторизованного юзера")
    @Description("При обновлении name  возвращается: \n" +
            "1) статус код 200, \n" +
            "2) новый name")
    public void changeNameReturnStatusCode200() {
        registerUser.registerUser(commonFields.email, commonFields.password, commonFields.name);
        CommonFields.accessTokenAfterRegister = CommonFields.response.path("accessToken");
        loginUser.loginUser(commonFields.email, commonFields.password);
        CommonFields.accessTokenAfterLogin = CommonFields.response.path("accessToken");
        userInfo.updateUserInfo(commonFields.email, "NEW" + commonFields.name);
        userInfo.getUserInfo();
        //проверки
        assertEquals(200, CommonFields.response.statusCode());
        CommonFields.newName = CommonFields.response.path("user.name");
        assertEquals(CommonFields.newName.toLowerCase(), ("NEW" + commonFields.name).toLowerCase());
        //удаление юзера
        deleteUser.deleteUser();
    }


    @Test
    @DisplayName("Обновление данных юзера без логина возвращается статус код 401")
    @Description("При обновлении данных юзера без логина возвращается: \n" +
            "1) статус код 200, \n" +
            "2) сообщение You should be authorised")
    public void getUserInfoWithoutLoginReturnStatusCode401() {
        userInfo.updateUserInfoWithoutLogin("NEW" + commonFields.email, "NEW" + commonFields.name);
        //проверки
        assertEquals(401, CommonFields.response.statusCode());
        assertTrue(CommonFields.response.path("message").equals("You should be authorised"));
    }
}
