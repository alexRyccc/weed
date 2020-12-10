import com.weed.loginfo.annotion.ExcelColumn;


public class ExcelReq {
    @ExcelColumn(columnName = "姓名")
    private String userName;

    @ExcelColumn(columnIndex = 1, columnName = "手机号")
    private String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
