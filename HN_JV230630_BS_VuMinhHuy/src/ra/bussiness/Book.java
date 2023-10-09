package ra.bussiness;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Book {
    int bookId;
    String bookName;
    String author;
    String descriptions;
    double importPrice;
    double exportPrice;
    float interest;
    boolean bookStatus;
    static int countId = 1;

    public Book() {
        this.bookId = countId++;
        this.bookStatus = true;
        this.interest = (float) (this.exportPrice - this.importPrice);
    }

    public Book(String bookName, String author, String descriptions, double importPrice, double exportPrice) {
        this.bookId = countId++;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = (float) (this.exportPrice - this.importPrice);
    }

    public float getInterest() {
        return interest;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mã sách : " + bookId);
        System.out.println("Nhập tên sách :");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.err.print("Tên sách không được để trống, vui lòng nhập lại :");
            name = scanner.nextLine().trim();
        }
        this.bookName = name;

        System.out.println("Nhập tên tác giả :");
        String author = scanner.nextLine().trim();
        while (author.isEmpty()) {
            System.err.print("Tên sách không được để trống, vui lòng nhập lại :");
            author = scanner.nextLine().trim();
        }
        this.author = author;

        System.out.print("Nhập mô tả của sách (tối thiểu 10 ký tự): ");
        String descriptions = scanner.nextLine().trim();
        while (descriptions.length() < 10) {
            System.err.print("Mô tả chưa đủ 10 ký tự, hãy nhập lại: ");
            descriptions = scanner.nextLine().trim();
        }
        this.descriptions = descriptions;

        System.out.print("Nhập giá mua vào: ");
        double importPrice = scanner.nextDouble();
        while (importPrice <= 0) {
            System.err.print("Giá mua vào phải lớn hơn 0, hãy nhập lại: ");
            importPrice = scanner.nextDouble();
        }
        this.importPrice = importPrice;

        System.out.print("Nhập giá bán ra (phải lớn hơn 1.2 lần giá nhập): ");
        double exportPrice = scanner.nextDouble();
        while (exportPrice < 1.2 * importPrice) {
            System.err.print("Giá bán ra phải lớn hơn 1.2 lần giá nhập, hãy nhập lại: ");
            exportPrice = scanner.nextDouble();
        }
        this.exportPrice = exportPrice;

        this.interest = (float) (exportPrice - importPrice);
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return "{" +
                "Mã sách =" + bookId +
                " , Tên sách ='" + bookName + '\'' +
                " , Tác giả ='" + author + '\'' +
                " , Mô tả ='" + descriptions + '\'' +
                " , Giá nhập =" + currencyFormat.format(importPrice) +
                " , Giá bán =" + currencyFormat.format(exportPrice) +
                " , Lợi nhuận =" + currencyFormat.format(interest) +
                " , Trạng thái =" + bookStatus +
                '}';
    }

    public String displayData() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return "{" +
                "Mã sách =" + bookId +
                ", Tên sách ='" + bookName + '\'' +
                ", Tác giả ='" + author + '\'' +
                ", Mô tả ='" + descriptions + '\'' +
                ", Giá nhập =" + currencyFormat.format(importPrice) +
                ", Giá bán =" + currencyFormat.format(exportPrice) +
                ", Lợi nhuận =" + currencyFormat.format(interest) +
                ", Trạng thái =" + bookStatus +
                '}';
    }
}
