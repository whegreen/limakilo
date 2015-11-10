package id.limakilo.www.bawang.util.api.campaign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 11/8/15.
 */
public class GetCampaignResponseModel extends RootResponseModel {

    @SerializedName("data")
    @Expose
    private List<GetCampaignResponseData> data = new ArrayList<GetCampaignResponseData>();

    /**
     *
     * @return
     * The data
     */
    public List<GetCampaignResponseData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<GetCampaignResponseData> data) {
        this.data = data;
    }

    public class Farmer {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("avatar_url")
        @Expose
        private String avatarUrl;
        @SerializedName("biograph")
        @Expose
        private String biograph;

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
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
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

        /**
         *
         * @return
         * The biograph
         */
        public String getBiograph() {
            return biograph;
        }

        /**
         *
         * @param biograph
         * The biograph
         */
        public void setBiograph(String biograph) {
            this.biograph = biograph;
        }

    }

    public class GetCampaignResponseData {

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
        @SerializedName("farmer")
        @Expose
        private List<Farmer> farmer = new ArrayList<Farmer>();

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

        /**
         *
         * @return
         * The farmer
         */
        public List<Farmer> getFarmer() {
            return farmer;
        }

        /**
         *
         * @param farmer
         * The farmer
         */
        public void setFarmer(List<Farmer> farmer) {
            this.farmer = farmer;
        }

    }

}
