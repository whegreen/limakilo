package id.limakilo.www.bawang.util.common;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Constant {

    public static final String PROJECT_NUMBER = "1095409058135";
    public static final String ID_SUPPORTKIT = "8tuby7pjo67l17r6c8ttsofo5";

    public static final String TAG_COPY = "label_copy";
    public static final String TAG_ROLE_SHOPPER = "shopper";
    public static final String TAG_ROLE_MERCHANT = "merchant";
    public static final String TAG_ROLE_DELIVERYMAN = "deliveryman";
    public static final String TAG_NOTIFICATION_ACTIVE_VOUCHER = "active_voucher";

    public static final String TAG_FROM = "from";
    public static final String TAG_CLASS_CURRENTDEALS = "current_deals";
    public static final String TAG_CLASS_DEALS_DETAILS = "deals_details";
    public static final String TAG_CLASS_ACTIVE_VOUCHER = "active_voucher";
    public static final String TAG_CLASS_HISTORY_VOUCHER = "history_voucher";
    public static final String TAG_CLASS_EXPIRED_VOUCHER = "expired_voucher";
    public static final String TAG_CLASS_PROFILE = "profile";
    public static final String TAG_CLASS_WELCOME = "welcome";
    public static final String TAG_CLASS_FASTORDER_BIDDING = "FastOrderBiddingActivity";

    public static final String TAG_INTENT_DEALS_ID = "deals_id";
    public static final String TAG_INTENT_VOUCHER_ID = "voucher_id";
    public static final String TAG_INTENT_ASSETS_DIRECTORY = "assets_directory";
    public static final String TAG_INTENT_CLAIMED = "claimed";
    public static final String TAG_INTENT_REDEEM_AT = "redeem_at";
    public static final String TAG_INTENT_DEALS_DATA = "deals_data";
    public static final String TAG_INTENT_DEALS_MODEL = "deals_model";
    public static final String TAG_INTENT_USERS_MODEL = "users_model";
    public static final String TAG_INTENT_VOUCHERS_MODEL = "vouchers_model";
    public static final String TAG_INTENT_VOUCHERS_DATA = "vouchers_data";
    public static final String TAG_INTENT_DUMMY_DATA = "dummy_data";
    public static final String TAG_INTENT_FAST_COMMODITY = "commodity";
    public static final String TAG_INTENT_FAST_BIDDING = "bidding";
    public static final String TAG_INTENT_FAST_ITEM = "fastorder_items";
    public static final String TAG_INTENT_FAST_TOTAL = "fastorder_amount_total";
    public static final String TAG_INTENT_MESSAGE = "root_message";
    public static final String TAG_INTENT_JSON = "json_string";

    public static final String TAG_PREF_SUBDISTRICTID = "sub_district_id";
    public static final String TAG_PREF_NAME = "name";
    public static final String TAG_PREF_HANDPHONE = "handphone";
    public static final String TAG_PREF_POSTALCODE = "postal_code";
    public static final String TAG_PREF_ADDRESS = "address";
    public static final String TAG_PREF_AUTH_TOKEN = "auth_token";
    public static final String TAG_PREF_USER_ID = "user_id";
    public static final String TAG_PREF_FIRST_TRANSACTION = "first_transaction";

    // GCM
    public static final String TAG_INTENT_ACTION = "intent_action";
    public static final String TAG_INTENT_DETAIL = "intent_detail";

    public static final String PUSH_NOTIF_ACTION = "push_notification";
    public static final String OPEN_PESANAN = "pesanan";
    public static final String OPEN_SALDO = "saldo";
    public static final String OPEN_KADALUARSA = "kadaluarsa";
    public static final String OPEN_AKTIF = "aktif";
    public static final String SHOW_STATE_REDEEM = "state_redeem";
    public static final String TAG_CLASS_GCM = "GCM";
    public static final String TAG_INTENT_ACTION_TAP = "action_tap";
    public static final String TAG_INTENT_ACTION_SWIPE = "action_swipe";
    public static final String TAG_CLASS_BELANJAAN = "belanjaan";
    public static final String TAG_CLASS_MAIN = "main_screen";
    public static final String TAG_PREF_SUBDISTRICTNAME = "sub_district_name";
    public static final String TAG_ACTION_UPLOAD = "upload_contact_list";
    public static final String TAG_PREF_FIRSTUSE = "first_use";

    public static final String TAG_ACTION_DIAL = "action_dial";
    public static final String TAG_ACTION_OPEN_WEB = "action_open_web";

    public static final String TAG_PROMO_FIRST_TRANSACTION = "firsttransactiononly";

    public static final String TAG_MESSAGE_REDEEMFIRST = "RedeemFirst";
    public static final String TAG_MESSAGE_REJECTED_VOUCHER = "ErrRejectedVoucher";
    public static final String TAG_MESSAGE_ACCEPTED_VOUCHER = "ErrAcceptedVoucher";
    public static final String TAG_MESSAGE_FAST_ORDER_LIMIT = "ErrActiveFastOrderLimit";
    public static final String TAG_MESSAGE_INVALID_VERSION = "errinvalidversion";
    public static final String TAG_MESSAGE_FAST_RECLAIM = "ErrNoReclaimableFastOrders";
    public static final String TAG_MESSAGE_ERR_CANCELED = "ErrCanceled";

    // Geo Location
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;

    public static final String PACKAGE_NAME =
            "co.sembako.sembakovoucher";
    public static final String RECEIVER = PACKAGE_NAME
            + ".RECEIVER";
    public static final String RESULT_ADDRESS = PACKAGE_NAME
            + ".RESULT_ADDRESS";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME
            + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME
            + ".LOCATION_DATA_EXTRA";
    public static final String FETCH_TYPE_EXTRA = PACKAGE_NAME
            + ".FETCH_TYPE_EXTRA";

    public static final String LOCATION_LATITUDE_DATA_EXTRA = PACKAGE_NAME
            + ".LOCATION_LATITUDE_DATA_EXTRA";
    public static final String LOCATION_LONGITUDE_DATA_EXTRA = PACKAGE_NAME
            + ".LOCATION_LONGITUDE_DATA_EXTRA";
    public static final String LOCATION_NAME_DATA_EXTRA = PACKAGE_NAME
            + ".LOCATION_NAME_DATA_EXTRA";

    public static final String TAG_CATCH_EXCEPTION = "CatchException: %s";
    public static final String TAG_ERROR_STATE = "ErrorState: %s";
    public static final String TAG_PREF_APPROVED = "fast_approved";
    public static final String TAG_PREF_FIRSTUSE_FAST = "first_use_fast";
    public static final String TAG_PREF_FIRSTUSE_FAST_REDEEM = "first_use_fast_redeem";
    public static final String TAG_BUNDLE_VERIFICATION_CODE = "verification_code";

    public static String formatCurrency(long amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setMinimumFractionDigits(0);
        DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();

        decimalFormatSymbols.setCurrencySymbol("Rp");
        decimalFormatSymbols.setMonetaryDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat.format(amount) + ",-";
    }

    /**
     * check if phone number is valid
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
//        String regex = "^\\+?[0-9. ()-]{10,25}$";
        String regex = "\\A(?:(?:\\+62)|0){1}[1-9][0-9]{7,14}\\z";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * formatting timestamp to dd/m/yyyy - hh:mm
     *
     * @param timestamp
     * @return
     */
    public static String dateFormat(Long timestamp) {
        String format = "%d/%d/%d - %02d:%02d";
        Calendar c = getCalendar(timestamp * 1000);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        format = String.format(format, date, month, year, hour, min);
        return format;
    }

    /**
     * convert timestamp to calendar type
     *
     * @param timestamp
     * @return
     */
    public static Calendar getCalendar(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        Date date = new Date(stamp.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * check if string is a numeric format?
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}
