package ilya.samoletov.Nonograms;

import ilya.samoletov.Nonograms.image.EditorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.URL;

@SpringBootApplication
@Slf4j
public class NonogramsApplication {


  public static void main(String[] args) {
    SpringApplication.run(NonogramsApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      EditorController editorController = ctx.getBean(EditorController.class);
      URL cat = getClass().getResource("/TestImage/cat.jpg");
      URL outputFolder = getClass().getResource("/TestImage");
      editorController.convertImageToMonochrome(cat, outputFolder);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 255);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 192);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 64);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 32);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 16);

      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 255, true);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 192, true);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, true);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 64, true);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 32, true);
      editorController.convertImageToMonochromeWithBinarization(cat, outputFolder, 16, true);
    };
  }

}
