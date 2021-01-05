package com.devsuperior.dsdeliver.entities.enums;

public enum OrderStatus {

    PENDING(0, "Pending"),
    DELIVERED(1, "Delivered");

    private Integer codOrderStatus;
    private String descOrderStatus;

    private OrderStatus(Integer codOrderStatus, String descOrderStatus) {

        this.codOrderStatus = codOrderStatus;
        this.descOrderStatus = descOrderStatus;
    }

    public Integer getCodOrderStatus() {
        return codOrderStatus;
    }

    public void setCodOrderStatus(Integer codOrderStatus) {
        this.codOrderStatus = codOrderStatus;
    }

    public String getDescOrderStatus() {
        return descOrderStatus;
    }

    public void setDescOrderStatus(String descOrderStatus) {
        this.descOrderStatus = descOrderStatus;
    }

    public static OrderStatus toEnum (Integer codOrderStatus) {

        if (codOrderStatus == null) {
            return null;
        }

        for (OrderStatus orderStatus : OrderStatus.values()) {

            if (codOrderStatus.equals(orderStatus.getCodOrderStatus())) {

                return orderStatus;
            }
        }

        throw new IllegalArgumentException("Id OrderStatus inválido: " + codOrderStatus);
    }
}
