/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tspgeneticalg;
import java.lang.Math;
class Point {
    public double x;
    public double y;
    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    Point(int x, int y){
        this.x = x/1.0;
        this.y = y/1.0;
    }
    double distanceTo(Point p){
        return java.lang.Math.sqrt(((this.x - p.x) * (this.x - p.x)) + ((this.y - p.y) * (this.y - p.y)));
    }
}
