package org.exschool.pocketworld.resource.building.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by manoylo on 20.11.15.
 */
@Entity
public class Production {
    @EmbeddedId
    private ProductionId productionId;

    private Integer amount;

    public Production() {

    }

    public Production(ProductionId productionId, int amount) {
        this.productionId = productionId;
        this.amount = amount;
    }

    public ProductionId getProductionId() {
        return productionId;
    }

    public void setProductionId(ProductionId productionId) {
        this.productionId = productionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
