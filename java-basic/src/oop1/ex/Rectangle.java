package oop1.ex;

public class Rectangle {
    public int width;
    public int height;
    public int area;

    public void calculateArea() {
        this.area = this.width * this.height;
        System.out.println("넓이: " + this.area);
    }

    public void calculatePerimeter() {
        System.out.println("둘레 길이: " + 2 * (this.width + this.height));
    }

    public void isSquare() {
        System.out.println("정사각형 여부: " + (this.width == this.height));
    }
}
