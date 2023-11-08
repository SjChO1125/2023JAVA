package Collection2;
import java.util.Scanner;

public class BookArrayListTest {

    public static void main(String[] args) {
        BookArrayList bookArrayList = new BookArrayList();

        Book book1 = new Book(0001, "파우스트");
        Book book2 = new Book(0002, "황무지");
        Book book3 = new Book(0003, "변신");
        Book book4 = new Book(0004, "픽션들");
        Book book5 = new Book(0005, "톨스토이");

        bookArrayList.addBook(book1);
        bookArrayList.addBook(book2);
        bookArrayList.addBook(book3);
        bookArrayList.addBook(book4);
        bookArrayList.addBook(book5);

        bookArrayList.showAllBook();

     Scanner scanner = new Scanner(System.in);
        System.out.println("도서를 추가할 위치를 입력:");
        int insertIndex = scanner.nextInt();
        
      System.out.println("책 ID 입력:");
      int bookId = scanner.nextInt();
      scanner.nextLine();
        
      System.out.println("책 제목 입력:");
      String title = scanner.nextLine();
      
      Book newBook = new Book(bookId, title);
      bookArrayList.insertBook(insertIndex, newBook);

      bookArrayList.showAllBook();

      scanner.close();
    }
}
//정말 많이 확인해봤는데 문제점이 뭔지 모르겠다 대체 뭘까 왜 입력은 제대로 받는데 출력이 이상할까 