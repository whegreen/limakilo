package id.limakilo.www.bawang.util.api.ecash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 2/26/16.
 */
public class GetTransferMember  extends RootResponseModel {
    @SerializedName("toAccount")
    @Expose
    private String toAccount;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("trxDate")
    @Expose
    private String trxDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fromAccount")
    @Expose
    private String fromAccount;

    /**
     *
     * @return
     * The toAccount
     */
    public String getToAccount() {
        return toAccount;
    }

    /**
     *
     * @param toAccount
     * The toAccount
     */
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    /**
     *
     * @return
     * The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The trxDate
     */
    public String getTrxDate() {
        return trxDate;
    }

    /**
     *
     * @param trxDate
     * The trxDate
     */
    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
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
     * The fromAccount
     */
    public String getFromAccount() {
        return fromAccount;
    }

    /**
     *
     * @param fromAccount
     * The fromAccount
     */
    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

}
