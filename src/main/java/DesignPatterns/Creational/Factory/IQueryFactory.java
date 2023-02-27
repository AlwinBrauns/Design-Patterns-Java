package DesignPatterns.Creational.Factory;

import DesignPatterns.Creational.Builder.Query;

public interface IQueryFactory {
    public Query getAll(String ...tables);
}
