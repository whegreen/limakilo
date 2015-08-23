package com.limakilogram.www.bawang.util.api.commodity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walesadanto on 23/8/15.
 */
public class GetCommodityCategoriesResponseModel {
    private List<GetCommodityCategoriesResponseData> data = new ArrayList<GetCommodityCategoriesResponseData>();

    public List<GetCommodityCategoriesResponseData> getData() {
        return data;
    }

    public void setData(List<GetCommodityCategoriesResponseData> data) {
        this.data = data;
    }
    public class GetCommodityCategoriesResponseData{

        @Expose
        private Integer id;
        @Expose
        private String name;

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
    }
}
