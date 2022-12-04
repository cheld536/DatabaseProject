import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.36.3:4567/STORE", "yunhee","1234");       // mysql STORE databases 연결
            Statement stmt=con.createStatement();                                                     // 쿼리문 생성을 위한 객체

            /* Mading database Book table*/
            Scanner scan = new Scanner(System.in);      // 검색을 위한 객체 생성
            Integer menunumber_int= 0;                  // 메뉴번호 선택을 위한 menunumber_int 선언 Integer로 선언하여 SQL과 연동하여 처리가 유용함
            // 0으로 초기화

            System.out.println("\n\n\n\n\t안녕하세요. 충대 편의점 관리 시스템 입니다.\n");

            /*메뉴 생성*/
            do{

                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("\t\t 수정 기능은 추후 업데이트 할 예정입니다.\n");            // 요구사항은 입력 조회 삭제로 구현
                System.out.println("\t1. 시스템 연결\t\t\t\t\t2.전체 직원 정보 조회\n");
                System.out.println("\t3. 새로운 직원 정보 입력\t\t\t4. 직원 정보 삭제 \n");
                System.out.println("\t5. 직원 정보 수정\t\t\t\t\t6. 전체 상품 정보 조회\n");
                System.out.println("\t7. 새로운 상품 정보 입력\t\t\t8. 상품 정부 수정 \n");
                System.out.println("\t9. 상품 정보 삭제\t\t\t\t\t10. 전체 지점 검색\n");
                System.out.println("\t11. 지점 정보 입력 \t\t\t\t12. 지점 정보 수정\n");
                System.out.println("\t13. 지점 정보 삭제 \t\t\t\t14. 지점 근무자 검색\n");
                System.out.println("\n\n\t0. 종료");
                System.out.println("---------------------------------------------------------------------------------------------\n");
                System.out.println("원하는 메뉴를 골라주세요. !");
                menunumber_int = scan.nextInt();

                if(menunumber_int==1){              // 연결
                    connection();
                }else if(menunumber_int==2){        // 전체 직원 정보 탐색
                    Search_data_all_employee();
                }else if(menunumber_int==3){        //새로운 직원 정보 입력
                    New_employee();
                }else if (menunumber_int==4){       // 직원 정보 삭제
                    Delete_employee();
                }
                else if(menunumber_int==5){
                    Modify_employee();
                }        // 직원 정보 수정
                else if(menunumber_int==6){
                    Search_all_Goods();
                }        // 전체 상품 정보 검색
                else if(menunumber_int==7){
                    New_Goods();
                }        // 새로운 상품 정보 입력
                else if(menunumber_int==8){
                    Modify_Goods();
                }        // 상품 정보 수정
                else if(menunumber_int==9){
                    Delete_Goods();
                }        // 상품 정보 삭제
                else if(menunumber_int==10){
                    Search_all_Branch();
                }       // 전체 지점 검색
                else if(menunumber_int==11){
                    New_Branch();
                }       // 새로운 지점 정보 입력
                else if(menunumber_int==12){
                    Modify_Branch();
                }       // 지점 정보 수정
                else if(menunumber_int==13){
                    Delete_Branch();
                }       // 지점 정보 삭제
                else if(menunumber_int==14){
                    branch_empolyee();
                }       // 지점 근무자 검색
                else if (menunumber_int ==0 ) {     // 종료
                    System.out.println("\n 프로그램을 종료합니다 \n");
                    break;
                }

            }while(true);


        }catch(Exception e){ System.out.println(e);}
    }


    /*연결 */
    public static void connection(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.36.3:4567/STORE", "yunhee","1234");       // mysql STORE databases 연결
            Statement stmt=con.createStatement();                                                     // 쿼리문 생성을 위한 객체

            /* Mading database Book table*/
            Scanner scan = new Scanner(System.in);      // 검색을 위한 객체 생성
            System.out.println("---------------------------------------------------\n");
            System.out.println("\n\t연결되었습니다.\n");
            System.out.println("---------------------------------------------------\n");

        }catch(Exception e){ System.out.println(e);}
    }

    /*직원 정보*/
    public static void Search_data_all_employee(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.36.3:4567/STORE", "yunhee","1234");       // mysql madang databases 연결
            Statement stmt=con.createStatement();                                                     // 쿼리문 생성을 위한 객체

            /* Mading database Book table*/
            Scanner scan = new Scanner(System.in);      // 검색을 위한 객체 생성

            ResultSet rs=stmt.executeQuery("SELECT * FROM empolyee");

            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3) + " " +rs.getInt(4)+" "+rs.getString(5)+
                        " "+rs.getInt(6)+" "+rs.getDate(7)+" "+rs.getInt(8));
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }
    public static void New_employee(){
        /*table 데이터*/
        String empolyee_id; //자동생성으로 필요없음
        String empolyee_name;
        String Gender;
        String age;
        String Phonenumber;
        String Salary;
        String worksdate;
        String branchcode;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수
            String sql = "insert into empolyee values(0,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            Scanner scan = new Scanner(System.in);
            System.out.println("추가 할 직원의 이름을 입력하세요.(필수) : ");
            empolyee_name = scan.nextLine();
            System.out.println("추가 할  직원의 성별을 입력하세요.(필수) : ");
            Gender = scan.nextLine();
            System.out.println("추가 할 직원의 나이를 입력하세요. : ");
            age = scan.nextLine();
            System.out.println("추가 할 직원의 전화번호를 입력하세요(010-1234-5678)(필수). : ");
            Phonenumber = scan.nextLine();
            System.out.println("추가 할 직원의 월급을 입력하세요. : ");
            Salary = scan.nextLine();
            System.out.println("추가 할 직원이 일하는 날짜를 입력하세요.(YYYY-MM-DD) : ");
            worksdate = scan.nextLine();
            System.out.println("추가 할 직원의 일하는 곳을 입력하세요. : ");
            branchcode = scan.nextLine();


            stmt.setString(1, empolyee_name);
            stmt.setString(2, Gender);
            stmt.setString(3, age);
            stmt.setString(4, Phonenumber);
            stmt.setInt(5, Integer.parseInt(Salary));
            stmt.setString(6,worksdate);
            stmt.setInt(7, Integer.parseInt(branchcode));


            int result = stmt.executeUpdate();
            if(result ==1) System.out.println("Inset 저장 성공!");
            else System.out.println("저장 실패");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Delete_employee(){
        String id;      // 삭제할 id 값

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수

            String sql = "delete from empolyee where Ecode=?";                                // 직원의 id로 데이터 삭제
            PreparedStatement stmt = con.prepareStatement(sql);                                 // stmt sql 객체 생성

            Scanner scan = new Scanner(System.in);
            System.out.println("삭제 직원 번호를 입력하세요 : ");                                  // 삭제 할 책의 id 입력
            id = scan.nextLine();
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 수정 기능은 추후에 구현
    public static void Modify_employee(){}

    /*지점 정보*/
    public static void New_Branch(){

        /*table 데이터*/
        String branchcode;
        String branchname;
        String branchaddress;
        String branchNumber;
        String Managername;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수
            String sql = "insert into branch values(0,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            Scanner scan = new Scanner(System.in);
            System.out.println("추가 할 지점의 이름을 입력하세요.(필수) : ");
            branchname = scan.nextLine();
            System.out.println("추가 할 지점의 주소를 입력하세요.(필수) : ");
            branchaddress = scan.nextLine();
            System.out.println("추가 할 지점의 전화번호를 입력하세요(043-123-4567)(필수). : ");
            branchNumber = scan.nextLine();
            System.out.println("추가 할 지점의 메니저 이름을 입력하세요.(필수) : ");
            Managername = scan.nextLine();



            stmt.setString(1, branchname);
            stmt.setString(2, branchaddress);
            stmt.setString(3, branchNumber);
            stmt.setString(4, Managername);



            int result = stmt.executeUpdate();
            if(result ==1) System.out.println("Inset 저장 성공!");
            else System.out.println("저장 실패");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Delete_Branch(){
        String id;      // 삭제할 id 값

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수

            String sql = "delete from branch where Ecode=?";                                // 지점의 id로 데이터 삭제
            PreparedStatement stmt = con.prepareStatement(sql);                             // stmt sql 객체 생성

            Scanner scan = new Scanner(System.in);
            System.out.println("삭제 지점 번호를 입력하세요 : ");                                  // 삭제 할 지점 id 입력
            id = scan.nextLine();
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Modify_Branch(){}// 수정 기능은 추후에 구현
    public static void Search_all_Branch(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.36.3:4567/STORE", "yunhee","1234");       // mysql STORE databases 연결
            Statement stmt=con.createStatement();                                                     // 쿼리문 생성을 위한 객체

            Scanner scan = new Scanner(System.in);      // 검색을 위한 객체 생성

            ResultSet rs=stmt.executeQuery("SELECT * FROM branch");

            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3) + " " +rs.getString(4)+" "+rs.getString(5));
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }
    public static void branch_empolyee(){
        String branchcode;

        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("검색할 지점의 아이디를 입력하세요 : ");

            branchcode = scan.nextLine();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT \n" +
                    "    branch.branchname,\n" +
                    "    empolyee.ecode,\n" +
                    "    empolyee.ename,\n" +
                    "    empolyee.eGender,\n" +
                    "    empolyee.age,\n" +
                    "    empolyee.Phonenumber,\n" +
                    "    empolyee.worksdate\n" +
                    "FROM branch JOIN empolyee\n" +
                    "ON empolyee.branchcode = branch.branchcode\n" +
                    "AND branch.branchcode = "+branchcode+";");

            while(rs.next())
                System.out.println(rs.getString(1)+" "+rs.getInt(2)+ " "+rs.getString(3) + " " +rs.getString(4)+rs.getInt(5)+rs.getString(6)+rs.getString(7));
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*상품 정보*/
    public static void Search_all_Goods(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://192.168.36.3:4567/STORE", "yunhee","1234");       // mysql STORE databases 연결
            Statement stmt=con.createStatement();                                                     // 쿼리문 생성을 위한 객체

            Scanner scan = new Scanner(System.in);      // 검색을 위한 객체 생성

            ResultSet rs=stmt.executeQuery("SELECT * FROM goods");

            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3) + " " +rs.getString(4)+" "+rs.getInt(5)+" "+rs.getDate(6));
            con.close();

        }catch(Exception e){ System.out.println(e);}
    }
    public static void New_Goods(){
        /*table 데이터*/
        Integer goodscode;
        String manufacturecompany;
        String classification;
        String Prace;
        Integer Availablestock;
        String expirationdate;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수
            String sql = "insert into goods values(0,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            Scanner scan = new Scanner(System.in);
            System.out.println("추가 할 상품의 제조 회사를 입력하세요.(필수) : ");
            manufacturecompany = scan.nextLine();
            System.out.println("추가 할 상품의 분류를 입력하세요.(필수) : ");
            classification = scan.nextLine();
            System.out.println("추가 할 상품의 가격를 입력하세요.(필수) : ");
            Prace = scan.nextLine();
            System.out.println("추가 할 상품의 제고를 입력하세요. : ");
            Availablestock = Integer.valueOf(scan.nextLine());
            System.out.println("추가 할 상품의 유통기한을 입력하세요. 입력하세요.(YYYY-DD-MM) : ");
            expirationdate = scan.nextLine();



            stmt.setString(1, manufacturecompany);
            stmt.setString(2, classification);
            stmt.setString(3, Prace);
            stmt.setInt(4, Availablestock);
            stmt.setString(5, expirationdate);



            int result = stmt.executeUpdate();
            if(result ==1) System.out.println("Inset 저장 성공!");
            else System.out.println("저장 실패");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Delete_Goods(){
        String id;      // 삭제할 id 값

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.36.3:4567/STORE", "yunhee", "1234");  // 연결을 위한 con 변수

            String sql = "delete from branch where Ecode=?";                                // 상품의 id로 데이터 삭제
            PreparedStatement stmt = con.prepareStatement(sql);                             // stmt sql 객체 생성

            Scanner scan = new Scanner(System.in);
            System.out.println("삭제 지점 번호를 입력하세요 : ");                                  // 삭제 할 지점 id 입력
            id = scan.nextLine();
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void Modify_Goods(){}
// 수정 기능은 추후에 구현





}
