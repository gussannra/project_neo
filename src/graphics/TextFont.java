package graphics;

public enum TextFont {
    MENU_FONT("Arial", 0.05f);

    public final String fontName;
    public final float size;
    public TextFontMetrics metrics;

    TextFont(String fontName, float size) {
        this.fontName = fontName;
        this.size = size;
    }

    public float getDrawWidth(String text) {
        return metrics.getStringDrawWidth(text);
    }
}