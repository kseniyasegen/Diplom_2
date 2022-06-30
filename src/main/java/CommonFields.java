import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

public class CommonFields {

    public static Response response;
    public String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    public String password = RandomStringUtils.randomAlphabetic(10);
    public String name = RandomStringUtils.randomAlphabetic(10);
    public static String accessTokenAfterRegister;
    public static String accessTokenAfterLogin;
    public static String newName;
    public static String newEmail;
    public static String ingredient1;
    public static String ingredient2;

}
