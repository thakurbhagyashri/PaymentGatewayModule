package com.example.pgm.util;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public class RazorpayUtil {

    public static RazorpayClient getClient() throws  RazorpayException {
        return new RazorpayClient("rzp_test_YourKey", "YourSecret");
    }
}
