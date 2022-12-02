import java.sql.*;
import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.36.3:4567/madang", "yunhee","1234");       // mysql madang databases 연결
            Statement stmt=con.createStatement();                                                     // 쿼리문 생성을 위한 객체
            ResultSet rs=stmt.executeQuery("SELECT * FROM Book");                                 // 쿼리문을 담는 객체

            System.out.println("\n\n\n 안녕하세요. 현재 Madang DB Book Table 현황입니다. \n\n\n ");
            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3) + " " +rs.getInt(4));
            con.close();

            /* Mading database Book table*/
            Scanner scan = new Scanner(System.in);      // 검색을 위한 객체 생성
            Integer menunumber_int= 0;                  // 메뉴번호 선택을 위한 menunumber_int 선언 Integer로 선언하여 SQL과 연동하여 처리가 유용함
            // 0으로 초기화


            /*메뉴 생성*/
            do{
                System.out.println("\t안녕하세요. 충대 편의점 관리 시스템 입니다.\n");
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("\n\t\t 사용하고자 하는 메뉴의 번호를 골라주세요! \n");
                System.out.println("\t1. 시스템 연결\t\t\t\t\t2.전체 직원 정보 조회\n");
                System.out.println("\t3. 새로운 직원 정보 입력\t\t\t4. 직원 정보 삭제 \n");
                System.out.println("\t5. 직원 정보 수정\t\t\t\t\t6. 전체 상품 정보 조회\n");
                System.out.println("\t7. 새로운 상품 정보 입력\t\t\t8. 상품 정부 수정 \n");
                System.out.println("\t9. 상품 정보 삭제\t\t\t\t\t10. 전체 지점 검색\n");
                System.out.println("\t11. 지점 정보 입력 \t\t\t\t12. 지점 정보 수정\n");
                System.out.println("\t13. 지점 정보 삭제 \t\t\t\t14. 지점 근무자 검색\n");
                System.out.println("\n\n\t0. 종료");
                System.out.println("---------------------------------------------------------------------------------------------\n");

                menunumber_int = scan.nextInt();

                if(menunumber_int==1){              // 데이터 삽입
                    Insert_data();
                    Select_All();
                }else if(menunumber_int==2){        // 데이터 삭제
                    Delete_data();
                    Select_All();
                }else if(menunumber_int==3){        // 데이터 검색
                    Search_data();
                }else if (menunumber_int==4){       // 전체 데이터 확인
                    Select_All();
                } else if (menunumber_int ==5 ) {   // 종료
                    System.out.println("\n 프로그램을 종료합니다 \n");
                    break;
                }

            }while(true);


        }catch(Exception e){ System.out.println(e);}
    }
    /*데이터 삽입 함수 생성 */
    public static void Insert_data() {

        /*table 데이터*/
        String Book_id;
        String bookname;
        String publisher;
        String price;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/madang", "yunhee", "1234");  // 연결을 위한 con 변수
            String sql = "insert into Book values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            Scanner scan = new Scanner(System.in);
            System.out.println("추가 할 책 Book_id를 입력하세요 : ");
            Book_id = scan.nextLine();
            System.out.println("추가 할 책 Book_name을 입력하세요 : ");
            bookname = scan.nextLine();
            System.out.println("추가 할 책 publisher 입력하세요 : ");
            publisher = scan.nextLine();
            System.out.println("추가 할 책 price를 입력하세요 : ");
            price = scan.nextLine();

            stmt.setInt(1, Integer.parseInt(Book_id));
            stmt.setString(2, bookname);
            stmt.setString(3, publisher);
            stmt.setString(4, price);

            int result = stmt.executeUpdate();
            if(result ==1) System.out.println("Inset 저장 성공!");
            else System.out.println("저장 실패");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*검색에 필요한 Search 함수 생성 */
    public static void Search_data(){

        String name;

        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("검색할 책 이름을 입력하세요 : ");

            name = scan.nextLine();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/madang", "yunhee", "1234");  // 연결을 위한 con 변수

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Book WHERE bookname LIKE '%"+name+"%'");

            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3) + " " +rs.getInt(4));
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*삭제에 필요한 delete 함수 생성 */
    public static void Delete_data() {

        String id;      // 삭제할 책의 id 값

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/madang", "yunhee", "1234");  // 연결을 위한 con 변수

            String sql = "delete from Book where bookid=?";                                // Book id로 데이터 삭제
            PreparedStatement stmt = con.prepareStatement(sql);                            // stmt sql 객체 생성

            Scanner scan = new Scanner(System.in);
            System.out.println("삭제 책 id를 입력하세요 : ");                                  // 삭제 할 책의 id 입력
            id = scan.nextLine();
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*Book 테이블 전체 상태 확인하기 위한 함수 생성*/
    public static void Select_All() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/madang", "yunhee", "1234");  // 연결을 위한 con 변수

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Book");             // sql 문 생성

            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3) + " " +rs.getInt(4));
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
