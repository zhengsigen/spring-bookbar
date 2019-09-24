package com.c1801.spring.dzy.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;


/**
 * 生成二维码封装类
 */
public class ZXingUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZXingUtil.class);

    private ZXingUtil() {
        throw new IllegalStateException("ZXingUtil class");
    }

//    /**
//     * @param content 内容
//     * @param path
//     * @param width
//     * @param height
//     * @throws WriterException
//     * @throws IOException
//     */
//    public static void getImage(String content, String path, int width, int height) {
//        // 二维码的图片格式
//        String format = "jpg";
//        Map hints = new HashMap();
//        //设置二维码四周白色区域的大小
//        hints.put(EncodeHintType.MARGIN, 1);
//        //设置二维码的容错性
//        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//        // 内容所使用字符集编码
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        BitMatrix bitMatrix = null;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//        } catch (WriterException e) {
//            logger.error(e.getMessage());
//        }
//        // 生成二维码
//        Path pathfile = Paths.get(path);
//        try {
//            MatrixToImageWriter.writeToPath(bitMatrix, format, pathfile);
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        }
//    }

    /**<pre>
     * 生成二维码并使用Base64编码 data前缀
     * @author 潘清山
     * @since 1.0
     * @since JDK 8.0
     * </pre>
     */
    public static String getBase64QRCodeData(String content, int width, int height){
        return "data:image/jpg;base64,"+getBase64QRCode(content, width, height);
    }

    /**
     * 生成二维码并使用Base64编码
     *
     * @param content
     * @param width
     * @param height
     * @return
     * @throws WriterException
     */
    public static String getBase64QRCode(String content, int width, int height) {
        return Base64.getEncoder().encodeToString(getBytes(content, width, height));
    }

    public static byte[] getBytes(String content, int width, int height) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN, 1);//设置0-4之间
        //设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //画二维码
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            logger.error(e.getMessage());
        }
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        //注意此处拿到字节数据
        return imageToBytes(image, "jpg");
    }

    private static byte[] imageToBytes(BufferedImage image, String type) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, out);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
        return out.toByteArray();
    }


    public static BufferedImage toBufferedImageCustom(BitMatrix matrix) {
        //二维码边框的宽度，默认二维码的宽度是100，经过多次尝试后自定义的宽度
        int left = 3;
        int right = 4;
        int top = 2;
        int bottom = 2;

        //1、首先要自定义生成边框
        int[] rec = matrix.getEnclosingRectangle();   //获取二维码图案的属性
        int resWidth = rec[2] + left + right;
        int resHeight = rec[3] + top + bottom;

        BitMatrix matrix2 = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
        matrix2.clear();
        for (int i = left + 1; i < resWidth - right; i++) {   //循环，将二维码图案绘制到新的bitMatrix中
            for (int j = top + 1; j < resHeight - bottom; j++) {
                if (matrix.get(i - left + rec[0], j - top + rec[1])) {
                    matrix2.set(i, j);
                }
            }
        }

        int width = matrix2.getWidth();
        int height = matrix2.getHeight();

        //2、为边框设置颜色
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x < left || x > width - right || y < top || y > height - bottom) {
                    image.setRGB(x, y, BLACK);    //为了与Excel边框重合，设置二维码边框的颜色为黑色
                } else {
                    image.setRGB(x, y, matrix2.get(x, y) ? BLACK : WHITE);
                }
            }
        }
        return image;
    }
}