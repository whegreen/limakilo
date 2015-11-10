package id.limakilo.www.bawang.util.api.campaign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 11/8/15.
 */
public class GetCampaignDetailResponseModel extends RootResponseModel {

    @SerializedName("data")
    @Expose
    private List<GetCampaignDetailData> data = new ArrayList<GetCampaignDetailData>();

    /**
     *
     * @return
     * The data
     */
    public List<GetCampaignDetailData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<GetCampaignDetailData> data) {
        this.data = data;
    }


    public class Campaign {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("commodity")
        @Expose
        private String commodity;
        @SerializedName("commodity_category")
        @Expose
        private String commodityCategory;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("sold")
        @Expose
        private String sold;
        @SerializedName("target")
        @Expose
        private String target;
        @SerializedName("farmer_id")
        @Expose
        private Integer farmerId;

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The title
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         * The description
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         * The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         * The imageUrl
         */
        public String getImageUrl() {
            return imageUrl;
        }

        /**
         *
         * @param imageUrl
         * The image_url
         */
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        /**
         *
         * @return
         * The commodity
         */
        public String getCommodity() {
            return commodity;
        }

        /**
         *
         * @param commodity
         * The commodity
         */
        public void setCommodity(String commodity) {
            this.commodity = commodity;
        }

        /**
         *
         * @return
         * The commodityCategory
         */
        public String getCommodityCategory() {
            return commodityCategory;
        }

        /**
         *
         * @param commodityCategory
         * The commodity_category
         */
        public void setCommodityCategory(String commodityCategory) {
            this.commodityCategory = commodityCategory;
        }

        /**
         *
         * @return
         * The address
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         *
         * @return
         * The city
         */
        public String getCity() {
            return city;
        }

        /**
         *
         * @param city
         * The city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         *
         * @return
         * The endDate
         */
        public String getEndDate() {
            return endDate;
        }

        /**
         *
         * @param endDate
         * The end_date
         */
        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        /**
         *
         * @return
         * The price
         */
        public String getPrice() {
            return price;
        }

        /**
         *
         * @param price
         * The price
         */
        public void setPrice(String price) {
            this.price = price;
        }

        /**
         *
         * @return
         * The sold
         */
        public String getSold() {
            return sold;
        }

        /**
         *
         * @param sold
         * The sold
         */
        public void setSold(String sold) {
            this.sold = sold;
        }

        /**
         *
         * @return
         * The target
         */
        public String getTarget() {
            return target;
        }

        /**
         *
         * @param target
         * The target
         */
        public void setTarget(String target) {
            this.target = target;
        }

        /**
         *
         * @return
         * The farmerId
         */
        public Integer getFarmerId() {
            return farmerId;
        }

        /**
         *
         * @param farmerId
         * The farmer_id
         */
        public void setFarmerId(Integer farmerId) {
            this.farmerId = farmerId;
        }

    }

    public class City {

        @SerializedName("cit_id")
        @Expose
        private Integer citId;
        @SerializedName("cit_province_id")
        @Expose
        private Integer citProvinceId;
        @SerializedName("cit_name")
        @Expose
        private String citName;

        /**
         *
         * @return
         * The citId
         */
        public Integer getCitId() {
            return citId;
        }

        /**
         *
         * @param citId
         * The cit_id
         */
        public void setCitId(Integer citId) {
            this.citId = citId;
        }

        /**
         *
         * @return
         * The citProvinceId
         */
        public Integer getCitProvinceId() {
            return citProvinceId;
        }

        /**
         *
         * @param citProvinceId
         * The cit_province_id
         */
        public void setCitProvinceId(Integer citProvinceId) {
            this.citProvinceId = citProvinceId;
        }

        /**
         *
         * @return
         * The citName
         */
        public String getCitName() {
            return citName;
        }

        /**
         *
         * @param citName
         * The cit_name
         */
        public void setCitName(String citName) {
            this.citName = citName;
        }

    }

    public class Contributor {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("avatar_url")
        @Expose
        private String avatarUrl;

        /**
         *
         * @return
         * The userId
         */
        public Integer getUserId() {
            return userId;
        }

        /**
         *
         * @param userId
         * The user_id
         */
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        /**
         *
         * @return
         * The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         * The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         * The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         * The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         *
         * @return
         * The avatarUrl
         */
        public String getAvatarUrl() {
            return avatarUrl;
        }

        /**
         *
         * @param avatarUrl
         * The avatar_url
         */
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

    }

    public class GetCampaignDetailData {

        @SerializedName("campaign")
        @Expose
        private List<Campaign> campaign = new ArrayList<Campaign>();
        @SerializedName("contributor")
        @Expose
        private List<Contributor> contributor = new ArrayList<Contributor>();
        @SerializedName("feedback")
        @Expose
        private List<Feedback> feedback = new ArrayList<Feedback>();
        @SerializedName("package")
        @Expose
        private List<Package> _package = new ArrayList<Package>();

        /**
         *
         * @return
         * The campaign
         */
        public List<Campaign> getCampaign() {
            return campaign;
        }

        /**
         *
         * @param campaign
         * The campaign
         */
        public void setCampaign(List<Campaign> campaign) {
            this.campaign = campaign;
        }

        /**
         *
         * @return
         * The contributor
         */
        public List<Contributor> getContributor() {
            return contributor;
        }

        /**
         *
         * @param contributor
         * The contributor
         */
        public void setContributor(List<Contributor> contributor) {
            this.contributor = contributor;
        }

        /**
         *
         * @return
         * The feedback
         */
        public List<Feedback> getFeedback() {
            return feedback;
        }

        /**
         *
         * @param feedback
         * The feedback
         */
        public void setFeedback(List<Feedback> feedback) {
            this.feedback = feedback;
        }

        /**
         *
         * @return
         * The _package
         */
        public List<Package> getPackage() {
            return _package;
        }

        /**
         *
         * @param _package
         * The package
         */
        public void setPackage(List<Package> _package) {
            this._package = _package;
        }

    }


    public class Feedback {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("message_date")
        @Expose
        private String messageDate;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The message
         */
        public String getMessage() {
            return message;
        }

        /**
         *
         * @param message
         * The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         *
         * @return
         * The messageDate
         */
        public String getMessageDate() {
            return messageDate;
        }

        /**
         *
         * @param messageDate
         * The message_date
         */
        public void setMessageDate(String messageDate) {
            this.messageDate = messageDate;
        }

        /**
         *
         * @return
         * The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         * The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         * The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         * The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    }

    public class Package {

        @SerializedName("stock_id")
        @Expose
        private Integer stockId;
        @SerializedName("stock_name")
        @Expose
        private String stockName;
        @SerializedName("stock_quantity")
        @Expose
        private Double stockQuantity;
        @SerializedName("stock_price")
        @Expose
        private String stockPrice;
        @SerializedName("commodity_id")
        @Expose
        private Integer commodityId;
        @SerializedName("stock_commodity")
        @Expose
        private String stockCommodity;
        @SerializedName("stock_category")
        @Expose
        private String stockCategory;
        @SerializedName("seller_id")
        @Expose
        private Integer sellerId;
        @SerializedName("seller_name")
        @Expose
        private String sellerName;
        @SerializedName("seller_avatar_url")
        @Expose
        private String sellerAvatarUrl;
        @SerializedName("seller_reputation")
        @Expose
        private String sellerReputation;
        @SerializedName("seller_address")
        @Expose
        private String sellerAddress;
        @SerializedName("seller_city")
        @Expose
        private String sellerCity;
        @SerializedName("stock_total_order")
        @Expose
        private Integer stockTotalOrder;
        @SerializedName("stock_quota")
        @Expose
        private Integer stockQuota;
        @SerializedName("stock_available")
        @Expose
        private Integer stockAvailable;
        @SerializedName("stock_ordered")
        @Expose
        private Integer stockOrdered;
        @SerializedName("cities")
        @Expose
        private List<City> cities = new ArrayList<City>();

        /**
         *
         * @return
         * The stockId
         */
        public Integer getStockId() {
            return stockId;
        }

        /**
         *
         * @param stockId
         * The stock_id
         */
        public void setStockId(Integer stockId) {
            this.stockId = stockId;
        }

        /**
         *
         * @return
         * The stockName
         */
        public String getStockName() {
            return stockName;
        }

        /**
         *
         * @param stockName
         * The stock_name
         */
        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        /**
         *
         * @return
         * The stockQuantity
         */
        public Double getStockQuantity() {
            return stockQuantity;
        }

        /**
         *
         * @param stockQuantity
         * The stock_quantity
         */
        public void setStockQuantity(Double stockQuantity) {
            this.stockQuantity = stockQuantity;
        }

        /**
         *
         * @return
         * The stockPrice
         */
        public String getStockPrice() {
            return stockPrice;
        }

        /**
         *
         * @param stockPrice
         * The stock_price
         */
        public void setStockPrice(String stockPrice) {
            this.stockPrice = stockPrice;
        }

        /**
         *
         * @return
         * The commodityId
         */
        public Integer getCommodityId() {
            return commodityId;
        }

        /**
         *
         * @param commodityId
         * The commodity_id
         */
        public void setCommodityId(Integer commodityId) {
            this.commodityId = commodityId;
        }

        /**
         *
         * @return
         * The stockCommodity
         */
        public String getStockCommodity() {
            return stockCommodity;
        }

        /**
         *
         * @param stockCommodity
         * The stock_commodity
         */
        public void setStockCommodity(String stockCommodity) {
            this.stockCommodity = stockCommodity;
        }

        /**
         *
         * @return
         * The stockCategory
         */
        public String getStockCategory() {
            return stockCategory;
        }

        /**
         *
         * @param stockCategory
         * The stock_category
         */
        public void setStockCategory(String stockCategory) {
            this.stockCategory = stockCategory;
        }

        /**
         *
         * @return
         * The sellerId
         */
        public Integer getSellerId() {
            return sellerId;
        }

        /**
         *
         * @param sellerId
         * The seller_id
         */
        public void setSellerId(Integer sellerId) {
            this.sellerId = sellerId;
        }

        /**
         *
         * @return
         * The sellerName
         */
        public String getSellerName() {
            return sellerName;
        }

        /**
         *
         * @param sellerName
         * The seller_name
         */
        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        /**
         *
         * @return
         * The sellerAvatarUrl
         */
        public String getSellerAvatarUrl() {
            return sellerAvatarUrl;
        }

        /**
         *
         * @param sellerAvatarUrl
         * The seller_avatar_url
         */
        public void setSellerAvatarUrl(String sellerAvatarUrl) {
            this.sellerAvatarUrl = sellerAvatarUrl;
        }

        /**
         *
         * @return
         * The sellerReputation
         */
        public String getSellerReputation() {
            return sellerReputation;
        }

        /**
         *
         * @param sellerReputation
         * The seller_reputation
         */
        public void setSellerReputation(String sellerReputation) {
            this.sellerReputation = sellerReputation;
        }

        /**
         *
         * @return
         * The sellerAddress
         */
        public String getSellerAddress() {
            return sellerAddress;
        }

        /**
         *
         * @param sellerAddress
         * The seller_address
         */
        public void setSellerAddress(String sellerAddress) {
            this.sellerAddress = sellerAddress;
        }

        /**
         *
         * @return
         * The sellerCity
         */
        public String getSellerCity() {
            return sellerCity;
        }

        /**
         *
         * @param sellerCity
         * The seller_city
         */
        public void setSellerCity(String sellerCity) {
            this.sellerCity = sellerCity;
        }

        /**
         *
         * @return
         * The stockTotalOrder
         */
        public Integer getStockTotalOrder() {
            return stockTotalOrder;
        }

        /**
         *
         * @param stockTotalOrder
         * The stock_total_order
         */
        public void setStockTotalOrder(Integer stockTotalOrder) {
            this.stockTotalOrder = stockTotalOrder;
        }

        /**
         *
         * @return
         * The stockQuota
         */
        public Integer getStockQuota() {
            return stockQuota;
        }

        /**
         *
         * @param stockQuota
         * The stock_quota
         */
        public void setStockQuota(Integer stockQuota) {
            this.stockQuota = stockQuota;
        }

        /**
         *
         * @return
         * The stockAvailable
         */
        public Integer getStockAvailable() {
            return stockAvailable;
        }

        /**
         *
         * @param stockAvailable
         * The stock_available
         */
        public void setStockAvailable(Integer stockAvailable) {
            this.stockAvailable = stockAvailable;
        }

        /**
         *
         * @return
         * The stockOrdered
         */
        public Integer getStockOrdered() {
            return stockOrdered;
        }

        /**
         *
         * @param stockOrdered
         * The stock_ordered
         */
        public void setStockOrdered(Integer stockOrdered) {
            this.stockOrdered = stockOrdered;
        }

        /**
         *
         * @return
         * The cities
         */
        public List<City> getCities() {
            return cities;
        }

        /**
         *
         * @param cities
         * The cities
         */
        public void setCities(List<City> cities) {
            this.cities = cities;
        }

    }

}
