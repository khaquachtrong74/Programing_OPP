package com.excersice3.Question4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiKhoan {
    private static List<TaiKhoan> dsTaiKhoan = new ArrayList<>();

    public static void menu() {
        System.out.println("\n1. Xem thông tin tài khoản");
        System.out.println("2. Tra cứu tài khoản");
        System.out.println("3. Nộp tiền");
        System.out.println("4. Rút tiền");
        System.out.println("5. Tính lãi");
        System.out.println("6. Thoát");
        System.out.print("Chọn: ");
    }

    private static void xemThongTin() {
        for (TaiKhoan tk : dsTaiKhoan) {
            System.out.println(tk);
        }
    }

    private static TaiKhoan traCuu() {
        System.out.println("Nhap so tai khoan: ");
        String soTaiKhoan = CauHinh.SC.nextLine();
        for (TaiKhoan tk : dsTaiKhoan) {
            if (tk.getSoTaiKhoan().equals(soTaiKhoan)) {
                System.out.println(tk);
                return tk;
            }
        }
        System.out.println("Khong tim thay tai khoan nay");
        return null;
    }

    private static void nopTien() {
        TaiKhoan tmp = traCuu();
        if (tmp == null)
            return;
        for (TaiKhoan tk : dsTaiKhoan) {
            if (tk.equals(tmp)) {
                if (tk instanceof TaiKhoanKyHan tkkh) {
                    if (LocalDate.now().isEqual(tkkh.getNgayDaoHan())) {
                        tk.napTien();
                    } else {
                        System.out.println("Chi duoc nap tien vao ngay dao han.");
                    }
                } else {
                    tk.napTien();
                    System.out.println("Nap tien thanh cong");
                }
                return;
            }
        }
        System.out.println("Tai khoan khong ton tai trong danh sach.");
    }

    private static void rutTien() {
        TaiKhoan tmp = traCuu();
        if (tmp == null)
            return;
        for (TaiKhoan tk : dsTaiKhoan) {
            if (tk.equals(tmp)) {
                if (tk instanceof TaiKhoanKyHan taiKhoanKyHan) {
                    if (LocalDate.now().equals(taiKhoanKyHan.getNgayDaoHan())) {
                        tk.rutTien();
                        dsTaiKhoan.remove(tk);
                        dsTaiKhoan.add(new TaiKhoanKyHan(tk.getTenTaiKhoan(),
                                tk.getSoDienThoai(), tk.getEmail(),
                                tk.getSoTienTaiKhoan(), tk.getTrangThaiTaiKhoan(), taiKhoanKyHan.getKyHan()));
                    } else {
                        System.out.println("Chi duoc rut tien vao ngay dao han");
                    }
                } else {
                    tk.rutTien();
                }
            }
            return;
        }

    }

    public static void program() {
        menu();
        int choice = Integer.parseInt(CauHinh.SC.nextLine());
        CauHinh.SC.nextLine(); // clear buffer
        while (true) {
            switch (choice) {
                case 1 -> xemThongTin();
                case 2 -> traCuu();
                case 3 -> nopTien();
                case 4 -> rutTien();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }

    }

    public static void main(String[] args) {
        dsTaiKhoan.add(new TaiKhoanKhongKyHan("Nguyen Van A", "123456789", "a@gmail.com", 5000000, "Active"));
        dsTaiKhoan.add(
                new TaiKhoanKyHan("Nguyen Van B", "987654321", "b@gmail.com", 10000000, "Active", KyHan.MOT_THANG));
        program();
    }
}