import java.time.LocalDate;

public class Student {
    private int ID;
    private String name;
    private LocalDate birDate;
    private boolean gender;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirDate() {
        return birDate;
    }

    public String getGender() {
        if ( gender==true ){
            return "男";
        }else{
            return "女";
        }
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirDate(LocalDate birDate) {
        this.birDate = birDate;
    }

    public void setGender(String gender) {
        if (gender.equals("男")){
            this.gender = true;
        }else {
            this.gender = false;
        }
    }

    public Student(int ID, String name, LocalDate birDate, boolean gender) {
        this.ID = ID;
        this.name = name;
        this.birDate = birDate;
        this.gender = gender;
    }

    public Student() {
    }

}

