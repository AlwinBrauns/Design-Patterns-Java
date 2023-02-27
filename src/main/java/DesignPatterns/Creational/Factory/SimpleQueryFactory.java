package DesignPatterns.Creational.Factory;

import DesignPatterns.Creational.Builder.Query;
import DesignPatterns.Creational.Builder.QueryBuilder;
import DesignPatterns.Creational.Builder.SelectQueryBuilder;

public class SimpleQueryFactory implements IQueryFactory {

    protected QueryBuilder queryBuilder;

    @Override
    public Query getAll(String... tables) {
        queryBuilder = new SelectQueryBuilder();
        return 
            ((SelectQueryBuilder) queryBuilder)
            .select("*")
            .from(tables)
            .end()
            .build();
    }
    
}
