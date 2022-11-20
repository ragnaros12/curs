package com.cursovay.curs;

public class Hello {
    public static void main(String[] argds) throws ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
        HelloApplication.main(argds);
    }

}
