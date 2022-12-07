package com.ecommerce.customer;

import com.ecommerce.customer.utils.CommonUtilities;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String StringGenerator() {
        return UUID.randomUUID().toString();
    }

    public static String StringGenerator(int length) {
        return UUID.randomUUID().toString().substring(0,length);
    }


    public static String StringDateGenerator() {
        DateFormat dateFormat = new SimpleDateFormat(CommonUtilities.TRX_DATETIME_FORMAT);
        return dateFormat.format(new Date(random.nextLong()));
    }

    public static String StringDateGenerator(String formatDate) {
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        return dateFormat.format(new Date(random.nextLong()));
    }

    public static Date DateGenerator() {
        return new Date(random.nextLong());
    }

    public static String StringTimestampGenerator() {
        DateFormat dateFormat = new SimpleDateFormat(CommonUtilities.TRX_DATETIME_FORMAT);
        return dateFormat.format( new Timestamp(random.nextLong()));
    }

    public static Timestamp TimestampGenerator() {
        return new Timestamp(random.nextLong());
    }

    public static Long LongGenerator() {
        return random.nextLong();
    }

    public static Integer IntegerGenerator() {
        return random.nextInt();
    }
}
