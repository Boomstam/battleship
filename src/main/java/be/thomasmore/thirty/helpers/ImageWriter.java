package be.thomasmore.thirty.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ImageWriter {

    private static final String imageFormat = "png";
    private static final String imagePath = "src" + File.separator + "main" + File.separator + "resources" +
            File.separator + "static" + File.separator + "tmp" + File.separator + "tile.png";

    private static final Color grassColor = new Color(11, 111, 11);

    public void writeImage(){


        byte[] aByteArray = {(byte)grassColor.getRed(),(byte)grassColor.getGreen(),(byte)grassColor.getBlue()};
        //byte[] aByteArray = {0xa,0x2,0xf,(byte)0xff,(byte)0xff,(byte)0xff};
        byte[] colors = repeat(aByteArray, aByteArray.length * 100);
        int width = 10;
        int height = 10;

        DataBuffer buffer = new DataBufferByte(colors, colors.length);

        //3 bytes per pixel: red, green, blue
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[] {0, 1, 2}, (Point)null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);

        try {
            Files.deleteIfExists(Path.of(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, imageFormat, new File(imagePath));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static byte[] repeat(byte[] arr, int newLength) {
        byte[] dup = Arrays.copyOf(arr, newLength);
        for (int last = arr.length; last != 0 && last < newLength; last <<= 1) {
            System.arraycopy(dup, 0, dup, last, Math.min(last << 1, newLength) - last);
        }
        return dup;
    }
}
