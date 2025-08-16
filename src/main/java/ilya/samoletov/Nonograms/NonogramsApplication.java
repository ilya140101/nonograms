package ilya.samoletov.Nonograms;

import ilya.samoletov.Nonograms.image.EditorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.util.List;

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

      List.of(false, true).forEach(inverted ->
          List.of(16, 32, 64, 128).forEach(threshold ->
              List.of(1, 1.5, 2, 10, 20, 30).forEach(scale -> {
                    editorController.convertImageToMonochromeWithScaleAndBinarization(cat, outputFolder, threshold, inverted, scale.doubleValue());
                    log.info("write image: inverted - {}, threshold - {}, scale - {}", inverted, threshold, scale);
                  }
              )
          )
      );
    };
  }

}
