package learn.demofsecommerceapp.Dto;

public class PurchaseResponseDto {
    private String orderTrackingNumber;

    public PurchaseResponseDto() {
    }

    public PurchaseResponseDto(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }


}
