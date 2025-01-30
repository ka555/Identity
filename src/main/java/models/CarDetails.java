package models;

import java.util.Objects;

public class CarDetails {
    //    private String variant_reg;
    private final String make_model;
    private final String year;

    public CarDetails(String make_model, String year) {
        this.make_model = make_model;
        this.year = year;
    }

    //Overriding 'equals' method so that two different CareDetails instances with the same makeModel
    // and year to be considered equal based on values and do not throw error on different hash values.
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CarDetails that = (CarDetails) object;
        return Objects.equals(make_model, that.make_model) &&
                Objects.equals(year, that.year);
    }

}

