import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;
class LoginTest {

    @Test
    fun login() {

        val personLogin = Login("António", "antonio1234")

        val testResult = personLogin.Login()

        assertEquals("António,antonio1234,25.99,66", testResult)

    }
}