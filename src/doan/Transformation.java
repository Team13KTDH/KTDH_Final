package doan;

import java.awt.Point;

public class Transformation {

	int X;
	int Y;
	int result[];
	int des[];
	int hsbd;
	int gocquay = 90;
	int sx=3, sy=2;

	public Transformation() {
		// TODO Auto-generated constructor stub
	}

	public Point nhanMT2(int[] mang, int[][] matran) {
		int[] mangtam;
		mangtam = new int[3];
		int dem = 0;
		for (int i = 0; i < 3; i++) {
			mangtam[i] = mang[0] * matran[0][dem] + mang[1] * matran[1][dem] + mang[2] * matran[2][dem];
			dem++;
		}
		des = new int[] { mangtam[0], mangtam[1] };
		Point pt = new Point(mangtam[0], mangtam[1]);
		return pt;
	}

	public Point nhanMT2(float[] mang, float[][] matran) {
		float[] mangtam;
		mangtam = new float[3];
		int dem = 0;
		for (int i = 0; i < 3; i++) {
			mangtam[i] = mang[0] * matran[0][dem] + mang[1] * matran[1][dem] + mang[2] * matran[2][dem];
			dem++;
		}
		des = new int[] { (int) Math.round(mangtam[0]), (int) Math.round(mangtam[1]) };
		Point pt = new Point((int) Math.round(mangtam[0]), (int) Math.round(mangtam[1]));
		return pt;
	}

	public Point nhanMT2(double[] mang, double[][] matran) {
		double[] mangtam;
		mangtam = new double[3];
		int dem = 0;
		for (int i = 0; i < 3; i++) {
			mangtam[i] = mang[0] * matran[0][dem] + mang[1] * matran[1][dem] + mang[2] * matran[2][dem];
			dem++;
		}
		des = new int[] { (int) Math.round(mangtam[0]), (int) Math.round(mangtam[1]) };
		Point pt = new Point((int) Math.round(mangtam[0]), (int) Math.round(mangtam[1]));
		return pt;
	}

	public Point translation(int x, int y, int vectorX, int vectorY) {
		int[][] matran1 = { { 1, 0, 0 }, { 0, 1, 0 }, { vectorX, vectorY, 1 } };
		int[] mang = { x, y, 1 };
		Point pt1 = nhanMT2(mang, matran1);
		Point kq = new Point(Math.round(pt1.x), Math.round(pt1.y));
		return kq;
	}

	public Point scaling(int x, int y, double scaleX, double scaleY) {
		double[][] matran1 = { { scaleX, 0, 0 }, { 0, scaleY, 0 }, { 0, 0, 1 } };
		double[] mang = { x, y, 1 };
		Point pt1 = nhanMT2(mang, matran1);
		Point kq = new Point(Math.round(pt1.x), Math.round(pt1.y));
		return kq;
	}

//	public void rotation(int x, int y, double degrees) {
//		// convert degrees to radians
//		double radians = Math.toRadians(degrees);
//		// sin() method to get the sine value
//		double sinValue = Math.sin(radians);
//		double cosValue = Math.cos(radians);
//		int transMatrix[][] = new int[][] { { (int) cosValue, (int) sinValue, 0 },
//				{ (int) -sinValue, (int) cosValue, 0 }, { 0, 0, 1 } };
//		int inputMatrix[] = new int[] { x, y, 1 };
//		this.nhanMT2(inputMatrix, transMatrix);
//	}

	public Point doixung(Point d1, Point d2) {
		int x1, y1; // Toa do diem lay doi xung
		int x2, y2; // Toa do diem goc
		x1 = d1.x;
		y1 = d1.y;
		x2 = d2.x;
		y2 = d2.y;

		int[][] matran1;
		int[][] matran2;
		int[] mang;
		mang = new int[3];
		matran1 = new int[3][3];
		matran2 = new int[3][3];

		// Ma tran tinh tien ve tam I rki sau do lay doi xung p' qua tam I
		matran1[0][0] = -1;
		matran1[0][1] = 0;
		matran1[0][2] = 0;
		matran1[1][0] = 0;
		matran1[1][1] = -1;
		matran1[1][2] = 0;
		matran1[2][0] = x2;
		matran1[2][1] = y2;
		matran1[2][2] = 1;
		// mang[] = {x1,y1,1}
		mang[0] = x1;
		mang[1] = y1;
		mang[2] = 1;
		Point pt1 = nhanMT2(mang, matran1);

		matran2[0][0] = 1;
		matran2[0][1] = 0;
		matran2[0][2] = 0;
		matran2[1][0] = 0;
		matran2[1][1] = 1;
		matran2[1][2] = 0;
		matran2[2][0] = x2;
		matran2[2][1] = y2;
		matran2[2][2] = 1;

		mang[0] = pt1.x;
		mang[1] = pt1.y;
		mang[2] = 1;
		Point pt2 = nhanMT2(mang, matran2);
		Point kq = new Point(Math.round(pt2.x), Math.round(pt2.y));
		return kq;
	}

