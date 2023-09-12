
class Circle {
    protected int radius;

    public Circle(int r) {
        radius = r;
    }
}

class Pizzao extends Circle {
    String name;

    public Pizzao(String name, int radius) {
        super(radius);
        this.name = name;
    }

    void order() {
        System.out.println(name + " 피자 " + radius + " 크기");
    }
}

public class Pizza {
    public static void main(String[] args) {
        Pizzao obj = new Pizzao("Pepperoni", 20);
        obj.order();
    }
}
