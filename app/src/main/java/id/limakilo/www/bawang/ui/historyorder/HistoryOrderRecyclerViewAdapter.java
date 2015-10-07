package id.limakilo.www.bawang.ui.historyorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;

/**
 * Created by walesadanto on 26/7/15.
 */
public class HistoryOrderRecyclerViewAdapter extends RecyclerView.Adapter<HistoryOrderRecyclerViewAdapter.OrderItemViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetOrderResponseModel.GetOrderResponseData> historyOrder;

    public GetOrderResponseModel.GetOrderResponseData getValueAt(int position){
        return historyOrder.get(position);
    }

    public HistoryOrderRecyclerViewAdapter(Context context, List<GetOrderResponseModel.GetOrderResponseData> historyOrder){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.historyOrder = historyOrder;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderItemViewHolder holder, int position) {
        GetOrderResponseModel.GetOrderResponseData item = historyOrder.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return historyOrder.size();
    }

    public static class OrderItemViewHolder extends RecyclerView.ViewHolder{
        public String text;
        public String totalPayment;
        public String orderId;
        public String stockId;
        public String commodityPrice;
        public String orderStatus;
        public String mBoundAvatar;
        public int mBoundStockId;

        @Bind(R.id.order_list_avatar) ImageView mImageView;
        @Bind(R.id.order_list_paket) TextView mTextView;
        @Bind(R.id.order_list_status)TextView mTextView2;
        @Bind(R.id.order_list_pembayaran)TextView mTextView3;
        @Bind(R.id.order_list_seller)TextView mTextView4;
        @Bind(R.id.order_item_view_holder) View mView;

        @OnClick(R.id.order_item_view_holder)
        public void OnOrderItemClick(View view){
            if (orderStatus.equalsIgnoreCase("order_processed")){

                Context context = view.getContext();
                ((HistoryOrderActivity)context).showLoadingBar();
                ((HistoryOrderActivity)context).retrievePaymentDetail(orderId, stockId, totalPayment);
            }
            else if (orderStatus.equalsIgnoreCase("order_paid")){
                Context context = view.getContext();
                ((HistoryOrderActivity)context).showLoadingBar();
                ((HistoryOrderActivity)context).retrieveOrderDetail(orderId, totalPayment);
            }
            else if (orderStatus.equalsIgnoreCase("order_verified")){
                Context context = view.getContext();
                ((HistoryOrderActivity)context).showLoadingBar();
                ((HistoryOrderActivity)context).finishOrderDialog.show();
            }
        }

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(GetOrderResponseModel.GetOrderResponseData item){
            mTextView.setText(item.getStockName());

            String status = "dalam pengiriman";
            if (item.getOrderStatus().toString().equalsIgnoreCase("order_processed")){
                status = "belum transfer";
            } else if (item.getOrderStatus().toString().equalsIgnoreCase("order_paid")){
                status = "transfer diproses";
            }

            mTextView2.setText(status);

            Locale locale  = new Locale("id", "ID");
            String pattern = "###,###.##";

            DecimalFormat decimalFormat = (DecimalFormat)
                    NumberFormat.getNumberInstance(locale);
            decimalFormat.applyPattern(pattern);

            int shipmentCost = 0;
            if (item.getOrderShipmentCost() != null){
                shipmentCost = Integer.valueOf(item.getOrderShipmentCost());
            }
            Float harga = (Float.valueOf(item.getStockPrice())*item.getStockQuantity())
                    +Float.valueOf(item.getOrderPaymentCode())
                    +shipmentCost;

            totalPayment = decimalFormat.format(harga);

            mTextView3.setText("Rp. "+totalPayment+",-");


            mTextView4.setText(item.getSellerName().toString());

            orderId = item.getOrderId().toString();
            stockId = item.getStockId().toString();

            orderStatus = item.getOrderStatus();

            Glide.with(mImageView.getContext())
//                .load(historyOrder.get(position).getAvaUrl())
                    .load(id.limakilo.www.bawang.R.drawable.onion3)
                    .fitCenter()
                    .into(mImageView);
        }

    }
}
