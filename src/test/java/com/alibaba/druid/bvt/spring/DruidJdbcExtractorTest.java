package com.alibaba.druid.bvt.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.druid.mock.MockConnection;
import com.alibaba.druid.mock.MockPreparedStatement;
import com.alibaba.druid.mock.MockStatement;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.DruidJdbcExtractor;

public class DruidJdbcExtractorTest extends TestCase {

    public void test_spring() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();

        try {
            DruidJdbcExtractor extractor = new DruidJdbcExtractor();

            dataSource.setUrl("jdbc:mock:xx1");
            Connection conn = dataSource.getConnection();
            Assert.assertEquals(true, extractor.getNativeConnection(conn) instanceof MockConnection);
            
            Statement stmt = conn.createStatement();
            Assert.assertEquals(true, extractor.getNativeConnectionFromStatement(stmt) instanceof MockConnection);
            Assert.assertEquals(true, extractor.getNativeStatement(stmt) instanceof MockStatement);
            
            stmt.close();
            
            PreparedStatement preStmt = conn.prepareStatement("select 1");
            Assert.assertEquals(true, extractor.getNativeConnectionFromStatement(preStmt) instanceof MockConnection);
            Assert.assertEquals(true, extractor.getNativeStatement(preStmt) instanceof MockPreparedStatement);
            Assert.assertEquals(true, extractor.getNativePreparedStatement(preStmt) instanceof MockPreparedStatement);
            preStmt.close();
            
            conn.close();
        } finally {
            dataSource.close();
        }
    }

    public void test_spring_filter() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();

        try {
            DruidJdbcExtractor extractor = new DruidJdbcExtractor();

            dataSource.setUrl("jdbc:mock:xx1");
            dataSource.setFilters("stat");
            Connection conn = dataSource.getConnection();
            Assert.assertEquals(true, extractor.getNativeConnection(conn) instanceof MockConnection);
            
            Statement stmt = conn.createStatement();
            Assert.assertEquals(true, extractor.getNativeConnectionFromStatement(stmt) instanceof MockConnection);
            Assert.assertEquals(true, extractor.getNativeStatement(stmt) instanceof MockStatement);
            
            stmt.close();
            
            PreparedStatement preStmt = conn.prepareStatement("select 1");
            Assert.assertEquals(true, extractor.getNativeConnectionFromStatement(preStmt) instanceof MockConnection);
            Assert.assertEquals(true, extractor.getNativeStatement(preStmt) instanceof MockPreparedStatement);
            Assert.assertEquals(true, extractor.getNativePreparedStatement(preStmt) instanceof MockPreparedStatement);
            preStmt.close();
            
            conn.close();
        } finally {
            dataSource.close();
        }
    }
}
