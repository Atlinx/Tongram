//******************************************************************************
// Tongram.java	
//
//
//  Copyright (c) 1996-1998 Harry Yu Tong All Rights Reserved.
// 
//******************************************************************************

import java.util.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.applet.Applet;
//import msrdo20.*;
//import com.ms.com.*;
//import TongramFrame;

class Chip {
	int index;
    double x;
    double y;

    double mx;
    double my;

    double dx;
    double dy;
    int dRotates=0;
    int dFlips=0;

    boolean fixed;

	boolean completed;

    double dxx[] = new double[30];
    double dyy[] = new double[30];

	double dcx[] = new double[30];
    double dcy[] = new double[30];

    int xx[] = new int[30];
    int yy[] = new int[30];

    int cx[] = new int[30];
    int cy[] = new int[30];

    int xf[] = new int[30];
    int yf[] = new int[30];

    int fx;
    int fy;
    int fxx[] = new int[30];
    int fyy[] = new int[30];

    double dfx[] = new double[30];
    double dfy[] = new double[30];

    int npoints;
    int nRotates=0;
    int nFlips=0;

    int fRotates;
    int fFlips;

    String lbl;


    public void move(double delta_x, double delta_y) {
	  for (int i = 0; i < npoints; i++) {
		dxx[i] += (double)delta_x;
		dyy[i] += (double)delta_y;
		dcx[i] += (double)delta_x;
		dcy[i] += (double)delta_y;
		xx[i] = (int) (dxx[i] + 0.5);
		yy[i] = (int) (dyy[i] + 0.5);
		cx[i] = (int) (dcx[i] + 0.5);
		cy[i] = (int) (dcy[i] + 0.5);

	  }
    }

    public void flip(){
	for (int i = 0; i < npoints; i++) {
	   if ( dxx[i] > dxx[0]){ 
		dxx[i] = dxx[i] - 2.0*(dxx[i] - dxx[0]);
		dcx[i] = dcx[i] - 2.0*(dcx[i] - dxx[0]);
	   }
	   else{
		dxx[i] = dxx[i] + 2.0*(dxx[0] - dxx[i]);
		dcx[i] = dcx[i] + 2.0*(dxx[0] - dcx[i]);
	   }	
         xx[i] = (int) (dxx[i]);
         cx[i] = (int) (dcx[i]);
	}
	if (mx > dxx[0])
		mx = mx - 2.0*(mx - dxx[0]);
	else
		mx = mx + 2.0*(dxx[0] - mx);
	if (nFlips == 0)
		nFlips = 1;
	else
		nFlips = 0;
    }

    public void flipFinal(){
	for (int i = 0; i < npoints; i++) {
		if ( fxx[i] > fxx[0]){ 
			fxx[i] = fxx[i] - 2*(fxx[i] - fxx[0]);
			dfx[i] = dfx[i] - 2*(dfx[i] - fxx[0]);
		}
		else {
			fxx[i] = fxx[i] + 2*(fxx[0] - fxx[i]);
			dfx[i] = dfx[i] + 2*(fxx[0] - dfx[i]);
		}
		xf[i] = (int) (dfx[i]);
      }
    }

    public void rotate(int Clockwise) {
	double new_x;
	double new_y;
	double offset_x;
	double offset_y;
	double alpha = - Math.PI/16.0;
	if (Clockwise == 1) alpha = -alpha;
	new_x = dxx[0] * Math.cos(alpha) - dyy[0] * Math.sin(alpha);
	new_y = dxx[0] * Math.sin(alpha) + dyy[0] * Math.cos(alpha);
	offset_x = dxx[0] - new_x;
	offset_y = dyy[0] - new_y;


	for (int i = 0; i < npoints; i++) {
		new_x = dxx[i] * Math.cos(alpha) - dyy[i] * Math.sin(alpha);
		new_y = dxx[i] * Math.sin(alpha) + dyy[i] * Math.cos(alpha);
		dxx[i] = new_x + offset_x;
		dyy[i] = new_y + offset_y;
		new_x = dcx[i] * Math.cos(alpha) - dcy[i] * Math.sin(alpha);
		new_y = dcx[i] * Math.sin(alpha) + dcy[i] * Math.cos(alpha);
		dcx[i] = new_x + offset_x;
		dcy[i] = new_y + offset_y;
		xx[i] = (int) (dxx[i] + 0.5);
		yy[i] = (int) (dyy[i] + 0.5);
		cx[i] = (int) (dcx[i] + 0.5);
		cy[i] = (int) (dcy[i] + 0.5);
	}
	new_x = mx * Math.cos(alpha) - my * Math.sin(alpha);
	new_y = mx * Math.sin(alpha) + my * Math.cos(alpha);
	mx = new_x + offset_x;
	my = new_y + offset_y;

	if (Clockwise == 0)
		if (nFlips == 0)
			nRotates = (nRotates + 1) % 32;
	    else
			nRotates = (nRotates + 31) % 32;
	else
		if (nFlips == 0)
			nRotates = (nRotates + 31) % 32;
	    else
			nRotates = (nRotates + 1) % 32;

    }
		
    public void rotateFinal(int nRot) {
	double new_x;
	double new_y;
	double offset_x;
	double offset_y;
	double alpha = - Math.PI/16.0*(double)nRot;

    new_x = (double)fxx[0] * Math.cos(alpha) - (double)fyy[0] * Math.sin(alpha);
	new_y = (double)fxx[0] * Math.sin(alpha) + (double)fyy[0] * Math.cos(alpha);
	offset_x = (double)fxx[0] - new_x;
	offset_y = (double)fyy[0] - new_y;


	for (int i = 0; i < npoints; i++) {
		new_x = (double)fxx[i] * Math.cos(alpha) - (double)fyy[i] * Math.sin(alpha);
		new_y = (double)fxx[i] * Math.sin(alpha) + (double)fyy[i] * Math.cos(alpha);
		fxx[i] = (int)(new_x + offset_x + 0.5);
		fyy[i] = (int)(new_y + offset_y + 0.5);
		new_x = dfx[i] * Math.cos(alpha) - dfy[i] * Math.sin(alpha);
		new_y = dfx[i] * Math.sin(alpha) + dfy[i] * Math.cos(alpha);
		dfx[i] = new_x + offset_x;
		dfy[i] = new_y + offset_y;
		xf[i] = (int) (dfx[i] + 0.5);
		yf[i] = (int) (dfy[i] + 0.5);
	}
      
    }
		
}


class GamePanel extends Panel implements Runnable {
    Tongram game;
    int nchips;
    Chip chips[] = new Chip[100];


    Thread relaxer;
    boolean border = true;
    boolean coordinates = false;
    boolean pattern = true;
    boolean run_demo = false;
	boolean display_logo = true;
    boolean demo_in = true;
    int k = 0;
    int inc = 1;
    
    boolean random;

    GamePanel(Tongram game) {
	this.game = game;
    }

