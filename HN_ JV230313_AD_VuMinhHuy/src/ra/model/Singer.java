package ra.model;

import java.util.Scanner;

public class Singer {
    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    static int count = 1;

    public Singer() {
        this.singerId = count++;
    }

    public Singer(String singerName, int age, String nationality, boolean gender, String genre) {
        this.singerId = count++;
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    public int getSingerId() {
        return singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên ca sĩ: ");
        String singerName = scanner.nextLine().trim();
        while (singerName.isEmpty()) {
            System.err.print("Tên ca sĩ không được để trống, vui lòng nhập lại :");
            singerName = scanner.nextLine().trim();
        }
        this.singerName = singerName;

        System.out.print("Nhập tuổi: ");
        int age = scanner.nextInt();
        while (age < 0) {
            System.err.print("Tuổi ca sĩ phải lớn hơn 0, vui lòng nhập lại :");
            age = scanner.nextInt();
        }
        this.age = age;
        scanner.nextLine(); // Đọc bỏ ký tự '\n' sau khi nhập số

        System.out.print("Nhập quốc tịch: ");
        String nationality = scanner.nextLine().trim();
        while (nationality.isEmpty()) {
            System.err.print("Quốc tịch không được để trống, vui lòng nhập lại :");
            nationality = scanner.nextLine().trim();
        }
        this.nationality = nationality;

        System.out.print("Nhập giới tính (Nam: true, Nữ: false): ");
        this.gender = scanner.nextBoolean();

        scanner.nextLine(); // Đọc bỏ ký tự '\n' sau khi nhập boolean

        System.out.print("Nhập thể loại: ");
        String genre = scanner.nextLine().trim();
        while (genre.isEmpty()) {
            System.err.print("Thể loại không được để trống, vui lòng nhập lại :");
            genre = scanner.nextLine().trim();
        }
        this.genre = genre;
    }

    public String displayData() {
        return "{" +
                "singerId =" + singerId +
                " , singerName ='" + singerName + '\'' +
                " , age =" + age +
                " , nationality ='" + nationality + '\'' +
                " , gender =" + (this.gender ? "Nam" : "Nữ") +
                " , genre ='" + genre + '\'' +
                '}';
    }
}