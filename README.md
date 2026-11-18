# Mattress Sales Assignment (Java)

This ZIP contains two independent Java Maven projects:

1. `Question1-SalesReport` — console-based three-month sales report, arrays, interface, `Product`, `PrintProduct`, and JUnit tests.
2. `Question2-ProductApp` — Swing GUI for capturing a mattress sale, calculating VAT/commission/total, appending records to `data.txt`, menus, and JUnit tests.

## Requirements
- JDK 17 or later
- Apache Maven 3.8+

## Run Question 1
Open a terminal in `Question1-SalesReport`:
```bash
mvn test
mvn package
mvn exec:java
```
Enter the monthly sales figures when prompted. The program uses a 2D array for monthly sales and a 1D array for product totals.

**Interpretation note:** The written instruction says the most popular product is based on the highest total sales across three months. The sample screenshot instead labels the highest single-month figure (25) as the popular product. This solution follows the written instruction and compares each product's three-month total. The sample figures therefore produce Cozy Nights Monarch (63), not (25).

## Run Question 2
Open a terminal in `Question2-ProductApp`:
```bash
mvn test
mvn package
mvn exec:java
```
The Swing application allows the user to enter a customer name, select one of the three products, enter a price, and process the sale. Each processed sale is appended to `data.txt` in the application's working directory.

## IDE use
Both folders are standard Maven projects and can be opened directly in NetBeans, IntelliJ IDEA, or Eclipse as existing Maven projects. Source code is under `src/main/java`; unit tests are under `src/test/java`.