	public Point thuPhong(int x, int y, double scaleX, double scaleY) {
		double[][] matran1 = { { scaleX, 0, 0 }, { 0, scaleY, 0 }, { 0, 0, 1 } };
		double[] mang = { x, y, 1 };
		Point pt1 = nhanMT2(mang, matran1);
		Point kq = new Point(Math.round(pt1.x), Math.round(pt1.y));
		return kq;
	}

	// DOI XUNG QUA DUONG THANG BAT KI
	// Tim diem doi xung cua 1 diem (x,y) qua 1 duong thang Y=mX+b
	public Point Doixung(double m, int b, Point diem) {
		float s = 0, c = 0;
		float[][] matran1;
		float[][] matran2;
		float[][] matran3;
		float[][] matran;
		float[] mang;
		// vi du x=1, y=1
		int x = 1, y = 1;

		mang = new float[3];
		matran1 = new float[3][3];
		matran2 = new float[3][3];
		matran3 = new float[3][3];
		matran = new float[3][3];

		float gocQuay = -1 * (float) (Math.atan(m));
		// gocQuay = -arctan(m)
		c = (float) (Math.cos(gocQuay));
		s = (float) (Math.sin(gocQuay));

		// ma tran tinh tien duong thang ve thanh duong thang di qua goc t/d O...
		matran[0][0] = 1;
		matran[0][1] = 0;
		matran[0][2] = 0;
		matran[1][0] = 0;
		matran[1][1] = 1;
		matran[1][2] = 0;
		matran[2][0] = 0;
		matran[2][1] = -b;
		matran[2][2] = 1;
		mang[0] = x;
		mang[1] = y;
		mang[2] = 1;
		Point pt = nhanMT2(mang, matran);

		// ma tran quay duong thang ve trung truc Ox
		matran1[0][0] = c;
		matran1[0][1] = s;
		matran1[0][2] = 0;
		matran1[1][0] = -1 * s;
		matran1[1][1] = c;
		matran1[1][2] = 0;
		matran1[2][0] = 0;
		matran1[2][1] = 0;
		matran1[2][2] = 1;
		mang[0] = pt.x;
		mang[1] = pt.y;
		mang[2] = 1;
		Point pt1 = nhanMT2(mang, matran1);

		// Ma tran cua phep lay doi xung qua truc 0x
		matran2[0][0] = 1;
		matran2[0][1] = 0;
		matran2[0][2] = 0;
		matran2[1][0] = 0;
		matran2[1][1] = -1;
		matran2[1][2] = 0;
		matran2[2][0] = 0;
		matran2[2][1] = 0;
		matran2[2][2] = 1;
		mang[0] = pt1.x;
		mang[1] = pt1.y;
		mang[2] = 1;
		Point pt2 = nhanMT2(mang, matran2);

		// Ma tran cua phep quay nguoc lai vi tri cu
		matran3[0][0] = c;
		matran3[0][1] = -s;
		matran3[0][2] = 0;
		matran3[1][0] = s;
		matran3[1][1] = c;
		matran3[1][2] = 0;
		matran3[2][0] = 0;
		matran3[2][1] = 0;
		matran3[2][2] = 1;
		mang[0] = pt2.x;
		mang[1] = pt2.y;
		mang[2] = 1;
		Point pt3 = nhanMT2(mang, matran3);

		// Ma tran cua phep tinh tien diem do ve vi tri ban dau
		matran[0][0] = 1;
		matran[0][1] = 0;
		matran[0][2] = 0;
		matran[1][0] = 0;
		matran[1][1] = 1;
		matran[1][2] = 0;
		matran[2][0] = 0;
		matran[2][1] = b;
		matran[2][2] = 1;
		mang[0] = pt3.x;
		mang[1] = pt3.y;
		mang[2] = 1;
		Point pt4 = nhanMT2(mang, matran);
		Point kq = new Point(Math.round(pt4.x), Math.round(pt4.y));
		return kq;
	}

