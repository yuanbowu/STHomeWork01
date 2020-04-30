import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class StudentManager {

    private Student[] stu;
    private int num;
    private Map<Integer, Integer> map;

    public StudentManager() {
        stu = new Student[20];
        num = 0;
        map = new HashMap<Integer, Integer>();
    }

    public void App() {

        Scanner scanner = new Scanner( System.in );
        System.out.println();
        System.out.println("请选择操作：");
        System.out.println("**************************");
        System.out.println("*        1.插入           *");
        System.out.println("*        2.查找           *");
        System.out.println("*        3.删除           *");
        System.out.println("*        4.修改           *");
        System.out.println("*        5.输出           *");
        System.out.println("*        6.退出           *");
        System.out.println("**************************");
        while ( true ) {
            System.out.println();
            switch ( scanner.nextInt() ) {
                case 1:
                    Insert();
                    break;
                case 2:
                    Search();
                    break;
                case 3:
                    Delete();
                    break;
                case 4:
                    Update();
                    break;
                case 5:
                    Output();
                    break;
                case 6:
                    Exit();
            }
        }
    }

    public void Insert(){

        if ( num >= 20 ){
            System.out.println("抱歉，系统已满，无法继续插入学生信息。");
            return;
        }

        Student s = new Student();
        Scanner scanner = new Scanner( System.in );
        System.out.println("插入学生信息");

        boolean flag = true;
        while (flag) {
            System.out.print("请输入学生学号：");
            try {
                int id = Integer.parseInt( scanner.next() );
                if (map.get(id) == null) {
                    s.setID(id);
                    map.put(id, 1);
                    flag = false;
                } else {
                    System.out.println("此学生信息已存在，请勿重复插入");
                    return;
                }
            }catch (NumberFormatException e){
                System.out.println("学号输入格式错误，请重新输入");
            }
        }

        System.out.print("请输入学生姓名：");
        s.setName( scanner.next() );

        flag = true;
        while (flag) {
            System.out.println("按照指定格式输入学生出生日期，例如2018年2月2日即为20180202");
            System.out.print("请输入学生出生日期：");
            try {
                s.setBirDate(LocalDate.parse(scanner.next(), DateTimeFormatter.BASIC_ISO_DATE));
                flag = false;
            } catch (DateTimeParseException e) {
                System.out.println("出生日期格式输入错误，请按正确格式重新输入");
            }
        }

        flag = true;
        while (flag) {
            System.out.print("请输入学生性别（男或女）：");
            String gender = scanner.next();
            if (gender.equals("男")|| gender.equals("女")){
                s.setGender(gender);
                flag = false;
            }else {
                System.out.println("输入格式错误，请重新输入");
            }
        }

        stu[num] = s;
        num++;
        sortStudent(0, num);

        System.out.println("学生信息插入成功！");
    }

    public void Search(){
        System.out.println("查找学生信息");
        System.out.print("请输入学生姓名：");
        Scanner scanner = new Scanner( System.in );
        String name = scanner.next();
        int i;
        Stack<Integer> st = new Stack<Integer>();
        boolean flag = false;
        int[] indexs = new int[20];
        int x = 0;
        for ( i=0; i<num; i++ ){
            if ( stu[i].getName().equals(name) ){
                indexs[x] = i;
                x++;
                flag = true;
            }
        }

        if ( flag == false ){
            System.out.println("未查找到相关学生信息");
            return;
        }

        int n;
        for (n=0; n<x; n++){
            System.out.println("-------------------------------");
            System.out.println("查找到第"+(n+1)+"位学生信息如下");
            System.out.println("学号："+stu[indexs[n]].getID());
            System.out.println("姓名："+stu[indexs[n]].getName());
            System.out.println("出生日期："+stu[indexs[n]].getBirDate());
            System.out.println("性别："+stu[indexs[n]].getGender());
            System.out.println("-------------------------------");
            n++;
        }

    }

    public void Delete(){
        System.out.println("删除学生信息");
        System.out.print("请输入学生姓名：");
        Scanner scanner = new Scanner( System.in );
        String name = scanner.next();
        int i;
        boolean flag = false;
        int[] indexs = new int[20];
        int x = 0;
        for (i=0; i<num; i++){
            if ( stu[i].getName().equals(name) ){
                flag = true;
                indexs[x] = i;
                x++;
            }
        }

        if (flag == false){
            System.out.println("未查找到相关学生信息!");
            return;
        }

        int n;
        for (n=0; n<x; n++){
            System.out.println("-------------------------------");
            System.out.println("查找到第"+(n+1)+"位学生信息如下");
            System.out.println("学号："+stu[indexs[n]].getID());
            System.out.println("姓名："+stu[indexs[n]].getName());
            System.out.println("出生日期："+stu[indexs[n]].getBirDate());
            System.out.println("性别："+stu[indexs[n]].getGender());
            System.out.println("-------------------------------");
        }

        System.out.print("请选择删除以上第几位学生的信息：");
        while (true) {
            n = scanner.nextInt();
            if (n > x) {
                System.out.print("输入错误，请在正确范围内选择");
            }else {
                break;
            }
        }
        int pos = indexs[n-1];
        map.remove(stu[pos].getID());
        for (i=pos; i<num-1; i++){
            stu[i] = stu[i+1];
        }
        num--;

        System.out.println("成功删除信息！");

    }

    public void Update(){
        System.out.println("修改学生信息");
        System.out.print("请输入学生姓名：");
        Scanner scanner = new Scanner( System.in );
        String name = scanner.next();

        int i;
        boolean flag = false;
        int[] indexs = new int[20];
        int x = 0;
        for (i=0; i<num; i++){
            if ( stu[i].getName().equals(name) ){
                flag = true;
                indexs[x] = i;
                x++;
            }
        }

        if (flag == false){
            System.out.println("未查找到相关学生信息!");
            return;
        }
        int n;
        for (n=0; n<x; n++){
            System.out.println("-------------------------------");
            System.out.println("查找到第"+(n+1)+"位学生信息如下");
            System.out.println("学号："+stu[indexs[n]].getID());
            System.out.println("姓名："+stu[indexs[n]].getName());
            System.out.println("出生日期："+stu[indexs[n]].getBirDate());
            System.out.println("性别："+stu[indexs[n]].getGender());
            System.out.println("-------------------------------");
        }

        System.out.print("请选择修改以上第几位学生的信息：");
        while (true) {
            n = scanner.nextInt();
            if (n > x) {
                System.out.print("输入错误，请在正确范围内选择");
            }else {
                break;
            }
        }
        int pos = indexs[n-1];
        System.out.println("-------------------------------");
        System.out.println("该学生的信息如下");
        System.out.println("学号："+stu[pos].getID());
        System.out.println("姓名："+stu[pos].getName());
        System.out.println("出生日期："+stu[pos].getBirDate());
        System.out.println("性别："+stu[pos].getGender());
        System.out.println("-------------------------------");
        System.out.println("请选择需要修改哪项信息：");
        System.out.println("1.学号 2.姓名 3.出生日期 4.性别");
        int choose;
        while (true){
            choose = scanner.nextInt();
            if (choose > 4){
                System.out.println("输入错误，请在正确范围内选择");
            }else {
                break;
            }
        }

        boolean lag;
        switch (choose){
            case 1:
                System.out.print("请输入新学号：");
                lag = true;
                while (lag) {
                    System.out.print("请输入学生学号：");
                    try {
                        int id = Integer.parseInt( scanner.next() );
                        if (map.get(id) == null) {
                            stu[pos].setID(id);
                            map.put(id, 1);
                            lag = false;
                        } else {
                            System.out.println("此学号已存在，请勿重复插入");
                            return;
                        }
                    }catch (NumberFormatException e){
                        System.out.println("学号输入格式错误，请重新输入");
                    }
                }
                sortStudent(0, num);
                break;

            case 2:
                System.out.print("请输入新姓名：");
                stu[pos].setName( scanner.next() );
                break;
            case 3:
                lag = true;
                while (lag) {
                    System.out.println("按照指定格式输入学生出生日期，例如2018年2月2日即为20180202");
                    System.out.print("请输入学生出生日期：");
                    try {
                        stu[pos].setBirDate(LocalDate.parse(scanner.next(), DateTimeFormatter.BASIC_ISO_DATE));
                        lag = false;
                    } catch (DateTimeParseException e) {
                        System.out.println("出生日期格式输入错误，请按正确格式重新输入");
                    }
                }
                break;
            case 4:
                lag = true;
                while (lag) {
                    System.out.print("请输入学生性别（男或女）：");
                    String gender = scanner.next();
                    if (gender.equals("男")|| gender.equals("女")){
                        stu[pos].setGender(gender);
                        lag = false;
                    }else {
                        System.out.println("输入格式错误，请重新输入");
                    }
                }
                break;
        }
        System.out.println("成功修改信息！");

    }

    public void Output(){
        int i;
        for (i=0; i<num; i++){
            System.out.println("-------------------------------");
            System.out.println("查找到第"+(i+1)+"位学生信息如下");
            System.out.println("学号："+stu[i].getID());
            System.out.println("姓名："+stu[i].getName());
            System.out.println("出生日期："+stu[i].getBirDate());
            System.out.println("性别："+stu[i].getGender());
            System.out.println("-------------------------------");
        }
    }

    public void Exit(){
        System.exit(0);
    }

    public void sortStudent( int low,int high ){
        Arrays.sort( stu, low, high, new cmp());
    }

}

class cmp implements Comparator<Student> {
    public int compare( Student s1, Student s2 ){
        if ( s1.getID() < s2.getID() ){
            return  -1;
        }else{
            return 1;
        }
    }
}