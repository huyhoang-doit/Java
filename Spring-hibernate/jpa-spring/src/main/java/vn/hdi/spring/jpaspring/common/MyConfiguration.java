package vn.hdi.spring.jpaspring.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.hdi.spring.jpaspring.dao.SinhVienDAOImpl;
import vn.hdi.spring.jpaspring.entity.SinhVien;

import java.util.List;
import java.util.Scanner;

@Configuration
public class MyConfiguration {
    Scanner scanner = new Scanner(System.in);
    @Bean
    @Autowired
    public CommandLineRunner getRunner(SinhVienDAOImpl dao) {

        return runner -> {
            while(true) {
                printMenu();
                int choose = scanner.nextInt();
                scanner.nextLine();
                if(choose==1) {
                    addSinhVien(dao);
                }else if(choose== 2) {
                    getSVByID(dao);
                }else if(choose==3) {
                    getSVByName(dao);
                }else if(choose==4) {
                    getAllSV(dao);
                }else if(choose==5) {
                    updateSVByID(dao);
                }else if(choose==6) {
                    updateAllNameSV(dao);
                }else if(choose==7) {
                    deleteSVByID(dao);
                }else if(choose==8) {
                    deleteSVByName(dao);
                }

            }
        };
    }

    public void printMenu() {
        System.out.println("======================================");
        System.out.println("MENU:\n"+
                    "1.Them sinh vien\n"+
                    "2. Tim sinh vien\n"+
                    "3. Tim kiem sinh vien bang ten\n"+
                    "4. Hien thi danh sach sinh vien\n" +
                    "5. Cap nhat sinh vien dua tren ID\n" +
                    "6. Cap nhat sinh NAME all sinh vien\n" +
                    "7. Xoa sinh vien dua tren ID\n" +
                    "8. Xoa sinh vien dua tren Name\n" +
                    "Choice: "
                );

    }

    // Methods
    public void addSinhVien(SinhVienDAOImpl dao) {
        // Lay thong tin sinh vien
        System.out.print("Nhap ho dem: ");
        String ho_dem = scanner.nextLine();
        System.out.println("Nhap ten:");
        String ten = scanner.nextLine();
        System.out.println("Nhap email");
        String email = scanner.nextLine();
        SinhVien sv = new SinhVien(ho_dem, ten, email);
        // Luu sinh vien
        dao.save(sv);
    }

    public void getSVByID(SinhVienDAOImpl dao) {
        // Nhap id sinh vien
        System.out.println("Nhap ID sinh vien:");
        int id = scanner.nextInt();
        scanner.nextLine();
        // Lay sinh vien tu CSDL
        SinhVien sv = dao.getById(id);
        if(sv==null) {
            System.out.println("Khong tim thay sinh vien nay");
        }else {
            System.out.println(sv.toString());
        }
    }

    public void getSVByName(SinhVienDAOImpl dao) {
        // Nhap ten sinh vien
        System.out.println("Nhap TEN sinh vien:");
        String name = scanner.nextLine();
        // Lay sinh vien tu CSDL
        List<SinhVien> svs = dao.getByName(name);
        if(svs.size() == 0) {
            System.out.println("Khong tim thay sinh vien nao co TEN nhu mo ta");
        }else {
            for (SinhVien s : svs) {
                System.out.println(s.toString());
            }
        }
    }

    public void getAllSV(SinhVienDAOImpl dao) {
        // Lay tat ca sinh vien
        List<SinhVien> svs = dao.getAll();
        if(svs.size() == 0) {
            System.out.println("Khong tim thay sinh vien nao co TEN nhu mo ta");
        }else {
            for (SinhVien s : svs) {
                System.out.println(s.toString());
            }
        }
    }
    public void updateSVByID(SinhVienDAOImpl dao) {
        // Nhap id sinh vien
        System.out.println("Nhap ID sinh vien:");
        int id = scanner.nextInt();
        scanner.nextLine();
        // Lay sinh vien tu CSDL
        SinhVien sv = dao.getById(id);
        if(sv==null) {
            System.out.println("Khong tim thay sinh vien nay");
        }else {
            // Cap nhat lai thong tin sinh vien
            System.out.println("Update thong tin sinh vien:");
            System.out.print("Nhap ho dem: ");
            String ho_dem = scanner.nextLine();
            System.out.println("Nhap ten:");
            String ten = scanner.nextLine();
            System.out.println("Nhap email");
            String email = scanner.nextLine();
            sv.setTen(ten);
            sv.setHoDem(ho_dem);
            sv.setEmail(email);
            // Cap nhat sinh vien xuong CSDL
            dao.update(sv);
        }
    }

    public void updateAllNameSV(SinhVienDAOImpl dao) {
        // Nhap ten sinh vien
        System.out.println("Nhap TEN can cap nhat:");
        String name = scanner.nextLine();
        dao.updateAllName(name);
    }

    public void deleteSVByID(SinhVienDAOImpl dao) {
        // Nhap id sinh vien
        System.out.println("Nhap ID sinh vien:");
        int id = scanner.nextInt();
        scanner.nextLine();
        // Lay sinh vien tu CSDL
        SinhVien sv = dao.getById(id);
        if(sv==null) {
            System.out.println("Khong tim thay sinh vien nay");
        }else {
            // Remove sinh vien
            dao.delete(id);
        }
    }

    public void deleteSVByName(SinhVienDAOImpl dao) {
        // Nhap ten sinh vien
        System.out.println("Nhap TEN sinh vien muon xoa:");
        String name = scanner.nextLine();
        // Lay sinh vien tu CSDL
        List<SinhVien> svs = dao.getByName(name);
        if(svs.size() == 0) {
            System.out.println("Khong tim thay sinh vien nao co TEN nhu mo ta");
        }else {
            for (SinhVien s : svs) {
                dao.deleteByName(name);
            }
        }
    }
}
