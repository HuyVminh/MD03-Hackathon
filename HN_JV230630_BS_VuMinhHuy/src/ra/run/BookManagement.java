package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {
    static Book[] books = new Book[100];

    static int countBooks = 3;

    static {
        books[0] = new Book("Dạy nấu ăn", "Minh Huy", "Dạy nấu các món ăn ngon trong gia đình !", 100000, 250000);
        books[1] = new Book("Dạy kiếm tiền", "Đào Hữu Thịnh", "Dạy kinh doanh và kiểm soát tài chính !", 80000, 250000);
        books[2] = new Book("Trâm ngôn", "Dương Việt Anh", "Những câu trâm ngôn - ngụ ngôn hay !", 150000, 250000);
    }

    ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 7) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************\n" +
                    "1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách \n" +
                    "2. Hiển thị thông tin tất cả sách trong thư viện\n" +
                    "3. Sắp xếp sách theo lợi nhuận tăng dần \n" +
                    "4. Xóa sách theo mã sách \n" +
                    "5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả \n" +
                    "6. Thay đổi thông tin sách theo mã sách \n" +
                    "7. Thoát \n" +
                    "Nhập lựa chọn của bạn :");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNewBook(scanner);
                    break;
                case 2:
                    displayAllBooks(scanner);
                    break;
                case 3:
                    sortListBook(scanner);
                    break;
                case 4:
                    deleteBook(scanner);
                    break;
                case 5:
                    searchBook(scanner);
                    break;
                case 6:
                    updateBook(scanner);
                    break;
                case 7:
                    System.out.println("Đang thoát chương trình...");
                    break;
                default:
                    System.out.println("Không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }

    }

    private static void updateBook(Scanner scanner) {
        System.out.println("Nhập ID sách cần cập nhật :");
        int idUpdate = scanner.nextInt();
        boolean check = false;
        for (int i = 0; i < countBooks; i++) {
            if (books[i].getBookId() == idUpdate) {
                books[i].inputData();
                System.out.println("Thông tin sách đã được cập nhật thành công");
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy sách cần cập nhật");
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.println("Nhập từ khóa tìm kiếm: ");
        scanner.nextLine();
        String keyword = scanner.nextLine().trim().toLowerCase();

        boolean searchCheck = false;
        for (int i = 0; i < countBooks; i++) {
            if (books[i].getBookName().toLowerCase().contains(keyword) || books[i].getDescriptions().toLowerCase().contains(keyword)) {
                if (!searchCheck) {
                    System.out.println("Kết quả tìm kiếm:");
                    searchCheck = true;
                }
                System.out.println(books[i]);
            }
        }
        if (!searchCheck) {
            System.out.println("Không tìm thấy sách nào phù hợp với từ khóa tìm kiếm.");
        }
    }

    private static void deleteBook(Scanner scanner) {
        System.out.println("Nhập ID sách cần xóa :");
        int idDel = scanner.nextInt();
        boolean check = false;
        for (int i = 0; i < countBooks; i++) {
            if (books[i].getBookId() == idDel) {
                for (int j = i; j < countBooks - 1; j++) {
                    books[j] = books[j + 1];
                }
                countBooks--;
                System.out.println("Xóa sách thành công");
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy sách cần xóa");
        }
    }

    private static void sortListBook(Scanner scanner) {
        if (countBooks == 0) {
            System.out.println("Thư viện chưa có sách nào.");
        } else {
            for (int i = 0; i < countBooks - 1; i++) {
                for (int j = i + 1; j < countBooks; j++) {
                    if (books[i].getInterest() > books[j].getInterest()) {
                        Book temp = books[i];
                        books[i] = books[j];
                        books[j] = temp;
                    }
                }
            }
            System.out.println("========= Danh sách theo thứ tự lợi nhuận tăng dần ==============");
            for (int i = 0; i < countBooks; i++) {
                System.out.println(books[i].displayData());
            }
        }
    }

    private static void displayAllBooks(Scanner scanner) {
        if (countBooks == 0) {
            System.out.println("Không có sách");
            return;
        }
        System.out.println("=========Danh sách==============");
        for (int i = 0; i < countBooks; i++) {
            System.out.println(books[i].displayData());
        }
    }

    private static void addNewBook(Scanner scanner) {
        System.out.print("Nhập số lượng sách muốn thêm mới : ");
        int numToAdd = scanner.nextInt();
        if (countBooks + numToAdd > 100) {
            System.out.println("Không thể thêm hơn " + 100 + " cuốn sách.");
            return;
        }
        for (int i = 0; i < numToAdd; i++) {
            System.out.println("Nhập thông tin của cuốn sách #" + (i + 1) + ":");
            Book book = new Book();
            book.inputData();
            books[countBooks++] = book;
            System.out.println("Đã thêm sách thành công !");
        }
    }
}
