package com.tensei;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
        }
        if (!args[0].equals("y")) {
            printUsage();
        }

        int year = Integer.parseInt(args[1]);

        MyGregorianCalendar calendar = new MyGregorianCalendar(year);
        System.out.println(calendar);
    }

    private static void printUsage() {
        System.err.println("Usage: java -y <year>");
        System.exit(1);
    }
}