    void addChip(int i, int ox, int oy, int oRot, int oFlp, int fx, int fy, int fRot, int fFlp) {
//	Chip n = new Chip();
	Chip n = chips[i];
	n.index = i;
	int delta = 3;
//	n.x = 40 + 300*Math.random();
//	n.y = 40 + 220*Math.random();
/*	n.x = 40 + 528*Math.random();
	n.y = 40 + 400*Math.random();
*/
	n.x = ox;
	n.y = oy;
	n.mx = n.x;
	n.my = n.y;
/*	n.dx = n.x;
	n.dy = n.y; */
//	n.lbl = lbl;
	n.fx = fx;
	n.fy = fy;
	n.fRotates = fRot;
	n.fFlips = fFlp;
//	chips[nchips] = n;
	int shape = i%15 + 1;
	double x = n.x;
	double y = n.y;
	n.dx = ((double)n.fx - n.x) /32.0;
	n.dy = ((double)n.fy - n.y) /32.0;
/*	n.dRotates = 0;
	n.dFlips = 0;
	n.nRotates = 0;
	n.nFlips = 0;
*/
	n.dRotates = oRot;
	n.dFlips = oFlp;
	n.nRotates = oRot;
	n.nFlips = oFlp;

	//Populate start position's coordinates
	switch (shape){
	case 1:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x + delta;
		n.dcy[0] = y + 2*delta;
		n.dxx[1] = x + 40;
		n.dyy[1] = y + 40;
		n.dcx[1] = x + 40 - delta;
		n.dcy[1] = y + 40;
		n.dxx[2] = x ;
		n.dyy[2] = y + 80;
		n.dcx[2] = x + delta;
		n.dcy[2] = y + 80 - 2*delta;
		n.dxx[3] = x;
		n.dyy[3] = y;
		n.dcx[3] = x + delta;
		n.dcy[3] = y + 2*delta;
		n.npoints = 4;
		break;

	case 2:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x + 2*delta;
		n.dcy[0] = y + delta;
		n.dxx[1] = x + 80;
		n.dyy[1] = y;
		n.dcx[1] = x + 80 - 2*delta;
		n.dcy[1] = y + delta;
		n.dxx[2] = x + 40;
		n.dyy[2] = y + 40;
		n.dcx[2] = x + 40;
		n.dcy[2] = y + 40 - delta;
		n.dxx[3] = x;
		n.dyy[3] = y;
		n.dcx[3] = x + 2*delta;
		n.dcy[3] = y + delta;
		n.npoints = 4;
		break;

	case 3:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x;
		n.dcy[0] = y + delta;
		n.dxx[1] = x + 40;
		n.dyy[1] = y;
		n.dcx[1] = x + 40 - 2*delta;
		n.dcy[1] = y + delta;
		n.dxx[2] = x ;
		n.dyy[2] = y + 40;
		n.dcx[2] = x;
		n.dcy[2] = y + 40 - delta;
		n.dxx[3] = x - 40;
		n.dyy[3] = y + 40;
		n.dcx[3] = x - 40 + 2*delta;
		n.dcy[3] = y + 40 - delta;
		n.dxx[4] = x;
		n.dyy[4] = y;
		n.dcx[4] = x;
		n.dcy[4] = y + delta;
		n.npoints = 5;
		break;

	case 4:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x - delta;
		n.dcy[0] = y + 2*delta;
		n.dxx[1] = x;
		n.dyy[1] = y + 40;
		n.dcx[1] = x - delta;
		n.dcy[1] = y + 40 - delta;
		n.dxx[2] = x - 40;
		n.dyy[2] = y + 40;
		n.dcx[2] = x - 40 + 2*delta;
		n.dcy[2] = y + 40 - delta;
		n.dxx[3] = x;
		n.dyy[3] = y;
		n.dcx[3] = x - delta;
		n.dcy[3] = y + 2* delta;
		n.npoints = 4;
		break;

	case 5:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x + delta;
		n.dcy[0] = y + delta;
		n.dxx[1] = x + 20;
		n.dyy[1] = y ;
		n.dcx[1] = x + 20 - delta;
		n.dcy[1] = y + delta;
		n.dxx[2] = x + 20;
		n.dyy[2] = y + 5;
		n.dcx[2] = x + 20 - delta;
		n.dcy[2] = y + 5 - delta;
		n.dxx[3] = x + 17;
		n.dyy[3] = y + 5;
		n.dcx[3] = x + 17 - delta;
		n.dcy[3] = y + 5 - delta;
		n.dxx[4] = x + 16;
		n.dyy[4] = y + 6;
		n.dcx[4] = x + 16 - delta;
		n.dcy[4] = y + 6 - delta;
		n.dxx[5] = x + 14;
		n.dyy[5] = y + 6;
		n.dcx[5] = x + 14 - delta;
		n.dcy[5] = y + 6 - delta;
		n.dxx[6] = x + 13;
		n.dyy[6] = y + 7;
		n.dcx[6] = x + 13 - delta;
		n.dcy[6] = y + 7 - delta;
		n.dxx[7] = x + 12;
		n.dyy[7] = y + 7;
		n.dcx[7] = x + 12 - delta;
		n.dcy[7] = y + 7 - delta;
		n.dxx[8] = x + 7;
		n.dyy[8] = y + 12;
		n.dcx[8] = x + 7 - delta;
		n.dcy[8] = y + 12 - delta;
		n.dxx[9] = x + 7;
		n.dyy[9] = y + 13;
		n.dcx[9] = x + 7 - delta;
		n.dcy[9] = y + 13 - delta;
		n.dxx[10] = x + 6;
		n.dyy[10] = y + 14;
		n.dcx[10] = x + 6 - delta;
		n.dcy[10] = y + 14 - delta;
		n.dxx[11] = x + 6;
		n.dyy[11] = y + 16;
		n.dcx[11] = x + 6 - delta;
		n.dcy[11] = y + 16 - delta;
		n.dxx[12] = x + 5;
		n.dyy[12] = y + 17;
		n.dcx[12] = x + 5 - delta;
		n.dcy[12] = y + 17 - delta;
		n.dxx[13] = x + 5;
		n.dyy[13] = y + 20;
		n.dcx[13] = x + 5 - delta;
		n.dcy[13] = y + 20 - delta;
		n.dxx[14] = x;
		n.dyy[14] = y + 20;
		n.dcx[14] = x + delta;
		n.dcy[14] = y + 20 - delta;
		n.dxx[15] = x;
		n.dyy[15] = y;
		n.dcx[15] = x + delta;
		n.dcy[15] = y + delta;
		n.npoints = 16;
		break;


	case 6:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x - delta;
		n.dcy[0] = y + delta;
		n.dxx[1] = x - 20;
		n.dyy[1] = y ;
		n.dcx[1] = x -20 + delta;
		n.dcy[1] = y + delta;
		n.dxx[2] = x - 20;
		n.dyy[2] = y + 5;
		n.dcx[2] = x - 20 + delta;
		n.dcy[2] = y + 5 - delta;
		n.dxx[3] = x - 17;
		n.dyy[3] = y + 5;
		n.dcx[3] = x - 17 + delta;
		n.dcy[3] = y + 5 - delta;
		n.dxx[4] = x - 16;
		n.dyy[4] = y + 6;
		n.dcx[4] = x - 16 + delta;
		n.dcy[4] = y + 6 - delta;
		n.dxx[5] = x - 14;
		n.dyy[5] = y + 6;
		n.dcx[5] = x - 14 + delta;
		n.dcy[5] = y + 6 - delta;
		n.dxx[6] = x - 13;
		n.dyy[6] = y + 7;
		n.dcx[6] = x - 13 + delta;
		n.dcy[6] = y + 7 - delta;
		n.dxx[7] = x - 12;
		n.dyy[7] = y + 7;
		n.dcx[7] = x - 12 + delta;
		n.dcy[7] = y + 7 - delta;
		n.dxx[8] = x - 7;
		n.dyy[8] = y + 12;
		n.dcx[8] = x - 7 + delta;
		n.dcy[8] = y + 12 - delta;
		n.dxx[9] = x - 7;
		n.dyy[9] = y + 13;
		n.dcx[9] = x - 7 + delta;
		n.dcy[9] = y + 13 - delta;
		n.dxx[10] = x - 6;
		n.dyy[10] = y + 14;
		n.dcx[10] = x - 6 + delta;
		n.dcy[10] = y + 14 - delta;
		n.dxx[11] = x - 6;
		n.dyy[11] = y + 16;
		n.dcx[11] = x - 6 + delta;
		n.dcy[11] = y + 16 - delta;
		n.dxx[12] = x - 5;
		n.dyy[12] = y + 17;
		n.dcx[12] = x - 5 + delta;
		n.dcy[12] = y + 17 - delta;
		n.dxx[13] = x - 5;
		n.dyy[13] = y + 20;
		n.dcx[13] = x - 5 + delta;
		n.dcy[13] = y + 20 - delta;
		n.dxx[14] = x;
		n.dyy[14] = y + 20;
		n.dcx[14] = x - delta;
		n.dcy[14] = y + 20 - delta;
		n.dxx[15] = x;
		n.dyy[15] = y;
		n.dcx[15] = x - delta;
		n.dcy[15] = y + delta;
		n.npoints = 16;
		break;


	case 7:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x + delta;
		n.dcy[0] = y - delta;
		n.dxx[1] = x + 20;
		n.dyy[1] = y ;
		n.dcx[1] = x + 20 - delta;
		n.dcy[1] = y - delta;
		n.dxx[2] = x + 20;
		n.dyy[2] = y - 5;
		n.dcx[2] = x + 20 - delta;
		n.dcy[2] = y - 5 + delta;
		n.dxx[3] = x + 17;
		n.dyy[3] = y - 5;
		n.dcx[3] = x + 17 - delta;
		n.dcy[3] = y - 5 + delta;
		n.dxx[4] = x + 16;
		n.dyy[4] = y - 6;
		n.dcx[4] = x + 16 - delta;
		n.dcy[4] = y - 6 + delta;
		n.dxx[5] = x + 14;
		n.dyy[5] = y - 6;
		n.dcx[5] = x + 14 - delta;
		n.dcy[5] = y - 6 + delta;
		n.dxx[6] = x + 13;
		n.dyy[6] = y - 7;
		n.dcx[6] = x + 13 - delta;
		n.dcy[6] = y - 7 + delta;
		n.dxx[7] = x + 12;
		n.dyy[7] = y - 7;
		n.dcx[7] = x + 12 - delta;
		n.dcy[7] = y - 7 + delta;
		n.dxx[8] = x + 7;
		n.dyy[8] = y - 12;
		n.dcx[8] = x + 7 - delta;
		n.dcy[8] = y - 12 + delta;
		n.dxx[9] = x + 7;
		n.dyy[9] = y - 13;
		n.dcx[9] = x + 7 - delta;
		n.dcy[9] = y - 13 + delta;
		n.dxx[10] = x + 6;
		n.dyy[10] = y - 14;
		n.dcx[10] = x + 6 - delta;
		n.dcy[10] = y - 14 + delta;
		n.dxx[11] = x + 6;
		n.dyy[11] = y - 16;
		n.dcx[11] = x + 6 - delta;
		n.dcy[11] = y - 16 + delta;
		n.dxx[12] = x + 5;
		n.dyy[12] = y - 17;
		n.dcx[12] = x + 5 - delta;
		n.dcy[12] = y - 17 + delta;
		n.dxx[13] = x + 5;
		n.dyy[13] = y - 20;
		n.dcx[13] = x + 5 - delta;
		n.dcy[13] = y - 20 + delta;
		n.dxx[14] = x;
		n.dyy[14] = y - 20;
		n.dcx[14] = x + delta;
		n.dcy[14] = y - 20 + delta;
		n.dxx[15] = x;
		n.dyy[15] = y;
		n.dcx[15] = x + delta;
		n.dcy[15] = y - delta;
		n.npoints = 16;
		break;


	case 8:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x - delta;
		n.dcy[0] = y - delta;
		n.dxx[1] = x - 20;
		n.dyy[1] = y ;
		n.dcx[1] = x - 20 + delta;
		n.dcy[1] = y - delta;
		n.dxx[2] = x - 20;
		n.dyy[2] = y - 5;
		n.dcx[2] = x - 20 + delta;
		n.dcy[2] = y - 5 + delta;
		n.dxx[3] = x - 17;
		n.dyy[3] = y - 5;
		n.dcx[3] = x - 17 + delta;
		n.dcy[3] = y - 5 + delta;
		n.dxx[4] = x - 16;
		n.dyy[4] = y - 6;
		n.dcx[4] = x - 16 + delta;
		n.dcy[4] = y - 6 + delta;
		n.dxx[5] = x - 14;
		n.dyy[5] = y - 6;
		n.dcx[5] = x - 14 + delta;
		n.dcy[5] = y - 6 + delta;
		n.dxx[6] = x - 13;
		n.dyy[6] = y - 7;
		n.dcx[6] = x - 13 + delta;
		n.dcy[6] = y - 7 + delta;
		n.dxx[7] = x - 12;
		n.dyy[7] = y - 7;
		n.dcx[7] = x - 12 + delta;
		n.dcy[7] = y - 7 + delta;
		n.dxx[8] = x - 7;
		n.dyy[8] = y - 12;
		n.dcx[8] = x - 7 + delta;
		n.dcy[8] = y - 12 + delta;
		n.dxx[9] = x - 7;
		n.dyy[9] = y - 13;
		n.dcx[9] = x - 7 + delta;
		n.dcy[9] = y - 13 + delta;
		n.dxx[10] = x - 6;
		n.dyy[10] = y - 14;
		n.dcx[10] = x - 6 + delta;
		n.dcy[10] = y - 14 + delta;
		n.dxx[11] = x - 6;
		n.dyy[11] = y - 16;
		n.dcx[11] = x - 6 + delta;
		n.dcy[11] = y - 16 + delta;
		n.dxx[12] = x - 5;
		n.dyy[12] = y - 17;
		n.dcx[12] = x - 5 + delta;
		n.dcy[12] = y - 17 + delta;
		n.dxx[13] = x - 5;
		n.dyy[13] = y - 20;
		n.dcx[13] = x - 5 + delta;
		n.dcy[13] = y - 20 + delta;
		n.dxx[14] = x;
		n.dyy[14] = y - 20;
		n.dcx[14] = x - delta;
		n.dcy[14] = y - 20 + delta;
		n.dxx[15] = x;
		n.dyy[15] = y;
		n.dcx[15] = x - delta;
		n.dcy[15] = y - delta;
		n.npoints = 16;
		break;


	case 9:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x + delta;
		n.dcy[0] = y;
		n.dxx[1] = x;
		n.dyy[1] = y - 3;
		n.dcx[1] = x + delta;
		n.dcy[1] = y - 3 + delta;
		n.dxx[2] = x + 1;
		n.dyy[2] = y - 4;
		n.dcx[2] = x + 1 + delta;
		n.dcy[2] = y - 4 + delta;
		n.dxx[3] = x + 1;
		n.dyy[3] = y - 6;
		n.dcx[3] = x + 1 + delta;
		n.dcy[3] = y - 6 + delta;
		n.dxx[4] = x + 2;
		n.dyy[4] = y - 7;
		n.dcx[4] = x + 2 + delta;
		n.dcy[4] = y - 7 + delta;
		n.dxx[5] = x + 2;
		n.dyy[5] = y - 8;
		n.dcx[5] = x + 2 + delta;
		n.dcy[5] = y - 8 + delta;
		n.dxx[6] = x + 3;
		n.dyy[6] = y - 9;
		n.dcx[6] = x + 3 + delta;
		n.dcy[6] = y - 9 + delta;
        n.dxx[7] = x + 6; 
		n.dyy[7] = y - 12;
        n.dcx[7] = x + 6 + delta/2; 
		n.dcy[7] = y - 12 + delta;
		n.dxx[8] = x + 7;
		n.dyy[8] = y - 13;
		n.dcx[8] = x + 7;
		n.dcy[8] = y - 13 + delta;
		n.dxx[9] = x + 8;
		n.dyy[9] = y - 13;
		n.dcx[9] = x + 8 - delta/2;
		n.dcy[9] = y - 13 + delta;
		n.dxx[10] = x + 9;
		n.dyy[10] = y - 14;
		n.dcx[10] = x + 9 - delta;
		n.dcy[10] = y - 14 + delta;
		n.dxx[11] = x + 11;
		n.dyy[11] = y - 14;
		n.dcx[11] = x + 11 - delta;
		n.dcy[11] = y - 14 + delta;
		n.dxx[12] = x + 12;
		n.dyy[12] = y - 15;
		n.dcx[12] = x + 12 - delta;
		n.dcy[12] = y - 15 + delta;
		n.dxx[13] = x + 15;
		n.dyy[13] = y - 15;
		n.dcx[13] = x + 15 - delta;
		n.dcy[13] = y - 15 + delta;
		n.dxx[14] = x + 15;
		n.dyy[14] = y ;
		n.dcx[14] = x + 15 - delta;
		n.dcy[14] = y ;
		n.dxx[15] = x + 15;
		n.dyy[15] = y + 15;
		n.dcx[15] = x + 15 - delta;
		n.dcy[15] = y + 15 - delta;
		n.dxx[16] = x + 12;
		n.dyy[16] = y + 15;
		n.dcx[16] = x + 12 - delta;
		n.dcy[16] = y + 15 - delta;
		n.dxx[17] = x + 11;
		n.dyy[17] = y + 14;
		n.dcx[17] = x + 11 - delta;
		n.dcy[17] = y + 14 - delta;
		n.dxx[18] = x + 9;
		n.dyy[18] = y + 14;
		n.dcx[18] = x + 9 - delta;
		n.dcy[18] = y + 14 - delta;
		n.dxx[19] = x + 8;
		n.dyy[19] = y + 13;
		n.dcx[19] = x + 8 - delta/2;
		n.dcy[19] = y + 13 - delta;
		n.dxx[20] = x + 7;
		n.dyy[20] = y + 13;
		n.dcx[20] = x + 7;
		n.dcy[20] = y + 13 - delta;
		n.dxx[21] = x + 6;
		n.dyy[21] = y + 12;
		n.dcx[21] = x + 6 + delta/2;
		n.dcy[21] = y + 12 - delta;
		n.dxx[22] = x + 3;
		n.dyy[22] = y + 9;
		n.dcx[22] = x + 3 + delta;
		n.dcy[22] = y + 9 - delta;
		n.dxx[23] = x + 2;
		n.dyy[23] = y + 8;
		n.dcx[23] = x + 2 + delta;
		n.dcy[23] = y + 8 - delta;
		n.dxx[24] = x + 2;
		n.dyy[24] = y + 7;
		n.dcx[24] = x + 2 + delta;
		n.dcy[24] = y + 7 - delta;
		n.dxx[25] = x + 1;
		n.dyy[25] = y + 6;
		n.dcx[25] = x + 1 + delta;
		n.dcy[25] = y + 6 - delta;
		n.dxx[26] = x + 1;
		n.dyy[26] = y + 4;
		n.dcx[26] = x + 1 + delta;
		n.dcy[26] = y + 4 - delta;
		n.dxx[27] = x;
		n.dyy[27] = y + 3;
		n.dcx[27] = x + delta;
		n.dcy[27] = y + 3 - delta;
		n.dxx[28] = x;
		n.dyy[28] = y;
		n.dcx[28] = x + delta;
		n.dcy[28] = y;
		n.npoints = 29;

		break;



	case 10:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dcx[0] = x - delta;
		n.dcy[0] = y;
		n.dxx[1] = x;
		n.dyy[1] = y - 3;
		n.dcx[1] = x - delta;
		n.dcy[1] = y - 3 + delta;
		n.dxx[2] = x - 1;
		n.dyy[2] = y - 4;
		n.dcx[2] = x - 1 - delta;
		n.dcy[2] = y - 4 + delta;
		n.dxx[3] = x - 1;
		n.dyy[3] = y - 6;
		n.dcx[3] = x - 1 - delta;
		n.dcy[3] = y - 6 + delta;
		n.dxx[4] = x - 2;
		n.dyy[4] = y - 7;
		n.dcx[4] = x - 2 - delta;
		n.dcy[4] = y - 7 + delta;
		n.dxx[5] = x - 2;
		n.dyy[5] = y - 8;
		n.dcx[5] = x - 2 - delta;
		n.dcy[5] = y - 8 + delta;
		n.dxx[6] = x - 3;
		n.dyy[6] = y - 9;
		n.dcx[6] = x - 3 - delta;
		n.dcy[6] = y - 9 + delta;
       	n.dxx[7] = x - 6; 
		n.dyy[7] = y - 12;
       	n.dcx[7] = x - 6 - delta/2; 
		n.dcy[7] = y - 12 + delta;
		n.dxx[8] = x - 7;
		n.dyy[8] = y - 13;
		n.dcx[8] = x - 7;
		n.dcy[8] = y - 13 + delta;
		n.dxx[9] = x - 8;
		n.dyy[9] = y - 13;
		n.dcx[9] = x - 8 + delta/2;
		n.dcy[9] = y - 13 + delta;
		n.dxx[10] = x - 9;
		n.dyy[10] = y - 14;
		n.dcx[10] = x - 9 + delta;
		n.dcy[10] = y - 14 + delta;
		n.dxx[11] = x - 11;
		n.dyy[11] = y - 14;
		n.dcx[11] = x - 11 + delta;
		n.dcy[11] = y - 14 + delta;
		n.dxx[12] = x - 12;
		n.dyy[12] = y - 15;
		n.dcx[12] = x - 12 + delta;
		n.dcy[12] = y - 15 + delta;
		n.dxx[13] = x - 15;
		n.dyy[13] = y - 15;
		n.dcx[13] = x - 15 + delta;
		n.dcy[13] = y - 15 + delta;
		n.dxx[14] = x - 15;
		n.dyy[14] = y ;
		n.dcx[14] = x - 15 + delta;
		n.dcy[14] = y ;
		n.dxx[15] = x - 15;
		n.dyy[15] = y + 15;
		n.dcx[15] = x - 15 + delta;
		n.dcy[15] = y + 15 - delta;
		n.dxx[16] = x - 12;
		n.dyy[16] = y + 15;
		n.dcx[16] = x - 12 + delta;
		n.dcy[16] = y + 15 - delta;
		n.dxx[17] = x - 11;
		n.dyy[17] = y + 14;
		n.dcx[17] = x - 11 + delta;
		n.dcy[17] = y + 14 - delta;
		n.dxx[18] = x - 9;
		n.dyy[18] = y + 14;
		n.dcx[18] = x - 9 + delta;
		n.dcy[18] = y + 14 - delta;
		n.dxx[19] = x - 8;
		n.dyy[19] = y + 13;
		n.dcx[19] = x - 8 + delta/2;
		n.dcy[19] = y + 13 - delta;
		n.dxx[20] = x - 7;
		n.dyy[20] = y + 13;
		n.dcx[20] = x - 7;
		n.dcy[20] = y + 13 - delta;
		n.dxx[21] = x - 6;
		n.dyy[21] = y + 12;
		n.dcx[21] = x - 6 - delta/2;
		n.dcy[21] = y + 12 - delta;
		n.dxx[22] = x - 3;
		n.dyy[22] = y + 9;
		n.dcx[22] = x - 3 - delta;
		n.dcy[22] = y + 9 - delta;
		n.dxx[23] = x - 2;
		n.dyy[23] = y + 8;
		n.dcx[23] = x - 2 - delta;
		n.dcy[23] = y + 8 - delta;
		n.dxx[24] = x - 2;
		n.dyy[24] = y + 7;
		n.dcx[24] = x - 2 - delta;
		n.dcy[24] = y + 7 - delta;
		n.dxx[25] = x - 1;
		n.dyy[25] = y + 6;
		n.dcx[25] = x - 1 - delta;
		n.dcy[25] = y + 6 - delta;
		n.dxx[26] = x - 1;
		n.dyy[26] = y + 4;
		n.dcx[26] = x - 1 - delta;
		n.dcy[26] = y + 4 - delta;
		n.dxx[27] = x;
		n.dyy[27] = y + 3;
		n.dcx[27] = x - delta;
		n.dcy[27] = y + 3 - delta;
		n.dxx[28] = x;
		n.dyy[28] = y;
		n.dcx[28] = x - delta;
		n.dcy[28] = y;
		n.npoints = 29;

		break;



	case 11:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dxx[1] = x + 40;
		n.dyy[1] = y - 40;
		n.dxx[2] = x + 40;
		n.dyy[2] = y;
		n.dxx[3] = x;
		n.dyy[3] = y;
		n.dcx[0] = x + 2*delta;
		n.dcy[0] = y - delta;
		n.dcx[1] = x + 40 - delta;
		n.dcy[1] = y - 40 + 2*delta;
		n.dcx[2] = x + 40 - delta;
		n.dcy[2] = y - delta;
		n.dcx[3] = x + 2*delta;
		n.dcy[3] = y - delta;
		n.npoints = 4;
		break;

	case 12:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dxx[1] = x + 40;
		n.dyy[1] = y ;
		n.dxx[2] = x + 40;
		n.dyy[2] = y + 60;
		n.dxx[3] = x + 20;
		n.dyy[3] = y + 60;
		n.dxx[4] = x + 20;
		n.dyy[4] = y + 20;
		n.dxx[5] = x;
		n.dyy[5] = y + 20;
		n.dxx[6] = x;
		n.dyy[6] = y;
		n.dcx[0] = x + delta;
		n.dcy[0] = y + delta;
		n.dcx[1] = x + 40 - delta;
		n.dcy[1] = y + delta;
		n.dcx[2] = x + 40 - delta;
		n.dcy[2] = y + 60 - delta;
		n.dcx[3] = x + 20 + delta;
		n.dcy[3] = y + 60 - delta;
		n.dcx[4] = x + 20 + delta;
		n.dcy[4] = y + 20 - delta;
		n.dcx[5] = x + delta;
		n.dcy[5] = y + 20 - delta;
		n.dcx[6] = x + delta;
		n.dcy[6] = y + delta;
		n.npoints = 7;
		break;

	case 13:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dxx[1] = x + 20;
		n.dyy[1] = y ;
		n.dxx[2] = x + 20;
		n.dyy[2] = y + 40;
		n.dxx[3] = x + 40;
		n.dyy[3] = y + 40;
		n.dxx[4] = x + 40;
		n.dyy[4] = y + 60;
		n.dxx[5] = x;
		n.dyy[5] = y + 60;
		n.dxx[6] = x;
		n.dyy[6] = y;
		n.dcx[0] = x + delta;
		n.dcy[0] = y + delta;
		n.dcx[1] = x + 20 - delta;
		n.dcy[1] = y + delta;
		n.dcx[2] = x + 20 - delta;
		n.dcy[2] = y + 40 + delta;
		n.dcx[3] = x + 40 - delta;
		n.dcy[3] = y + 40 + delta;
		n.dcx[4] = x + 40 - delta;
		n.dcy[4] = y + 60 - delta;
		n.dcx[5] = x + delta;
		n.dcy[5] = y + 60 - delta;
		n.dcx[6] = x + delta;
		n.dcy[6] = y + delta;
		n.npoints = 7;
		break;

	case 14:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dxx[1] = x + 60;
		n.dyy[1] = y ;
		n.dxx[2] = x + 20;
		n.dyy[2] = y + 40;
		n.dxx[3] = x ;
		n.dyy[3] = y + 40;
		n.dxx[4] = x ;
		n.dyy[4] = y ;
		n.dcx[0] = x + delta;
		n.dcy[0] = y + delta;
		n.dcx[1] = x + 60 - 2*delta;
		n.dcy[1] = y + delta;
		n.dcx[2] = x + 20 - delta;
		n.dcy[2] = y + 40 - delta;
		n.dcx[3] = x + delta;
		n.dcy[3] = y + 40 - delta;
		n.dcx[4] = x + delta;
		n.dcy[4] = y + delta;
		n.npoints = 5;
		break;

	case 15:
		n.dxx[0] = x;
		n.dyy[0] = y;
		n.dxx[1] = x + 40;
		n.dyy[1] = y - 40;
		n.dxx[2] = x + 60;
		n.dyy[2] = y - 40;
		n.dxx[3] = x + 60 ;
		n.dyy[3] = y ;
		n.dxx[4] = x ;
		n.dyy[4] = y ;
		n.dcx[0] = x + 2*delta;
		n.dcy[0] = y - delta;
		n.dcx[1] = x + 40 + delta;
		n.dcy[1] = y - 40 + delta;
		n.dcx[2] = x + 60 - delta;
		n.dcy[2] = y - 40 + delta;
		n.dcx[3] = x + 60 - delta;
		n.dcy[3] = y - delta;
		n.dcx[4] = x + 2*delta;
		n.dcy[4] = y - delta;
		n.npoints = 5;


	default:

	}
	for (int j=0; j < n.npoints; j++){
		n.xx[j] = (int) n.dxx[j];
		n.yy[j] = (int) n.dyy[j];
	}
    if (oRot > 0) {
		for (int j=0; j < oRot; j++) n.rotate(0);
	}
	if (oFlp > 0) n.flip();

	// Populate final position's coordinates
	switch (shape){
	case 1:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy - 2*delta;
		n.fxx[1] = fx + 40;
		n.fyy[1] = fy + 40;
		n.dfx[1] = fx + 40 + delta;
		n.dfy[1] = fy + 40;
		n.fxx[2] = fx ;
		n.fyy[2] = fy + 80;
		n.dfx[2] = fx - delta;
		n.dfy[2] = fy + 80 + 2*delta;
		n.fxx[3] = fx;
		n.fyy[3] = fy;
		n.dfx[3] = fx - delta;
		n.dfy[3] = fy - 2*delta;
		n.npoints = 4;
		break;

	case 2:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx - 2*delta;
		n.dfy[0] = fy - delta;
		n.fxx[1] = fx + 80;
		n.fyy[1] = fy;
		n.dfx[1] = fx + 80 + 2*delta;
		n.dfy[1] = fy - delta;
		n.fxx[2] = fx + 40;
		n.fyy[2] = fy + 40;
		n.dfx[2] = fx + 40;
		n.dfy[2] = fy + 40 + delta;
		n.fxx[3] = fx;
		n.fyy[3] = fy;
		n.dfx[3] = fx - 2*delta;
		n.dfy[3] = fy - delta;
		n.npoints = 4;
		break;

	case 3:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx;
		n.dfy[0] = fy - delta;
		n.fxx[1] = fx + 40;
		n.fyy[1] = fy;
		n.dfx[1] = fx + 40 + 2*delta;
		n.dfy[1] = fy - delta;
		n.fxx[2] = fx ;
		n.fyy[2] = fy + 40;
		n.dfx[2] = fx;
		n.dfy[2] = fy + 40 + delta;
		n.fxx[3] = fx - 40;
		n.fyy[3] = fy + 40;
		n.dfx[3] = fx - 40 -2*delta;
		n.dfy[3] = fy + 40 + delta;
		n.fxx[4] = fx;
		n.fyy[4] = fy;
		n.dfx[4] = fx;
		n.dfy[4] = fy - delta;
		n.npoints = 5;
		break;

	case 4:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx + delta;
		n.dfy[0] = fy - 2*delta;
		n.fxx[1] = fx;
		n.fyy[1] = fy + 40;
		n.dfx[1] = fx + delta;
		n.dfy[1] = fy + 40 + delta;
		n.fxx[2] = fx - 40;
		n.fyy[2] = fy + 40;
		n.dfx[2] = fx - 40 - 2*delta;
		n.dfy[2] = fy + 40 + delta;
		n.fxx[3] = fx;
		n.fyy[3] = fy;
		n.dfx[3] = fx + delta;
		n.dfy[3] = fy - 2*delta;
		n.npoints = 4;
		break;

	case 5:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy - delta;
		n.fxx[1] = fx + 20;
		n.fyy[1] = fy ;
		n.dfx[1] = fx + 20 + delta;
		n.dfy[1] = fy - delta;
		n.fxx[2] = fx + 20;
		n.fyy[2] = fy + 5;
		n.dfx[2] = fx + 20 + delta;
		n.dfy[2] = fy + 5 + delta;
		n.fxx[3] = fx + 17;
		n.fyy[3] = fy + 5;
		n.dfx[3] = fx + 17 + delta;
		n.dfy[3] = fy + 5 + delta;
		n.fxx[4] = fx + 16;
		n.fyy[4] = fy + 6;
		n.dfx[4] = fx + 16 + delta;
		n.dfy[4] = fy + 6 + delta;
		n.fxx[5] = fx + 14;
		n.fyy[5] = fy + 6;
		n.dfx[5] = fx + 14 + delta;
		n.dfy[5] = fy + 6 + delta;
		n.fxx[6] = fx + 13;
		n.fyy[6] = fy + 7;
		n.dfx[6] = fx + 13 + delta;
		n.dfy[6] = fy + 7 + delta;
		n.fxx[7] = fx + 12;
		n.fyy[7] = fy + 7;
		n.dfx[7] = fx + 12 + delta;
		n.dfy[7] = fy + 7 + delta;
		n.fxx[8] = fx + 7;
		n.fyy[8] = fy + 12;
		n.dfx[8] = fx + 7 + delta;
		n.dfy[8] = fy + 12 + delta;
		n.fxx[9] = fx + 7;
		n.fyy[9] = fy + 13;
		n.dfx[9] = fx + 7 + delta;
		n.dfy[9] = fy + 13 + delta;
		n.fxx[10] = fx + 6;
		n.fyy[10] = fy + 14;
		n.dfx[10] = fx + 6 + delta;
		n.dfy[10] = fy + 14 + delta;
		n.fxx[11] = fx + 6;
		n.fyy[11] = fy + 16;
		n.dfx[11] = fx + 6 + delta;
		n.dfy[11] = fy + 16 + delta;
		n.fxx[12] = fx + 5;
		n.fyy[12] = fy + 17;
		n.dfx[12] = fx + 5 + delta;
		n.dfy[12] = fy + 17 + delta;
		n.fxx[13] = fx + 5;
		n.fyy[13] = fy + 20;
		n.dfx[13] = fx + 5 + delta;
		n.dfy[13] = fy + 20 + delta;
		n.fxx[14] = fx;
		n.fyy[14] = fy + 20;
		n.dfx[14] = fx - delta;
		n.dfy[14] = fy + 20 + delta;
		n.fxx[15] = fx;
		n.fyy[15] = fy;
		n.dfx[15] = fx - delta;
		n.dfy[15] = fy - delta;
		n.npoints = 16;
		break;


	case 6:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx + delta;
		n.dfy[0] = fy - delta;
		n.fxx[1] = fx - 20;
		n.fyy[1] = fy;
		n.dfx[1] = fx - 20 - delta;
		n.dfy[1] = fy - delta;
		n.fxx[2] = fx - 20;
		n.fyy[2] = fy + 5;
		n.dfx[2] = fx - 20 - delta;
		n.dfy[2] = fy + 5 + delta;
		n.fxx[3] = fx - 17;
		n.fyy[3] = fy + 5;
		n.dfx[3] = fx - 17 - delta;
		n.dfy[3] = fy + 5 + delta;
		n.fxx[4] = fx - 16;
		n.fyy[4] = fy + 6;
		n.dfx[4] = fx - 16 - delta;
		n.dfy[4] = fy + 6 + delta;
		n.fxx[5] = fx - 14;
		n.fyy[5] = fy + 6;
		n.dfx[5] = fx - 14 - delta;
		n.dfy[5] = fy + 6 + delta;
		n.fxx[6] = fx - 13;
		n.fyy[6] = fy + 7;
		n.dfx[6] = fx - 13 - delta;
		n.dfy[6] = fy + 7 + delta;
		n.fxx[7] = fx - 12;
		n.fyy[7] = fy + 7;
		n.dfx[7] = fx - 12 - delta;
		n.dfy[7] = fy + 7 + delta;
		n.fxx[8] = fx - 7;
		n.fyy[8] = fy + 12;
		n.dfx[8] = fx - 7 - delta;
		n.dfy[8] = fy + 12 + delta;
		n.fxx[9] = fx - 7;
		n.fyy[9] = fy + 13;
		n.dfx[9] = fx - 7 - delta;
		n.dfy[9] = fy + 13 + delta;
		n.fxx[10] = fx - 6;
		n.fyy[10] = fy + 14;
		n.dfx[10] = fx - 6 - delta;
		n.dfy[10] = fy + 14 + delta;
		n.fxx[11] = fx - 6;
		n.fyy[11] = fy + 16;
		n.dfx[11] = fx - 6 - delta;
		n.dfy[11] = fy + 16 + delta;
		n.fxx[12] = fx - 5;
		n.fyy[12] = fy + 17;
		n.dfx[12] = fx - 5 - delta;
		n.dfy[12] = fy + 17 + delta;
		n.fxx[13] = fx - 5;
		n.fyy[13] = fy + 20;
		n.dfx[13] = fx - 5 - delta;
		n.dfy[13] = fy + 20 + delta;
		n.fxx[14] = fx;
		n.fyy[14] = fy + 20;
		n.dfx[14] = fx + delta;
		n.dfy[14] = fy + 20 + delta;
		n.fxx[15] = fx;
		n.fyy[15] = fy;
		n.dfx[15] = fx + delta;
		n.dfy[15] = fy - delta;
		n.npoints = 16;
		break;


	case 7:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy + delta;
		n.fxx[1] = fx + 20;
		n.fyy[1] = fy ;
		n.dfx[1] = fx + 20 + delta;
		n.dfy[1] = fy + delta;
		n.fxx[2] = fx + 20;
		n.fyy[2] = fy - 5;
		n.dfx[2] = fx + 20 + delta;
		n.dfy[2] = fy - 5 - delta;
		n.fxx[3] = fx + 17;
		n.fyy[3] = fy - 5;
		n.dfx[3] = fx + 17 + delta;
		n.dfy[3] = fy - 5 - delta;
		n.fxx[4] = fx + 16;
		n.fyy[4] = fy - 6;
		n.dfx[4] = fx + 16 + delta;
		n.dfy[4] = fy - 6 - delta;
		n.fxx[5] = fx + 14;
		n.fyy[5] = fy - 6;
		n.dfx[5] = fx + 14 + delta;
		n.dfy[5] = fy - 6 - delta;
		n.fxx[6] = fx + 13;
		n.fyy[6] = fy - 7;
		n.dfx[6] = fx + 13 + delta;
		n.dfy[6] = fy - 7 - delta;
		n.fxx[7] = fx + 12;
		n.fyy[7] = fy - 7;
		n.dfx[7] = fx + 12 + delta;
		n.dfy[7] = fy - 7 - delta;
		n.fxx[8] = fx + 7;
		n.fyy[8] = fy - 12;
		n.dfx[8] = fx + 7 + delta;
		n.dfy[8] = fy - 12 - delta;
		n.fxx[9] = fx + 7;
		n.fyy[9] = fy - 13;
		n.dfx[9] = fx + 7 + delta;
		n.dfy[9] = fy - 13 - delta;
		n.fxx[10] = fx + 6;
		n.fyy[10] = fy - 14;
		n.dfx[10] = fx + 6 + delta;
		n.dfy[10] = fy - 14 - delta;
		n.fxx[11] = fx + 6;
		n.fyy[11] = fy - 16;
		n.dfx[11] = fx + 6 + delta;
		n.dfy[11] = fy - 16 - delta;
		n.fxx[12] = fx + 5;
		n.fyy[12] = fy - 17;
		n.dfx[12] = fx + 5 + delta;
		n.dfy[12] = fy - 17 - delta;
		n.fxx[13] = fx + 5;
		n.fyy[13] = fy - 20;
		n.dfx[13] = fx + 5 + delta;
		n.dfy[13] = fy - 20 - delta;
		n.fxx[14] = fx;
		n.fyy[14] = fy - 20;
		n.dfx[14] = fx - delta;
		n.dfy[14] = fy - 20 - delta;
		n.fxx[15] = fx;
		n.fyy[15] = fy;
		n.dfx[15] = fx - delta;
		n.dfy[15] = fy + delta;
		n.npoints = 16;
		break;


	case 8:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx + delta;
		n.dfy[0] = fy + delta;
		n.fxx[1] = fx - 20;
		n.fyy[1] = fy ;
		n.dfx[1] = fx - 20 - delta;
		n.dfy[1] = fy + delta;
		n.fxx[2] = fx - 20;
		n.fyy[2] = fy - 5;
		n.dfx[2] = fx - 20 - delta;
		n.dfy[2] = fy - 5 - delta;
		n.fxx[3] = fx - 17;
		n.fyy[3] = fy - 5;
		n.dfx[3] = fx - 17 - delta;
		n.dfy[3] = fy - 5 - delta;
		n.fxx[4] = fx - 16;
		n.fyy[4] = fy - 6;
		n.dfx[4] = fx - 16 - delta;
		n.dfy[4] = fy - 6 - delta;
		n.fxx[5] = fx - 14;
		n.fyy[5] = fy - 6;
		n.dfx[5] = fx - 14 - delta;
		n.dfy[5] = fy - 6 - delta;
		n.fxx[6] = fx - 13;
		n.fyy[6] = fy - 7;
		n.dfx[6] = fx - 13 - delta;
		n.dfy[6] = fy - 7 - delta;
		n.fxx[7] = fx - 12;
		n.fyy[7] = fy - 7;
		n.dfx[7] = fx - 12 - delta;
		n.dfy[7] = fy - 7 - delta;
		n.fxx[8] = fx - 7;
		n.fyy[8] = fy - 12;
		n.dfx[8] = fx - 7 - delta;
		n.dfy[8] = fy - 12 - delta;
		n.fxx[9] = fx - 7;
		n.fyy[9] = fy - 13;
		n.dfx[9] = fx - 7 - delta;
		n.dfy[9] = fy - 13 - delta;
		n.fxx[10] = fx - 6;
		n.fyy[10] = fy - 14;
		n.dfx[10] = fx - 6 - delta;
		n.dfy[10] = fy - 14 - delta;
		n.fxx[11] = fx - 6;
		n.fyy[11] = fy - 16;
		n.dfx[11] = fx - 6 - delta;
		n.dfy[11] = fy - 16 - delta;
		n.fxx[12] = fx - 5;
		n.fyy[12] = fy - 17;
		n.dfx[12] = fx - 5 - delta;
		n.dfy[12] = fy - 17 - delta;
		n.fxx[13] = fx - 5;
		n.fyy[13] = fy - 20;
		n.dfx[13] = fx - 5 - delta;
		n.dfy[13] = fy - 20 - delta;
		n.fxx[14] = fx;
		n.fyy[14] = fy - 20;
		n.dfx[14] = fx + delta;
		n.dfy[14] = fy - 20 - delta;
		n.fxx[15] = fx;
		n.fyy[15] = fy;
		n.dfx[15] = fx + delta;
		n.dfy[15] = fy + delta;
		n.npoints = 16;
		break;


	case 9:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy;
		n.fxx[1] = fx;
		n.fyy[1] = fy - 3;
		n.dfx[1] = fx - delta;
		n.dfy[1] = fy - 3 - delta;
		n.fxx[2] = fx + 1;
		n.fyy[2] = fy - 4;
		n.dfx[2] = fx + 1 - delta;
		n.dfy[2] = fy - 4 - delta;
		n.fxx[3] = fx + 1;
		n.fyy[3] = fy - 6;
		n.dfx[3] = fx + 1 - delta;
		n.dfy[3] = fy - 6 - delta;
		n.fxx[4] = fx + 2;
		n.fyy[4] = fy - 7;
		n.dfx[4] = fx + 2 - delta;
		n.dfy[4] = fy - 7 - delta;
		n.fxx[5] = fx + 2;
		n.fyy[5] = fy - 8;
		n.dfx[5] = fx + 2 - delta;
		n.dfy[5] = fy - 8 - delta;
		n.fxx[6] = fx + 3;
		n.fyy[6] = fy - 9;
		n.dfx[6] = fx + 3 - delta;
		n.dfy[6] = fy - 9 - delta;
       	n.fxx[7] = fx + 6; 
		n.fyy[7] = fy - 12;
       	n.dfx[7] = fx + 6 - delta/2; 
		n.dfy[7] = fy - 12 - delta;
		n.fxx[8] = fx + 7;
		n.fyy[8] = fy - 13;
		n.dfx[8] = fx + 7;
		n.dfy[8] = fy - 13 - delta;
		n.fxx[9] = fx + 8;
		n.fyy[9] = fy - 13;
		n.dfx[9] = fx + 8 + delta/2;
		n.dfy[9] = fy - 13 - delta;
		n.fxx[10] = fx + 9;
		n.fyy[10] = fy - 14;
		n.dfx[10] = fx + 9 + delta;
		n.dfy[10] = fy - 14 - delta;
		n.fxx[11] = fx + 11;
		n.fyy[11] = fy - 14;
		n.dfx[11] = fx + 11 + delta;
		n.dfy[11] = fy - 14 - delta;
		n.fxx[12] = fx + 12;
		n.fyy[12] = fy - 15;
		n.dfx[12] = fx + 12 + delta;
		n.dfy[12] = fy - 15 - delta;
		n.fxx[13] = fx + 15;
		n.fyy[13] = fy - 15;
		n.dfx[13] = fx + 15 + delta;
		n.dfy[13] = fy - 15 - delta;
		n.fxx[14] = fx + 15;    // Center point for Arc
		n.fyy[14] = fy ;
		n.dfx[14] = fx + 15 + delta;
		n.dfy[14] = fy ;
		n.fxx[15] = fx + 15;
		n.fyy[15] = fy + 15;
		n.dfx[15] = fx + 15 + delta;
		n.dfy[15] = fy + 15 + delta;
		n.fxx[16] = fx + 12;
		n.fyy[16] = fy + 15;
		n.dfx[16] = fx + 12 + delta;
		n.dfy[16] = fy + 15 + delta;
		n.fxx[17] = fx + 11;
		n.fyy[17] = fy + 14;
		n.dfx[17] = fx + 11 + delta;
		n.dfy[17] = fy + 14 + delta;
		n.fxx[18] = fx + 9;
		n.fyy[18] = fy + 14;
		n.dfx[18] = fx + 9 + delta;
		n.dfy[18] = fy + 14 + delta;
		n.fxx[19] = fx + 8;
		n.fyy[19] = fy + 13;
		n.dfx[19] = fx + 8 + delta/2;
		n.dfy[19] = fy + 13 + delta;
		n.fxx[20] = fx + 7;
		n.fyy[20] = fy + 13;
		n.dfx[20] = fx + 7;
		n.dfy[20] = fy + 13 + delta;
		n.fxx[21] = fx + 6;
		n.fyy[21] = fy + 12;
		n.dfx[21] = fx + 6 - delta/2;
		n.dfy[21] = fy + 12 + delta;
		n.fxx[22] = fx + 3;
		n.fyy[22] = fy + 9;
		n.dfx[22] = fx + 3 - delta;
		n.dfy[22] = fy + 9 + delta;
		n.fxx[23] = fx + 2;
		n.fyy[23] = fy + 8;
		n.dfx[23] = fx + 2 - delta;
		n.dfy[23] = fy + 8 + delta;
		n.fxx[24] = fx + 2;
		n.fyy[24] = fy + 7;
		n.dfx[24] = fx + 2 - delta;
		n.dfy[24] = fy + 7 + delta;
		n.fxx[25] = fx + 1;
		n.fyy[25] = fy + 6;
		n.dfx[25] = fx + 1 - delta;
		n.dfy[25] = fy + 6 + delta;
		n.fxx[26] = fx + 1;
		n.fyy[26] = fy + 4;
		n.dfx[26] = fx + 1 - delta;
		n.dfy[26] = fy + 4 + delta;
		n.fxx[27] = fx;
		n.fyy[27] = fy + 3;
		n.dfx[27] = fx - delta;
		n.dfy[27] = fy + 3 + delta;
		n.fxx[28] = fx;
		n.fyy[28] = fy;
		n.dfx[28] = fx - delta;
		n.dfy[28] = fy;
		n.npoints = 29;

		break;



	case 10:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.dfx[0] = fx + delta;
		n.dfy[0] = fy;
		n.fxx[1] = fx;
		n.fyy[1] = fy - 3;
		n.dfx[1] = fx + delta;
		n.dfy[1] = fy - 3 - delta;
		n.fxx[2] = fx - 1;
		n.fyy[2] = fy - 4;
		n.dfx[2] = fx - 1 + delta;
		n.dfy[2] = fy - 4 - delta;
		n.fxx[3] = fx - 1;
		n.fyy[3] = fy - 6;
		n.dfx[3] = fx - 1 + delta;
		n.dfy[3] = fy - 6 - delta;
		n.fxx[4] = fx - 2;
		n.fyy[4] = fy - 7;
		n.dfx[4] = fx - 2 + delta;
		n.dfy[4] = fy - 7 - delta;
		n.fxx[5] = fx - 2;
		n.fyy[5] = fy - 8;
		n.dfx[5] = fx - 2 + delta;
		n.dfy[5] = fy - 8 - delta;
		n.fxx[6] = fx - 3;
		n.fyy[6] = fy - 9;
		n.dfx[6] = fx - 3 + delta;
		n.dfy[6] = fy - 9 - delta;
       	n.fxx[7] = fx - 6; 
		n.fyy[7] = fy - 12;
       	n.dfx[7] = fx - 6 + delta/2; 
		n.dfy[7] = fy - 12 - delta;
		n.fxx[8] = fx - 7;
		n.fyy[8] = fy - 13;
		n.dfx[8] = fx - 7;
		n.dfy[8] = fy - 13 - delta;
		n.fxx[9] = fx - 8;
		n.fyy[9] = fy - 13;
		n.dfx[9] = fx - 8 - delta/2;
		n.dfy[9] = fy - 13 - delta;
		n.fxx[10] = fx - 9;
		n.fyy[10] = fy - 14;
		n.dfx[10] = fx - 9 - delta;
		n.dfy[10] = fy - 14 - delta;
		n.fxx[11] = fx - 11;
		n.fyy[11] = fy - 14;
		n.dfx[11] = fx - 11 - delta;
		n.dfy[11] = fy - 14 - delta;
		n.fxx[12] = fx - 12;
		n.fyy[12] = fy - 15;
		n.dfx[12] = fx - 12 - delta;
		n.dfy[12] = fy - 15 - delta;
		n.fxx[13] = fx - 15;
		n.fyy[13] = fy - 15;
		n.dfx[13] = fx - 15 - delta;
		n.dfy[13] = fy - 15 - delta;
		n.fxx[14] = fx - 15;
		n.fyy[14] = fy ;
		n.dfx[14] = fx - 15 - delta;
		n.dfy[14] = fy ;
		n.fxx[15] = fx - 15;
		n.fyy[15] = fy + 15;
		n.dfx[15] = fx - 15 - delta;
		n.dfy[15] = fy + 15 + delta;
		n.fxx[16] = fx - 12;
		n.fyy[16] = fy + 15;
		n.dfx[16] = fx - 12 - delta;
		n.dfy[16] = fy + 15 + delta;
		n.fxx[17] = fx - 11;
		n.fyy[17] = fy + 14;
		n.dfx[17] = fx - 11 - delta;
		n.dfy[17] = fy + 14 + delta;
		n.fxx[18] = fx - 9;
		n.fyy[18] = fy + 14;
		n.dfx[18] = fx - 9 - delta;
		n.dfy[18] = fy + 14 + delta;
		n.fxx[19] = fx - 8;
		n.fyy[19] = fy + 13;
		n.dfx[19] = fx - 8 - delta/2;
		n.dfy[19] = fy + 13 + delta;
		n.fxx[20] = fx - 7;
		n.fyy[20] = fy + 13;
		n.dfx[20] = fx - 7;
		n.dfy[20] = fy + 13 + delta;
		n.fxx[21] = fx - 6;
		n.fyy[21] = fy + 12;
		n.dfx[21] = fx - 6 + delta/2;
		n.dfy[21] = fy + 12 + delta;
		n.fxx[22] = fx - 3;
		n.fyy[22] = fy + 9;
		n.dfx[22] = fx - 3 + delta;
		n.dfy[22] = fy + 9 + delta;
		n.fxx[23] = fx - 2;
		n.fyy[23] = fy + 8;
		n.dfx[23] = fx - 2 + delta;
		n.dfy[23] = fy + 8 + delta;
		n.fxx[24] = fx - 2;
		n.fyy[24] = fy + 7;
		n.dfx[24] = fx - 2 + delta;
		n.dfy[24] = fy + 7 + delta;
		n.fxx[25] = fx - 1;
		n.fyy[25] = fy + 6;
		n.dfx[25] = fx - 1 + delta;
		n.dfy[25] = fy + 6 + delta;
		n.fxx[26] = fx - 1;
		n.fyy[26] = fy + 4;
		n.dfx[26] = fx - 1 + delta;
		n.dfy[26] = fy + 4 + delta;
		n.fxx[27] = fx;
		n.fyy[27] = fy + 3;
		n.dfx[27] = fx + delta;
		n.dfy[27] = fy + 3 + delta;
		n.fxx[28] = fx;
		n.fyy[28] = fy;
		n.dfx[28] = fx + delta;
		n.dfy[28] = fy;
		n.npoints = 29;

		break;



	case 11:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.fxx[1] = fx + 40;
		n.fyy[1] = fy - 40;
		n.fxx[2] = fx + 40;
		n.fyy[2] = fy;
		n.fxx[3] = fx;
		n.fyy[3] = fy;
		n.dfx[0] = fx - 2*delta;
		n.dfy[0] = fy + delta;
		n.dfx[1] = fx + 40 + delta;
		n.dfy[1] = fy - 40 - 2*delta;
		n.dfx[2] = fx + 40 + delta;
		n.dfy[2] = fy + delta;
		n.dfx[3] = fx - 2*delta;
		n.dfy[3] = fy + delta;
		n.npoints = 4;
		break;

	case 12:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.fxx[1] = fx + 40;
		n.fyy[1] = fy ;
		n.fxx[2] = fx + 40;
		n.fyy[2] = fy + 60;
		n.fxx[3] = fx + 20;
		n.fyy[3] = fy + 60;
		n.fxx[4] = fx + 20;
		n.fyy[4] = fy + 20;
		n.fxx[5] = fx;
		n.fyy[5] = fy + 20;
		n.fxx[6] = fx;
		n.fyy[6] = fy;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy - delta;
		n.dfx[1] = fx + 40 + delta;
		n.dfy[1] = fy - delta;
		n.dfx[2] = fx + 40 + delta;
		n.dfy[2] = fy + 60 + delta;
		n.dfx[3] = fx + 20 - delta;
		n.dfy[3] = fy + 60 + delta;
		n.dfx[4] = fx + 20 - delta;
		n.dfy[4] = fy + 20 + delta;
		n.dfx[5] = fx - delta;
		n.dfy[5] = fy + 20 + delta;
		n.dfx[6] = fx - delta;
		n.dfy[6] = fy - delta;
		n.npoints = 7;
		break;

	case 13:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.fxx[1] = fx + 20;
		n.fyy[1] = fy ;
		n.fxx[2] = fx + 20;
		n.fyy[2] = fy + 40;
		n.fxx[3] = fx + 40;
		n.fyy[3] = fy + 40;
		n.fxx[4] = fx + 40;
		n.fyy[4] = fy + 60;
		n.fxx[5] = fx;
		n.fyy[5] = fy + 60;
		n.fxx[6] = fx;
		n.fyy[6] = fy;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy - delta;
		n.dfx[1] = fx + 20 + delta;
		n.dfy[1] = fy - delta;
		n.dfx[2] = fx + 20 + delta;
		n.dfy[2] = fy + 40 - delta;
		n.dfx[3] = fx + 40 + delta;
		n.dfy[3] = fy + 40 - delta;
		n.dfx[4] = fx + 40 + delta;
		n.dfy[4] = fy + 60 + delta;
		n.dfx[5] = fx - delta;
		n.dfy[5] = fy + 60 + delta;
		n.dfx[6] = fx - delta;
		n.dfy[6] = fy - delta;
		n.npoints = 7;
		break;

	case 14:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.fxx[1] = fx + 60;
		n.fyy[1] = fy ;
		n.fxx[2] = fx + 20;
		n.fyy[2] = fy + 40;
		n.fxx[3] = fx ;
		n.fyy[3] = fy + 40;
		n.fxx[4] = fx ;
		n.fyy[4] = fy ;
		n.dfx[0] = fx - delta;
		n.dfy[0] = fy - delta;
		n.dfx[1] = fx + 60 + 2*delta;
		n.dfy[1] = fy - delta;
		n.dfx[2] = fx + 20 + delta;
		n.dfy[2] = fy + 40 + delta;
		n.dfx[3] = fx - delta;
		n.dfy[3] = fy + 40 + delta;
		n.dfx[4] = fx - delta;
		n.dfy[4] = fy - delta;
		n.npoints = 5;
		break;

	case 15:
		n.fxx[0] = fx;
		n.fyy[0] = fy;
		n.fxx[1] = fx + 40;
		n.fyy[1] = fy - 40;
		n.fxx[2] = fx + 60;
		n.fyy[2] = fy - 40;
		n.fxx[3] = fx + 60 ;
		n.fyy[3] = fy ;
		n.fxx[4] = fx ;
		n.fyy[4] = fy ;
		n.dfx[0] = fx - 2*delta;
		n.dfy[0] = fy + delta;
		n.dfx[1] = fx + 40 - delta;
		n.dfy[1] = fy - 40 - delta;
		n.dfx[2] = fx + 60 + delta;
		n.dfy[2] = fy - 40 - delta;
		n.dfx[3] = fx + 60 + delta;
		n.dfy[3] = fy + delta;
		n.dfx[4] = fx - 2*delta;
		n.dfy[4] = fy + delta;
		n.npoints = 5;


	default:

	}
	for (int j=0; j < n.npoints; j++){
		n.xf[j] = (int) n.dfx[j];
		n.yf[j] = (int) n.dfy[j];
	}

    if (fRot > 0) n.rotateFinal(fRot);
	if (fFlp > 0) n.flipFinal();

//	return nchips++;
    }



