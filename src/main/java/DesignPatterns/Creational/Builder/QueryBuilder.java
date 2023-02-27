package DesignPatterns.Creational.Builder;


public abstract class QueryBuilder {
    private final Query query;

    public QueryBuilder() {
        this.query = new Query();
    }

    public abstract Query build();

    public QueryBuilder custom(String customSQL) {
        getQuery().addQuery(customSQL);
        getQuery().addQuery(" ");
        return this;
    }

    public QueryBuilder query(Query query) {
        query.removeDelimiter();
        getQuery().addQuery(query.toString());
        return this;
    }

    public QueryBuilder subquery(Query query) {
        getQuery().addQuery("(");
        query(query);
        getQuery().addQuery(")");
        return this;
    }

    public QueryBuilder end() {
        getQuery().addDelimiter();
        return this;
    }

    protected Query getQuery() {
        return this.query;
    }
}