class VektorBsp {
    public static void main(String[] args) {

        double a = 2.1234;
        Vektor u = new Vektor(2, a, 1);
        Vektor v = new Vektor(2.12);
        Vektor w = new Vektor();

        double lu = u.length();
        double lv = v.length();
        double lw = w.length();

        Vektor x = u.add(v);
        Vektor y = u.add(u);

        Vektor z = u.mult(a);

        Vektor uEinheits = u.unitVektor();
        double lUEinheits = uEinheits.length();

        double dotUV = u.dotP(v);

        u.output();
        v.output();
        w.output();
        x.output();
        y.output();
        z.output();
    }
}