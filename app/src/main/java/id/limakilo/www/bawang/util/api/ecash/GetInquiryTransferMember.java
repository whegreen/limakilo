package id.limakilo.www.bawang.util.api.ecash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 2/26/16.
 */
public class GetInquiryTransferMember  extends RootResponseModel {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fromAccount")
    @Expose
    private String fromAccount;
    @SerializedName("toAccount")
    @Expose
    private String toAccount;
    @SerializedName("totalFee")
    @Expose
    private String totalFee;
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;

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
     * The totalFee
     */
    public String getTotalFee() {
        return totalFee;
    }

    /**
     *
     * @param totalFee
     * The totalFee
     */
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    /**
     *
     * @return
     * The totalAmount
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param totalAmount
     * The totalAmount
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
