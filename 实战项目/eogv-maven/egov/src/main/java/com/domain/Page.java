package com.domain;

public class Page {
    private Integer pageno;//�ڼ�ҳ
    private Integer pagesize;//ÿҳ�ļ�¼����
    private Integer pagecount;//��ҳ��
    private Integer totalsize;//�ܼ�¼����

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
    }
}
