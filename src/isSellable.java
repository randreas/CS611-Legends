import java.math.BigDecimal;

public interface isSellable {
    public abstract BigDecimal getPrice();
    public void setPrice(BigDecimal price);
    public abstract BigDecimal getSellPrice();
}