	public Point biendangOy(Point diem, int xx) {
		// vi du x=1,y=1
		int x = 1, y = 1;
		int shxy = hsbd; // he so bien doi
		double[][] matran0;
		double[][] matran1;
		double[][] matran2;
		double[] mang;

		mang = new double[3];
		matran0 = new double[3][3];
		matran1 = new double[3][3];
		matran2 = new double[3][3];

		matran0[0][0] = 1;
		matran0[0][1] = 0;
		matran0[0][2] = 0;
		matran0[1][0] = 0;
		matran0[1][1] = 1;
		matran0[1][2] = 0;
		matran0[2][0] = -xx;
		matran0[2][1] = 0;
		matran0[2][2] = 1;
		mang[0] = x;
		mang[1] = y;
		mang[2] = 1;
		Point pt0 = nhanMT2(mang, matran0);

		matran1[0][0] = 1;
		matran1[0][1] = shxy;
		matran1[0][2] = 0;
		matran1[1][0] = 0;
		matran1[1][1] = 1;
		matran1[1][2] = 0;
		matran1[2][0] = 0;
		matran1[2][1] = 0;
		matran1[2][2] = 1;
		mang[0] = pt0.x;
		mang[1] = pt0.y;
		mang[2] = 1;
		Point pt1 = nhanMT2(mang, matran1);

		matran2[0][0] = 1;
		matran2[0][1] = 0;
		matran2[0][2] = 0;
		matran2[1][0] = 0;
		matran2[1][1] = 1;
		matran2[1][2] = 0;
		matran2[2][0] = xx;
		matran2[2][1] = 0;
		matran2[2][2] = 1;
		mang[0] = pt1.x;
		mang[1] = pt1.y;
		mang[2] = 1;
		Point pt2 = nhanMT2(mang, matran2);
		Point kq = new Point(Math.round(pt2.x), Math.round(pt2.y));
		return kq;
	}

	// Bien dang diem (x,y) theo phuong duong thang y=mx+b, he so bien dang la shxy
	public Point BienDang(double m, int b, Point diem) {
		// vi du x=1, y=1
		int x = diem.x;
		int y = diem.y;
		int shxy = hsbd;// he so bien dang
		double[][] matran0;
		double[][] matran1;
		double[][] matran2;
		double[][] matran3;
		double[][] matran4;
		double[] mang;
		double c, s, _c, _s;
		mang = new double[3];
		matran0 = new double[3][3];
		matran1 = new double[3][3];
		matran2 = new double[3][3];
		matran3 = new double[3][3];
		matran4 = new double[3][3];
		double gocQuay = -1 * Math.atan(m);

		matran0[0][0] = 1;
		matran0[0][1] = 0;
		matran0[0][2] = 0;
		matran0[1][0] = 0;
		matran0[1][1] = 1;
		matran0[1][2] = 0;
		matran0[2][0] = 0;
		matran0[2][1] = -b;
		matran0[2][2] = 1;
		mang[0] = x;
		mang[1] = y;
		mang[2] = 1;
		Point pt0 = nhanMT2(mang, matran0);

		// Ma tran quay quanh goc toa do mot goc a;
		s = Math.sin(gocQuay);
		c = Math.cos(gocQuay);
		matran1[0][0] = c;
		matran1[0][1] = s;
		matran1[0][2] = 0;
		matran1[1][0] = -1 * s;
		matran1[1][1] = c;
		matran1[1][2] = 0;
		matran1[2][0] = 0;
		matran1[2][1] = 0;
		matran1[2][2] = 1;
		mang[0] = pt0.x;
		mang[1] = pt0.y;
		mang[2] = 1;
		Point pt1 = nhanMT2(mang, matran1);

		matran2[0][0] = 1;
		matran2[0][1] = 0;
		matran2[0][2] = 0;
		matran2[1][0] = shxy;
		matran2[1][1] = 1;
		matran2[1][2] = 0;
		matran2[2][0] = 0;
		matran2[2][1] = 0;
		matran2[2][2] = 1;
		mang[0] = pt1.x;
		mang[1] = pt1.y;
		mang[2] = 1;
		Point pt2 = nhanMT2(mang, matran2);

		_s = -s;
		_c = c;
		matran3[0][0] = _c;
		matran3[0][1] = _s;
		matran3[0][2] = 0;
		matran3[1][0] = -1 * _s;
		matran3[1][1] = _c;
		matran3[1][2] = 0;
		matran3[2][0] = 0;
		matran3[2][1] = 0;
		matran3[2][2] = 1;
		mang[0] = pt2.x;
		mang[1] = pt2.y;
		mang[2] = 1;
		Point pt3 = nhanMT2(mang, matran3);

		matran4[0][0] = 1;
		matran4[0][1] = 0;
		matran4[0][2] = 0;
		matran4[1][0] = 0;
		matran4[1][1] = 1;
		matran4[1][2] = 0;
		matran4[2][0] = 0;
		matran4[2][1] = b;
		matran4[2][2] = 1;
		mang[0] = pt3.x;
		mang[1] = pt3.y;
		mang[2] = 1;
		Point pt4 = nhanMT2(mang, matran4);
		Point kq = new Point(Math.round(pt4.x), Math.round(pt4.y));
		return kq;
	}

