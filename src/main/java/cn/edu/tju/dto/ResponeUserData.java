package cn.edu.tju.dto;

public class ResponeUserData extends ResponseData{

    private String username;
    private String password;
    private int previlege;
    private String registertime;

    public ResponeUserData(String username, String password, int previlege, String registertime) {
        this.username = username;
        this.password = password;
        this.previlege = previlege;
        this.registertime = registertime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrevilege() {
        return previlege;
    }

    public void setPrevilege(int previlege) {
        this.previlege = previlege;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }
}
