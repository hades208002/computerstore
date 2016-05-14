package com.computerstore.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * generate verification codes
 * @author carlo
 *
 */
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 2273682797053328578L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int width = 120;
		int height = 30;
		// get canvas
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// get brush
		Graphics g = image.getGraphics();
		// set color as "gray"
		g.setColor(Color.GRAY);
		// draw a filled rectangle on the canvas
		g.fillRect(0, 0, width, height);
		
		// set character sets
		String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		int x = 20;
		int y = 20;
		// set font color
		g.setColor(Color.YELLOW);
		// set font
		g.setFont(new Font("HELVETICA", Font.BOLD, 20));
		// loop for 4 letters
		for(int i=1;i<=4;i++){
			// get letter
			char ch = str.charAt(new Random().nextInt(str.length()));
			// draw the letter on canvas
			g.drawString(ch+"", x, y);
			x += 20;
		}
		
		// set line color
		g.setColor(Color.BLUE);
		// draw line
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		for(int i=0;i<=6;i++){
			x1 = new Random().nextInt(width);
			y1 = new Random().nextInt(height);
			x2 = new Random().nextInt(width);
			y2 = new Random().nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		
		
		// output the image
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
