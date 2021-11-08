package org.wpy;

import java.io.*;
import java.util.Base64;

public class Base64ToImage {

    public static void convert(File srcStr, File dstImage) throws IOException {
        InputStream input = new FileInputStream(srcStr);
        byte[] bytes = new byte[input.available()];
        input.read(bytes);
        byte[] img = Base64.getDecoder().decode(bytes);
        input.close();

        File parent = dstImage.getAbsoluteFile().getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!dstImage.exists() || dstImage.isDirectory()) {
            dstImage.createNewFile();
            dstImage.setWritable(true);
        }
        OutputStream out = new FileOutputStream(dstImage);
        out.write(img);
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Base64ToImage.convert(new File("imageStr2"), new File("image2.gif"));
    }
}
