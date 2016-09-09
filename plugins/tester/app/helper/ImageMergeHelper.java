package helper;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

/**
 * Created by zhangmeng on 16-8-12.
 */
public class ImageMergeHelper {
    /**
     * @des 将第二张图片，合并到第一张图片上
     * @param imageOne 第一张图片流
     * @param imageTwo 第二张图片流
     * @param newFile 新文件
     * @param x  图片合成时，起始 x 坐标
     * @param y  图片合成时，起始 y 坐标
     * @return   true:图片合成成功; false:图片合成失败
     */
    public static boolean mergeImage(InputStream imageOne,InputStream imageTwo,File newFile ,int x,int y) {
        try {
            BufferedImage overlay = ImageIO.read(imageOne);
            BufferedImage qrcode = Thumbnails.of(imageTwo).size(100, 100).keepAspectRatio(true).asBufferedImage();
            int w = Math.max(qrcode.getWidth(), overlay.getWidth());
            int h = Math.max(qrcode.getWidth(), overlay.getWidth());
            BufferedImage combined = new BufferedImage(w, qrcode.getHeight() + overlay.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = combined.getGraphics();
            g.drawImage(overlay, 0, 0, null);
            g.drawImage(qrcode, 0, overlay.getHeight(), null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            String txt = "你好啊啊";
            int strWidth = g.getFontMetrics().stringWidth(txt);
            g.drawString(txt, qrcode.getWidth(), overlay.getHeight() + (combined.getHeight() - overlay.getHeight()) / 2 + 12);
            combined = Thumbnails.of(combined).size(500, 500).keepAspectRatio(true).asBufferedImage();
            ImageIO.write(combined, "PNG", newFile);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                if(imageOne != null) {
                    imageOne.close();
                }
                if(imageTwo != null) {
                    imageTwo.close();
                }
            }catch(Exception  ignore){
            }

        }
    }

    public static void main(String[] args){
        InputStream containerStream = ImageMergeHelper.class.getClassLoader().getResourceAsStream("images/container.jpg");
        InputStream qrcodeStream = ImageMergeHelper.class.getClassLoader().getResourceAsStream("images/qrcode.jpg");
        System.out.println(ImageMergeHelper.class.getResource(".").getFile());
        File newFile = new File("newimage.jpg");
        mergeImage(containerStream, qrcodeStream, newFile, 0, 0);
    }
}
