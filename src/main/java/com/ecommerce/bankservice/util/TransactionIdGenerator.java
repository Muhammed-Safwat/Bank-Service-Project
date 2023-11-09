package com.ecommerce.bankservice.util;


import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class TransactionIdGenerator implements IdentifierGenerator {

    @Override
    public String generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        long id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        return String.valueOf(id);
    }

}
