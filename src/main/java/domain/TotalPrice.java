package domain;


public class TotalPrice {
    private int totalMoney;
    private Point point;

    public TotalPrice(int totalMoney, Point point) {
        this.totalMoney = totalMoney;
        this.point = point;
    }

    private int calculateMoney() {
        return totalMoney - point.getPoint();
    }

    @Override
    public String toString() {
        return "최종 결제한 금액은 " + calculateMoney() + "입니다.";
    }
}
