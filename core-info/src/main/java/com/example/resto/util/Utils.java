package com.example.resto.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static boolean isBetween(double actual, double low, double high) {
        return (low <= actual) && (actual <= high);
    }
}
