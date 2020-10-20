package com.tinytongtong.androidstudy.java.trycatchfinally;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description: try-with-resource
 *
 * @Author tinytongtong
 * @Date 2020/10/20 10:26 AM
 * @Version
 */
public class TryWithResourceTest {
    private static int strToInt(String str) {
        return 0;
    }

    public static void main(String[] args) {
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("test.txt")));
        BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File("out.txt")))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
