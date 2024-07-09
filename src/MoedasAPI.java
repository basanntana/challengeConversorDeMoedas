import java.util.Map;

public record MoedasAPI(String base_code, Map<String, Double>conversion_rates) {

    @Override
    public String toString() {
        return "MoedasAPI{" +
                "base_code='" + base_code + '\'' +
                ", conversion_rates=" + conversion_rates +
                '}';
    }

    public Map<String, Double> conversionRates() {
        return conversion_rates;
    }
}
