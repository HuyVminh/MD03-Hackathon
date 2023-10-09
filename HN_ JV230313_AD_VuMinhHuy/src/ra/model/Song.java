package ra.model;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Song {
    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private Date createdDate;
    private boolean songStatus;

    public Song() {
        // Không tham số
        this.createdDate = new Date();
    }

    public Song(String songId, String songName, String descriptions, Singer singer, String songWriter, boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.descriptions = descriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.createdDate = new Date();
        this.songStatus = songStatus;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    public void inputData(Singer[] singerList, Song[] songsList, int currentSinger, int currentSongs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã bài hát");
        // mã bài hát (Có 4 ký tự và bắt đầu bằng kí tự S, không trùng lặp)
        String songId = scanner.nextLine().trim();
        while (!songId.matches("^S\\w{3}$")) {
            System.err.println("Mã bài hát không đúng định dạng, vui lòng nhập lại:");
            songId = scanner.nextLine().trim();
        }
        boolean checkId = false;
        for (int i = 0; i < currentSongs; i++) {
            if (songsList[i].getSongId().equals(songId)) {
                checkId = true;
            }
        }
        while (checkId) {
            System.err.println("Mã bài hát đã tồn tại, vui lòng nhập lại:");
            songId = scanner.nextLine().trim();
            checkId = false;
            for (int i = 0; i < currentSongs; i++) {
                if (songsList[i].getSongId().equals(songId)) {
                    checkId = true;
                }
            }
        }
        this.songId = songId;

        System.out.print("Nhập tên bài hát: ");
        String songName = scanner.nextLine().trim();
        while (songName.isEmpty()) {
            System.err.print("Tên bài hát không được để trống, vui lòng nhập lại :");
            songName = scanner.nextLine().trim();
        }
        this.songName = songName;

        System.out.print("Nhập mô tả bài hát: ");
        this.descriptions = scanner.nextLine();

        // Hiển thị danh sách ca sĩ
        System.out.println("Danh sách ca sĩ:");

        for (int i = 0; i < currentSinger; i++) {
            System.out.println(singerList[i].getSingerId() + ". " + singerList[i].getSingerName());
        }

        // Nhập id của ca sĩ
        System.out.print("Nhập mã ca sĩ: ");
        int singerId = scanner.nextInt();
        scanner.nextLine();

        // Tìm ca sĩ theo id
        Singer selectedSinger = null;
        for (int i = 0; i < currentSinger; i++) {
            if (singerList[i].getSingerId() == singerId) {
                selectedSinger = singerList[i];
                break;
            }
        }

        // Nếu không tìm thấy ca sĩ, yêu cầu người dùng nhập thông tin ca sĩ mới
        if (selectedSinger == null) {
            System.out.println("Không tìm thấy ca sĩ. Vui lòng nhập thông tin ca sĩ mới:");
            Singer newSinger = new Singer();
            newSinger.inputData();
            currentSinger++;
            selectedSinger = newSinger;
        }

        this.singer = selectedSinger;

        System.out.print("Nhập tên người sáng tác: ");
        this.songWriter = scanner.nextLine();

        this.songStatus = true;
    }


    public String displayData() {
        return "{" +
                "Mã bài hát ='" + songId + '\'' +
                ", Tên bài hát='" + songName + '\'' +
                ", Mô tả='" + descriptions + '\'' +
                ", Ca sĩ=" + singer.getSingerName() +
                ", Tác giả='" + songWriter + '\'' +
                ", Ngày tạo=" + createdDate +
                ", Trang thái=" + (this.songStatus ? "Đang hoạt động" : "Ngừng hoạt động") +
                '}';
    }
}

