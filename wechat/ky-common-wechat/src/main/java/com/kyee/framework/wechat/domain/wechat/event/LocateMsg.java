package com.kyee.framework.wechat.domain.wechat.event;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 程峰
 * 创建时间:15/8/20 上午9:32
 * 任务号:MOBILEDEVELOP-10110
 * 创建说明:用户上报地理位置bean
 */
public class LocateMsg extends  BaseEventMsg {
    /**
     * 地理位置纬度
     */
    @JsonProperty("Latitude")
    private Long latitude;
    /**
     * 地理位置经度
     */
    @JsonProperty("Longitude")
    private Long longitude;
    /**
     * 地理位置精度
     */
    @JsonProperty("Precision")
    private Long precision;

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getPrecision() {
        return precision;
    }

    public void setPrecision(Long precision) {
        this.precision = precision;
    }
}
