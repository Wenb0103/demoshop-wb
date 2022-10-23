package com.example.demodanei.entity;

public class Cart extends BaseEntity{
    private static final long serialVersionUID = -2692377799099693032L;
    private Integer cid;
    private Integer uid;
    private long gid;
    private Integer num;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cart{");
        sb.append("cid=").append(cid);
        sb.append(", uid=").append(uid);
        sb.append(", gid=").append(gid);
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }
}
