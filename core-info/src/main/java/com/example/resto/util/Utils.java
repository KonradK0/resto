package com.example.resto.util;

import com.example.resto.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static <T> T unauthorizedAccess(HttpServletResponse res) {
        try {
            res.sendError(401, "401 Unauthorized access");
        } catch (IOException e) {
            res.setStatus(500);
            log.error(String.valueOf(e));
        }
        return null;
    }

    public static boolean isBetween(double actual, double low, double high) {
        return (low <= actual) && (actual <= high);
    }
}
