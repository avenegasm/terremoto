package cl.avenegasm.terremoto.terremotoapi.security;

import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserDataTokenParser {

    /**
     *
     * @param user
     * @return
     */
   public static Consumer<Map<String, Object>> setTokenClaims(SessionUser user) {
        return claims -> {
            claims.put("rutUsuario",user.getDato());
        };
    }

    /**
     *
     * @return
     */
    public static Function<Claims, UserData> buildUserDataFunction() {
        return claims -> {
            UserData userData = new UserData();
            userData.setDato((String) claims.get("dato"));
            return userData;
        };
    }

}
