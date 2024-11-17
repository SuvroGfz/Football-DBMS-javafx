package sample;

import java.io.Serializable;
import java.lang.String;

public class Player implements Serializable {
    private String name;

    public String getCountry() {
        return country;
    }

    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int number;
    private double weeklySalary;
    //public TransferDetails transferDetails = new TransferDetails();

    public void setName(String n){
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getClub() {
        return club;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public void PrintPlayerInfo(){
        System.out.println("Name: " + getName());
        System.out.println("Country: " + getCountry() + "\t" + " CLub: " + getClub() + "\t" + " Position: " + getPosition());
        System.out.println("Number: " + getNumber() + "\t" + " Age: " + getAge() + "\t" + " Height: " + getHeight());
        System.out.println("Weekly Salary: " + getWeeklySalary() + "\n");
    }

}