    public void run() {
	repaint();
    
	while (true) {
        if (run_demo) 
			demo();
	    else
	    	repaint();
			try {
			if (run_demo)
				Thread.sleep(100);
			else
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			break;
			}

		}

    }
	
    synchronized void demo(){
  	repaint();
/*	drip_au.play();        */
      if (inc > 0)
	 {
        	for(int i = 0; i < 15; i++){
 			Chip n = chips[i];
			n.move(n.dx, n.dy);
			if (n.dRotates < n.fRotates){
				n.rotate(n.dFlips);
				n.dRotates += 1;
			}

			if (n.dFlips < n.fFlips){
				n.flip();
				n.dFlips = 1;
			}
		}
         


         if (k < 32)
		k += inc;
	   else
         {
/*  			border = false;
	   		repaint();

	   		drip_au.play();*/

	   		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
		//		break;
	   		}
  
/*		      border = true;
         		repaint();
         
	   		drip_au.play();  */

		      k = 32;
			inc = (-1);
		
	   }
      }
	else
	{
	      for(int i = 0; i < 15; i++){
			Chip n = chips[i];
			n.move(-n.dx, -n.dy);
 
			if (n.dRotates > 0){
				n.rotate(1-n.dFlips);
				n.dRotates -= 1;
			}

			if (n.dFlips > 0){
				n.flip();
				n.dFlips = 0;
			}
		}
   
		if ( k > 0 )
			k += inc;
            else{	    	 
  			repaint();
/*		      drip_au.play();   */
	   		try {
				Thread.sleep(5000);
	   		} catch (InterruptedException e) {
		//		break;
	   		} 
      		
                  k = 0;
			inc = 1;   
		}
	    }
	 

	
    }
			

    public void solve(){
		for (int i=0; i < 15; i++){
			Chip n = chips[i];
			n.dx = ((double)n.fx - n.x) /32.0;
			n.dy = ((double)n.fy - n.y) /32.0;
			n.dRotates = n.nRotates;
			n.dFlips = n.nFlips;
		}

        k = 0;
        inc = 1;
 
		run_demo = true;

/*		for (int j=0; j <32; j++){
	   		for(int i = 0; i < 15; i++){
 				Chip n = chips[i];
				n.move(n.dx, n.dy);
				if (n.dRotates < n.fRotates){
					n.rotate(n.dFlips);
					n.dRotates += 1;
				}

				if (n.dFlips < n.fFlips){
					n.flip();
					n.dFlips = 1;
				}
			}
	  		repaint();
//		      drip_au.play();   
		   	try {
					Thread.sleep(100);
	   		} catch (InterruptedException e) {
			}

		}
*/	
    }
			


    Chip pick;
    int  last_chip = nchips;
	int  goodchips = 0;
    Chip current = null;
    boolean pickfixed;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;

	boolean success = false;

	Label game_time;
	long start_time;
	Label name_lable;
	TextField game_name;

    final Color fixedColor = Color.red;
    final Color selectColor = Color.pink;
    final Color edgeColor = Color.black;
