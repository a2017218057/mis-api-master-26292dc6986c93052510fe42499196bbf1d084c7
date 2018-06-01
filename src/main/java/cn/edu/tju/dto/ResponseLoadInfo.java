package cn.edu.tju.dto;

import cn.edu.tju.model.LoadInfo;

import java.sql.Blob;
import java.util.Date;

public class ResponseLoadInfo {
    private int uid;
    private String name;
    private String dynasty;
    private String type;
    private String place;
    private String loadtime;
    private String id;
    private String updatetime;
    private String pathdoc;
    private String pathpic;
    private Boolean ifcheck;
    private String tag;
    private Boolean ifcheckdown;
    private Boolean ispic;
    private String pathmovie;

    protected ResponseLoadInfo() {}

    public ResponseLoadInfo(LoadInfo loadInfo) {
        this.uid = loadInfo.getUid();
        this.name = loadInfo.getName();
        this.dynasty = loadInfo.getDynasty();
        this.type = loadInfo.getType();
        this.place = loadInfo.getPlace();
        this.loadtime = loadInfo.getLoadtime();
        this.id = loadInfo.getId();
        this.updatetime = loadInfo.getUpdatetime();
        this.pathdoc = loadInfo.getPathdoc();
        this.pathpic = loadInfo.getPathpic();
        this.ifcheck = loadInfo.getIfcheck();
        this.tag = loadInfo.getTag();
        this.ifcheckdown = loadInfo.getIfcheckdown();
        this.ispic = loadInfo.getIspic();
        this.pathmovie = loadInfo.getPathmovie();
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

