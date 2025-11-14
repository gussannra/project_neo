package graphics;

public enum TextFont {
    MENU_FONT("Arial", 32);

    public final String fontName;
    public final int size;

    TextFont(String fontName, int size) {
        this.fontName = fontName;
        this.size = size;
    }
}
