package top.zhengru.sso.unified.param;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/3
 * @Time: 21:35
 */
public class PageParam {
    private Integer pageNo;

    private Integer pageSize;

    private Integer offsetPage;

    public int getOffsetPage(){
        return (pageNo - 1) * pageSize;
    }

    public PageParam() {
    }

    public PageParam(Integer pageNo, Integer pageSize, Integer offsetPage) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.offsetPage = offsetPage;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setOffsetPage(Integer offsetPage) {
        this.offsetPage = offsetPage;
    }
}
