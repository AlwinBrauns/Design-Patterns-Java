package DesignPatterns.Creational.Builder;

public class SelectQueryBuilder extends QueryBuilder{
    public SelectQueryBuilder select(String ...attributes) {
        this.getQuery().addQuery("SELECT ");
        for (int attributeIndex = 0; attributeIndex < attributes.length; attributeIndex++) {
            this.getQuery().addQuery(attributes[attributeIndex]);
            if(attributeIndex == attributes.length-1) {
                this.getQuery().addQuery(" ");
            }else {
                this.getQuery().addQuery(", ");
            }
        }
        return this;
    }
    public SelectQueryBuilder from(String ...tables) {
        this.getQuery().addQuery("FROM ");
        for (int tableIndex = 0; tableIndex < tables.length; tableIndex++) {
            this.getQuery().addQuery(tables[tableIndex]);
            if(tableIndex == tables.length-1) {
                this.getQuery().addQuery(" ");
            }else {
                this.getQuery().addQuery(", ");
            }
        }
        return this;
    }
    public SelectQueryBuilder where(String condition){
        this.getQuery().addQuery("WHERE ");
        this.getQuery().addQuery(condition);
        this.getQuery().addQuery(" ");
        return this;
    }
    @Override
    public Query build() {
        return this.getQuery();
    }
}
