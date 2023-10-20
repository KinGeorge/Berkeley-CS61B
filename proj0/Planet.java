public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
                double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet b){
        return Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
    }
    public double calcForceExertedBy(Planet b){
        double G = 6.67e-11;
        return G * this.mass * b.mass / Math.pow(calcDistance(b), 2);
    }
    public double calcForceExertedByX(Planet b){
        return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
    }
    public double calcForceExertedByY(Planet b){
        return calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
    }
    public double calcNetForceExertedByX(Planet[] bodies){
        double result = 0.0;
        for(Planet b:bodies){
            if(b.equals(this)){
                // System.out.println("Double Check Your Understanding.");
                continue;
            }
            else {
                result += calcForceExertedByX(b);
            }
        }
        return result;
    }
    public double calcNetForceExertedByY(Planet[] bodies){
        double result = 0.0;
        for(Planet b:bodies){
            if(b.equals(this)){
//                System.out.println("Double Check Your Understanding.");
                continue;
            }
            else {
                result += calcForceExertedByY(b);
            }
        }
        return result;
    }
    public void update(double time, double x_force, double y_force) {
        double acce_x = x_force / this.mass, acce_y = y_force / this.mass;
        this.xxVel = this.xxVel + time * acce_x;
        this.yyVel = this.yyVel + time * acce_y;
        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}
