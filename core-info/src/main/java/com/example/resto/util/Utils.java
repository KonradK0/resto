package com.example.resto.util;

import com.example.resto.RestoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static <T> T unauthorizedAccess(HttpServletResponse res){
        try {
            res.sendError(401, "401 Unauthorized access");
            return null;
        } catch (IOException e) {
            res.setStatus(500);
            log.error(String.valueOf(e));
            return null;
        }
    }
}
