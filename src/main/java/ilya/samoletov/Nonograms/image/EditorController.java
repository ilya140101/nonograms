package ilya.samoletov.Nonograms.image;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

@Component
@Slf4j
public class EditorController {

  @Autowired
  private final Editor editor;

  public EditorController(Editor editor) {
    this.editor = editor;
  }

  public void convertImageToMonochrome(URL path, URL resultFolder) {
    try {
      writeImage(editor.convertImageToMonochrome(ImageIO.read(path)), path, resultFolder, "monochrome_");
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  public void convertImageToMonochromeWithScaleAndBinarization(URL path, URL resultFolder, int threshold, boolean inverted, double scale) {
    try {
      writeImage(editor.convertImageToMonochromeWithBinarization(editor.scaleImage(ImageIO.read(path), scale), threshold, inverted), path, resultFolder, "binarization_%d_%s%.1f".formatted(threshold, inverted ? "inverted_" : "", scale));
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @SneakyThrows
  private static void writeImage(BufferedImage image, URL path, URL resultFolder, String prefix) {
    ImageIO.write(image, FilenameUtils.getExtension(path.getFile()), Path.of(Path.of(resultFolder.toURI()).toString(), prefix + Path.of(path.toURI()).getFileName()).toFile());
  }
}
