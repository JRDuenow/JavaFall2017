package utility;

import controller.Main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteLoader {

    public static BufferedImage[] LoadSheet(String fileName, int spriteWidth, int spriteHeight, int rows, int cols) {

        try {
            BufferedImage sheet = ImageIO.read(new File(Main.class.getClassLoader().getResource("Spritesheets/" + fileName).getFile()));

            BufferedImage[] sprites = new BufferedImage[rows * cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sprites[(i * cols) + j] = sheet.getSubimage(
                            j * spriteWidth,
                            i * spriteHeight,
                            spriteWidth,
                            spriteHeight
                    );
                }
            }

            return sprites;

        } catch (IOException ex) {
            System.out.println("Spritesheet Error: " + ex.getMessage());
        }

        return null;
    }
    
    public static BufferedImage LoadImage(String fileName){
        try {
            BufferedImage image = ImageIO.read(new File(Main.class.getClassLoader().getResource("Spritesheets/" + fileName).getFile()));
            
            return image;
        } catch (IOException ex) {
            System.out.println("LoadImage Error: " + ex.getMessage());
        }
        
        
        return null;
    }
}
