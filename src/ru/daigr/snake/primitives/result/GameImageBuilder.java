package ru.daigr.snake.primitives.result;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import ru.daigr.snake.primitives.Cell;
import ru.daigr.snake.primitives.PlayingCourt;
import ru.daigr.snake.primitives.Snake;

public class GameImageBuilder {	
	
	private static int CELL_SIZE = 50;
	
	private static String BASE_DIR = "./skins/";
	private static String EMPTY_CELL = "empty_cell.jpg";
	private static String HEAD_CELL = "head_cell.jpg";
	private static String BODY_CELL = "body_cell.jpg";
	private static String EAT_CELL = "eat_cell.jpg";		
	
	public static void BuildSnapshotImage(Snake aSnake, PlayingCourt aCourt, Cell anEatCell, long anId) throws IOException {
		
		BufferedImage result = new BufferedImage(CELL_SIZE*aCourt.getSize(), CELL_SIZE*aCourt.getSize(), BufferedImage.TYPE_INT_RGB);
		Graphics canvas = result.getGraphics();
		
		ArrayList<Cell> courtCells = aCourt.getCells();	
		LinkedList<Cell> snakeCells = aSnake.getCells();
		
		for (int i = 0; i < aCourt.getSize()*aCourt.getSize(); i++){
			BufferedImage bi = null;
			Cell current = courtCells.get(i);
			if (current.equals(aSnake.getHeadCell())){
				bi = ImageIO.read(new File(BASE_DIR + HEAD_CELL));
				bi = rotate(bi,aSnake.getHeadOrientation());				
			} else if (snakeCells.contains(current)){
				bi = ImageIO.read(new File(BASE_DIR + BODY_CELL));
			} else if (anEatCell.equals(current)){
				bi = ImageIO.read(new File(BASE_DIR + EAT_CELL));
			} else {
				bi = ImageIO.read(new File(BASE_DIR + EMPTY_CELL));
			}
			int x = i%aCourt.getSize();
			int y = i/aCourt.getSize();
			
			canvas.drawImage(bi, x*CELL_SIZE, y*CELL_SIZE, null);
			
		}
		
		ImageIO.write(result, "png", new File("result" + anId +  ".png"));
		
	}
	
	public static BufferedImage rotate(BufferedImage image, int orientation) {
		 AffineTransform transform = new AffineTransform();
		 
		 switch (orientation) {
			 case 0: {
				 transform.rotate(0, image.getWidth()/2, image.getHeight()/2);
				 break;
			 }
			 case 1: {
				 transform.rotate(Math.PI/2, image.getWidth()/2, image.getHeight()/2);
				 break;	 
			 }
			 case 2: {
				 transform.rotate(Math.PI, image.getWidth()/2, image.getHeight()/2);
				 break;
			 }
			 case 3: {
				 transform.rotate(3*Math.PI/2, image.getWidth()/2, image.getHeight()/2);
				 break;
			 }
		 }		 		
		 AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		 image = op.filter(image, null);
		 
		 return image;
	}

}
