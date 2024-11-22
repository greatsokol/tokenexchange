package org.gs.tokenexchange.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;

import java.security.InvalidParameterException;
import java.util.Map;

public class JwtUtils {
    public static String removeSign(String jwt) {
        String[] chunks = jwt.split("\\.");
        String header = chunks[0];
        String payload = chunks[1];

        var parsed = Jwts.parserBuilder().build().parse(header + "." + payload + ".");
        Header<?> fixedHeader = parsed.getHeader();
        if (fixedHeader == null) {
            throw new InvalidParameterException("no header in token");
        }

        fixedHeader.put("alg", "none");
        var body = parsed.getBody();
        if (body == null || (body instanceof String && ((String) body).isEmpty())) {
            throw new InvalidParameterException("no body in token");
        }

        return Jwts.builder()
                .setHeader((Map<String, Object>) fixedHeader)
                .setClaims(body instanceof Claims ? (Claims) body : null)
                .setPayload(body instanceof String ? (String) body : null)
                .compact();
    }
}