//    final Color chipColor = new Color(250, 220, 100);
    final Color chipColor = Color.blue;
//	final Color finalColor = Color.green;
    final Color stressColor = Color.darkGray;
    final Color arcColor1 = Color.black;
    final Color arcColor2 = Color.pink;
    final Color arcColor3 = Color.red;
    AudioClip rotate_au;
    AudioClip flip_au;
    AudioClip drip_au;
	Image yizhitu;

    public void paintChip(Graphics g, int i, FontMetrics fm) {
			Color finalColor;
			if (pattern)
				finalColor = Color.green;
			else
				finalColor = Color.blue;

			Chip n = chips[i];
			
			g.setColor(success || run_demo? Color.yellow :((n == pick) ? selectColor : (n.fixed ? fixedColor : (n.completed? finalColor:chipColor))));
			if ((i<8) || (i>9)) {
				g.fillPolygon(n.xx, n.yy, n.npoints);
				if (border) {
					g.setColor(Color.lightGray);
					g.drawPolygon(n.xx, n.yy, n.npoints);
				}
			} else {
				int startAngle = 0;
				if (i==8 && n.nFlips==0){ 
					startAngle = 8 + n.nRotates ;
				}
				else if (i==8 && n.nFlips==1){ 
					startAngle = 24 - n.nRotates ;
				}
				else if (i==9 && n.nFlips==0){
					startAngle = 24 + n.nRotates ;
				}
				else if (i==9 && n.nFlips==1){
					startAngle = 8 - n.nRotates ;
				}

				g.fillArc((int)(n.dxx[14] - 15 + 0.5), (int)(n.dyy[14] - 15 + 0.5), 30, 30, (int)(startAngle*11.25+0.5), 180);
				if (border) {
					g.setColor(Color.lightGray);
					g.drawArc((int)(n.dxx[14] - 15 + 0.5), (int)(n.dyy[14] - 15 + 0.5), 30, 30, (int)(startAngle*11.25+0.5), 180);
					g.drawLine(n.xx[13], n.yy[13], n.xx[14], n.yy[14]);
					g.drawLine(n.xx[14], n.yy[14], n.xx[15], n.yy[15]);
				}
			}
    }

    public void paintShadow(Graphics g, int i, FontMetrics fm) {
			Chip n = chips[i];
			int sx[] = new int[30];
			int sy[] = new int[30];
			for(int j=0; j < n.npoints; j++){
				sx[j] = n.xx[j] + 3;
				sy[j] = n.yy[j] + 4;
			}

			
			g.setColor(Color.darkGray);
			if ((i<8) || (i>9))
				g.fillPolygon(sx, sy, n.npoints);
			else {
				int startAngle = 0;
				if (i==8 && n.nFlips==0){ 
					startAngle = 8 + n.nRotates ;
				}
				else if (i==8 && n.nFlips==1){ 
					startAngle = 24 - n.nRotates ;
				}
				else if (i==9 && n.nFlips==0){
					startAngle = 24 + n.nRotates ;
				}
				else if (i==9 && n.nFlips==1){
					startAngle = 8 - n.nRotates ;
				}

				g.fillArc((int)(n.dxx[14] - 12 + 0.5), (int)(n.dyy[14] - 11 + 0.5), 30, 30, (int)(startAngle*11.25+0.5), 180);
				

			}
    }

    public void paintFinal(Graphics g, int i, FontMetrics fm) {
	Chip n = chips[i];
	g.setColor(Color.white);
	g.fillPolygon(n.fxx, n.fyy, n.npoints);
//	g.setColor(Color.white);
	g.drawPolygon(n.fxx, n.fyy, n.npoints);
//	g.drawPolygon(n.xf, n.yf, n.npoints);
    }


    public synchronized void update(Graphics g) {
	  if (!success && !run_demo) {
//	  if (!run_demo) {
		Date dt = new Date();
		int hh,mm, ss;
		long current_time, elapsed_time;

		current_time = dt.getTime();
		elapsed_time = current_time - start_time;

		hh = (int)(elapsed_time / 3600000);
		elapsed_time = elapsed_time - hh * 3600000;
		hh = hh % 24;
		mm = (int) (elapsed_time / 60000);
		elapsed_time = elapsed_time - mm * 60000;
		ss = (int) (elapsed_time / 1000);
     
		game_time.setText("Elapsed Time "+ String.valueOf(hh)+":"+String.valueOf(mm)+":"+String.valueOf(ss));
      }

	Dimension d = size();
	if ((offscreen == null) || (d.width != offscreensize.width) || (d.height != offscreensize.height)) {
	    offscreen = createImage(d.width, d.height);
	    offscreensize = d;
	    offgraphics = offscreen.getGraphics();
	    offgraphics.setFont(getFont());
	}

//	offgraphics.setColor(getBackground());
	offgraphics.setColor(Color.lightGray);
	offgraphics.fillRect(0, 0, d.width, d.height);

	FontMetrics fm = offgraphics.getFontMetrics();
	if (pattern)
        for (int f = 0 ; f < nchips ; f++) paintFinal(offgraphics, f, fm);

	if (border)
	for (int i = 0 ; i < nchips ; i++) {
         int j = (i + last_chip + 1) % nchips; 
 	     paintShadow(offgraphics, j, fm);
	}

	for (int i = 0 ; i < nchips ; i++) {
          int j = (i + last_chip + 1) % nchips; 
	    paintChip(offgraphics, j, fm);
	}

//	if (display_logo){
//		g.drawImage(yizhitu, 0, 0, null);
/*			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			} */
//		display_logo = false;
//	}
//	else
		g.drawImage(offscreen, 0, 0, null);

    }


	public void verify(Chip current){
		boolean done, nextpoint;
		int goodpoints;
		nextpoint = true;
		goodpoints = 0;
//		if ((!success) && (!run_demo)){
			if (current != null){
				for (int i = 0; i < current.npoints && nextpoint; i++) {
					done = false;
					for (int j = 0; j < nchips && !done; j++){
						if (j != last_chip) {
	    					Polygon bound = new Polygon(chips[j].cx, chips[j].cy, chips[j].npoints);

							if (bound.inside((int)current.dxx[i], (int)current.dyy[i])){
								done = true;
								nextpoint = false;
							}
						}

						if (!done ) {
	    					Polygon bound = new Polygon(chips[j].xf, chips[j].yf, chips[j].npoints);

							if (bound.inside((int)current.dxx[i], (int)current.dyy[i])){
								done = true;
								nextpoint = true;
								goodpoints += 1;
							}
						}

					}
				}
				if (goodpoints == current.npoints)
					current.completed = true;
				else
					current.completed = false;

			}
//		}
		success = true;
		for (int k = 0; k < nchips; k++)
			success = success && chips[k].completed;

	}

    public synchronized boolean mouseDown(Event evt, int x, int y) {
//     if ((!success) && (!run_demo) && (evt.modifiers != evt.SHIFT_MASK) && 
     if ((!run_demo) && (evt.modifiers != evt.SHIFT_MASK) && 
		 (evt.modifiers != evt.ALT_MASK) && (evt.modifiers != evt.META_MASK)) {
		double bestdist = Double.MAX_VALUE; 
		boolean pickup = false;
		for (int i = 0 ; i < nchips ; i++) {
			Chip n = chips[i];
	
			if (n.fixed) current = n;
			double dist = (n.mx - x) * (n.mx - x) + (n.my - y) * (n.my - y); 
			if (dist < bestdist) {
	    		Polygon bound = new Polygon(n.xx, n.yy, n.npoints);

	    		if (bound.inside(x, y)) {
					last_chip = i;
					pick = n;
					pickup = true;
				}
			}
		}

		if (!pickup) {
			if (current != null) current.fixed =false;
			current = null;
			pick = null;
		}
		else {	
/*	pickfixed = pick.fixed; */
			if ( !pick.fixed){
				if (current != null) current.fixed = false;
				current = pick;
				pick.fixed = true;
				pick.mx = x;
				pick.my = y;
			}
		}
	  }
	  else if (current != null && (evt.modifiers == evt.ALT_MASK))
		    current.flip();
	  else if (current != null && (evt.modifiers == evt.SHIFT_MASK))
		    current.rotate(0);
	  else if (current != null && (evt.modifiers == evt.META_MASK))
		    current.rotate(1);
	 
/*	  if (current != null){
		  	String Location = String.valueOf((int)current.x)+"-"+String.valueOf((int)current.y)+"-"
			+String.valueOf(current.nRotates)+"-"+String.valueOf(current.nFlips);
			game_name.setText(Location);
	  }
*/
	repaint();

	return true;
    
    }

    public synchronized boolean mouseDrag(Event evt, int x, int y) {
//     if ((!success) && (!run_demo) && (pick != null) && (evt.modifiers != evt.SHIFT_MASK) && 
     if ((!run_demo) && (pick != null) && (evt.modifiers != evt.SHIFT_MASK) && 
		 (evt.modifiers != evt.ALT_MASK) && (evt.modifiers != evt.META_MASK)) {
		pick.x = pick.x + (x - pick.mx);
		pick.y = pick.y + (y - pick.my);
		pick.move((x - pick.mx), (y - pick.my));
		pick.mx = x;
		pick.my = y;
		repaint();
	 }
/*	  if (current != null){
		  	String Location = String.valueOf((int)current.x)+"-"+String.valueOf((int)current.y)+"-"
			+String.valueOf(current.nRotates)+"-"+String.valueOf(current.nFlips);
			game_name.setText(Location);
	  }
*/
	return true;
     
    }

    public synchronized boolean mouseUp(Event evt, int x, int y) {
/*     if ((!run_demo) && (evt.modifiers != evt.SHIFT_MASK) && 
		 (evt.modifiers != evt.ALT_MASK) && (evt.modifiers != evt.META_MASK)) {
/*	pick.x = pick.x + (x - pick.mx);
	pick.y = pick.y + (y - pick.my);
	pick.move((x - pick.mx), (y - pick.my));
	pick.mx = x;
	pick.my = y;
/*	pick.fixed = pickfixed;*/
//		if ((!success) && (!run_demo)){
		if ((!run_demo)){
			if (pick != null){
				verify(pick);

				pick = null;
			}
	
			repaint();
		}
		return true;
     
    }

	public synchronized boolean keyDown(Event  evt, int  key){
		int step;

//		if ((current == null)&& (!success) && (!run_demo) & (evt.id == evt.KEY_ACTION)&&(key == evt.HOME)){ 
		if ((current == null) && (!run_demo) & (evt.id == evt.KEY_ACTION)&&(key == evt.HOME)){ 
			last_chip = 0;
			current = chips[0];
			pick = null;
			current.fixed = true;
			repaint();
		}
//		else if ((current != null) && (current.fixed) && (!success) && (!run_demo) & (evt.id == evt.KEY_ACTION)){
		else if ((current != null) && (current.fixed) && (!run_demo) & (evt.id == evt.KEY_ACTION)){
			if (evt.modifiers == evt.SHIFT_MASK) 
				step = 1;
			else
				step = 10;

			switch (key){
			case evt.UP:
				current.x = current.x ;
				current.y = current.y - step;
				current.move(0, -step);
				current.mx = current.mx;
				current.my = current.my - step;
				pick = current;
				repaint();
				break;

			case evt.DOWN:
				current.x = current.x ;
				current.y = current.y + step;
				current.move(0, step);
				current.mx = current.mx;
				current.my = current.my + step;
				pick = current;
				repaint();
				break;

			case evt.LEFT:
				current.x = current.x - step;
				current.y = current.y ;
				current.move(-step, 0);
				current.mx = current.mx - step;
				current.my = current.my ;
				pick = current;
				repaint();
				break;

			case evt.RIGHT:
				current.x = current.x + step;
				current.y = current.y ;
				current.move(step, 0);
				current.mx = current.mx + step;
				current.my = current.my;
				pick = current;
				repaint();
				break;

			case evt.PGUP:
				current.rotate(0);
				pick = current;
				repaint();
				break;

			case evt.PGDN:
				current.rotate(1);
				pick = current;
				repaint();
				break;

			case evt.HOME:
				current.fixed = false;
				verify(current);
				last_chip = (last_chip + 1) % 15;
				current = chips[last_chip];
				pick = null;
				

				current.fixed = true;

				repaint();

				break;

			case evt.END:
				current.flip();
				repaint();
				break;

			default:
			}

		}

/*		if (current != null){
		  	String Location = String.valueOf((int)current.x)+"-"+String.valueOf((int)current.y)+"-"
			+String.valueOf(current.nRotates)+"-"+String.valueOf(current.nFlips);
			game_name.setText(Location);
		}
*/
		return true;
	
	}

	public synchronized boolean keyUp(Event  evt, int  key){
//		if ((current != null) && (!success) && (!run_demo) & (evt.id == evt.KEY_ACTION_RELEASE)){
		if ((current != null) && (!run_demo) & (evt.id == evt.KEY_ACTION_RELEASE)){
			pick = null;
			verify(current);
			repaint();
		}
		return true;
	
	}



    public void start() {
	relaxer = new Thread(this);
	relaxer.start();
    }
    public void stop() {
	relaxer.stop();
    }
}

