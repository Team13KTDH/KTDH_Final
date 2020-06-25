package doan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import static java.lang.Math.abs;

public class Object3D {
	// put pixel in eclip
	public void plot(int xc, int yc, int x, int y, Graphics2D g2d, int temp) {
		g2d.setStroke(new BasicStroke(5));
		if (temp == 0 || temp == 1 || temp == 2)
			g2d.setColor(Color.BLUE);
		else
			g2d.setColor(Color.WHITE);
		g2d.drawLine(xc + x, yc - y, xc + x, yc - y);
		g2d.drawLine(xc - x, yc - y, xc - x, yc - y);
		g2d.setColor(Color.BLUE);
		g2d.drawLine(xc + x, yc + y, xc + x, yc + y);
		g2d.drawLine(xc - x, yc + y, xc - x, yc + y);
	}

	// draw eclip with midpoit althorithm
	public void eclipMidpoint(int xc, int yc, int a, int b, Graphics2D g2d) {
		int x, y, fx, fy, a2, b2, p, temp = 0;
		x = 0;
		y = b;
		a2 = a * a;
		b2 = b * b;
		fx = 0;
		fy = 2 * a2 * y;
		plot(xc, yc, x, y, g2d, temp);
		// chia lam 6 so 0,1,2,3,4,5
		if (temp <= 5)
			temp++;
		else
			temp = 0;
		p = (int) (b2 - (a2 * b) + (0.25 * a2)); // p = b2-a2*b+a2/4
		while (fx < fy) {
			x++;
			fx += 2 * b2;
			// Delay(50);
			if (p < 0) {
				p += b2 * (2 * x + 3);// p=p + b2*(2x +3)
			} else {
				y--;
				p += b2 * (2 * x + 3) + a2 * (2 - 2 * y);// p=p +b2(2x +3) +a2(2-2y)
				fy -= 2 * a2;
			}
			plot(xc, yc, x, y, g2d, temp);
			if (temp <= 5)
				temp++;
			else
				temp = 0;
		}
		p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
		//
		while (y > 0) {
			y--;
			fy -= 2 * a2;
			// delay(50);
			if (p >= 0) {
				p += a2 * (3 - 2 * y); // p=p +a2(3-2y)
			} else {
				x++;
				fx += 2 * b2;
				p += b2 * (2 * x + 2) + a2 * (3 - 2 * y);// p=p+ b2(2x +2) + a2(3-2y)
			}
			plot(xc, yc, x, y, g2d, temp);
			if (temp <= 5)
				temp++;
			else
				temp = 0;
		}
	}

	public void drawCone3D(int ox, int oy, int oz, int r, int h, Graphics2D g2d) {

		// bien doi cabinet
		ox = Math.round((float) (ox - Math.sqrt(oz) / 4));
		oy = Math.round((float) (oy + Math.sqrt(oz) / 4));
		// bien doi tao do thuc cua may tinh
		ox = ox * 5 + 375;
		oy = 375 - oy * 5;
		h *= 5;
		r *= 5;
		g2d.setColor(Color.BLUE);

		// ve hinh eclip
		Object3D eclip = new Object3D();
		eclip.eclipMidpoint(ox, oy, r, r / 2, g2d);
		// two line can visible
		/// g2d.setStroke(new BasicStroke(1));
		duongThang(g2d, ox, oy - h, ox - r - 2, oy);
		duongThang(g2d, ox, oy - h, ox + r, oy);
		// line index unvisible
		// g2d.setStroke(bs1);
		netDut(g2d, ox, oy - h, ox, oy);
		// g2d.dispose();
		netDut(g2d, ox, oy, ox + r, oy);
		// ve cai ten
		// tam O
		g2d.setFont(new Font("Arial", Font.BOLD, 15));
		g2d.drawString("O", ox - 25, oy);
		g2d.drawString("A", ox, oy - h - 10);
		g2d.drawString("r", (ox + r / 2), oy - 10);
		g2d.drawString("h", ox + 10, oy - (h / 2));

	} // váº½ dÆ°á»�ng tháº³ng

