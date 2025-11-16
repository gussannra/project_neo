package graphics;

public enum TextColor {
    BLACK(0, 0, 0),
    WHITE(255, 255, 255),
    YELLOW(255, 255, 100),
    RED(255, 0, 0);

    public final int r;
    public final int g;
    public final int b;

    TextColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
