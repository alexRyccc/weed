import com.weed.loginfo.annotion.Check;
import com.weed.loginfo.annotion.CheckField;

import java.util.List;

/**
 * @Author Alexryc
 * @Date 2020/10/30/030 14:48
 * @Version 1.0
 */

public class testBean {

    @CheckField(name = "总可用余额", check = Check.checks)
    private String aviAmt;
    /**
     *
     */
    @CheckField(name = "待结算余额", check = Check.checks)
    private String settAmt;
    /**
     *
     */
    @CheckField(name = "冻结金额", check = Check.checks)
    private String freezeAmt;
    /**
     *
     */
    @CheckField(name = "可提现余额", check = Check.checks)
    private String txAviAmt;
    /**
     *
     */
    @CheckField(name = "二类户可用余额")
    private String eacAmt;
    /**
     *
     */
    @CheckField(name = "二类户总余额")
    private String eacTotalAmt;

    @CheckField(name = "屁屁超人", type = Check.arrays)
    private String[] TotalAmt;

    @CheckField(name = "派大星", type = Check.sets)
    private List<String> tutu;

    @CheckField(name = "用户信息", type = Check.beans)
    private User testBean;

    public String getAviAmt() {
        return aviAmt;
    }

    public void setAviAmt(String aviAmt) {
        this.aviAmt = aviAmt;
    }

    public String getSettAmt() {
        return settAmt;
    }

    public void setSettAmt(String settAmt) {
        this.settAmt = settAmt;
    }

    public String getFreezeAmt() {
        return freezeAmt;
    }

    public void setFreezeAmt(String freezeAmt) {
        this.freezeAmt = freezeAmt;
    }

    public String getTxAviAmt() {
        return txAviAmt;
    }

    public void setTxAviAmt(String txAviAmt) {
        this.txAviAmt = txAviAmt;
    }

    public String getEacAmt() {
        return eacAmt;
    }

    public void setEacAmt(String eacAmt) {
        this.eacAmt = eacAmt;
    }

    public String getEacTotalAmt() {
        return eacTotalAmt;
    }

    public void setEacTotalAmt(String eacTotalAmt) {
        this.eacTotalAmt = eacTotalAmt;
    }

    public String[] getTotalAmt() {
        return TotalAmt;
    }

    public void setTotalAmt(String[] totalAmt) {
        TotalAmt = totalAmt;
    }

    public List<String> getTutu() {
        return tutu;
    }

    public void setTutu(List<String> tutu) {
        this.tutu = tutu;
    }

    public User getTestBean() {
        return testBean;
    }

    public void setTestBean(User testBean) {
        this.testBean = testBean;
    }

    public testBean(String aviAmt, String settAmt, String freezeAmt, String txAviAmt, String eacAmt, String eacTotalAmt, String[] totalAmt, List<String> tutu, User testBean) {
        this.aviAmt = aviAmt;
        this.settAmt = settAmt;
        this.freezeAmt = freezeAmt;
        this.txAviAmt = txAviAmt;
        this.eacAmt = eacAmt;
        this.eacTotalAmt = eacTotalAmt;
        TotalAmt = totalAmt;
        this.tutu = tutu;
        this.testBean = testBean;
    }
}
