package ch6;

public class Tv {
    String color;
    boolean power;
    int channel;

    void power() {
        power = !power;
    }

    void channerDown() {
        --channel;
    }

    void channerlUp() {
        ++channel;
    }

    public Tv() {

    }
}
