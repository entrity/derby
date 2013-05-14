/*

   Derby - Class org.apache.derby.client.am.PreparedStatement42

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

package org.apache.derby.client.am;

import java.sql.SQLException;
import java.sql.SQLType;
import org.apache.derby.client.ClientPooledConnection;

public class  ClientPreparedStatement42 extends ClientPreparedStatement
{
    /**
     * The PreparedStatement used for JDBC 4.2 positioned update statements.
     * Called by material statement constructors.
     * It has the ClientPooledConnection as one of its parameters 
     * this is used to raise the Statement Events when the prepared
     * statement is closed
     */
    public ClientPreparedStatement42
        (
         Agent agent, ClientConnection connection, String sql,
         Section section,ClientPooledConnection cpc
         ) throws SqlException
    {
        super(agent, connection,sql,section,cpc);
    }
    
    /**
     * The PreparedStatementConstructor used for jdbc 4.2 prepared statements 
     * with scroll attributes. Called by material statement constructors.
     * It has the ClientPooledConnection as one of its parameters 
     * this is used to raise the Statement Events when the prepared
     * statement is closed
     */
    public ClientPreparedStatement42
        (
         Agent agent, ClientConnection connection,String sql,
         int type, int concurrency, int holdability, int autoGeneratedKeys, 
         String[] columnNames, int[] columnIndexes, ClientPooledConnection cpc
         ) throws SqlException
    {
        super(agent, connection, sql, type, concurrency, holdability, autoGeneratedKeys,
                columnNames, columnIndexes, cpc);
    }
    
    public  void setObject
        ( int parameterIndex, Object x, SQLType targetSqlType )
        throws SQLException
    {
        synchronized (connection_)
        {
            if (agent_.loggingEnabled()) {
                agent_.logWriter_.traceEntry( this, "setObject", parameterIndex, x, targetSqlType );
            }
            
            checkStatus();
            setObject
                (
                 parameterIndex, x,
                 Utils42.getTypeAsInt( agent_, targetSqlType )
                 );
        }
    }
    
    public void setObject
        (
         int parameterIndex, Object x,
         SQLType targetSqlType, int scaleOrLength
         )  throws SQLException
    {
        synchronized (connection_)
        {
            if (agent_.loggingEnabled()) {
                agent_.logWriter_.traceEntry
                    ( this, "setObject", new Integer( parameterIndex ), x, targetSqlType, new Integer( scaleOrLength ) );
            }
            
            checkStatus();
            setObject
                (
                 parameterIndex, x,
                 Utils42.getTypeAsInt( agent_, targetSqlType ),
                 scaleOrLength
                 );
        }
    }

}
