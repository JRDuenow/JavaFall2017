package utility;

import controller.Main;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontLoader {

    public static void LoadFonts() {

        try {
            File[] fontDirectory = new File(Main.class.getClassLoader().getResource("Fonts/").getFile()).listFiles();

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            for (File fontFile : fontDirectory) {

                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

                ge.registerFont(font);
            }

        } catch (FontFormatException | IOException e) {
            System.out.println("Error Loading Fonts: " + e.getMessage());
            System.exit(1);
        }

    }

}
