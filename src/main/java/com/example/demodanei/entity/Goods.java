package com.example.demodanei.entity;

public class Goods extends BaseEntity{

    private static final long serialVersionUID = 5960164494648879998L;

    private Long id;
    private Long categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String barcode;
    private String image;
    private Integer status;
    private Integer priority;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Goods{");
        sb.append("id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", itemType='").append(itemType).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", sellPoint='").append(sellPoint).append('\'');
        sb.append(", price=").append(price);
        sb.append(", num=").append(num);
        sb.append(", barcode='").append(barcode).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", status=").append(status);
        sb.append(", priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }
}
