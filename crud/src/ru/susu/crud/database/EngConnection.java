package ru.susu.crud.database;

import ru.susu.crud.database.datareader.EngDataReader;

public abstract class EngConnection {
    private final ConnectionProperties connectionProperties;
    private boolean connected;

    private String clientEncoding;

    public EngConnection(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }


    protected abstract void doConnect();

    protected abstract void disconnect();

    protected abstract EngDataReader doCreateDataReader(String sql);

    public ConnectionProperties getConnectionProperties() {
        return this.connectionProperties;
    }

    public EngDataReader createDataReader(String sql) {
        return this.doCreateDataReader(sql);
    }

    public EngConnection getConnectionHandle() {
        return null;
    }

    public boolean isDriverSupported() {
        return true;
    }

    public String getDriverNotSupportedMessage() {
        return "";
    }

    public String getClientEncoding() {
        return clientEncoding;
    }

    public void setClientEncoding(String clientEncoding) {
        this.clientEncoding = clientEncoding;
    }

    public boolean isConnected(){
        return this.connected;
    }

    protected boolean doExecSQL(String sql){
        return false;
    }

    public void execSql(String sql) throws Exception{
        if(!this.doExecSQL(sql)){
            throw new Exception(lastError());
        }
    }

    public abstract void execScalarSql(String sql);
//
//    public Object[][] execQueryToArray(String sql){
//
//    }

    //TODO: continue after reader is done

    public String lastError(){
        return "";
    }

}
