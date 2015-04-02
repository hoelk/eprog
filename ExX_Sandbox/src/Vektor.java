import java.lang.Math.*;

class Vektor {
    private double x;
    private double y;
    private double z;

    // Konstructor
    Vektor(double a, double b, double c) {
        x = a;
        y = b;
        z = c;
    }

    Vektor(double a) {
        x = a;
        y = a;
        z = a;
    }

    Vektor() {
        x = 0;
        y = 0;
        z = 0;
    }

    // Getter Methoden
    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getZ() {
        return z;
    }


    // Methoden
    double length() {
        double l = Math.sqrt(x * x + y * y + z * z);
        return l;
    }

    Vektor add(Vektor a) {
        double nx = x + a.getX();
        double ny = y + a.getY();
        double nz = z + a.getZ();
        Vektor n = new Vektor(nx, ny, nz);
        return n;
    }

    Vektor mult(double a) {
        double nx = x * a;
        double ny = y * a;
        double nz = z * a;
        Vektor n = new Vektor(nx, ny, nz);
        return n;
    }

    Vektor unitVektor() {
        double l = this.length();
        Vektor n = this.mult(1 / l);
        return n;
    }

    double dotP(Vektor a) {
        double nx = x * a.getX();
        double ny = y * a.getY();
        double nz = z * a.getZ();
        double n = nx + ny + nz;
        return n;
    }

    void output() {
        System.out.printf("( %5.2f | %5.2f | % 5.2f )%n", x, y, z);
    }


}