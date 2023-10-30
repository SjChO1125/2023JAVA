import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File; // File 클래스 추가

public class UserInform {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        PrintWriter in = new PrintWriter(new FileWriter("user1.txt"));
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("번호: ");
            String num = s.next();
            System.out.println("이름: ");
            String name = s.next();
            System.out.println("전화번호: ");
            String tel = s.next();
            System.out.println("이메일: ");
            String email = s.next();
            System.out.println("입력 끝났으면 1, 계속하려면 0: ");
            int num2 = s.nextInt();
            in.print(num + "," + name + "," + tel + "," + email);
            in.println(); // 개행 문자 추가
            in.flush();
            if (num2 == 1)
                break;
        }
        in.close();

        System.out.println("검색할 번호를 입력하세요:");
        String sc = scan.next();

        File file = new File("user1.txt"); // File 객체 생성

        if (file.exists()) {
            Scanner fileScanner = new Scanner(file); // File 객체로 파일 열기
            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(sc)) {
                    System.out.println("번호: " + parts[0]);
                    System.out.println("이름: " + parts[1]);
                    System.out.println("전화번호: " + parts[2]);
                    System.out.println("이메일: " + parts[3]);
                    found = true;
                    break;
                }
            }
            fileScanner.close();

            if (!found) {
                System.out.println("해당 번호를 찾을 수 없습니다.");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }
}
