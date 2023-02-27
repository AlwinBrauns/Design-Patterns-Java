package DesignPatterns.Strucural.Facade;

import DesignPatterns.Creational.Builder.SelectQueryBuilder;
import DesignPatterns.Creational.Factory.MySpecialQueryFactory;
import DesignPatterns.Creational.Singleton.DatabaseConnection;

public class LocalDatabaseAppFacade implements IDatabaseApp {

    private DatabaseConnection connection = DatabaseConnection.getInstance();
    private SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder();
    private MySpecialQueryFactory mySpecialQueryFactory = new MySpecialQueryFactory();

    @Override
    public void connect() {
        DatabaseConnection.host = "localhost";
        connection.connectWithDB("maschinendaten", "root", "12345678");
    }

    @Override
    public void disconnect() {
        connection.disconnectFromDB();
    }

    public void printSomeQueries() {
        System.out.println(
            connection.executeQuery(
                selectQueryBuilder
                .select("MaschineID", "KundeFirma")
                .from("Maschine", "Kunde")
                .where("MaschineID = KundeID")
                .end()
                .build()
                .toString()
            )
        );
        System.out.println(
            connection.executeQuery(
                mySpecialQueryFactory.getAll("Kunde").toString()
            )
        );
        System.out.println(
            connection.executeQuery(
                mySpecialQueryFactory.getFirst("Maschinentyp").toString()
            )
        );
    }

}
