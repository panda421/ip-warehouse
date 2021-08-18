package top.panda421.spider.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author panda421
 * @date 2021-08-17 23:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProxyIP {
    @ApiModelProperty(value = "唯一ID")
    private String proxyId;
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;
    @ApiModelProperty(value = "IP端口")
    private Integer ipPort;
    @ApiModelProperty(value = "数据来源")
    private String dataSources;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "位置")
    private String location;
    @ApiModelProperty(value = "支持https")
    private Boolean https;
    @ApiModelProperty(value = "支持http")
    private Boolean http;
    @ApiModelProperty(value = "匿名性")
    private Boolean anonymity;
    @ApiModelProperty(value = "可用性")
    private Boolean available;


}