	public void duongThang(Graphics2D g2d, int a, int b, int c, int d) {
		int temp;
		if (abs(c - a) > abs(d - b)) {
			if (a > c) {
				temp = a;
				a = c;
				c = temp;
				temp = b;
				b = d;
				d = temp;
			}
			int x = a;
			float y = b;
			float m = (float) (d - b) / (float) (c - a);
//			int count = 0;
			while (x <= c) {
				y = y + m;
//					if (count >= 8 && count <= 14) {
//						x++;
//						if (count == 14) {
//							count = 0;
//							continue;
//						}
//						count++;
//						continue;
//					}
				g2d.setColor(Color.BLUE);
				g2d.setStroke(new BasicStroke(5));
				g2d.drawLine(x, Math.round(y), x, Math.round(y));
				x++;
//				count++;
			}
		}
		if (a == c && b == d) {
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(5));
			g2d.drawLine(a, b, a, b);
		} else {
			if (b > d) {
				temp = a;
				a = c;
				c = temp;
				temp = b;
				b = d;
				d = temp;
			}
			float x = a;
			int y = b;
			float m = (float) (c - a) / (float) (d - b);
			int count = 0;
			while (y <= d) {
				x = x + m;
//					if (count >= 8 && count <= 14) {
//						y++;
//						if (count == 14) {
//							count = 0;
//							continue;
//						}
//						count++;
//						continue;
//					}
				g2d.setColor(Color.BLUE);
				g2d.setStroke(new BasicStroke(5));
				g2d.drawLine(Math.round(x), y, Math.round(x), y);
				y++;
				count++;
			}
		}

	}

	// net dut
	public void netDut(Graphics2D g2d, int a, int b, int c, int d) {
		{
			int temp;

			if (abs(c - a) > abs(d - b)) {
				if (a > c) {
					temp = a;
					a = c;
					c = temp;
					temp = b;
					b = d;
					d = temp;
				}
				int x = a;
				float y = b;
				float m = (float) (d - b) / (float) (c - a);
				int count = 0;
				while (x <= c) {
					y = y + m;
					if (count >= 8 && count <= 14) {
						x++;
						if (count == 14) {
							count = 0;
							continue;
						}
						count++;
						continue;
					}
					g2d.setColor(Color.BLUE);
					g2d.setStroke(new BasicStroke(5));
					g2d.drawLine(x, Math.round(y), x, Math.round(y));
					x++;
					count++;
				}
			}
			if (a == c && b == d) {
				g2d.setColor(Color.BLUE);
				g2d.setStroke(new BasicStroke(5));
				g2d.drawLine(a, b, a, b);
			} else {
				if (b > d) {
					temp = a;
					a = c;
					c = temp;
					temp = b;
					b = d;
					d = temp;
				}
				float x = a;
				int y = b;
				float m = (float) (c - a) / (float) (d - b);
				int count = 0;
				while (y <= d) {
					x = x + m;
					if (count >= 8 && count <= 14) {
						y++;
						if (count == 14) {
							count = 0;
							continue;
						}
						count++;
						continue;
					}
					g2d.setColor(Color.BLUE);
					g2d.setStroke(new BasicStroke(5));
					g2d.drawLine(Math.round(x), y, Math.round(x), y);
					y++;
					count++;
				}
			}
		}
	}

	void Put8Pixel(Graphics2D g2d, int x, int y, int a, int b) {
		g2d.setStroke(new BasicStroke(6));
		g2d.setColor(Color.BLUE);
		y = 375 - y * 5;
		x = 375 - x * 5;
		// g2d.drawLine(375, 375, 375, 375);
		g2d.setColor(Color.BLUE);
		g2d.drawLine(x + a * 5, y - b * 5, x + a * 5, y - b * 5);
		g2d.drawLine(y + a * 5, x - b * 5, y + a * 5, x - b * 5);
		g2d.drawLine(x + a * 5, 750 - y - b * 5, x + a * 5, 750 - y - b * 5);
		g2d.drawLine(750 - y + a * 5, x - b * 5, 750 - y + a * 5, x - b * 5);
		g2d.drawLine(750 - y + a * 5, 750 - x - b * 5, 750 - y + a * 5, 750 - x - b * 5);
		g2d.drawLine(750 - x + a * 5, 750 - y - b * 5, 750 - x + a * 5, 750 - y - b * 5);
		g2d.drawLine(750 - x + a * 5, y - b * 5, 750 - x + a * 5, y - b * 5);
		g2d.drawLine(y + a * 5, 750 - x - b * 5, y + a * 5, 750 - x - b * 5);
	}

	// ve hinh tron (Khuong)
	public void drawCircle(Graphics2D g2d, int R, int a, int b) {
		float p;
		int d = 0;
		int x = 0, y = R;
		Put8Pixel(g2d, x, y, a, b);
		p = 5 / 4 - R;
		while (x < y) {
			if (p < 0)
				p += 2 * x + 3;
			else {
				p += 2 * (x - y) + 5;
				y--;
			}
			x++;
			if (d % 13 != 0)
				Put8Pixel(g2d, x, y, a, b);
			d++;
		}
	}

	// ve hinh cau (Khuong)
	public void drawSphere(int x, int y, int z, int r, Graphics2D g2d) {
		int a1 = Math.round((float) (x - z * Math.sqrt(2) / 4));
		int b1 = Math.round((float) (y + z * Math.sqrt(2) / 4));
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(6));
		g2d.drawLine(a1 * 5 + 375, 375 - b1 * 5, a1 * 5 + 375, 375 - b1 * 5);
		eclipMidpoint(a1 * 5 + 375, 375 - b1 * 5, r * 5, (int) Math.round(r / (2 * Math.sqrt(2))) * 5, g2d);
		drawCircle(g2d, r, a1, b1);
	}

	public void drawLapPhuong(int x, int y, int z, int a, Graphics2D g2d) {
		int x0 = Math.round((float) (x - z * Math.sqrt(2) / 4));
		int y0 = Math.round((float) (y + z * Math.sqrt(2) / 4));
//		 g2d.drawLine(375,375,-133,133);
//		 g2d.drawLine(375,375,-265,265);
		g2d.drawLine(x0 * 5 + 375, 375 - y0 * 5, x0 * 5 + 375, 375 - y0 * 5);
		int a1 = 375 + 5 * Math.round((float) (x + a - z * Math.sqrt(2) / 4));
		int b1 = 375 - 5 * Math.round((float) (y + z * Math.sqrt(2) / 4));

		int a2 = 375 + 5 * Math.round((float) (x + a - (z + a) * Math.sqrt(2) / 4));
		int b2 = 375 - 5 * Math.round((float) (y + (z + a) * Math.sqrt(2) / 4));

		int a3 = 375 + 5 * Math.round((float) (x - (z + a) * Math.sqrt(2) / 4));
		int b3 = 375 - 5 * Math.round((float) (y + (z + a) * Math.sqrt(2) / 4));

		int a4 = 375 + 5 * Math.round((float) (x + a - z * Math.sqrt(2) / 4));
		int b4 = 375 - 5 * Math.round((float) (y + a + z * Math.sqrt(2) / 4));

		int a5 = 375 + 5 * Math.round((float) (x - z * Math.sqrt(2) / 4));
		int b5 = 375 - 5 * Math.round((float) (y + a + z * Math.sqrt(2) / 4));

		int a6 = 375 + 5 * Math.round((float) (x - (z + a) * Math.sqrt(2) / 4));
		int b6 = 375 - 5 * Math.round((float) (y + a + (z + a) * Math.sqrt(2) / 4)); // y+a

		int a7 = 375 + 5 * Math.round((float) (x + a - (z + a) * Math.sqrt(2) / 4));
		int b7 = 375 - 5 * Math.round((float) (y + a + (z + a) * Math.sqrt(2) / 4));

		duongThang(g2d, a1, b1, a2, b2);
		duongThang(g2d, a2, b2, a3, b3);
		duongThang(g2d, a3, b3, a6, b6);
		duongThang(g2d, a6, b6, a7, b7);
		duongThang(g2d, a7, b7, a4, b4);
		duongThang(g2d, a4, b4, a5, b5);
		duongThang(g2d, a5, b5, a6, b6);
		duongThang(g2d, a1, b1, a4, b4);
	}
}