public class Tongram extends Applet {
    GamePanel panel;
/*	// RDO objects
	//	The connection to the ODBC data source
	public static _rdoConnection m_IConnection;
	//  The resultset from the query
	public static _rdoResultset m_IResultSet;
*/

	public static int game_index = 0;
	public final int total_games = 200;
	public static short original_position[] = {543,330,0,0,22,378,0,0,56,323,0,0,64,160,0,0,547,226,0,0,62,223,0,0,547,307,0,0,63,305,0,0,552,267,0,0,58,265,0,0,543,205,0,0,544,19,0,0,29,24,0,0,28,102,0,0,523,145,0,0};
	public static short puzzles[][] = {	
/*1-1*/		{274,250,28,0,314,254,29,0,302,158,28,0,380,298,29,0,315,144,28,0,343,172,28,0,273,186,24,0,313,186,8,0,293,191,24,0,326,161,4,0,413,320,13,0,273,246,8,0,292,246,8,0,273,206,0,1,274,306,8,1},
/*82-1*/	{234,154,0,0,334,347,11,0,244,279,0,0,353,116,28,0,294,219,21,0,310,101,28,0,284,75,20,0,293,194,5,0,309,274,8,0,295,93,4,0,244,279,0,0,324,199,8,1,324,199,0,1,312,131,4,1,265,201,20,0},
/*82-2*/	{430,299,23,0,335,174,7,0,321,238,28,0,318,238,20,0,280,310,28,0,340,220,28,0,289,171,4,0,317,328,1,0,382,170,8,0,372,323,3,0,317,170,7,0,324,178,0,0,280,310,20,1,308,337,4,0,319,238,28,0},
/*2-1*/		{401,268,28,0,249,198,0,0,401,268,4,0,339,137,28,0,283,244,28,0,311,272,28,0,255,272,28,0,283,300,28,0,268,272,0,0,298,272,0,0,284,300,12,0,339,187,28,0,325,201,28,0,339,136,28,1,331,253,20,1},
/*2-2*/		{386,227,28,0,290,243,0,0,386,227,4,0,248,256,16,0,320,203,0,0,340,144,0,0,290,183,0,0,340,183,0,0,355,238,8,0,335,164,0,0,224,223,8,0,270,183,0,0,310,183,24,1,205,242,4,0,224,223,4,0},
/*83-1*/	{263,136,0,1,251,273,4,0,251,273,0,0,345,252,0,0,343,176,24,0,283,136,0,0,283,196,16,0,283,168,8,0,318,177,16,0,338,196,0,0,236,232,28,0,323,216,16,0,283,136,24,1,349,249,20,0,344,292,28,1},
/*83-2*/	{259,188,31,0,288,263,3,0,276,289,3,1,340,291,30,0,305,202,0,0,283,325,9,0,325,162,0,0,325,182,0,0,305,177,8,0,320,237,24,0,407,340,6,0,365,162,0,1,377,242,28,0,334,341,4,0,354,219,28,0},
/*3-1*/		{241,159,0,1,298,182,20,0,325,210,12,1,281,265,12,0,268,345,0,1,288,285,0,0,248,285,24,0,248,285,16,1,293,380,8,1,268,290,8,0,253,237,28,0,288,265,24,1,308,305,0,1,354,181,28,1,262,134,28,0},
/*3-2*/		{302,183,8,0,243,162,0,0,287,223,4,1,339,285,7,0,307,230,0,1,324,197,0,0,265,148,4,0,307,258,8,0,369,148,8,0,342,237,0,0,298,157,7,0,324,197,0,0,307,218,0,0,364,120,20,0,241,317,12,1},
/*84-1*/	{222,172,4,0,335,172,20,0,294,272,0,0,354,212,4,0,334,272,0,0,334,312,16,0,374,192,0,0,354,132,0,0,339,292,0,0,349,113,0,0,294,213,0,0,334,232,8,0,334,132,0,0,354,312,0,1,294,272,0,0},
/*84-2*/	{290,304,8,0,193,214,1,0,370,304,0,1,251,247,30,0,311,284,12,0,269,180,29,0,269,330,29,0,235,309,29,0,209,355,5,0,251,174,5,0,304,222,13,0,263,231,29,0,214,341,19,1,346,280,12,0,304,237,3,0},
/*4-1*/		{233,140,3,0,254,250,11,0,344,213,28,0,372,258,7,0,363,193,0,0,322,194,24,0,362,133,0,0,322,193,16,0,302,189,8,0,327,118,8,0,281,278,30,0,402,193,16,0,322,133,0,0,231,282,4,0,344,213,28,0},
/*4-1*/		{284,243,0,1,256,175,24,0,313,187,28,0,369,187,28,0,324,294,0,0,304,264,0,0,304,344,8,0,304,294,8,0,284,339,8,0,359,329,0,0,314,186,6,0,344,344,16,0,304,224,0,0,387,271,20,0,327,229,12,1},
/*85-1*/	{218,125,0,0,171,213,0,0,336,229,4,1,323,300,12,0,237,227,28,0,264,199,4,0,238,284,12,0,291,227,4,0,275,217,20,0,221,294,28,0,336,229,28,0,251,213,28,0,393,229,28,0,278,145,28,1,275,246,20,1},
/*85-2*/	{260,155,0,0,340,261,29,0,260,235,4,0,288,320,16,0,356,236,29,0,320,194,28,0,298,130,20,1,372,247,29,0,333,249,8,0,360,232,21,0,347,291,9,0,348,194,8,1,288,234,8,0,246,306,4,0,269,103,28,0},
/*5-1*/		{310,182,8,0,310,282,0,0,311,225,8,1,310,222,16,0,350,182,0,0,390,182,0,0,350,222,0,0,390,222,0,0,355,202,0,0,385,202,0,0,315,261,3,0,350,222,0,0,310,282,8,0,313,183,20,0,311,225,20,0},
/*5-2*/		{254,136,1,0,232,367,5,0,302,228,28,0,303,303,4,0,265,189,17,0,322,179,6,0,322,179,6,0,313,224,30,0,246,188,9,0,297,213,22,0,340,345,4,0,273,256,24,1,345,123,30,0,344,270,12,0,312,372,12,1},
/*86-1*/	{323,226,1,0,248,146,24,0,300,313,12,1,288,245,0,1,272,229,4,0,299,202,4,0,299,202,28,0,166,235,20,0,180,327,4,0,289,219,12,0,288,186,16,0,258,299,4,1,272,229,28,0,247,254,4,1,138,207,20,1},
/*86-2*/	{241,227,2,0,354,326,31,0,355,326,27,0,359,236,20,0,379,206,22,0,380,217,0,1,359,293,4,0,380,260,0,0,391,273,21,0,360,255,24,0,364,300,28,0,360,157,0,0,420,300,16,0,301,272,20,1,323,350,11,0},
/*6-1*/		{277,262,24,0,203,183,4,0,277,262,28,1,251,204,4,1,279,159,28,0,271,204,0,0,274,165,4,0,266,248,28,0,298,244,14,0,316,259,24,0,292,132,8,0,307,132,28,0,271,204,24,1,334,105,20,0,305,290,12,1},
/*6-1*/		{239,138,31,0,262,272,0,0,262,225,9,1,265,168,6,0,302,232,0,0,342,232,0,0,302,272,0,0,342,272,0,0,307,252,0,0,337,252,0,0,285,265,6,0,302,152,0,0,302,172,0,0,212,258,3,0,343,152,12,0},
/*87-1*/	{221,135,0,0,300,191,10,0,249,242,4,1,310,169,12,0,297,233,0,1,317,292,0,0,357,234,24,0,329,272,4,0,342,327,8,0,377,239,8,0,289,259,16,0,317,312,16,1,297,253,8,0,343,171,28,1,310,114,28,0},
/*87-2*/	{319,139,0,0,233,287,8,0,399,280,0,1,343,280,12,0,371,252,12,0,268,216,4,0,329,238,4,0,315,280,12,0,300,233,12,0,361,236,20,0,239,159,28,0,282,230,12,0,329,294,12,0,376,162,28,1,303,273,28,1},
/*7-1*/		{305,123,28,0,350,223,30,0,248,180,0,0,370,142,28,0,328,260,0,0,375,196,4,0,389,211,4,0,348,180,16,0,378,193,4,0,306,295,24,0,361,249,10,0,288,220,8,0,288,220,0,0,297,132,12,1,306,123,12,1},
/*7-2*/		{443,291,23,0,270,198,0,0,284,264,0,0,374,164,28,0,329,140,28,0,334,104,0,0,338,243,20,0,339,299,4,0,343,134,20,0,321,309,28,0,284,264,0,0,334,104,0,0,338,299,12,0,364,183,28,1,316,183,4,1},
/*88-1*/	{274,343,8,0,217,181,0,0,220,313,28,1,292,350,7,0,265,166,12,0,262,355,28,0,291,261,12,0,330,155,4,0,300,295,8,0,244,365,28,0,163,313,28,0,275,220,0,0,262,355,12,0,313,165,28,1,270,220,20,0},
/*88-2*/	{287,257,0,1,232,206,4,0,344,179,4,0,381,247,0,0,411,176,20,0,297,240,5,0,415,266,12,0,303,198,29,0,308,309,13,0,317,338,24,0,342,287,28,0,292,263,0,0,314,180,29,0,400,286,28,1,343,237,12,1},
/*8-1*/		{347,129,0,0,208,154,0,0,347,209,4,0,375,293,20,0,243,255,28,0,218,225,4,0,215,283,28,0,215,283,12,0,232,273,28,0,278,269,28,0,310,254,4,0,271,199,28,0,260,182,28,0,347,265,28,1,260,182,12,1},
/*8-2*/		{261,147,0,1,278,289,4,0,318,172,28,0,345,141,27,0,275,315,0,1,275,275,0,0,347,201,20,0,275,315,0,0,353,283,18,0,270,295,0,0,272,221,28,0,334,249,0,0,374,329,8,1,361,215,12,0,220,275,12,1},
/*89-1*/	{313,205,8,0,229,282,8,0,353,244,4,0,341,177,28,0,305,265,4,0,305,265,4,0,291,228,4,0,292,271,0,0,322,274,4,0,347,236,0,0,393,205,16,0,272,271,24,1,272,251,8,0,340,314,4,0,261,250,20,0},
/*89-2*/	{279,258,1,1,384,334,11,0,329,234,28,0,346,136,27,0,344,221,28,0,307,214,0,0,320,286,4,0,371,248,4,0,287,219,24,0,354,238,4,0,287,214,8,0,367,174,24,0,347,174,24,0,335,288,28,1,280,259,20,1},
/*9-1*/		{269,112,0,0,281,295,31,0,290,265,28,0,229,134,8,0,280,365,12,1,292,330,28,0,256,205,0,0,287,205,0,1,242,250,0,0,322,221,0,0,229,125,20,0,257,225,24,1,247,205,24,1,249,280,28,1,292,135,28,0},
/*9-2*/		{320,247,0,1,323,272,31,0,323,172,4,1,337,230,28,0,320,307,0,0,344,214,20,0,226,271,12,0,344,192,28,0,209,302,4,0,358,270,29,0,306,138,28,0,246,175,28,0,231,303,12,0,260,189,12,0,319,248,12,1},
/*90-1*/	{286,184,31,0,207,214,4,0,270,262,3,0,252,323,20,0,332,216,0,0,332,196,0,0,392,305,20,0,336,277,28,0,315,304,4,0,337,141,8,0,364,277,8,0,322,291,4,0,332,156,0,0,414,210,20,0,198,264,12,1},
/*90-2*/	{261,131,24,0,334,213,29,0,332,213,8,1,292,214,0,0,290,116,4,0,298,158,4,0,392,121,12,0,280,207,28,0,366,205,12,0,283,130,20,0,235,129,5,0,297,159,4,0,239,153,28,0,209,240,4,0,326,75,20,1},
/*10-1*/  	{239,212,8,0,188,183,0,0,252,212,0,0,275,239,4,1,341,166,20,0,298,112,28,0,279,153,0,0,299,258,4,0,307,224,24,0,309,143,24,0,260,153,28,0,284,68,0,0,322,239,0,1,332,227,12,0,274,142,4,1},
/*10-2*/  	{426,288,21,0,268,188,0,0,287,260,0,0,324,168,26,0,348,128,0,0,308,128,0,0,307,300,16,0,348,128,0,0,332,335,8,0,343,108,0,0,351,172,1,0,308,128,0,0,347,260,0,1,347,260,16,0,315,220,12,1},
/*91-1*/  	{252,223,30,0,295,140,0,0,289,209,6,1,426,229,26,0,356,279,12,0,293,289,20,0,313,196,0,0,353,196,0,1,302,247,4,1,349,117,2,0,396,320,6,0,293,196,0,0,373,256,16,0,283,305,4,0,370,264,20,0},
/*91-2*/  	{322,328,8,0,274,297,0,0,360,155,28,0,380,198,28,0,276,277,12,0,276,195,4,0,361,277,12,0,321,290,28,0,200,305,4,0,289,188,4,0,230,195,1,0,277,277,4,1,380,198,28,0,263,263,12,0,333,264,4,1},
/*11-1*/  	{382,195,29,0,369,270,30,0,339,260,1,0,270,269,4,0,316,246,1,0,403,253,4,0,417,267,4,0,256,311,28,0,303,254,1,0,406,250,20,0,297,240,20,0,283,198,28,0,256,254,28,0,288,152,28,1,212,212,28,0},
/*11-2*/  	{260,127,29,0,211,253,5,0,262,278,4,1,324,124,6,0,354,235,0,0,334,254,24,0,338,155,18,0,334,234,0,1,314,249,8,0,374,240,8,0,292,186,28,0,394,274,24,0,334,234,0,0,334,274,0,1,320,214,12,1},
/*92-1*/  	{279,267,28,1,256,158,0,0,279,267,4,1,364,295,28,0,336,158,28,0,377,281,4,0,308,185,28,0,405,309,4,0,325,175,28,0,388,298,4,0,392,323,12,0,308,185,28,1,350,171,28,0,377,281,4,0,279,268,4,1},
/*92-2*/  	{408,128,0,0,357,209,0,0,312,228,4,0,368,168,8,0,361,231,20,0,388,319,0,0,428,279,0,0,399,296,5,0,299,239,3,1,413,354,24,0,312,228,4,0,428,239,24,0,428,279,0,1,449,197,28,1,340,255,20,0},
/*12-1*/  	{297,133,28,0,174,257,0,0,366,285,28,0,298,214,28,0,310,172,28,0,253,286,28,0,323,159,20,0,351,187,4,0,236,296,12,0,334,176,4,0,282,329,4,0,310,257,12,0,239,272,20,1,297,314,28,1,366,285,12,0},
/*12-2*/  	{310,152,0,0,394,278,16,0,394,278,0,1,353,278,28,0,340,252,12,0,272,292,0,0,368,253,4,0,292,192,0,0,358,235,4,0,287,172,0,0,224,244,4,0,252,252,0,0,292,192,0,1,210,258,4,0,328,214,12,1},
/*93-1*/  	{268,159,28,0,272,182,0,0,211,215,0,0,231,248,4,0,353,329,0,0,293,309,24,1,349,277,0,1,350,246,0,0,329,272,8,0,330,241,24,0,332,181,7,0,293,309,24,1,313,289,24,1,216,319,4,0,322,212,12,1},
/*93-2*/  	{281,274,0,1,310,295,31,0,282,174,4,1,201,233,8,0,296,287,4,0,296,287,4,0,325,206,12,0,328,181,4,0,308,216,12,0,311,192,28,0,281,354,8,1,241,273,8,0,261,273,8,0,321,273,16,1,263,134,28,0},
/*13-1*/  	{225,184,0,0,247,242,24,0,351,227,4,0,322,206,28,0,289,296,8,1,289,246,0,0,309,246,24,0,249,189,28,0,269,291,8,0,322,227,28,0,266,201,4,0,294,171,20,1,289,236,0,0,393,185,20,0,379,256,20,0},
/*13-2*/  	{348,215,26,0,239,291,8,0,299,184,30,0,299,126,4,0,311,231,10,0,258,184,20,0,294,302,29,0,387,311,18,0,351,172,20,0,305,267,5,0,210,289,4,0,396,330,18,0,269,274,0,0,335,246,26,1,262,268,20,0},
/*94-1*/  	{344,300,28,0,256,226,24,0,316,272,4,0,355,167,8,1,255,187,0,0,295,187,0,0,276,256,8,0,316,235,0,0,275,192,24,0,328,371,24,0,316,272,4,0,256,296,16,1,255,167,24,1,255,167,0,1,315,207,12,1},
/*94-2*/  	{252,194,29,0,283,283,20,1,339,339,8,0,299,223,4,1,319,183,8,0,319,223,0,0,319,203,8,0,319,263,8,0,354,278,0,1,299,198,24,0,359,279,4,0,359,223,0,1,379,183,24,0,267,311,4,1,260,345,12,1},
/*14-1*/  	{211,198,0,0,305,212,9,0,305,212,5,0,289,205,29,0,326,250,29,0,288,277,29,0,304,283,29,0,304,283,13,0,319,270,29,0,273,291,29,0,353,316,3,1,288,277,13,0,342,262,13,0,194,294,4,0,321,294,19,1},
/*14-2*/  	{234,147,28,0,348,174,30,0,307,173,0,0,362,269,6,0,243,257,4,0,332,218,0,0,307,291,11,0,295,274,19,0,367,249,0,1,292,278,3,0,307,173,0,0,352,218,24,0,352,258,0,1,277,191,20,0,216,228,20,1},
/*95-1*/  	{193,264,1,0,317,255,16,0,241,142,4,1,274,131,1,0,296,136,1,0,254,289,20,0,275,132,13,0,327,284,28,0,260,145,13,0,283,145,17,0,288,311,4,0,326,341,12,0,268,275,28,0,292,230,12,0,306,188,3,0},
/*95-2*/  	{276,150,28,0,277,263,0,0,264,282,4,1,323,263,20,0,345,330,4,0,302,288,4,0,331,317,4,0,322,325,12,0,362,340,4,0,343,240,2,0,304,198,4,0,327,203,0,0,336,339,12,0,353,148,28,1,210,250,12,1},
/*15-1*/  	{241,140,29,0,189,264,5,0,247,287,4,1,355,170,4,0,355,207,0,1,295,334,0,0,335,170,0,1,315,190,0,0,295,185,8,0,330,150,0,0,250,209,29,0,355,170,0,1,335,294,0,1,315,251,28,0,293,230,28,0},
/*15-2*/  	{261,152,0,1,203,283,7,0,312,231,30,0,301,193,8,1,327,319,2,0,293,240,18,0,327,320,30,1,251,307,4,0,338,335,2,0,304,296,2,0,280,190,4,0,241,269,0,0,304,264,2,0,185,295,3,0,279,215,12,1},
/*96-1*/  	{310,200,29,0,328,238,7,0,304,299,4,1,260,193,26,0,348,298,0,0,203,309,4,0,348,287,16,0,328,267,16,0,259,314,29,1,383,281,0,0,251,214,1,0,232,252,28,0,368,298,16,0,348,361,12,0,205,225,28,0},
/*96-2*/  	{333,292,27,1,299,232,7,0,219,216,0,0,259,176,0,0,212,178,4,0,212,178,4,0,268,255,4,0,288,307,12,0,294,173,18,0,259,238,20,0,215,270,28,0,316,250,28,0,270,270,28,0,183,293,20,1,327,262,3,0},
/*16-1*/  	{259,151,31,0,162,193,0,0,243,230,3,0,237,150,27,0,324,232,0,1,300,244,28,0,304,168,0,0,324,218,0,0,304,213,8,0,309,267,24,0,284,168,28,0,324,138,0,0,364,258,16,0,229,260,4,1,304,298,0,0},
/*16-2*/  	{269,297,16,0,268,137,20,1,270,297,24,0,325,136,4,0,310,177,28,0,337,203,28,0,355,311,12,0,327,339,28,0,338,322,12,0,321,194,4,0,268,218,12,1,313,326,12,0,327,312,12,0,298,285,12,1,275,209,4,1},
/*97-1*/  	{274,208,24,0,251,83,0,0,308,307,28,0,308,250,4,0,271,157,0,0,255,124,30,1,268,249,24,0,284,226,28,0,244,134,2,0,326,171,0,0,295,209,28,0,268,289,16,1,311,137,0,1,389,234,18,0,317,153,4,0},
/*97-2*/  	{314,269,24,0,188,161,0,0,268,161,28,1,323,126,28,0,278,249,0,0,283,66,0,0,332,210,12,0,245,223,28,0,313,239,0,1,285,115,24,0,343,297,12,0,283,66,0,0,298,189,0,1,339,146,28,1,266,147,4,1},
/*17-1*/  	{230,144,0,1,225,266,0,0,299,228,28,0,276,294,12,0,267,199,0,0,267,159,0,0,316,203,12,0,267,139,0,0,257,194,29,1,247,134,24,0,293,352,4,0,267,159,8,0,327,159,24,0,370,173,20,0,292,334,12,0},
/*17-2*/  	{241,269,0,1,220,290,8,0,337,220,28,0,323,306,0,0,247,223,12,0,332,200,0,1,294,262,20,0,280,304,12,0,236,206,4,0,326,184,3,0,303,215,4,1,312,140,0,0,294,318,12,0,365,304,20,0,204,152,28,0},
/*98-1*/  	{357,234,24,0,217,210,5,0,317,194,0,0,338,273,6,0,298,296,20,0,367,252,28,0,367,252,4,0,284,282,20,0,281,285,20,0,357,235,20,0,357,194,12,0,237,275,12,0,328,277,0,1,218,294,4,0,302,193,4,0},
/*98-2*/  	{253,252,1,1,269,339,4,0,284,218,28,0,337,267,5,0,340,178,8,0,244,158,0,0,300,158,0,0,264,158,0,1,242,208,8,0,342,210,24,0,268,263,28,0,244,158,0,0,300,218,16,1,335,261,12,0,255,268,20,1},
/*18-1*/  	{326,135,31,0,372,277,16,0,372,277,0,1,334,277,28,0,314,255,12,0,242,258,0,0,242,307,8,0,248,236,28,0,222,302,8,0,277,268,0,0,281,256,6,0,282,307,16,0,248,180,28,0,262,194,12,0,309,214,28,0},
/*18-2*/  	{305,105,0,0,337,207,31,0,231,224,6,1,326,277,28,0,305,165,0,1,244,190,31,0,311,230,4,1,252,215,6,0,369,142,1,1,292,133,4,0,313,312,0,0,236,165,0,0,339,286,12,1,325,165,28,1,216,265,0,0},
/*99-1*/  	{346,129,0,0,208,211,0,0,351,209,28,0,234,237,4,0,366,277,8,0,326,337,0,0,346,257,0,0,346,179,0,0,329,294,20,0,326,174,24,0,267,203,7,0,366,297,8,1,366,297,0,1,233,293,4,0,351,209,28,0},
/*99-2*/  	{294,258,8,0,245,228,0,0,350,141,4,1,309,275,29,0,395,182,0,0,322,142,0,0,270,181,3,0,267,206,28,0,275,153,6,0,385,217,24,0,382,320,7,0,350,142,0,0,345,258,0,1,386,271,12,0,279,212,12,1},
/*19-1*/  	{252,125,0,1,274,245,24,0,314,149,28,0,287,232,28,0,287,177,0,0,286,109,0,0,269,223,4,0,324,247,12,0,307,182,24,0,309,282,20,0,297,233,0,0,286,149,8,0,337,290,12,0,357,192,12,0,302,324,12,1},
/*19-2*/  	{317,165,24,0,344,300,8,0,249,270,4,1,373,271,0,0,284,300,0,0,320,281,5,0,284,220,0,0,264,281,20,0,333,272,21,0,256,294,20,0,316,311,28,0,344,260,8,1,284,300,16,1,284,221,0,1,282,200,12,1},
/*100-1*/  	{284,308,24,0,215,114,31,0,290,302,28,1,273,289,12,0,298,159,29,0,304,114,5,0,260,275,28,0,288,247,12,0,277,264,28,0,329,267,4,0,289,224,8,0,315,98,29,0,349,184,24,0,307,267,12,0,305,344,12,1},
/*100-2*/  	{279,259,0,1,194,202,0,0,274,202,28,1,299,340,8,0,287,255,12,0,295,182,0,1,244,178,4,0,300,312,4,0,336,241,20,0,258,258,28,0,265,192,5,0,284,269,0,0,295,122,0,0,266,326,28,1,302,230,12,1},
/*20-1*/  	{329,152,25,0,345,128,5,0,345,128,1,0,321,213,17,0,293,247,17,0,292,246,17,0,332,240,1,0,332,240,1,0,273,246,9,0,311,239,25,0,283,220,1,0,371,210,29,0,360,227,13,0,321,247,29,0,289,75,19,1},
/*20-2*/  	{332,224,0,1,221,174,4,0,348,192,28,0,388,242,28,0,363,152,0,0,343,172,0,1,343,112,0,0,323,172,0,0,303,167,8,0,383,157,8,0,265,175,20,0,374,312,12,0,363,112,0,1,239,260,4,0,348,192,28,0},
/*101-1*/  	{287,188,24,0,290,100,24,0,287,188,28,1,328,280,6,0,286,302,22,0,311,141,28,0,296,188,0,0,332,245,0,0,320,174,8,0,312,240,24,0,252,333,6,1,295,99,0,0,332,265,8,1,277,322,6,0,315,216,12,1},
/*101-2*/  	{309,264,24,0,242,103,31,0,350,121,0,0,309,264,0,0,329,364,24,0,329,324,0,0,310,181,0,0,329,364,0,0,324,384,0,1,324,344,0,0,391,121,12,0,309,324,8,0,369,284,8,1,311,202,28,0,290,181,28,0},
/*21-1*/  	{322,89,28,0,209,202,4,0,309,269,28,0,419,152,10,1,284,219,12,0,309,169,0,0,352,206,20,0,309,249,0,0,366,199,20,0,289,244,24,0,265,185,8,1,349,169,0,1,369,229,24,0,382,137,2,1,309,269,28,0},
/*21-2*/  	{307,215,28,0,288,88,0,0,352,230,4,1,317,205,28,0,287,272,0,0,317,129,0,0,286,205,0,0,369,236,4,0,356,170,1,1,313,160,3,0,392,216,12,0,357,129,0,1,392,216,0,1,400,144,20,0,290,117,20,0},
/*102-1*/  	{262,145,28,0,289,222,8,0,305,262,4,1,337,138,5,0,338,227,0,1,338,194,0,0,338,294,16,0,338,263,8,0,373,237,0,1,373,273,0,0,249,222,0,0,378,194,0,1,338,254,0,0,206,243,4,0,257,249,20,0},
/*102-2*/  	{268,238,0,1,297,212,0,0,376,278,4,1,196,255,12,0,391,198,28,0,238,240,12,0,419,226,12,0,267,212,12,0,401,215,20,0,255,230,12,0,406,240,12,0,295,185,4,1,225,227,20,1,358,202,12,0,305,349,12,1},
/*22-1*/  	{255,192,26,0,329,187,28,0,298,259,4,0,298,120,28,0,242,179,4,0,313,170,12,0,298,260,16,0,326,231,4,0,341,262,0,1,299,245,24,0,310,112,28,0,270,207,12,1,327,129,28,1,239,237,4,0,399,173,20,0},
/*22-2*/  	{278,225,0,1,238,305,0,0,355,157,28,0,271,185,12,0,318,157,16,0,318,117,0,0,298,117,0,0,262,157,0,0,313,137,0,1,257,137,0,0,318,281,4,0,278,305,16,1,318,185,0,1,256,200,12,1,292,330,12,1},
/*103-1*/  	{299,82,0,0,269,147,24,0,369,290,28,0,259,122,8,0,284,290,20,0,298,276,4,0,241,304,12,0,270,304,20,0,288,293,28,0,224,315,28,0,340,318,12,0,297,190,28,0,227,290,20,1,312,205,12,0,410,248,20,0},
/*103-2*/  	{243,89,0,0,284,175,8,0,309,210,4,1,323,265,7,0,337,132,12,0,322,146,4,0,326,245,12,0,329,220,4,0,338,262,8,0,393,182,28,0,271,197,4,1,313,187,0,0,393,161,20,0,296,254,28,1,322,89,28,0},
/*23-1*/  	{313,189,1,0,251,213,24,0,250,292,28,1,291,216,4,0,237,279,28,0,346,307,28,0,319,279,20,0,208,306,20,0,225,296,28,0,329,296,4,0,334,321,12,0,251,292,16,1,291,173,0,1,306,131,28,1,292,145,28,1},
/*23-2*/  	{361,166,0,0,315,201,8,0,315,200,4,0,314,174,30,0,370,119,28,0,397,146,28,0,341,147,28,0,369,175,28,0,359,136,28,0,379,157,28,0,206,172,30,0,299,210,22,0,258,151,30,0,299,105,20,1,343,228,20,0},
/*104-1*/  	{284,183,28,0,284,217,8,0,313,245,4,1,284,273,4,0,340,282,12,0,327,314,28,0,346,232,12,0,298,342,28,0,329,264,4,0,309,325,28,0,211,241,12,0,237,157,28,0,224,170,28,0,337,164,28,1,210,241,20,1},
/*104-2*/  	{263,237,1,1,320,164,30,0,296,279,2,1,272,119,8,0,315,229,0,0,307,276,20,0,315,249,8,0,303,196,12,0,295,244,8,0,317,189,4,0,258,215,12,0,284,131,28,0,271,144,28,0,400,271,3,1,357,270,18,0},
/*24-1*/  	{212,141,0,0,199,182,24,0,292,158,4,1,199,262,16,0,313,161,4,1,348,241,24,0,328,240,0,1,300,241,0,0,323,220,0,1,256,222,1,0,368,280,4,0,308,240,24,1,260,217,0,0,324,159,12,0,344,139,20,0},
/*24-2*/  	{307,131,0,0,287,168,24,0,307,209,4,0,336,236,0,0,287,209,0,0,356,209,0,0,287,248,0,0,356,249,0,0,292,228,0,0,351,229,0,0,276,258,20,0,276,315,8,0,296,315,8,0,398,290,20,0,356,249,4,0},
/*105-1*/  	{282,148,28,0,304,141,0,0,277,278,4,1,365,285,30,0,367,222,0,0,340,284,20,0,300,217,0,0,336,141,0,1,363,220,15,0,342,217,2,0,320,266,19,0,387,222,8,1,360,257,8,1,411,291,3,1,214,248,12,1},
/*105-2*/  	{264,198,24,0,234,100,0,0,284,287,28,0,244,141,4,1,264,201,0,0,284,256,0,0,339,222,4,0,263,177,5,0,329,205,4,0,319,261,0,0,297,219,5,0,244,141,0,0,304,287,16,0,325,330,12,0,298,151,28,0},
/*25-1*/  	{258,89,28,0,396,173,28,1,202,145,0,0,368,145,28,0,339,202,28,0,282,230,4,0,299,200,12,0,260,211,28,0,231,196,29,1,284,123,4,0,233,159,28,0,311,174,28,0,325,188,20,1,336,230,0,0,279,222,28,1},
/*25-2*/  	{231,158,0,0,362,197,8,0,362,195,4,0,350,129,28,0,336,182,0,0,356,224,0,0,293,177,4,0,327,239,0,0,351,244,0,1,322,219,0,0,322,157,0,0,276,219,24,1,276,202,8,0,223,246,4,0,391,223,20,0},
/*106-1*/  	{328,79,28,0,283,248,22,1,271,136,0,0,326,177,28,0,240,262,4,0,268,233,4,0,352,150,28,0,254,276,4,0,258,251,28,0,237,287,28,0,310,136,28,0,338,220,4,1,353,150,28,0,359,273,28,1,331,244,28,0},
/*106-2*/  	{420,307,22,0,257,313,6,0,309,291,10,0,240,152,6,0,346,254,0,0,386,168,0,0,346,132,16,0,366,132,0,0,313,171,11,1,361,112,0,0,386,188,4,0,386,132,0,1,274,274,14,0,349,211,28,0,327,190,28,0},
/*26-1*/  	{271,230,24,0,190,92,0,0,271,230,28,1,272,230,12,0,230,210,24,0,230,190,8,0,190,209,24,0,269,190,8,0,210,215,24,0,250,195,8,0,230,150,0,1,230,210,16,0,269,150,24,0,311,163,20,0,239,123,12,1},
/*26-2*/  	{350,123,24,0,187,191,1,0,285,226,5,1,349,234,4,0,309,235,0,1,349,270,0,0,303,221,4,0,308,294,16,0,195,284,5,0,345,212,0,0,290,235,0,0,217,210,29,0,309,295,16,1,317,207,12,0,194,130,28,0},
/*107-1*/  	{367,143,30,0,219,158,0,0,240,215,4,0,268,299,20,0,269,299,20,0,241,271,4,0,309,271,0,0,240,271,0,0,252,288,20,0,220,266,24,0,339,258,2,0,269,189,0,0,309,309,16,0,390,291,3,1,267,189,12,1},
/*107-2*/  	{304,262,24,0,232,143,0,0,304,262,28,1,304,222,0,0,375,248,28,0,326,129,4,0,400,274,12,0,353,156,4,0,384,265,20,0,336,146,4,0,389,290,12,0,312,142,28,0,299,156,28,0,346,220,20,0,261,88,28,0},
/*27-1*/  	{435,281,22,0,202,264,1,0,295,270,0,0,242,199,4,0,355,179,0,0,335,179,0,0,335,170,0,0,315,210,8,0,228,228,0,0,370,222,0,0,283,205,3,0,315,330,16,1,355,170,0,1,355,270,16,0,212,169,28,0},
/*27-2*/  	{226,163,0,0,397,167,16,0,357,207,4,0,357,207,16,0,321,254,29,0,310,183,28,0,327,210,5,0,280,254,4,0,269,197,7,0,290,290,24,0,251,131,27,0,265,215,0,0,338,193,29,0,214,255,4,0,385,235,20,0},
/*108-1*/  	{310,202,24,0,345,243,31,0,305,224,0,0,265,304,16,0,358,297,0,0,310,147,4,0,383,229,12,0,336,118,0,0,333,332,8,0,331,98,0,0,225,304,0,0,282,174,20,1,318,257,0,0,330,181,28,1,282,118,28,0},
/*108-2*/  	{316,225,0,1,206,188,0,0,267,264,28,0,331,115,4,0,260,180,12,0,336,247,0,0,342,283,12,0,361,284,4,0,381,302,8,0,250,163,20,0,360,197,3,0,299,152,24,1,336,247,24,1,345,246,12,0,223,249,12,1},
/*28-1*/  	{298,238,0,1,262,155,0,0,318,178,0,0,278,218,0,0,382,304,4,0,325,276,4,0,341,240,4,0,340,262,28,0,364,212,12,0,399,315,20,0,278,258,8,1,382,191,28,0,396,290,20,0,360,135,28,1,291,101,28,0},
/*28-2*/  	{378,143,24,0,210,195,0,0,316,236,4,1,238,222,30,0,329,237,8,0,288,235,0,0,248,195,0,0,376,257,0,1,375,185,4,0,308,187,28,0,319,277,28,0,376,238,8,1,316,237,24,1,262,132,28,1,268,217,20,0},
/*109-1*/  	{172,174,4,0,286,174,20,0,286,174,28,1,319,280,28,0,275,237,4,0,309,143,4,0,291,339,20,0,289,252,4,0,320,358,12,0,271,241,4,0,293,274,2,0,369,230,12,1,319,281,28,0,342,172,28,1,280,115,28,0},
/*109-2*/  	{297,207,0,0,262,172,0,0,276,237,4,1,328,256,0,0,279,296,0,0,318,296,0,0,279,335,0,0,318,335,0,0,284,316,0,0,314,316,0,0,328,240,28,0,318,335,8,0,338,334,8,0,279,296,0,1,308,205,12,1},
/*29-1*/  	{343,109,0,0,330,237,30,0,324,190,28,0,265,297,16,0,303,140,0,1,303,110,0,0,303,190,0,0,303,170,8,0,323,185,8,1,338,151,0,0,225,297,0,0,284,318,16,1,303,170,16,1,241,142,20,1,264,258,0,0},
/*29-2*/  	{319,97,0,0,377,263,4,0,394,263,0,0,333,245,4,1,372,141,28,0,277,275,28,0,386,155,4,0,320,261,4,0,376,139,4,0,260,286,28,0,261,126,27,0,333,162,28,0,263,261,20,1,334,263,0,0,263,177,28,0},
/*110-1*/  	{360,152,24,0,175,238,4,0,272,181,8,1,319,203,28,0,391,211,20,0,312,299,28,0,350,154,0,1,298,312,4,0,278,285,28,0,345,135,0,0,322,306,0,0,334,208,28,1,362,151,28,0,274,277,4,1,361,306,28,1},
/*110-2*/  	{268,145,0,1,293,368,4,0,376,188,0,0,268,225,16,0,320,255,28,0,280,239,12,0,293,282,28,0,306,213,12,0,309,272,28,0,296,230,12,0,350,368,4,0,280,239,28,1,333,328,12,0,416,188,16,0,376,227,28,1},
/*30-1*/  	{281,113,26,0,241,258,0,0,279,204,28,0,320,162,28,0,308,83,0,0,268,83,0,0,288,143,24,0,308,83,0,0,328,88,24,0,303,63,0,0,281,258,0,0,268,83,0,0,320,161,28,0,345,164,11,0,308,206,28,0},
/*30-2*/  	{332,99,29,0,342,154,31,0,288,166,1,0,370,215,6,0,206,166,0,0,226,124,0,0,246,224,24,0,225,185,8,0,191,229,0,0,261,183,0,0,331,240,4,0,266,124,0,1,246,184,0,1,272,233,3,0,359,212,20,0},
/*111-1*/  	{262,117,0,1,246,284,0,0,325,89,28,0,273,232,0,1,332,197,16,0,354,257,28,0,297,138,0,0,297,158,0,0,312,193,8,0,277,153,24,0,272,233,0,0,297,177,8,0,297,257,20,1,399,150,20,0,297,312,12,1},
/*111-2*/  	{266,59,0,0,210,139,0,0,265,291,8,0,327,178,0,1,274,175,28,0,194,206,4,0,278,206,12,0,266,119,0,0,243,220,4,0,246,114,24,0,309,217,28,0,264,193,4,1,284,192,20,1,277,303,28,1,244,230,28,0},
/*31-1*/  	{371,253,26,1,285,211,0,0,298,277,0,0,334,139,3,0,358,138,30,0,378,175,28,0,339,211,0,0,358,139,30,0,287,154,9,0,311,185,5,0,314,164,6,0,394,155,14,0,358,277,0,1,358,277,16,0,338,237,12,1},
/*31-2*/  	{310,176,25,0,258,131,0,0,331,245,28,0,300,277,30,0,344,231,28,0,314,203,0,0,372,259,12,0,335,131,0,0,354,248,20,0,330,111,0,0,372,326,7,0,294,333,16,1,314,163,0,0,370,223,11,0,305,163,12,1},
/*112-1*/  	{247,255,0,1,153,193,1,0,246,135,28,0,234,204,16,0,344,158,28,0,288,186,4,0,316,185,28,0,267,276,0,0,333,175,28,0,274,290,3,0,250,316,28,0,259,213,20,1,307,316,16,0,218,220,4,0,273,143,28,0},
/*112-2*/  	{232,131,1,0,273,241,0,0,307,281,28,0,302,119,27,0,303,241,8,0,303,146,0,0,342,201,0,1,323,126,0,0,318,106,0,1,323,196,24,0,326,142,2,0,282,181,24,1,363,166,8,1,346,323,12,0,363,202,4,0},
/*32-1*/  	{299,183,27,0,299,335,8,0,233,228,31,0,280,306,22,0,312,163,29,0,270,146,10,0,311,164,5,0,278,164,10,0,355,103,21,0,223,171,18,0,301,255,18,0,242,179,14,1,344,112,29,0,347,225,29,0,297,192,19,1},
/*32-2*/  	{297,194,0,1,230,126,0,0,329,274,28,0,336,194,4,0,364,179,28,0,281,174,10,0,317,127,0,0,317,254,0,0,378,173,20,0,270,185,18,0,337,154,12,0,297,214,16,1,337,214,0,1,321,66,28,1,309,126,28,0},
/*113-1*/  	{321,165,0,1,215,135,0,0,245,184,4,0,290,103,27,0,343,134,0,0,274,163,0,1,336,211,4,0,358,184,0,0,367,195,20,0,338,179,24,0,341,224,28,0,342,100,0,0,398,223,16,0,296,121,20,1,246,164,20,0},
/*113-2*/  	{255,152,0,1,349,194,8,0,375,255,28,0,309,153,8,0,273,212,20,0,328,186,4,0,390,223,6,0,235,231,8,0,315,222,4,0,280,287,24,0,376,255,2,0,255,172,0,0,234,231,24,1,277,170,12,0,371,172,20,0},
/*33-1*/  	{237,122,0,0,277,218,24,0,237,202,4,0,302,106,4,0,386,181,28,0,307,184,20,0,358,209,28,0,355,185,28,0,375,198,28,0,400,174,4,0,318,296,0,0,317,195,28,1,330,181,28,1,352,112,28,1,357,295,4,0},
/*33-2*/  	{329,253,24,0,301,152,0,0,357,281,12,1,293,193,8,0,304,240,12,0,307,312,20,0,389,301,12,0,333,341,0,0,381,236,2,1,313,336,24,0,293,109,27,0,373,193,0,1,333,281,0,0,399,239,28,1,342,151,3,0},
/*114-1*/  	{267,293,2,1,229,133,0,0,307,272,28,0,336,313,6,0,301,252,0,0,272,252,0,1,321,212,0,1,292,212,0,0,316,192,0,1,287,192,0,0,276,321,22,0,301,251,16,1,252,192,0,0,324,118,28,1,337,312,18,0},
/*114-2*/  	{240,173,0,0,310,297,0,0,310,216,0,0,331,197,28,0,370,257,0,0,360,252,28,0,370,297,0,0,350,157,0,0,375,277,0,0,330,152,24,0,310,216,0,0,350,197,0,0,390,197,16,0,284,320,12,1,271,221,20,0},
/*34-1*/  	{278,208,24,0,222,147,31,0,343,138,28,1,301,247,20,0,358,199,0,0,358,239,16,0,358,199,0,0,321,208,0,0,363,219,0,0,363,314,24,0,288,138,28,0,338,299,16,1,341,188,0,1,399,137,28,1,285,243,19,0},
/*34-2*/  	{320,168,2,0,334,282,6,0,300,242,5,1,299,220,10,0,315,272,3,1,226,256,12,0,335,269,16,0,260,207,28,0,320,257,8,0,320,303,24,0,385,224,5,0,225,296,16,1,225,177,0,0,362,270,2,0,225,296,4,1},
/*115-1*/  	{257,222,28,0,190,209,0,0,300,258,4,1,298,133,4,0,237,279,0,0,340,236,0,0,340,316,0,0,320,268,8,0,360,311,8,0,355,277,0,0,285,204,28,0,340,236,0,0,340,256,0,1,339,147,28,1,209,153,28,0},
/*115-2*/  	{312,255,8,0,217,203,0,0,245,255,4,0,277,143,0,0,316,321,28,0,334,177,4,0,340,150,0,0,257,203,0,1,317,285,4,0,385,321,3,0,279,232,28,0,307,204,20,1,330,335,12,0,203,272,4,0,307,150,28,0},
/*35-1*/  	{402,196,24,0,216,169,6,0,349,249,4,0,198,167,7,0,358,197,8,0,320,194,20,0,348,250,4,0,292,222,20,0,306,152,6,0,255,194,2,0,340,143,5,0,263,156,29,0,306,179,28,1,235,209,29,0,330,179,11,1},
/*35-2*/  	{408,284,22,0,181,188,0,0,294,258,1,0,325,211,1,0,293,197,29,0,372,238,28,0,247,234,29,0,208,256,21,0,296,152,28,1,291,227,4,0,354,182,3,0,293,198,5,0,264,245,3,1,354,142,3,1,186,133,28,0},
/*116-1*/  	{321,54,31,0,205,222,0,0,284,222,28,1,304,96,27,0,305,202,0,1,285,182,0,1,305,202,24,0,305,162,0,0,340,192,0,1,285,157,24,0,298,279,28,0,345,142,0,1,355,279,28,0,339,221,28,1,298,262,20,0},
/*116-2*/  	{326,266,26,1,195,214,4,0,296,292,4,0,238,214,4,1,280,193,12,0,296,330,0,0,282,249,20,0,269,292,12,0,302,163,4,1,345,258,30,0,262,213,28,0,296,235,28,0,306,213,12,0,209,299,4,0,297,235,12,1},
/*36-1*/  	{303,183,8,0,297,288,4,0,354,231,4,0,296,263,28,0,243,219,13,0,324,206,28,0,227,208,21,0,316,254,28,0,229,204,5,0,306,237,20,0,197,243,29,0,257,183,0,0,257,203,0,0,255,140,28,0,342,243,8,1},
/*36-2*/  	{197,102,0,0,286,281,4,0,286,224,4,1,300,100,28,0,312,243,20,0,215,191,28,0,261,100,0,0,321,97,0,1,351,173,28,1,291,175,4,0,302,97,28,0,264,121,28,0,307,188,12,0,195,238,4,0,283,199,28,0},
/*117-1*/  	{356,156,24,0,194,306,3,0,260,261,3,0,276,184,1,1,359,285,29,0,369,169,28,0,338,318,29,0,341,198,28,0,353,304,29,0,352,181,28,0,306,230,15,0,369,224,20,0,382,182,4,1,359,285,3,1,425,329,13,0},
/*117-2*/  	{307,73,31,0,226,194,0,0,321,153,28,0,298,116,27,0,332,221,28,0,343,328,4,0,262,323,20,0,314,329,28,0,297,353,4,0,354,345,4,0,332,223,12,0,289,267,28,0,318,353,12,0,345,236,12,0,262,240,28,0},
/*37-1*/  	{219,230,0,1,155,198,0,0,275,226,4,0,259,145,28,0,329,237,20,0,234,139,20,0,238,279,12,0,234,169,12,0,323,218,8,0,244,313,24,0,267,222,12,0,219,238,0,0,279,162,24,1,316,267,4,0,345,296,12,0},
/*37-2*/  	{408,173,0,0,277,167,6,0,407,213,8,1,288,135,27,0,274,203,0,1,274,173,0,0,294,233,24,0,254,233,8,0,396,252,13,0,309,214,0,0,323,201,28,0,314,173,0,1,294,293,16,0,325,253,4,0,431,230,20,0},
/*118-1*/  	{248,167,0,0,296,218,8,0,296,218,4,0,247,188,27,0,348,177,28,0,216,262,12,0,341,271,20,0,230,276,4,0,371,290,12,0,214,266,4,0,186,219,28,0,362,164,28,0,369,214,28,0,282,288,4,0,281,214,20,0},
/*118-2*/  	{315,322,24,0,196,255,4,0,266,157,28,0,253,226,16,0,275,302,0,0,263,255,0,0,345,224,0,0,365,199,0,0,310,208,0,0,338,289,4,0,346,188,12,0,275,262,0,0,345,188,0,1,305,230,20,0,283,353,12,1},
/*38-1*/  	{310,211,27,1,197,273,3,0,376,255,29,0,251,285,15,0,299,255,15,0,267,210,7,0,259,248,31,0,306,218,7,0,268,230,31,0,298,236,31,0,323,140,11,0,282,131,31,0,278,150,31,0,264,184,2,1,313,204,2,0},
/*38-2*/  	{210,167,0,0,297,137,0,0,324,216,4,1,238,275,20,0,346,300,0,0,366,260,0,0,346,200,0,0,366,300,0,0,391,275,8,0,361,280,0,0,406,164,12,0,326,200,0,0,406,259,16,0,294,223,4,1,224,153,28,0},
/*119-1*/  	{288,86,0,0,177,179,0,0,317,259,4,1,315,139,0,0,260,251,28,0,297,179,0,0,274,265,4,0,297,219,0,0,264,248,4,0,292,199,0,0,317,259,8,0,377,179,24,0,357,179,24,0,289,286,28,1,217,219,0,0},
/*119-2*/  	{218,136,0,1,223,281,0,0,276,157,28,0,256,102,5,0,212,235,0,0,282,215,0,0,282,215,0,0,268,261,28,0,325,252,29,1,237,200,8,0,300,365,6,0,322,215,0,1,252,215,0,1,318,199,12,0,204,347,12,1},
/*39-1*/  	{298,63,28,0,270,175,28,0,298,147,4,1,252,247,28,0,263,264,12,0,319,146,0,0,288,261,4,0,317,125,4,0,318,244,20,0,319,178,2,0,371,219,20,0,299,86,0,0,319,206,16,1,342,246,28,1,301,206,28,0},
/*39-2*/  	{255,189,0,1,242,146,0,0,312,213,28,0,274,317,20,0,334,301,0,1,314,321,0,0,294,261,0,0,287,205,28,0,309,341,0,1,331,202,29,0,304,145,6,0,274,261,0,0,354,321,16,0,354,255,12,0,289,178,12,1},
/*120-1*/  	{258,233,30,0,324,290,30,0,325,291,26,0,327,169,28,0,302,249,0,1,287,109,0,0,242,229,0,1,294,146,4,0,317,225,0,1,222,224,24,0,192,293,30,0,287,109,0,0,242,209,24,1,227,146,20,1,346,212,20,0},
/*120-2*/  	{316,125,0,0,344,178,30,0,248,217,4,1,276,148,28,0,354,241,21,0,307,266,0,0,336,314,28,0,352,217,5,0,367,296,20,0,312,211,8,0,276,206,0,0,347,286,16,0,276,148,28,0,262,288,4,1,351,328,12,0},
/*40-1*/  	{316,91,1,0,399,279,14,0,341,212,30,0,429,249,26,0,256,214,29,0,294,192,29,0,288,236,13,0,273,225,29,0,269,230,21,0,279,206,29,0,435,310,10,0,288,164,29,0,452,303,18,0,245,230,27,1,321,114,21,0},
/*40-2*/  	{262,145,0,1,277,293,8,0,326,165,28,0,303,267,0,0,343,348,16,0,323,288,0,0,303,348,0,0,337,234,0,0,323,343,8,0,317,229,24,0,303,347,8,0,323,288,0,0,357,214,0,1,368,207,12,0,280,115,28,0},
/*121-1*/  	{381,212,22,0,263,156,4,0,275,255,4,1,224,127,6,0,274,324,20,0,295,248,29,0,329,353,4,0,297,270,6,0,319,336,4,0,256,334,28,0,263,206,1,0,319,253,8,1,319,253,0,1,215,194,2,0,320,185,4,0},
/*121-2*/  	{356,201,24,0,194,204,0,0,377,303,9,1,299,287,7,0,274,284,28,0,247,180,4,0,324,177,20,0,192,287,12,0,335,119,18,0,257,122,2,0,215,291,30,0,302,227,28,0,206,301,12,0,252,185,12,0,287,140,28,0},
/*41-1*/  	{365,94,29,0,183,152,0,0,341,213,4,1,340,263,12,0,393,189,11,0,269,231,28,0,382,172,19,0,277,223,20,0,387,170,3,0,252,241,28,0,316,160,27,0,283,160,28,0,278,137,28,0,382,249,12,0,278,80,20,1},
/*41-2*/  	{272,66,30,0,193,295,0,0,289,195,28,0,316,139,28,0,262,266,0,1,267,173,4,0,282,286,0,0,262,255,0,0,242,250,8,0,281,166,4,0,301,323,12,0,316,138,28,0,262,295,16,1,258,154,21,0,273,295,28,0},
/*122-1*/  	{340,221,0,0,223,145,0,0,295,169,0,0,303,89,4,0,320,253,0,0,300,223,0,0,300,273,0,1,300,302,0,0,280,268,8,0,280,297,24,0,263,342,28,0,340,222,0,1,300,342,16,1,321,127,28,1,281,223,12,1},
/*122-2*/  	{296,238,24,0,296,198,4,0,296,198,0,0,376,296,12,0,391,254,28,0,293,107,4,0,363,281,28,0,321,134,4,0,380,271,28,0,304,125,4,0,258,180,28,0,308,226,4,0,321,239,4,0,279,120,28,1,326,108,28,0},
/*42-1*/  	{337,281,28,1,222,173,0,0,302,173,28,1,258,277,28,0,310,225,4,0,372,186,28,0,268,295,12,0,345,214,28,0,251,305,12,0,355,197,28,0,337,281,20,0,253,281,4,0,324,296,12,1,313,165,12,0,226,225,28,0},
/*42-2*/  	{245,73,0,0,332,165,8,0,332,165,4,0,298,130,4,0,265,153,0,0,245,115,0,0,245,193,16,0,205,153,8,0,250,268,8,0,224,124,30,0,285,249,4,0,285,193,0,1,205,153,24,1,183,221,4,0,360,193,20,0},
/*123-1*/  	{268,154,24,0,182,246,9,0,268,154,28,1,228,251,16,0,325,154,28,0,268,239,28,0,297,181,28,0,241,266,28,0,314,171,28,0,251,249,28,0,284,213,12,0,338,169,28,0,324,183,28,0,181,248,5,0,240,267,12,1},
/*123-2*/  	{369,122,0,0,232,299,4,0,329,242,8,1,311,261,6,0,369,202,24,0,309,122,0,0,329,202,24,0,309,182,0,0,349,206,24,0,290,177,24,0,273,202,28,0,309,202,8,0,369,122,24,0,374,270,28,1,349,246,28,0},
/*43-1*/  	{313,293,20,0,204,219,4,0,204,219,0,0,285,139,4,0,285,265,4,0,313,237,4,0,312,292,4,0,340,264,4,0,302,254,28,0,323,275,28,0,340,264,8,0,312,168,12,1,242,153,20,1,242,153,12,1,341,224,12,0},
/*43-2*/  	{277,116,0,1,247,234,0,0,333,131,28,0,299,262,12,0,341,191,12,0,315,302,0,0,311,181,27,0,322,198,3,0,307,184,29,1,340,337,24,0,339,133,6,0,314,321,16,1,355,177,28,0,369,220,28,0,333,132,28,0},
/*124-1*/  	{197,174,3,0,196,241,0,0,276,241,28,1,260,285,29,0,329,176,28,0,264,211,20,0,310,177,4,0,255,164,4,0,310,233,4,0,237,174,28,0,349,328,7,0,289,170,28,0,330,234,12,0,317,142,20,0,304,269,12,1},
/*124-2*/  	{278,54,1,0,177,220,1,0,356,218,7,1,281,95,29,0,322,149,29,0,281,233,29,0,255,239,13,0,260,266,29,0,242,271,5,0,266,247,29,0,334,125,3,0,333,132,29,0,252,167,27,1,273,180,13,0,309,288,11,1},
/*44-1*/  	{383,172,30,0,352,245,31,0,270,256,0,0,250,176,8,0,313,149,28,0,290,176,8,0,350,296,24,0,330,176,8,0,325,331,8,0,310,181,8,0,270,256,0,0,290,196,0,0,310,256,0,0,372,189,20,0,284,121,28,0},
/*44-2*/  	{295,143,0,0,324,193,24,0,235,208,5,1,351,254,8,0,344,164,28,1,255,179,28,0,345,245,4,0,242,142,8,0,267,277,8,0,238,210,20,0,392,254,12,0,242,142,0,0,282,202,0,1,374,236,12,0,317,137,28,0},
/*125-1*/  	{302,211,28,0,217,183,0,0,297,183,28,1,279,141,8,0,379,283,20,0,339,242,20,1,312,298,4,0,340,298,20,0,369,222,20,0,362,272,4,0,342,211,16,0,354,312,20,0,353,227,28,0,367,169,28,1,318,181,4,0},
/*125-2*/  	{336,160,31,0,376,308,16,0,376,308,0,1,263,169,27,0,218,286,2,0,230,249,4,0,315,288,4,0,258,290,4,0,238,334,4,0,207,298,30,1,216,207,28,0,272,264,28,0,272,207,28,0,305,173,19,0,307,239,28,0},
/*45-1*/  	{255,154,0,0,291,230,7,0,330,251,4,1,214,173,8,0,350,215,0,1,350,181,0,0,350,251,24,1,350,281,8,0,385,223,0,1,385,261,0,0,354,180,6,0,390,181,0,1,350,241,0,0,230,259,4,0,255,214,27,1},
/*45-2*/  	{288,254,26,1,227,224,0,0,235,143,4,1,254,250,0,0,305,193,8,1,265,138,0,0,265,118,0,0,318,115,4,0,285,189,8,0,242,110,4,0,300,259,2,0,265,118,24,1,304,213,8,1,296,293,4,1,325,194,4,0},
/*126-1*/  	{325,142,0,1,201,276,1,0,384,255,4,1,227,147,6,0,332,253,28,0,226,258,20,0,346,183,12,0,356,248,0,0,353,217,8,0,273,247,30,0,296,251,0,0,328,142,0,0,343,294,8,1,247,241,12,0,314,324,12,1},
/*126-2*/  	{299,117,0,0,283,270,24,0,315,197,28,0,296,164,27,0,303,303,0,1,305,245,4,0,302,329,24,1,320,270,8,0,338,311,0,1,319,238,4,0,332,186,1,0,320,270,0,0,303,290,0,0,272,225,4,1,315,197,28,0},
/*46-1*/  	{358,197,24,0,189,163,0,0,330,121,28,0,268,130,27,0,297,197,8,0,278,282,4,0,324,197,0,0,289,173,28,0,352,160,29,1,321,93,3,0,376,308,6,0,306,225,28,0,244,188,28,0,209,280,4,0,364,281,28,1},
/*46-2*/  	{284,112,28,0,269,198,24,0,228,169,0,0,272,242,8,0,363,232,12,0,323,223,27,0,349,218,20,0,312,206,19,0,353,215,4,0,308,208,3,0,289,157,28,0,312,282,8,0,332,282,8,0,343,144,12,0,317,185,12,1},
/*127-1*/  	{350,135,0,0,298,307,4,0,281,244,7,1,387,237,2,0,310,215,0,0,310,195,0,0,317,244,27,0,310,155,0,0,308,256,3,0,290,150,24,0,372,275,6,0,350,215,16,0,310,95,0,0,248,176,20,1,285,236,20,1},
/*127-2*/  	{337,112,29,0,205,277,1,0,275,222,1,0,329,297,25,0,318,243,29,0,289,199,1,0,296,276,29,0,250,207,7,1,311,263,29,0,270,208,9,0,236,231,3,1,234,230,9,0,328,298,13,0,311,215,19,1,344,168,17,0},
/*47-1*/  	{299,131,24,0,352,157,16,0,273,157,0,0,280,196,4,0,267,266,4,0,320,179,0,0,295,294,4,0,332,220,4,0,284,277,4,0,315,251,20,0,308,224,20,0,320,179,0,0,360,239,0,1,368,173,12,0,283,88,28,0},
/*47-2*/  	{222,192,0,0,331,301,16,0,349,205,4,0,363,134,28,0,272,196,20,0,295,173,1,0,294,174,1,0,266,225,28,0,293,202,20,0,343,209,1,0,223,273,28,0,333,165,31,1,266,240,23,1,335,275,4,0,349,205,4,0},
/*128-1*/  	{345,304,24,0,282,103,0,0,344,212,28,0,351,184,28,0,333,144,0,0,273,144,0,0,314,104,0,0,376,144,12,1,347,291,14,0,325,280,2,0,302,244,20,0,371,287,8,1,273,144,24,1,395,125,20,0,321,328,12,1},
/*128-2*/  	{252,193,0,1,347,223,8,0,347,223,4,0,223,165,4,0,325,205,3,0,276,195,28,0,336,222,11,0,274,219,4,0,285,271,8,0,321,207,3,0,296,320,7,0,300,196,8,1,300,196,0,1,333,292,4,0,196,301,12,1},
/*48-1*/  	{340,228,28,0,261,170,6,0,339,229,4,0,243,143,28,0,300,229,0,0,215,200,4,0,300,201,24,0,280,200,8,0,245,234,0,0,285,174,8,0,297,159,28,0,259,248,16,1,243,143,28,0,235,248,4,1,340,230,4,0},
/*48-2*/  	{307,273,24,0,237,113,24,0,304,245,28,0,309,289,7,0,262,131,0,1,262,91,0,0,267,313,12,0,288,216,12,0,250,324,12,0,297,168,0,0,258,181,20,0,262,171,8,0,322,91,24,0,301,231,12,0,322,188,4,0},
/*129-1*/  	{212,144,0,0,311,208,8,0,212,223,4,0,380,257,4,0,267,170,28,0,253,267,28,0,260,263,20,0,226,294,28,0,291,280,12,0,236,277,28,0,343,253,3,0,281,157,28,0,287,206,28,0,340,300,12,1,327,192,12,1},
/*129-2*/  	{203,172,0,1,196,132,4,0,196,132,0,0,275,160,28,0,253,252,0,0,294,229,0,0,253,290,0,0,223,212,0,0,258,271,0,0,258,304,24,0,293,249,4,0,293,192,0,1,242,192,0,1,313,124,30,0,258,101,18,1},
/*49-1*/  	{362,209,0,0,262,120,0,0,331,162,28,0,256,144,4,0,302,270,0,0,322,310,0,0,302,310,0,0,362,269,8,0,307,290,0,0,317,330,0,0,262,250,0,0,302,270,8,0,362,230,8,1,342,119,28,1,277,178,20,0},
/*49-2*/  	{244,193,29,0,253,116,0,0,282,186,7,1,298,313,12,0,343,202,0,0,303,197,0,0,303,222,16,0,323,202,0,0,329,87,29,1,338,203,0,0,292,313,6,0,303,201,24,1,302,161,24,1,338,297,12,0,302,147,12,1},
/*50-1*/  	{277,132,29,0,269,301,9,0,235,196,1,0,414,189,26,0,375,247,2,0,328,138,18,0,389,283,2,0,407,150,4,0,387,264,2,0,405,229,2,0,341,218,10,0,332,200,2,0,346,129,30,1,367,227,30,1,444,260,18,0},
/*50-2*/  	{220,120,0,0,238,118,2,0,220,200,4,0,292,185,15,0,390,262,3,0,287,211,3,0,304,201,3,0,319,192,19,0,390,303,11,0,339,184,11,0,254,177,31,0,287,211,3,0,337,250,3,0,351,98,22,0,262,214,20,0},
/*51-1*/  	{230,139,0,0,263,186,24,0,351,182,4,0,365,111,28,0,315,151,0,0,295,179,0,0,335,230,0,1,295,151,8,0,315,225,8,0,299,123,8,0,379,210,20,0,275,210,24,1,295,139,0,0,351,238,28,1,351,182,4,0},
/*51-2*/  	{240,149,3,0,278,138,0,0,320,248,4,1,291,214,12,0,381,240,14,0,261,247,21,0,383,284,22,0,283,285,29,0,309,183,30,1,341,182,31,0,335,275,4,0,272,302,13,0,388,222,30,0,321,289,4,0,342,208,4,0},
/*130-1*/  	{312,125,28,0,295,269,8,0,255,182,0,0,266,212,4,0,370,191,4,1,394,194,28,0,376,289,20,0,348,262,12,0,387,306,20,0,377,205,28,0,367,167,12,0,377,204,28,1,391,275,4,1,275,289,4,0,307,107,28,0},
/*130-2*/  	{220,143,0,0,260,239,24,0,220,223,4,0,366,270,6,0,290,283,4,0,290,283,4,0,364,227,0,0,331,260,0,0,316,224,12,0,311,255,24,0,310,148,28,0,331,220,24,1,351,260,0,1,268,176,4,0,325,189,12,1},

/*0-0*/		{257,135,0,0,257,135,0,0,336,135,0,0,297,174,0,0,296,175,0,0,336,174,0,0,296,214,0,0,336,214,0,0,301,195,0,0,331,194,0,0,336,175,0,0,336,174,0,0,336,194,0,0,257,214,0,0,276,254,0,0}

	};





									
	// STANDALONE APPLICATION SUPPORT:
	//		m_fStandAlone will be set to true if applet is run standalone
	//--------------------------------------------------------------------------
	boolean m_fStandAlone = false;

	
	
