package cn.edu.tju.dto;

public class ResponseAnalysis extends ResponseData{
    private float totaluser;
    private float totaldoc;
    private float totalisdown;
    private float totalischeck;
    private String perdown;//可下载百分比
    private String percheck;//可查看百分比

    public ResponseAnalysis(){}

    public ResponseAnalysis(float totaluser, float totaldoc, float totalisdown, float totalischeck, String perdown, String percheck) {
        this.totaluser = totaluser;
        this.totaldoc = totaldoc;
        this.totalisdown = totalisdown;
        this.totalischeck = totalischeck;
        this.perdown = perdown;
        this.percheck = percheck;
    }

    public float getTotaluser() {
        return totaluser;
    }

    public void setTotaluser(float totaluser) {
        this.totaluser = totaluser;
    }

    public float getTotaldoc() {
        return totaldoc;
    }

    public void setTotaldoc(float totaldoc) {
        this.totaldoc = totaldoc;
    }

    public float getTotalisdown() {
        return totalisdown;
    }

    public void setTotalisdown(float totalisdown) {
        this.totalisdown = totalisdown;
    }

    public float getTotalischeck() {
        return totalischeck;
    }

    public void setTotalischeck(float totalischeck) {
        this.totalischeck = totalischeck;
    }

    public String getPerdown() {
        return perdown;
    }

    public void setPerdown(String perdown) {
        this.perdown = perdown;
    }

    public String getPercheck() {
        return percheck;
    }

    public void setPercheck(String percheck) {
        this.percheck = percheck;
    }
}
