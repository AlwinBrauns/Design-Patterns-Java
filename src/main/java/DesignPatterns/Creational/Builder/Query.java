package DesignPatterns.Creational.Builder;

public class Query {
    private String query = "";
    private String delimiter = ";";

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public String toString() {
        return query;
    }

    public void addQuery(String query) {
        this.query += query;
    }

    public void removeDelimiter() {
        if(query.contains(delimiter)) {
            query = query.replace(delimiter, "");
        }
    }

    public void addDelimiter() {
        removeDelimiter();
        query += delimiter;
    }
}
