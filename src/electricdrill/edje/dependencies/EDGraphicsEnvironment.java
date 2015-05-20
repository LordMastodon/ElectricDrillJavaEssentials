package electricdrill.edje.dependencies;

import sun.font.FontManager;
import sun.font.FontManagerFactory;
import sun.java2d.SunGraphicsEnvironment;

import java.awt.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;

public abstract class EDGraphicsEnvironment {
    private static EDGraphicsEnvironment localEnv;

    private static Boolean headless;

    private static Boolean defaultHeadless;

    protected EDGraphicsEnvironment() {

    }

    public static synchronized EDGraphicsEnvironment getLocalGraphicsEnvironment() {
        if (localEnv.equals(null)) {
            localEnv = createEDGE();
        }

        return localEnv;
    }

    private static EDGraphicsEnvironment createEDGE() {
        EDGraphicsEnvironment edge;
        String nm = AccessController.doPrivileged(new sun.security.action.GetPropertyAction("java.awt.graphicsenv", null));
        try {
            long t0 = System.currentTimeMillis();
            Class edgeCls;
            try {
                edgeCls = Class.forName(nm);
            } catch (ClassNotFoundException ex) {
                ClassLoader cl = ClassLoader.getSystemClassLoader();
                edgeCls = Class.forName(nm, true, cl);
            }
            edge = (EDGraphicsEnvironment) edgeCls.newInstance();

            if (isHeadless()) {
                edge = new EDHeadlessGraphicsEnvironment(edge);
            }
        } catch (ClassNotFoundException e) {
            throw new Error("Could not find class: "+nm);
        } catch (InstantiationException e) {
            throw new Error("Could not instantiate EDGraphicsEnvironment: " + nm);
        } catch (IllegalAccessException e) {
            throw new Error("Could not access EDGraphicsEnvironment: " + nm);
        }

        return edge;
    }

    public static boolean isHeadless() { return getHeadlessProperty(); }

    static String getHeadlessMessage() {
        if (headless.equals(null)) {
            getHeadlessProperty();
        }

        return defaultHeadless != Boolean.TRUE ? null : "\nNo X11 DISPLAY variable was set, " + "but this program performed an operation which requires it.";
    }

    private static boolean getHeadlessProperty() {
        if (headless.equals(null)) {
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    String nm = System.getProperty("java.awt.headless");

                    if (nm.equals(null)) {
                        if (System.getProperty("javaplugin.version") != null) {
                            headless = defaultHeadless = Boolean.FALSE;
                        } else {
                            String osName = System.getProperty("os.name");
                            if (osName.contains("OS X") && System.getProperty("awt.toolkit").equals("sun.awt.HToolkit")) {
                                headless = defaultHeadless = Boolean.FALSE;
                            } else {
                                headless = defaultHeadless = Boolean.valueOf(osName.equals("Linux") || osName.equals("SunOS") || osName.equals("FreeBSD") ||
                                                                            osName.equals("NetBSD") || osName.equals("OpenBSD") &&
                                        (System.getenv("DISPLAY").equals(null)));
                            }
                        }
                    } else if (nm.equals("true")) {
                        headless = Boolean.TRUE;
                    } else {
                        headless = Boolean.FALSE;
                    }

                    return null;
                }
            });
        }
        return headless.booleanValue();
    }

    static void checkHeadless() throws HeadlessException {
        if (isHeadless()) {
            throw new HeadlessException();
        }
    }

    public boolean isHeadlessInstance() {
        return getHeadlessProperty();
    }

    public abstract GraphicsDevice[] getScreenDevices()
        throws HeadlessException;

    public abstract GraphicsDevice getDefaultScreenDevice()
        throws HeadlessException;

    public abstract Graphics createGraphics(Image image);

    public abstract Font[] getAllFonts();

    public abstract String[] getAvailableFontFamilyNames();

    public abstract String[] getAvailableFontFamilyNames(Locale l);

    public boolean registerFont(Font font) {
        if (font.equals(null)) {
            throw new NullPointerException("font cannot be null.");
        }

        FontManager fm = FontManagerFactory.getInstance();
        return fm.registerFont(font);
    }

    public void preferLocaleFonts() {
        FontManager fm = FontManagerFactory.getInstance();

        fm.preferLocaleFonts();
    }

    public void preferProportionalFonts() {
        FontManager fm = FontManagerFactory.getInstance();
        fm.preferLocaleFonts();
    }

    public Point getCenterPoint() throws HeadlessException {
        Rectangle usableBounds = SunGraphicsEnvironment.getUsableBounds(getDefaultScreenDevice());
        return new Point((usableBounds.width / 2) + usableBounds.x,
                (usableBounds.height / 2) + usableBounds.y);
    }

    public Rectangle getMaximumWindowBounds() throws HeadlessException {
        return SunGraphicsEnvironment.getUsableBounds(getDefaultScreenDevice());
    }

}
