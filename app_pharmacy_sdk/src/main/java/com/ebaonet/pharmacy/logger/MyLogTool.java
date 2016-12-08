package com.ebaonet.pharmacy.logger;

/**
 * 本类主要针对Android手机不能使用Log打印，所以采用System的方法
 * Created by yao.feng on 2016/5/13.
 */
public class MyLogTool implements LogTool {

    private final static int MAX_PRINT_COUNT = 2000;

    @Override
    public void d(String tag, String message) {
        sysout(message);
    }

    @Override
    public void e(String tag, String message) {
        syserr(message);
    }

    @Override
    public void w(String tag, String message) {
        sysout(message);
    }

    @Override
    public void i(String tag, String message) {
        sysout(message);
    }

    @Override
    public void v(String tag, String message) {
        sysout(message);
    }

    @Override
    public void wtf(String tag, String message) {
        sysout(message);
    }


    private void sysout(String message) {
        int len = message.length();
        int a = len / MAX_PRINT_COUNT;
        if (a == 0) {
            System.out.println(message);
        } else {
            for (int c = 0; c < a; c++) {
                System.out.println(message.substring(c * MAX_PRINT_COUNT, c * MAX_PRINT_COUNT + MAX_PRINT_COUNT - 1));
            }
            System.out.println(message.substring(a * MAX_PRINT_COUNT));
        }
    }

    private void syserr(String message) {
        int len = message.length();
        int a = len / MAX_PRINT_COUNT;
        if (a == 0) {
            System.err.println(message);
        } else {
            for (int c = 0; c < a; c++) {
                System.err.println(message.substring(c * MAX_PRINT_COUNT, c * MAX_PRINT_COUNT + MAX_PRINT_COUNT - 1));
            }
            System.err.println(message.substring(a * MAX_PRINT_COUNT));
        }
    }
}
