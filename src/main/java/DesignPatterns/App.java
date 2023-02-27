package DesignPatterns;

import DesignPatterns.Strucural.Facade.LocalDatabaseAppFacade;

public class App 
{
    public static void main( String[] args )
    {
        LocalDatabaseAppFacade localDatabase = new LocalDatabaseAppFacade();
        localDatabase.connect();
        localDatabase.printSomeQueries();
        localDatabase.disconnect();
    }
}
