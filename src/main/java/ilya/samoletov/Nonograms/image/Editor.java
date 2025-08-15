package ilya.samoletov.Nonograms.image;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import java.util.stream.IntStream;

@Component
public class Editor {
  public static final int DEFAULT_THRESHOLD = 128;

  public BufferedImage convertImageToMonochrome(BufferedImage image) {
    return pixelProcessing(image, ImageComponent::getAverage);
  }

  private BufferedImage pixelProcessing(BufferedImage image, Function<ImageComponent, Integer> function) {
    int height = image.getHeight();
    int width = image.getWidth();
    IntStream.range(0, height).forEach(y ->
        IntStream.range(0, width).forEach(x ->
            image.setRGB(x, y, function.apply(new ImageComponent(image.getRGB(x, y))))
        )
    );
    return image;
  }

  public BufferedImage convertImageToMonochromeWithBinarization(BufferedImage image) {
    return convertImageToMonochromeWithBinarization(image, DEFAULT_THRESHOLD);
  }

  public BufferedImage convertImageToMonochromeWithBinarization(BufferedImage image, boolean inverted) {
    return convertImageToMonochromeWithBinarization(image, DEFAULT_THRESHOLD, inverted);
  }

  public BufferedImage convertImageToMonochromeWithBinarization(BufferedImage image, int threshold) {
    return convertImageToMonochromeWithBinarization(image, threshold, false);
  }

  public BufferedImage convertImageToMonochromeWithBinarization(BufferedImage image, int threshold, boolean inverted) {
    return pixelProcessing(image, imageComponent -> imageComponent.getBinarization(threshold, inverted));
  }
}
