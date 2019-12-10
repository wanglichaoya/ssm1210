package com.po;

public class UserInfo {

    private Integer id;
    private String userName;
    private String password;
    private int age;
    private String sex;
    /*private String province;*/
    //private ProvincePo provincePo;

    private Integer p_Id;
    private String p_Name;

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getP_Id() {
        return p_Id;
    }

    public void setP_Id(Integer p_Id) {
        this.p_Id = p_Id;
    }

    public String getP_Name() {
        return p_Name;
    }

    public void setP_Name(String p_Name) {
        this.p_Name = p_Name;
    }

    private int salary;
    //当前第几页
    private int page;
    //每页显示多少条
    private int rows;
    //开始
    private int start;
    //结束
    private int end;

    //角色
    private String roleName;

    public UserInfo() {
    }

    /*public ProvincePo getProvincePo() {
        return provincePo;
    }

    public void setProvincePo(ProvincePo provincePo) {
        this.provincePo = provincePo;
    }*/

    public UserInfo(Integer id, String userName, String password, int age, String sex, /*ProvincePo provincePo,*/ int salary, int page, int rows, int start, int end, String roleName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.sex = sex;
        //this.provincePo = provincePo;
        this.salary = salary;
        this.page = page;
        this.rows = rows;
        this.start = start;
        this.end = end;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public UserInfo(Integer id, String userName, String password, int age, String sex, int salary, int page, int rows, int start, int end) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.sex = sex;

        this.salary = salary;
        this.page = page;
        this.rows = rows;
        this.start = start;
        this.end = end;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
