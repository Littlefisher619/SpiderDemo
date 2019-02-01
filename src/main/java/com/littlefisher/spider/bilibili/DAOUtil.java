package com.littlefisher.spider.bilibili;

import com.littlefisher.spider.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUtil {
    public static void insertNewAV(int avid,String title,String desc,String author,String pages,int view,int danmu,int pubdate,String pic,int videos) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "insert into bilibili (id,title,`desc`,author,pages,`view`,danmu,pubdate,pic,videos) values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1,avid);
        pstmt.setString(2,title);
        pstmt.setString(3,desc);
        pstmt.setString(4,author);
        pstmt.setString(5,pages);
        pstmt.setInt(6,view);
        pstmt.setInt(7,danmu);
        pstmt.setInt(8,pubdate);
        pstmt.setString(9,pic);
        pstmt.setInt(10,videos);

        pstmt.executeUpdate();
        pstmt.close();

    }
    public static void insertNewAV(int avid,int code,String message) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "insert into bilibili (id,title,`desc`,videos) values(?,?,?,?)";
        PreparedStatement pstmt;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1,avid);
        pstmt.setString(2,message);
        pstmt.setString(3,String.valueOf(code));
        pstmt.setInt(4,-1);
        pstmt.executeUpdate();
        pstmt.close();
    }
    public static boolean checkAVExist(int avid) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "select id from bilibili where id=?";
        PreparedStatement pstmt;
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setInt(1,avid);

        ResultSet rs= pstmt.executeQuery();
        boolean hasnext=rs.next();
        pstmt.close();
        return hasnext;
    }
}
