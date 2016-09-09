package helper;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangmeng on 16-8-12.
 */
public class QrCodeGene {
    private static final int QRCOLOR = 0xFF000000;
    private static final int BGWHITE = 0xFFFFFFFF;

    public static void main(String[] args) throws Exception {
        getLogoQRCode("http://www.baidu.com", "你真的需要知道这是什么吗？");
    }


    public static void getLogoQRCode(String qrUrl, String productName) throws Exception{
        File logo = new File(QrCodeGene.class.getClassLoader().getResource("images/container.jpg").getFile());
        BufferedImage bim = getQrCodeBufferedImage(qrUrl, BarcodeFormat.QR_CODE, 400, 400, getDecodeHintType());
        addLogoQrCode(bim, logo, new LogoConfig(), productName);
    }

    public static void addLogoQrCode(BufferedImage bim, File logoPic, LogoConfig logoConfig, String productName) throws Exception{
        BufferedImage image = bim;
        Graphics2D g = image.createGraphics();

        BufferedImage logo = ImageIO.read(logoPic);
        int widthLogo = logo.getWidth(null)>image.getWidth()*3/10?(image.getWidth()*3/10):logo.getWidth(null),
            heightLogo = logo.getHeight(null)>image.getHeight()*3/10?(image.getHeight()*3/10):logo.getWidth(null);
        int x = (image.getWidth() - widthLogo) / 2;
        int y = (image.getHeight() - heightLogo) / 2;
        g.drawImage(logo, x, y, widthLogo, heightLogo, null);
        g.dispose();
        if(StringUtils.isNotBlank(productName)) {
            BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D outg = outImage.createGraphics();
            outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            outg.setColor(Color.BLACK);
            outg.setFont(new Font("宋体", Font.BOLD, 30));
            int strWidth = outg.getFontMetrics().stringWidth(productName);
            if(strWidth > 399) {
                String productName1 = productName.substring(0, productName.length() / 2);
                String productName2 = productName.substring(productName.length() / 2, productName.length());
                int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                outg.drawString(productName1, 200  - strWidth1/2, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 12 );
                BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg2 = outImage2.createGraphics();
                outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                outg2.setColor(Color.BLACK);
                outg2.setFont(new Font("宋体",Font.BOLD,30)); //字体、字型、字号
                outg2.drawString(productName2, 200  - strWidth2/2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight())/2 + 5 );
                outg2.dispose();
                outImage2.flush();
                outImage = outImage2;
            } else {
                outg.drawString(productName, 200  - strWidth/2 , image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 12 ); //画文字
            }
            outg.dispose();
            outImage.flush();
            image = outImage;
        }
        logo.flush();
        image.flush();
        ImageIO.write(image, "png", new File("test.png"));
    }

    public static BufferedImage fileToBufferedImage(BitMatrix bm){
        BufferedImage image = null;

        int w = bm.getWidth(), h = bm.getHeight();
        image = new BufferedImage(w, h ,BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < w; i ++) {
            for(int j = 0 ; j < h ;j++) {
                image.setRGB(i, j , bm.get(i, j) ? QRCOLOR : BGWHITE);
            }
        }
        return image;
    }

    /***
     * 生成二维码bufferedImage图片
     * @param content 编码内容
     * @param barcodeFormat　参数类型
     * @param width　图片宽度
     * @param height　图片高度
     * @param hints　设置参数
     * @return
     */
    public static BufferedImage getQrCodeBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        BitMatrix bm = null;
        BufferedImage image = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        //编码内容, 编码类型,　生成图片宽度, 生成图片的高度, 设置参数
        bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
        return fileToBufferedImage(bm);
    }

    public static Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        return hints;
    }

}
class LogoConfig {
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    public static final int DEFAULT_BORDER = 2;
    public static final int DEFAULT_LOGOPART = 5;
    private final int border = DEFAULT_BORDER;
    private final Color borderColor ;
    private final int logoPart;

    public LogoConfig() {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public LogoConfig(Color borderColor, int logoPart) {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorder() {
        return border;
    }

    public int getLogoPart() {
        return logoPart;
    }

}