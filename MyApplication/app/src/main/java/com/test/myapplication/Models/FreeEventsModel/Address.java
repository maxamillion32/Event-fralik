package com.test.myapplication.Models.FreeEventsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NehaRege on 8/24/16.
 */
public class Address {
        @SerializedName("address_1")
        @Expose
        private String address1;
        @SerializedName("address_2")
        @Expose
        private Object address2;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("region")
        @Expose
        private String region;
        @SerializedName("postal_code")
        @Expose
        private String postalCode;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("localized_address_display")
        @Expose
        private String localizedAddressDisplay;
        @SerializedName("localized_area_display")
        @Expose
        private String localizedAreaDisplay;

        /**
         *
         * @return
         * The address1
         */
        public String getAddress1() {
                return address1;
        }

        /**
         *
         * @param address1
         * The address_1
         */
        public void setAddress1(String address1) {
                this.address1 = address1;
        }

        /**
         *
         * @return
         * The address2
         */
        public Object getAddress2() {
                return address2;
        }

        /**
         *
         * @param address2
         * The address_2
         */
        public void setAddress2(Object address2) {
                this.address2 = address2;
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
         * The region
         */
        public String getRegion() {
                return region;
        }

        /**
         *
         * @param region
         * The region
         */
        public void setRegion(String region) {
                this.region = region;
        }

        /**
         *
         * @return
         * The postalCode
         */
        public String getPostalCode() {
                return postalCode;
        }

        /**
         *
         * @param postalCode
         * The postal_code
         */
        public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
        }

        /**
         *
         * @return
         * The country
         */
        public String getCountry() {
                return country;
        }

        /**
         *
         * @param country
         * The country
         */
        public void setCountry(String country) {
                this.country = country;
        }

        /**
         *
         * @return
         * The latitude
         */
        public String getLatitude() {
                return latitude;
        }

        /**
         *
         * @param latitude
         * The latitude
         */
        public void setLatitude(String latitude) {
                this.latitude = latitude;
        }

        /**
         *
         * @return
         * The longitude
         */
        public String getLongitude() {
                return longitude;
        }

        /**
         *
         * @param longitude
         * The longitude
         */
        public void setLongitude(String longitude) {
                this.longitude = longitude;
        }

        /**
         *
         * @return
         * The localizedAddressDisplay
         */
        public String getLocalizedAddressDisplay() {
                return localizedAddressDisplay;
        }

        /**
         *
         * @param localizedAddressDisplay
         * The localized_address_display
         */
        public void setLocalizedAddressDisplay(String localizedAddressDisplay) {
                this.localizedAddressDisplay = localizedAddressDisplay;
        }

        /**
         *
         * @return
         * The localizedAreaDisplay
         */
        public String getLocalizedAreaDisplay() {
                return localizedAreaDisplay;
        }

        /**
         *
         * @param localizedAreaDisplay
         * The localized_area_display
         */
        public void setLocalizedAreaDisplay(String localizedAreaDisplay) {
                this.localizedAreaDisplay = localizedAreaDisplay;
        }



}