	// STANDALONE APPLICATION SUPPORT
	// 	The main() method acts as the applet's entry point when it is run
	// as a standalone application. It is ignored if the applet is run from
	// within an HTML page.
	//--------------------------------------------------------------------------
	public static void main(String args[])
	{
		// Create Toplevel Window to contain applet simplerdo
		//----------------------------------------------------------------------
		TongramFrame frame = new TongramFrame("Tongram");

		// Must show Frame before we size it so insets() will return valid values
		//----------------------------------------------------------------------
		frame.show();
        frame.hide();
		frame.resize(frame.insets().left + frame.insets().right  + 608,
					 frame.insets().top  + frame.insets().bottom + 480);

		// The following code starts the applet running within the frame window.
		// It also calls GetParameters() to retrieve parameter values from the
		// command line, and sets m_fStandAlone to true to prevent init() from
		// trying to get them from the HTML page.
		//----------------------------------------------------------------------
		Tongram applet_tongram = new Tongram();

		frame.add("Center", applet_tongram);
		applet_tongram.m_fStandAlone = true;
		applet_tongram.init();
		applet_tongram.start();
        frame.show();
	}


    public void init() {
	setLayout(new BorderLayout());

	panel = new GamePanel(this);
	add("Center", panel);
	Panel p = new Panel();
	add("South", p);


	panel.nchips = 15;
	for (int i = 0; i < panel.nchips; i++)
		panel.chips[i] = new Chip();

/*		panel.rotate_au = getAudioClip(getCodeBase(), "audio/computer.au");
		panel.flip_au = getAudioClip(getCodeBase(), "audio/gong.au");

		panel.drip_au = getAudioClip(getCodeBase(),"audio/drip.au");
		panel.yizhitu = getImage(getCodeBase(),"yizhitu.jpg");
*/

//	String chips = getParameter("chips");
/*	String chips = "401-268-28-0,249-198-0-0,401-268-4-0,339-137-28-0,283-244-28-0,311-272-28-0,255-272-28-0,283-300-28-0,268-272-0-0,298-272-0-0,284-300-12-0,339-187-28-0,325-201-28-0,339-136-28-1,331-253-20-1";
	for (StringTokenizer t = new StringTokenizer(chips, ",") ; t.hasMoreTokens() ; ) {
	    String str = t.nextToken();
	    int i = str.indexOf('-');
            int fx = Integer.parseInt(str.substring(0, i));
	    str = str.substring(i+1);
	    i = str.indexOf('-');
            int fy = Integer.parseInt(str.substring(0, i));
	    str = str.substring(i+1);
	    i = str.indexOf('-');
            int fRot = Integer.parseInt(str.substring(0, i));
	    str = str.substring(i+1);
          int fFlp = Integer.parseInt(str);
	    
	    panel.addChip("game", fx, fy, fRot, fFlp);

	}

	for (int i=0; i < panel.nchips; i++)
		panel.verify(panel.chips[i]);
*/

//	String demo = getParameter("run");

	play(game_index);
	String demo = "play";

	if ( demo.equals("demo"))
		panel.run_demo = true;
      else {
		panel.game_time = new Label("Elapsed Time 0:0:0");
		p.add(panel.game_time);
		Date dt = new Date();
		panel.start_time = dt.getTime();
		panel.game_time.setText("Elapsed Time 0:0:0");
//		p.add(new Button("Counter Clockwise"));
//		p.add(new Button("Clockwise"));
//		p.add(new Button("Flip"));
		p.add(new Button("New Game"));
//		p.add(new Button("Solve"));
		p.add(new Checkbox("Border", null, true));
//		p.add(new Checkbox("Location", null, false));
//		p.add(new Checkbox("Pattern", null, true));
//		p.add(new Label("Game Name: "));
		panel.game_name = new TextField(10);
		p.add(panel.game_name);
//		p.add(new Button("Save"));
	}
    }

