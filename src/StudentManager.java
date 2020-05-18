import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class StudentManager {

    private Student[] stu;//存放学生信息的数组 NO.7
    private int num;//现有的学生数量 NO.7
    private Map<Integer, Integer> map;//判断学号是否被占用 NO.7
    private int maxNum = 20;

    public StudentManager() {
        //stu = new Student[20];    NO.57
        stu = new Student[maxNum];
        num = 0;
        map = new HashMap<Integer, Integer>();
    }

    /**
     * 调用主程序 NO.8
     */
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

            /*  NO.37
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
             */

            //根据输入的数字来判断要执行的功能 NO.5
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
                    break;
                default:
                    System.out.println("请输入正确选项！");
            }//end switch() NO.6
        }//end while() NO.6
    }//end APP() NO.6

    /**
     * 实现插入学生的功能 NO.8
     */
    public void Insert(){

        if ( num >= maxNum ){
            System.out.println("抱歉，系统已满，无法继续插入学生信息。");
            return;
        }

        Student s = new Student();//当前要插入的学生 NO.7
        Scanner scanner = new Scanner( System.in );
        System.out.println("插入学生信息");

        boolean flag = true;//用来控制当输入格式错误后可以重新输入 NO.7
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
            System.out.print("请输入学生年龄：");
            try {
                int age = Integer.parseInt( scanner.next() );
                s.setAge(age);
                flag = false;
            }catch (NumberFormatException e){
                System.out.println("年龄输入格式错误，请重新输入");
            }
        }

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
        s = null;   //NO.29,41
        scanner = null; //NO.29,41
        System.out.println("学生信息插入成功！");
    }//end Insert() NO.6

    /**
     * 实现搜索学生的功能 NO.8
     */
    public void Search(){
        System.out.println("查找学生信息");
        System.out.print("请输入学生姓名：");
        Scanner scanner = new Scanner( System.in );
        String name = scanner.next();
        //int i; NO.11
        int i = 0;
        boolean flag = false; //表明是否找到对应学生 NO.7
        //int[] indexs = new int[20];
        int[] indexs = new int[maxNum]; //NO.57     存放找到的学生在原学生数组中的位置 NO.7
        int x = 0;  //记录一共找到几个这样的学生 NO.7
        for ( i=0; i<num; i++ ){
            if ( stu[i].getName().equals(name) ){
                indexs[x] = i;
                x++;
                flag = true;
            }
        }

        /*  NO.34
        if ( flag == false ){
            System.out.println("未查找到相关学生信息");
            return;
        }
         */

        if ( false == flag ){
            System.out.println("未查找到相关学生信息");
            return;
        }

        //int n; NO.11
        int n = 0;
        for (n=0; n<x; n++){
            System.out.println("查找到第"+(n+1)+"位学生信息如下");
            /*  NO.61
            System.out.println("-------------------------------");
            System.out.println("学号："+stu[indexs[n]].getID());
            System.out.println("姓名："+stu[indexs[n]].getName());
            System.out.println("年龄："+stu[indexs[n]].getAge());
            System.out.println("出生日期："+stu[indexs[n]].getBirDate());
            System.out.println("性别："+stu[indexs[n]].getGender());
            System.out.println("-------------------------------");
             */
            print(stu[indexs[n]]);
        }
        scanner = null; //NO.29,41
        name = null;    //NO.29,41
    }//end Search() NO.6

    /**
     * 实现删除学生的功能 NO.8
     */
    public void Delete(){
        System.out.println("删除学生信息");
        System.out.print("请输入学生姓名：");
        Scanner scanner = new Scanner( System.in );
        String name = scanner.next();
        //int i; NO.11
        int i = 0;
        boolean flag = false;   //表明是否找到对应学生 NO.7
        //int[] indexs = new int[20];
        int[] indexs = new int[maxNum]; //NO.57  存放找到的学生在原学生数组中的位置 NO.7
        int x = 0;  //记录一共找到几个这样的学生 NO.7
        for (i=0; i<num; i++){
            if ( stu[i].getName().equals(name) ){
                flag = true;
                indexs[x] = i;
                x++;
            }
        }

        /*  NO.34
        if (flag == false){
            System.out.println("未查找到相关学生信息!");
            return;
        }
         */

        if (false == flag){
            System.out.println("未查找到相关学生信息!");
            return;
        }

        //int n; NO.11
        int n = 0;
        for (n=0; n<x; n++){
            System.out.println("查找到第"+(n+1)+"位学生信息如下");
            /*  NO.61
            System.out.println("-------------------------------");
            System.out.println("学号："+stu[indexs[n]].getID());
            System.out.println("姓名："+stu[indexs[n]].getName());
            System.out.println("年龄："+stu[indexs[n]].getAge());
            System.out.println("出生日期："+stu[indexs[n]].getBirDate());
            System.out.println("性别："+stu[indexs[n]].getGender());
            System.out.println("-------------------------------");
             */
            print(stu[indexs[n]]);
        }

        System.out.print("请选择删除以上第几位学生的信息：");
        while (true) {
            n = scanner.nextInt();
            if (n > x || n <= 0) {
                System.out.print("输入错误，请在正确范围内选择");
            }else {
                break;
            }
        }
        int pos = indexs[n-1];  //表明选择删除的学生在查询到的学生中的位置 NO.7
        map.remove(stu[pos].getID());
        for (i=pos; i<num-1; i++){
            stu[i] = stu[i+1];
        }
        num--;

        System.out.println("成功删除信息！");
        scanner = null; //NO.29,41
        name = null;    //NO.29,41
    }//end Delete() NO.6

    /**
     * 实现修改学生信息的功能 NO.8
     */
    public void Update(){
        System.out.println("修改学生信息");
        System.out.print("请输入学生姓名：");
        Scanner scanner = new Scanner( System.in );
        String name = scanner.next();

        //int i; NO.11
        int i = 0;
        boolean flag = false;   //表明是否找到对应学生 NO.7
        //int[] indexs = new int[20];
        int[] indexs = new int[maxNum]; //NO.57     存放找到的学生在原学生数组中的位置 NO.7
        int x = 0;  //记录一共找到几个这样的学生 NO.7
        for (i=0; i<num; i++){
            if ( stu[i].getName().equals(name) ){
                flag = true;
                indexs[x] = i;
                x++;
            }
        }

        /*  NO.34
        if (flag == false){
            System.out.println("未查找到相关学生信息!");
            return;
        }
         */

        if (false == flag){
            System.out.println("未查找到相关学生信息!");
            return;
        }

        //int n; NO.11
        int n = 0;
        for (n=0; n<x; n++){
            System.out.println("查找到第"+(n+1)+"位学生信息如下");
            /*
            System.out.println("-------------------------------");
            System.out.println("学号："+stu[indexs[n]].getID());
            System.out.println("姓名："+stu[indexs[n]].getName());
            System.out.println("年龄："+stu[indexs[n]].getAge());
            System.out.println("出生日期："+stu[indexs[n]].getBirDate());
            System.out.println("性别："+stu[indexs[n]].getGender());
            System.out.println("-------------------------------");
             */
            print(stu[indexs[n]]);
        }

        System.out.print("请选择修改以上第几位学生的信息：");
        while (true) {
            n = scanner.nextInt();
            if (n > x || n <= 0) {
                System.out.print("输入错误，请在正确范围内选择");
            }else {
                break;
            }
        }

        int pos = indexs[n-1];  //表明选择修改信息的学生在查询到的学生中的位置 NO.7
        System.out.println("该学生的信息如下");
        /*  NO.61
        System.out.println("-------------------------------");
        System.out.println("学号："+stu[pos].getID());
        System.out.println("姓名："+stu[pos].getName());
        System.out.println("年龄："+stu[pos].getAge());
        System.out.println("出生日期："+stu[pos].getBirDate());
        System.out.println("性别："+stu[pos].getGender());
        System.out.println("-------------------------------");
         */
        print(stu[pos]);
        System.out.println("请选择需要修改哪项信息：");
        System.out.println("1.学号 2.姓名 3.年龄 4.出生日期 5.性别");

        //int choose; NO.11
        int choose = 0; //表明要修改哪一项信息 NO.7
        while (true){
            choose = scanner.nextInt();
            if (choose > 4){
                System.out.println("输入错误，请在正确范围内选择");
            }else {
                break;
            }
        }

        //boolean lag; NO.11
        boolean lag = true; //用来确保当输入格式错误时可以重新输入 NO.7

        /*  NO.37
        switch (choose){
            case 1:
                lag = true;
                while (lag) {
                    System.out.print("请输入新学号：");
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
                }//end while() NO.6
                sortStudent(0, num);
                break;

            case 2:
                System.out.print("请输入新姓名：");
                stu[pos].setName( scanner.next() );
                break;
            case 3:
                lag = true;
                while (lag) {
                    System.out.print("请输入新年龄：");
                    try {
                        int age = Integer.parseInt( scanner.next() );
                        stu[pos].setAge(age);
                        lag = false;
                    }catch (NumberFormatException e){
                        System.out.println("年龄输入格式错误，请重新输入");
                    }
                }
                break;
            case 4:
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
            case 5:
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
        }//end switch() NO.6
         */

        //根据输入来判断要修改学生的哪条信息 NO.5
        switch (choose){
            case 1:
                lag = true;
                while (lag) {
                    System.out.print("请输入新学号：");
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
                }//end while() NO.6
                sortStudent(0, num);
                break;

            case 2:
                System.out.print("请输入新姓名：");
                stu[pos].setName( scanner.next() );
                break;
            case 3:
                lag = true;
                while (lag) {
                    System.out.print("请输入新年龄：");
                    try {
                        int age = Integer.parseInt( scanner.next() );
                        stu[pos].setAge(age);
                        lag = false;
                    }catch (NumberFormatException e){
                        System.out.println("年龄输入格式错误，请重新输入");
                    }
                }
                break;
            case 4:
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
            case 5:
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
            default:
                System.out.println("请输入正确选项！");
                break;
        }//end switch() NO.6

        System.out.println("成功修改信息！");
        scanner = null; //NO.29,41
        name = null;    //NO.29,41
    }//end Update() NO.6

    /**
     * 实现输出所有学生信息的功能 NO.8
     */
    public void Output(){
        /*  NO.34
        if (num == 0){
            System.out.println("学生信息为空！");
        }
         */

        if (0 == num){
            System.out.println("学生信息为空！");
        }

        //int i; NO.11
        int i = 0;
        for (i=0; i<num; i++){
            System.out.println("-------------------------------");
            System.out.println("第"+(i+1)+"位学生信息如下");
            System.out.println("学号："+stu[i].getID());
            System.out.println("姓名："+stu[i].getName());
            System.out.println("年龄："+stu[i].getAge());
            System.out.println("出生日期："+stu[i].getBirDate());
            System.out.println("性别："+stu[i].getGender());
            System.out.println("-------------------------------");
        }
    }//end Output() NO.6

    /**
     * 实现退出系统的功能 NO.8
     */
    public void Exit(){
        System.out.println("成功退出系统，谢谢使用！");
        System.exit(0);
    }

    public void sortStudent( int low,int high ){
        Arrays.sort( stu, low, high, new cmp());
    }

    /**
     *用来打印对应学生的信息
     * @param s 输入要打印信息的学生对象
     */
    private void print(Student s){
        System.out.println("-------------------------------");
        System.out.println("学号："+s.getID());
        System.out.println("姓名："+s.getName());
        System.out.println("年龄："+s.getAge());
        System.out.println("出生日期："+s.getBirDate());
        System.out.println("性别："+s.getGender());
        System.out.println("-------------------------------");
    }

}

/**
 * 重载cmp，实现学生的排序 NO.8
 */
class cmp implements Comparator<Student> {
    public int compare( Student s1, Student s2 ){
        if ( s1.getID() < s2.getID() ){
            return  -1;
        }else{
            return 1;
        }
    }
}