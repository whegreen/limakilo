package id.limakilo.www.bawang.util.api.ecash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 2/26/16.
 */
public class GetBalanceInquiry  extends RootResponseModel {
    @SerializedName("accountBalance")
    @Expose
    private String accountBalance;
    @SerializedName("creditLimit")
    @Expose
    private String creditLimit;
    @SerializedName("status")
    @Expose
    private String status;

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
}
