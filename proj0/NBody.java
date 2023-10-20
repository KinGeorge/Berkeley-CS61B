import java.util.ArrayList;

public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename); // From examples/BasicInDemo.java
        in.readInt(); // Ignore the first number N
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filename){
        // TODO Not robust enough if we have planet numbers other than 5
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] Planets = new Planet[num];;
        for(int i = 0; i < num; i++){
            Planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return Planets;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] Planets = readPlanets(filename);
        int num = Planets.length;

        // Create an xForces array and yForces array.
        double[] xForces = new double[num];
        double[] yForces = new double[num];

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        for(double time=0.0; time <= T; time += dt){
            // Calculate the net x and y forces for each Body,
            // storing these in the xForces and yForces arrays respectively.
            for(int i = 0; i < num; i++){
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            // After calculating the net forces for every Body, call update on each of the Bodys.
            // This will update each body’s position, velocity, and acceleration.
            for(int i = 0; i < num; i++){
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            // Draw the background image
            StdDraw.picture(0, 0, "images/starfield.jpg");
            // Draw all of the Body s.
            for(int i = 0; i < num; i++){
                StdDraw.picture(Planets[i].xxPos, Planets[i].yyPos, "images/" + Planets[i].imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdDraw.clear();
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
        }
    }
}
