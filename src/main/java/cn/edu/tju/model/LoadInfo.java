package cn.edu.tju.model;

import javax.persistence.Entity;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoadInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int uid;//自增主键

    private String name;//物品名称
    private String dynasty;
    private String type;
    private String place;
    private String loadtime;
    private String id;//员工ID
    private String updatetime;
    private String pathdoc;//文件路径
    private String pathpic;//图片路径
    private Boolean ifcheck;
    private String tag;
    private Boolean ifcheckdown;
    private Boolean ispic;
    private String pathmovie;

    protected LoadInfo() {}

    public LoadInfo(String name, String dynasty, String type, String place, String loadtime, String id, String updatetime, String pathdoc, String pathpic, Boolean ifcheck, String tag, Boolean ifcheckdown, Boolean ispic, String pathmovie) {
        this.name = name;
        this.dynasty = dynasty;
        this.type = type;
        this.place = place;
        this.loadtime = loadtime;
        this.id = id;
        this.updatetime = updatetime;
        this.pathdoc = pathdoc;
        this.pathpic = pathpic;
        this.ifcheck = ifcheck;
        this.tag = tag;
        this.ifcheckdown = ifcheckdown;
        this.ispic = ispic;
        this.pathmovie = pathmovie;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLoadtime() {
        return loadtime;
    }

    public void setLoadtime(String loadtime) {
        this.loadtime = loadtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getPathdoc() {
        return pathdoc;
    }

    public void setPathdoc(String pathdoc) {
        this.pathdoc = pathdoc;
    }

    public String getPathpic() {
        return pathpic;
    }

    public void setPathpic(String pathpic) {
        this.pathpic = pathpic;
    }

    public Boolean getIfcheck() {
        return ifcheck;
    }

    public void setIfcheck(Boolean ifcheck) {
        this.ifcheck = ifcheck;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getIfcheckdown() {
        return ifcheckdown;
    }

    public void setIfcheckdown(Boolean ifcheckdown) {
        this.ifcheckdown = ifcheckdown;
    }

    public Boolean getIspic() {
        return ispic;
    }

    public void setIspic(Boolean ispic) {
        this.ispic = ispic;
    }

    public String getPathmovie() {
        return pathmovie;
    }

    public void setPathmovie(String pathmovie) {
        this.pathmovie = pathmovie;
    }
}
