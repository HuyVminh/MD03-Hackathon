package ra.run;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

import ra.model.Singer;
import ra.model.Song;

public class MusicManagement {
    static Singer[] singerList = new Singer[100];
    static Song[] songList = new Song[100];

    static {
        singerList[0] = new Singer("Ưng Hoàng Phúc", 35, "Việt Nam", true, "Nhạc trẻ");
        singerList[1] = new Singer("Justin Bieber", 25, "American", true, "Pop");
        singerList[2] = new Singer("Quang Lê", 38, "Việt Nam", true, "Nhạc vàng");
        singerList[3] = new Singer("Linkin Park", 36, "UK", true, "Rock");
    }

    static int currentSingersList = 4;
    static int currentSongsList = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("************************MUSIC-MANAGEMENT*************************");
            System.out.println("1. Quản lý ca sĩ");
            System.out.println("2. Quản lý bài hát");
            System.out.println("3. Tìm kiếm bài hát");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ ký tự '\n' sau khi nhập số

            switch (choice) {
                case 1:
                    singerManagement();
                    break;
                case 2:
                    songManagement();
                    break;
                case 3:
                    searchManagement();
                    break;
                case 4:
                    System.out.println("Đang thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 4);
    }

    public static void singerManagement() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("**********************SINGER-MANAGEMENT*************************");
            System.out.println("1. Nhập vào số lượng ca sĩ cần thêm và nhập thông tin cần thêm mới");
            System.out.println("2. Hiển thị danh sách tất cả ca sĩ đã lưu trữ");
            System.out.println("3. Thay đổi thông tin ca sĩ theo mã id");
            System.out.println("4. Xóa ca sĩ theo mã id");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ ký tự '\n' sau khi nhập số

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng ca sĩ muốn thêm mới : ");
                    int numToAdd = scanner.nextInt();
                    if (currentSingersList + numToAdd > 100) {
                        System.out.println("Không thể thêm hơn " + 100 + " ca sĩ.");
                        return;
                    }
                    for (int i = 0; i < numToAdd; i++) {
                        System.out.println("Nhập thông tin của ca sĩ #" + (i + 1) + ":");
                        Singer singer = new Singer();
                        singer.inputData();
                        singerList[currentSingersList++] = singer;
                        System.out.println("Đã thêm ca sĩ thành công !");
                    }
                    break;
                case 2:
                    if (currentSingersList == 0) {
                        System.out.println("Không có ca sĩ nào");
                        return;
                    }
                    System.out.println("========= Danh sách ca sĩ ==============");
                    for (int i = 0; i < currentSingersList; i++) {
                        System.out.println(singerList[i].displayData());
                    }
                    break;
                case 3:
                    System.out.println("Nhập ID ca sĩ cần cập nhật :");
                    int idUpdate = scanner.nextInt();
                    boolean checkUpdate = false;
                    for (int i = 0; i < currentSingersList; i++) {
                        if (singerList[i].getSingerId() == idUpdate) {
                            singerList[i].inputData();
                            System.out.println("Thông tin ca sĩ đã được cập nhật thành công");
                            checkUpdate = true;
                            break;
                        }
                    }
                    if (!checkUpdate) {
                        System.out.println("Không tìm thấy ca sĩ cần cập nhật");
                    }
                    break;
                case 4:
                    System.out.println("Nhập ID ca sĩ cần xóa:");
                    int idDel = scanner.nextInt();
                    boolean checkDel = false;
                    if (currentSingersList == 0) {
                        System.err.println("Danh sách ca sĩ rỗng!");
                    } else {
                        for (int i = 0; i < currentSingersList; i++) {
                            if (singerList[i].getSingerId() == idDel) {
                                boolean checkSong = false;
                                for (int j = 0; j < currentSongsList; j++) {
                                    if (songList[j].getSinger().getSingerId() == idDel) {
                                        System.err.println("Ca sĩ có tồn tại bài hát!");
                                        checkSong = true;
                                        break;
                                    }
                                }
                                if (!checkSong) {
                                    for (int j = i; j < currentSingersList - 1; j++) {
                                        singerList[j] = singerList[j + 1];
                                    }
                                    currentSingersList--;
                                    System.out.println("Xóa ca sĩ thành công!");
                                    checkDel = true;
                                    break;
                                }
                            }
                        }
                        if (!checkDel) {
                            System.err.println("Không tìm thấy ca sĩ cần xóa!");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 5);
    }

    public static void songManagement() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("**********************SONG-MANAGEMENT*************************");
            System.out.println("1. Nhập vào số lượng bài hát cần thêm và nhập thông tin cần thêm mới");
            System.out.println("2. Hiển thị danh sách tất cả bài hát đã lưu trữ");
            System.out.println("3. Thay đổi thông tin bài hát theo mã id");
            System.out.println("4. Xóa bài hát theo mã id");
            System.out.println("5. Quay lại menu chính");
            System.out.print("Chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng bài hát cần thêm: ");
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    if (currentSongsList + n > 100) {
                        System.out.println("Không thể thêm hơn " + 100 + " bài hát");
                        return;
                    }
                    for (int i = 0; i < n; i++) {
                        System.out.println("Nhập thông tin của bài hát #" + (i + 1) + ":");
                        Song song = new Song();
                        song.inputData(singerList, songList, currentSingersList, currentSongsList);
                        songList[currentSongsList++] = song;
                        System.out.println("Đã thêm bài hát thành công !");
                    }
                    break;
                case 2:
                    if (currentSongsList == 0) {
                        System.out.println("Không có bài hát nào");
                        return;
                    }
                    System.out.println("========= Danh sách bài hát ==============");
                    for (int i = 0; i < currentSongsList; i++) {
                        System.out.println(songList[i].displayData());
                    }
                    break;
                case 3:
                    System.out.println("Nhập ID bài hát cần cập nhật :");
                    String idUpdate = scanner.nextLine();
                    boolean checkUpdate = false;
                    for (int i = 0; i < currentSongsList; i++) {
                        if (Objects.equals(songList[i].getSongId(), idUpdate)) {
                            songList[i].inputData(singerList, songList, currentSingersList, currentSongsList);
                            System.out.println("Thông tin bài hát đã được cập nhật thành công");
                            checkUpdate = true;
                            break;
                        }
                    }
                    if (!checkUpdate) {
                        System.out.println("Không tìm thấy bài hát cần cập nhật");
                    }
                    break;
                case 4:
                    System.out.print("Nhập mã bài hát cần xóa: ");
                    String idDel = scanner.nextLine();
                    boolean checkDel = false;
                    for (int i = 0; i < currentSongsList; i++) {
                        if (Objects.equals(songList[i].getSongId(), idDel)) {
                            for (int j = i; j < currentSingersList - 1; j++) {
                                singerList[j] = singerList[j + 1];
                            }
                            currentSingersList--;
                            System.out.println("Xóa bài hát thành công");
                            checkDel = true;
                            break;
                        }
                    }
                    if (!checkDel) {
                        System.out.println("Không tìm thấy sách cần xóa");
                    }
                    break;
                case 5:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 5);
    }

    private static void searchManagement() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*********************SEARCH-MANAGEMENT************************");
            System.out.println("1. Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại");
            System.out.println("2. Tìm kiếm ca sĩ theo tên hoặc thể loại");
            System.out.println("3. Hiển thị danh sách bài hát theo thứ tự tên tăng dần");
            System.out.println("4. Hiển thị 10 bài hát được đăng mới nhất");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ ký tự '\n' sau khi nhập số

            switch (choice) {
                case 1:
                    System.out.println("Nhập tên ca sĩ hoặc thể loại bài hát:");
                    String keyword = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;
                    for (Song song : songList) {
                        if (song != null && (song.getSinger().getSingerName().toLowerCase().contains(keyword)
                                || song.getSinger().getGenre().toLowerCase().contains(keyword))) {
                            System.out.println(song.displayData());
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Không tìm thấy bài hát nào phù hợp với từ khóa \"" + keyword + "\"");
                    }
                    break;
                case 2:
                    System.out.println("Nhập tên hoặc thể loại ca sĩ:");
                    String keyword1 = scanner.nextLine().trim().toLowerCase();
                    boolean found1 = false;
                    for (Singer singer : singerList) {
                        if (singer != null && (singer.getSingerName().toLowerCase().contains(keyword1)
                                || singer.getGenre().toLowerCase().contains(keyword1))) {
                            System.out.println(singer.displayData());
                            found1 = true;
                        }
                    }
                    if (!found1) {
                        System.out.println("Không tìm thấy ca sĩ nào phù hợp với từ khóa \"" + keyword1 + "\"");
                    }
                    break;
                case 3:
                    Arrays.sort(songList, 0, currentSongsList, Comparator.comparing(Song::getSongName));
                    for (int i = 0; i < currentSongsList; i++) {
                        System.out.println(songList[i].displayData());
                    }
                    break;
                case 4:
                    Arrays.sort(songList, 0, currentSongsList, Comparator.comparing(Song::getCreatedDate).reversed());
                    int count = 0;
                    for (int i = 0; i < currentSongsList && count < 10; i++) {
                        if (songList[i].isSongStatus()) {
                            System.out.println(songList[i].displayData());
                            count++;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 5);
    }
}