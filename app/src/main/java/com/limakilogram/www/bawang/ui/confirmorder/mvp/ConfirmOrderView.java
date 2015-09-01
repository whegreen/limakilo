package com.limakilogram.www.bawang.ui.confirmorder.mvp;

/**
 * Created by walesadanto on 30/8/15.
 */
public interface ConfirmOrderView {

    public enum ConfirmOrderState{
        PAID, CONFIRMED, PACKED, DELIVERING, RECEIVED
    }

}
