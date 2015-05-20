package electricdrill.edje.dependencies;

import java.awt.*;
import java.util.Locale;

public class EDHeadlessGraphicsEnvironment extends EDGraphicsEnvironment {
    private EDGraphicsEnvironment edge;

    public EDHeadlessGraphicsEnvironment(EDGraphicsEnvironment newEdge) { edge = newEdge; }

    public GraphicsDevice[] getScreenDevices() throws HeadlessException {
        throw new HeadlessException();
    }

    public GraphicsDevice getDefaultScreenDevice() throws HeadlessException {
        throw new HeadlessException();
    }

    public Point getCenterPoint() throws HeadlessException {
        throw new HeadlessException();
    }

    public Rectangle getMaximumWindowBounds() throws HeadlessException {
        throw new HeadlessException();
    }

    public Graphics createGraphics(Image image) {
        return edge.createGraphics(image);
    }

    public Font[] getAllFonts() { return edge.getAllFonts(); }

    public String[] getAvailableFontFamilyNames() { return edge.getAvailableFontFamilyNames(); }

    public String[] getAvailableFontFamilyNames(Locale l) { return edge.getAvailableFontFamilyNames(l); }

    public EDGraphicsEnvironment getSunGraphicsEnvironment() { return edge; }

}
