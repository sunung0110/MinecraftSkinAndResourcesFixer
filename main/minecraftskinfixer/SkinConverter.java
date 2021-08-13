package minecraftskinandresourcesfixer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SkinConverter {
	
	public static BufferedImage convert(BufferedImage original, boolean isSlim, boolean flipBottoms, SuperSimpleLogger logger, String connectionId) {
		if(original.getHeight() <= 32 && !isSlim && !flipBottoms) {
			logger.log("[Skin Converter] " + connectionId + "Skin is compatible - not converting.");
			return original;
		}
		BufferedImage converted = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = converted.createGraphics();
		if(isSlim) {
			logger.log("[Skin Converter] " + connectionId + "Converting from slim to normal model");
			//head and second layer of head
			g.drawImage(original.getSubimage(0, 0, 64, 16), 0, 0, null);
			//legs, body and
			//arm: right, front pt. 1 and top pt. 1
			g.drawImage(original.getSubimage(0, 16, 46, 16), 0, 16, null);
			//arm: front pt. 2 and top pt. 2
			g.drawImage(original.getSubimage(45, 16, 2, 16), 46, 16, null);
			//arm: left, bottom pt. 1 and back pt. 1
			g.drawImage(original.getSubimage(47, 16, 6, 16), 48, 16, null);
			//arm: bottom pt. 2
			g.drawImage(original.getSubimage(48, 16, 2, 4), 50, 16, null);
			//arm: back pt. 2
			g.drawImage(original.getSubimage(52, 20, 2, 12), 54, 20, null);
		} else {
			g.drawImage(original.getSubimage(0, 0, 64, 32), 0, 0, null);
		}
		if(original.getHeight() >= 64) {
			logger.log("[Skin Converter] " + connectionId + "Merging second layer from 1.8+ skin onto first layer");
			if(isSlim) {
				//legs, body and
				//arm: right, front pt. 1 and top pt. 1
				g.drawImage(original.getSubimage(0, 32, 46, 16), 0, 16, null);
				//arm: front pt. 2 and top pt. 2
				g.drawImage(original.getSubimage(45, 32, 2, 16), 46, 16, null);
				//arm: left, bottom pt. 1 and back pt. 1
				g.drawImage(original.getSubimage(47, 32, 6, 16), 48, 16, null);
				//arm: bottom pt. 2
				g.drawImage(original.getSubimage(48, 32, 2, 4), 50, 16, null);
				//arm: back pt. 2
				g.drawImage(original.getSubimage(52, 36, 2, 12), 54, 20, null);
			} else {
				g.drawImage(original.getSubimage(0, 32, 56, 16), 0, 16, null);
			}
		}
		if(flipBottoms) {
			logger.log("[Skin Converter]" + connectionId + "Flipping bottom pieces");
			BufferedImage buffer = converted;
			converted = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
			g = converted.createGraphics();
			//head top
			g.drawImage(buffer.getSubimage(8, 0, 8, 8), 8, 0, null);
			//head2 top
			g.drawImage(buffer.getSubimage(40, 0, 8, 8), 40, 0, null);
			//head and head2 right, front, left and back
			g.drawImage(buffer.getSubimage(0, 8, 64, 8), 0, 8, null);
			//legs top
			g.drawImage(buffer.getSubimage(4, 16, 4, 4), 4, 16, null);
			//body top
			g.drawImage(buffer.getSubimage(20, 16, 8, 4), 20, 16, null);
			//arms top
			g.drawImage(buffer.getSubimage(44, 16, 4, 4), 44, 16, null);
			//legs, body and arms right, front, left and back
			g.drawImage(buffer.getSubimage(0, 20, 56, 12), 0, 20, null);
			for(int i = 0; i < 8; i++) {
				//head bottom
				g.drawImage(buffer.getSubimage(16, i, 8, 1), 16, 7 - i, null);
				//head2 bottom
				g.drawImage(buffer.getSubimage(48, i, 8, 1), 48, 7 - i, null);
			}
			for(int i = 0; i < 4; i++) {
				//legs bottom
				g.drawImage(buffer.getSubimage(8, 16 + i, 4, 1), 8, 16 + 3 - i, null);
				//body bottom
				g.drawImage(buffer.getSubimage(28, 16 + i, 8, 1), 28, 16 + 3 - i, null);
				//arms bottom
				g.drawImage(buffer.getSubimage(48, 16 + i, 4, 1), 48, 16 + 3 - i, null);
			}
		}
		return converted;
	}

}
