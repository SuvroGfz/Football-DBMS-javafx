package sample;

public class PlayerTable2Class {
    private String position;
    private double height;
    private double salary;
    private int number;

    public PlayerTable2Class(String position, double height, double salary, int number) {
        this.position = position;
        this.height = height;
        this.salary = salary;
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
