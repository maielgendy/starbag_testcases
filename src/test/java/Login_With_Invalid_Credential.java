import controls.pageObjects.Login;
import org.junit.jupiter.api.Test;
import root.IocBuilder;
import root.StarbagHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Login_With_Invalid_Credential {

    @Test
    void main(){
        StarbagHandler sb = IocBuilder.createNewTest("http://localhost:8080/");
        final System.Logger logger = System.getLogger(Login.class.getName());
        final String errorMessage = "Invalid login and/or password";
        final String user = "test";
        final String pass = "test";

        try
        {
            sb.pages.login.enterUsername(user);
            sb.pages.login.enterPassword(pass);
            sb.pages.login.submit();
            assertEquals(errorMessage, sb.pages.login.getLoginMessage(), "Failed to Login with user '%s' and password '%s'".formatted(user, pass));
        }
        catch (Exception e)
        {
            logger.log(System.Logger.Level.ERROR, e.getMessage());
        }
        finally
        {
             sb.browser.close();
        }
    }
}