package DesignPatterns.Creational.Factory;

import DesignPatterns.Creational.Builder.Query;
import DesignPatterns.Creational.Builder.SelectQueryBuilder;

public class MySpecialQueryFactory extends SimpleQueryFactory {
    public Query getFirst(String ...tables) {
        queryBuilder = new SelectQueryBuilder();
        return 
            ((SelectQueryBuilder) queryBuilder)
            .query(getAll(tables))
            .custom("LIMIT 1")
            .end()
            .build();
    }
}