	// Quay 1 diem (x,y)quanh diem(xo,yo) 1 goc a;
	public Point Quay(Point d1, Point d2) {
		int x = d1.x;
		int y = d1.y;
		int xo = d2.x;
		int yo = d2.y;
		int a = gocquay;
		double[][] matran1;
		double[][] matran2;
		double[][] matran3;
		double[] mang;
		double c, s;
		mang = new double[3];
		matran1 = new double[3][3];
		matran2 = new double[3][3];
		matran3 = new double[3][3];

		// ma tran tinh tien (xo,yo)ve goc toa do
		matran1[0][0] = 1;
		matran1[0][1] = 0;
		matran1[0][2] = 0;
		matran1[1][0] = 0;
		matran1[1][1] = 1;
		matran1[1][2] = 0;
		matran1[2][0] = -xo;
		matran1[2][1] = -yo;
		matran1[2][2] = 1;
		mang[0] = x;
		mang[1] = y;
		mang[2] = 1;
		Point pt = nhanMT2(mang, matran1);

		// Ma tran quay quanh goc toa do mot goc a;
		s = Math.sin((Math.PI * a) / 180);
		c = Math.cos((Math.PI * a) / 180);
		matran2[0][0] = c;
		matran2[0][1] = s;
		matran2[0][2] = 0;
		matran2[1][0] = -1 * s;
		matran2[1][1] = c;
		matran2[1][2] = 0;
		matran2[2][0] = 0;
		matran2[2][1] = 0;
		matran2[2][2] = 1;
		mang[0] = pt.x;
		mang[1] = pt.y;
		mang[2] = 1;
		Point pt1 = nhanMT2(mang, matran2);

		// ma tran doi diem ve toa do cu
		matran3[0][0] = 1;
		matran3[0][1] = 0;
		matran3[0][2] = 0;
		matran3[1][0] = 0;
		matran3[1][1] = 1;
		matran3[1][2] = 0;
		matran3[2][0] = xo;
		matran3[2][1] = yo;
		matran3[2][2] = 1;
		mang[0] = pt1.x;
		mang[1] = pt1.y;
		mang[2] = 1;
		Point pt2 = nhanMT2(mang, matran3);
		Point kq = new Point(Math.round(pt2.x), Math.round(pt2.y));
		return kq;
	}

	public Point tyle(Point d1, Point d2) {
		int x1, y1, xo, yo;
		x1 = d1.x;
		y1 = d1.y;
		xo = d2.x;
		yo = d2.y;
		int[][] matran1;
		int[][] matran2;
		int[][] matran3;
		int[] mang;
		mang = new int[3];
		matran1 = new int[3][3];
		matran2 = new int[3][3];
		matran3 = new int[3][3];

		// Ma tran tinh tien ve tam O
		matran1[0][0] = 1;
		matran1[0][1] = 0;
		matran1[0][2] = 0;
		matran1[1][0] = 0;
		matran1[1][1] = 1;
		matran1[1][2] = 0;
		matran1[2][0] = -xo;
		matran1[2][1] = -yo;
		matran1[2][2] = 1;
		mang[0] = x1;
		mang[1] = y1;
		mang[2] = 1;
		Point pt1 = nhanMT2(mang, matran1);
		// Ma tran ty le
		matran2[0][0] = sx;
		matran2[0][1] = 0;
		matran2[0][2] = 0;
		matran2[1][0] = 0;
		matran2[1][1] = sy;
		matran2[1][2] = 0;
		matran2[2][0] = 0;
		matran2[2][1] = 0;
		matran2[2][2] = 1;
		mang[0] = pt1.x;
		mang[1] = pt1.y;
		mang[2] = 1;
		Point pt2 = nhanMT2(mang, matran2);
		// Ma tran tinh tien nguoc ve vi tri cu
		matran3[0][0] = 1;
		matran3[0][1] = 0;
		matran3[0][2] = 0;
		matran3[1][0] = 0;
		matran3[1][1] = 1;
		matran3[1][2] = 0;
		matran3[2][0] = xo;
		matran3[2][1] = yo;
		matran3[2][2] = 1;
		mang[0] = pt2.x;
		mang[1] = pt2.y;
		mang[2] = 1;
		Point pt3 = nhanMT2(mang, matran3);
		Point kq = new Point(Math.round(pt3.x), Math.round(pt3.y));
		return kq;
	}

}
