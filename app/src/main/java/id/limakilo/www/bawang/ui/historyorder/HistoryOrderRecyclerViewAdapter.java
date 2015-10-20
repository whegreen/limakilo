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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderModel;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderPresenter;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderView;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.common.TextFormatter;

/**
 * Created by walesadanto on 26/7/15.
 */
public class HistoryOrderRecyclerViewAdapter extends RecyclerView.Adapter<HistoryOrderRecyclerViewAdapter.OrderItemViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetOrderResponseModel.GetOrderResponseData> historyOrder;
    private HistoryOrderPresenter presenter;
    private HistoryOrderModel model;

    public GetOrderResponseModel.GetOrderResponseData getValueAt(int position){
        return historyOrder.get(position);
    }

    public HistoryOrderRecyclerViewAdapter(Context context, List<GetOrderResponseModel.GetOrderResponseData> historyOrder,
                                           HistoryOrderPresenter presenter, HistoryOrderModel model){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.historyOrder = historyOrder;
        this.presenter = presenter;
        this.model = model;
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

    public class OrderItemViewHolder extends RecyclerView.ViewHolder{
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
        @Bind(R.id.order_list_tanggal_pesan)TextView mTextView5;
        @Bind(R.id.order_item_view_holder) View mView;

        @OnClick(R.id.order_item_view_holder)
        public void OnOrderItemClick(View view){
            model.getOrderDetailModel().setOrderId(Integer.parseInt(orderId));
            model.getOrderDetailModel().setStockId(Integer.parseInt(stockId));
            model.setTotalPayment(totalPayment);

            presenter.presentState(HistoryOrderView.ViewState.LOADING);
            if (orderStatus.equalsIgnoreCase("order_processed")){
                Context context = view.getContext();
                presenter.presentState(HistoryOrderView.ViewState.LOAD_STOCK);
            }
            else if (orderStatus.equalsIgnoreCase("order_paid")){
                Context context = view.getContext();
                presenter.presentState(HistoryOrderView.ViewState.SHOW_DETAIL_ORDER);
            }
            else if (orderStatus.equalsIgnoreCase("order_verified")){
                Context context = view.getContext();
                presenter.presentState(HistoryOrderView.ViewState.SHOW_FINISH_DIALOG);
            }
            else if (orderStatus.equalsIgnoreCase("order_shipment")){
                Context context = view.getContext();
                presenter.presentState(HistoryOrderView.ViewState.SHOW_SHIPMENT_DIALOG);
            }
            else if (orderStatus.equalsIgnoreCase("order_discard")){
                Context context = view.getContext();
                presenter.presentState(HistoryOrderView.ViewState.SHOW_DISCARD_DIALOG);
            }
        }

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(GetOrderResponseModel.GetOrderResponseData item){
            model.getOrderDetailModel().setData(item);

            mTextView.setText(item.getStockName());

            String status = "dalam pengiriman";
            if (item.getOrderStatus().toString().equalsIgnoreCase("order_processed")){
                status = "pesanan diproses";
            } else if (item.getOrderStatus().toString().equalsIgnoreCase("order_paid")){
                status = "konfirmasi pembayaran";
                presenter.doUpdateTextColor(mTextView2, R.color.fuchsia);
            } else if (item.getOrderStatus().toString().equalsIgnoreCase("order_verified")){
                status = "verifikasi pembayaran";
                presenter.doUpdateTextColor(mTextView2, R.color.teal);
            } else if (item.getOrderStatus().toString().equalsIgnoreCase("order_shipment")){
                status = "pesanan dikirim";
                presenter.doUpdateTextColor(mTextView2, R.color.green);
            } else if (item.getOrderStatus().toString().equalsIgnoreCase("order_discard")){
                status = "pesanan dibatalkan";
                presenter.doUpdateTextColor(mTextView2, R.color.red);
            }

            mTextView2.setText(status);

            int shipmentCost = 0;
            if (item.getOrderShipmentCost() != null){
                shipmentCost = Integer.valueOf(item.getOrderShipmentCost());
            }
            Double harga = ((Double.valueOf(item.getStockPrice())*item.getStockQuantity())*Double.parseDouble(item.getOrderQuantity()))
                    + Float.valueOf(item.getOrderPaymentCode())
                    +shipmentCost;

            totalPayment = TextFormatter.decimalFormat(harga);
            model.setTotalPayment(totalPayment);

            mTextView3.setText("Rp. "+totalPayment+",-");
            mTextView4.setText(item.getSellerName().toString());

            String[] timestamp = item.getOrderDate().toString().split("T");
            mTextView5.setText(timestamp[0]);

            orderId = item.getOrderId().toString();
            stockId = item.getStockId().toString();

            orderStatus = item.getOrderStatus();

            Glide.with(mImageView.getContext())
                    .load(id.limakilo.www.bawang.R.drawable.onion3)
                    .fitCenter()
                    .into(mImageView);
        }

    }
}