    public void play(int game_index){
		panel.run_demo = false;
		Date dt = new Date();
		panel.start_time = dt.getTime();
		for (int i = 0; i < panel.nchips; i ++  ) {
			int ox = original_position[i*4];
			int oy = original_position[i*4 + 1];
			int oRot = original_position[i*4 + 2];
			int oFlp = original_position[i*4 + 3];
			int fx = puzzles[game_index][i*4];
			int fy = puzzles[game_index][i*4 + 1];
			int	fRot = puzzles[game_index][i*4 + 2];
			int	fFlp = puzzles[game_index][i*4 + 3];
			    
			panel.addChip(i, ox, oy, oRot, oFlp, fx, fy, fRot, fFlp);

		}

		for (int i=0; i < panel.nchips; i++)
			panel.verify(panel.chips[i]);
	}




    public void start() {
/*		// Setup Variants for optional parameters
		Variant v1 = new Variant();
		Variant v2 = new Variant();
		Variant v3 = new Variant();
		Variant v4 = new Variant();
		Variant v5 = new Variant();
		Variant v6 = new Variant();
		Variant v7 = new Variant();

		// Set optional parameter values
		v1.putInt(PromptConstants.rdDriverNoPrompt);
		v2.putBoolean(false);
		v3.putInt(0);

		// Open a connection to the data source
		rdoConnection Connection = new rdoConnection();
		m_IConnection = (_rdoConnection) Connection;

		m_IConnection.putConnect("DSN=Tongram Database;UID=;PWD=;Database=Tongram Database;");

		m_IConnection.EstablishConnection(v1, v2, v3);
*/
		panel.start();
    }
    public void stop() {
	panel.stop();
    }
    public boolean action(Event evt, Object arg) {
	if (arg instanceof Boolean) {
	    if (((Checkbox)evt.target).getLabel().equals("Border")) {
		panel.border = ((Boolean)arg).booleanValue();
	    }
	    if (((Checkbox)evt.target).getLabel().equals("Location")) {
		panel.coordinates = ((Boolean)arg).booleanValue();
	    }
	    if (((Checkbox)evt.target).getLabel().equals("Pattern")) {
		panel.pattern = ((Boolean)arg).booleanValue();
	    }
	    return true;
	} 
/*	if ("Counter Clockwise".equals(arg)) {
	    if (panel.current != null && panel.current.fixed && !panel.run_demo && !panel.success){
//	    	rotate_au.play();
//		panel.drip_au.play();
//		panel.current.rotate(panel.current.nFlips);
		panel.current.rotate(0);
		panel.verify(panel.current);
	    }
	    repaint();
	    return true;
	}
	if ("Clockwise".equals(arg)) {
	    if (panel.current != null && panel.current.fixed && !panel.run_demo && !panel.success){
//	    	rotate_au.play();
//		panel.drip_au.play();
//		panel.current.rotate(1-panel.current.nFlips);
		panel.current.rotate(1);
		panel.verify(panel.current);
	    }
	    repaint();
	    return true;
	}
	if ("Flip".equals(arg)) {
	    if (panel.current != null && panel.current.fixed && !panel.run_demo && !panel.success){
//	    	panel.flip_au.play();
//            panel.drip_au.play();
		panel.current.flip();
		panel.verify(panel.current);
	    }
	    repaint();
	    return true;
	}
*/
	if ("New Game".equals(arg)) {
		int fx;
		try {
			fx = Integer.parseInt(panel.game_name.getText());
		} catch (NumberFormatException e) {
			fx = -1;
		}
		if (fx > 0 ) 
			game_index = (fx - 1) % total_games;
		else
			game_index = (game_index + 1) % total_games;
		play(game_index);
		return true;
	}
	if ("Solve".equals(arg)){
			panel.solve();			
	}
	if ("Save".equals(arg)) {
	    if (!panel.run_demo ){
			String Solution = "/*";
			Solution = Solution + panel.game_name.getText() + "*/  \t{";
			for (int i=0; i < panel.nchips; i++){
				Solution = Solution + String.valueOf((int)panel.chips[i].x)+","+String.valueOf((int)panel.chips[i].y)+","
					+String.valueOf(panel.chips[i].nRotates)+","+String.valueOf(panel.chips[i].nFlips);
				if ( i < (panel.nchips - 1))
					Solution = Solution + ",";
				else
					Solution = Solution + "},";
			}
					// Setup Variants for optional parameters
/*			Variant v4 = new Variant();
			Variant v5 = new Variant();
			Variant v6 = new Variant();

			// Set optional parameters
			v4.putInt(ResultsetTypeConstants.rdOpenKeyset);
			v5.putInt(LockTypeConstants.rdConcurReadOnly);
			v6.putInt(0);
		
			m_IResultSet = (_rdoResultset) m_IConnection.OpenResultset(Solution,v4,v5,v6);
			m_IResultSet.Close();
			m_IResultSet = null;
*/
			System.out.println(Solution);


	    }
//	    repaint();
	    return true;
	}

	return false;
    }
}

//==============================================================================
// STANDALONE APPLICATION SUPPORT
// 	This frame class acts as a top-level window in which the applet appears
// when it's run as a standalone application.
//==============================================================================
class TongramFrame extends Frame
{
	// simplerdoFrame constructor
	//--------------------------------------------------------------------------
	public TongramFrame(String str)
	{
		// TODO: Add additional construction code here
		super (str);
	}

	// The handleEvent() method receives all events generated within the frame
	// window. You can use this method to respond to window events. To respond
	// to events generated by menus, buttons, etc. or other controls in the
	// frame window but not managed by the applet, override the window's
	// action() method.
	//--------------------------------------------------------------------------
	public boolean handleEvent(Event evt)
	{
		switch (evt.id)
		{
			// Application shutdown (e.g. user chooses Close from the system menu).
			//------------------------------------------------------------------
			case Event.WINDOW_DESTROY:
				// TODO: Place additional clean up code here
				dispose();
				System.exit(0);
				return true;

			default:
				return super.handleEvent(evt);
		}			 
	}
}



