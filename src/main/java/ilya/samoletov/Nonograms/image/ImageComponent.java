package ilya.samoletov.Nonograms.image;

public record ImageComponent(int red, int green, int blue) {


  public ImageComponent(int argb) {
    this(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
  }

  public int getAverage() {
    return convertToArgb(getLuminance());
  }

  private int convertToArgb(int color) {
    return (color << 16) | (color << 8) | color;
  }

  private int getLuminance() {
    return (int) (0.299 * red + 0.587 * green + 0.114 * blue);
  }


  public int getBinarization(int threshold) {
    return getBinarization(threshold, false);
  }

  public int getBinarization(int threshold, boolean inverted) {
    int white = 255;
    int black = 0;
    if (inverted) {
      white = 0;
      black = 255;
    }
    return convertToArgb(getLuminance() >= threshold ? white : black);
  }
}
