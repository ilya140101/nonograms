package ilya.samoletov.Nonograms.image;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.util.function.Function;
import java.util.stream.IntStream;

@Component
public class Editor {
  public static final int DEFAULT_THRESHOLD = 128;

  public BufferedImage convertImageToMonochrome(BufferedImage image) {
    return pixelProcessing(image, ImageComponent::getAverage);
  }

  public BufferedImage convertImageToMonochromeWithBinarization(BufferedImage image, int threshold, boolean inverted) {
    return pixelProcessing(image, imageComponent -> imageComponent.getBinarization(threshold, inverted));
  }


  public BufferedImage scaleImage(BufferedImage image, double scale) {
    if (scale == 1) {
      return image;
    }
    return Scalr.resize(image, (int) (image.getWidth() / scale));
  }

  private BufferedImage pixelProcessing(BufferedImage image, Function<ImageComponent, Integer> function) {
    IntStream.range(0, image.getHeight()).forEach(y ->
        IntStream.range(0, image.getWidth()).forEach(x ->
            image.setRGB(x, y, function.apply(new ImageComponent(image.getRGB(x, y))))
        )
    );
    return image;
  }
}
