package id.limakilo.www.bawang.util.api.ecash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 2/26/16.
 */
public class GetAccountHistory extends RootResponseModel{
    @SerializedName("accountBalance")
    @Expose
    private String accountBalance;
    @SerializedName("creditLimit")
    @Expose
    private String creditLimit;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("accountHistoryDetails")
    @Expose
    private List<Object> accountHistoryDetails = new ArrayList<Object>();
    @SerializedName("onPage")
    @Expose
    private String onPage;
    @SerializedName("pageSize")
    @Expose
    private String pageSize;
    @SerializedName("totalCount")
    @Expose
    private String totalCount;

    /**
     *
     * @return
     * The accountBalance
     */
    public String getAccountBalance() {
        return accountBalance;
    }

    /**
     *
     * @param accountBalance
     * The accountBalance
     */
    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     *
     * @return
     * The creditLimit
     */
    public String getCreditLimit() {
        return creditLimit;
    }

    /**
     *
     * @param creditLimit
     * The creditLimit
     */
    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The accountHistoryDetails
     */
    public List<Object> getAccountHistoryDetails() {
        return accountHistoryDetails;
    }

    /**
     *
     * @param accountHistoryDetails
     * The accountHistoryDetails
     */
    public void setAccountHistoryDetails(List<Object> accountHistoryDetails) {
        this.accountHistoryDetails = accountHistoryDetails;
    }

    /**
     *
     * @return
     * The onPage
     */
    public String getOnPage() {
        return onPage;
    }

    /**
     *
     * @param onPage
     * The onPage
     */
    public void setOnPage(String onPage) {
        this.onPage = onPage;
    }

    /**
     *
     * @return
     * The pageSize
     */
    public String getPageSize() {
        return pageSize;
    }

    /**
     *
     * @param pageSize
     * The pageSize
     */
    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     *
     * @return
     * The totalCount
     */
    public String getTotalCount() {
        return totalCount;
    }

    /**
     *
     * @param totalCount
     * The totalCount
     */
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
