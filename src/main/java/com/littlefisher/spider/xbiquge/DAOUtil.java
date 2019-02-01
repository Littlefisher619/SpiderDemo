package com.littlefisher.spider.xbiquge;

import com.littlefisher.spider.util.DbUtil;
import com.littlefisher.spider.xbiquge.model.Novel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DAOUtil {
    public static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = LogManager.getLogger(DAOUtil.class);
    public static boolean checkNovelExist(String novel) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql="select * from xbiquge_novels where novel=?;";

        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,novel);
        ResultSet rs = pstmt.executeQuery();

        final boolean hasnext=rs.next();
        pstmt.close();
        return hasnext;
    }
    public static void updateLastUpdate(String novel,String lastupdate) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "update xibiquge_novels set lastupdate=? where novel=?";
        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,lastupdate);
        pstmt.setString(2,novel);
        pstmt.executeUpdate();
        pstmt.close();
    }
    public static void updateProgress(String novel,String progress) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "update xibiquge_novels set progress=? where novel=?";
        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,progress);
        pstmt.setString(2,novel);
        pstmt.executeUpdate();
        pstmt.close();
    }
    public static void updateChapter(String novel,String chapter,String content) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "update xibiquge_noveldump set content=? where novel=? and chapter=?";
        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,content);
        pstmt.setString(2,novel);
        pstmt.setString(3,content);
        pstmt.executeUpdate();
        pstmt.close();
    }
    public static boolean checkChapterExist( String chapter,String novel) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql="select id from xbiquge_noveldump where novel=? and chapter=?";

        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,novel);
        pstmt.setString(2,chapter);
        ResultSet rs = pstmt.executeQuery();

        final boolean hasnext=rs.next();
        pstmt.close();
        return hasnext;

    }
    public static List<Novel> getNovelsWaitForDump() throws SQLException{
        List<Novel> novelList=new ArrayList<Novel>();

        Connection conn = DbUtil.getConnection();
        String sql="select * from xbiquge_novels where progress=?";

        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,"no");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            novelList.add(new Novel(rs.getString("url"),rs.getString("novel"),rs.getString("progress")));
        }
        pstmt.close();
        return novelList;
    }

    public static boolean checkNovelHasUpdate(String novel,String date) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql="select lastupdate from xbiquge_novels where novel=?";

        PreparedStatement pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,novel);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            java.sql.Date lastupdate = rs.getDate("lastupdate");
            try {
                java.sql.Date tdate = new java.sql.Date(sf.parse(date).getTime());
                pstmt.close();
                return lastupdate.after(tdate);
            } catch (Exception e) {
                logger.error("failed to convert date string.", e);
            }
        }
        pstmt.close();
        return false;
    }
    public static void insertNewNovel(String novel, String author, String summary, String picture, String url, String progress, String lastupdate) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "insert into xbiquge_novels (novel,author,summary,picture,url,progress,lastupdate) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        String str[]={novel,author,summary,picture,url,progress,lastupdate};
        for(int i=1;i<=7;i++)
            pstmt.setString(i,str[i-1]);
        pstmt.executeUpdate();
        pstmt.close();


    }
    public static void insertNewChapter(String novel, String chapter, String content, String url) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "insert into xbiquge_noveldump (novel,chapter,content,url) values(?,?,?,?)";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        String str[]={novel,chapter,content,url};
        for(int i=1;i<=4;i++)
            pstmt.setString(i,str[i-1]);
        pstmt.executeUpdate();
        pstmt.close();


    }

    public static void checkConnection() throws SQLException {

        Connection conn = DbUtil.getConnection();
        String sql="select count(`id`) from xbiquge_novels;";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if(rs.next())
            logger.info(rs.getInt("count(`id`)")+" novels found!");
        logger.info("Mysql is online!");
        statement.close();
    }

    public static void cacheSummaryFetchJob(String novel,String url,String jobid) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "insert into xbiquge_jobcache (jobtype,novel,url,jobid) values(?,?,?,?)";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        String str[]={"sfjob",novel,url,jobid};

        for(int i=1;i<=4;i++)
            pstmt.setString(i,str[i-1]);
        pstmt.executeUpdate();
        pstmt.close();

    }
    public static void finishJob(String jobid) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "delete from xbiquge_jobcache where jobid=?";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);

        pstmt.setString(1,jobid);
        pstmt.executeUpdate();
        pstmt.close();

    }
    public static List<ChapterDumpJob> getCachedChapterDumpJobs() throws SQLException{

        Connection conn = DbUtil.getConnection();
        String sql = "select * from xbiquge_jobcache where jobtype=?";
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"cdjob");
        ResultSet rs= pstmt.executeQuery();

        List<ChapterDumpJob> list=new ArrayList<>();
        while(rs.next()){
            list.add(new ChapterDumpJob(rs.getString("novel"),rs.getString("chapter"),rs.getString("url"),rs.getString("jobid")));
        }
        pstmt.close();
        return  list;
    }


    public static List<SummaryFetchJob> getCachedSummaryFetchJobs() throws SQLException{

        Connection conn = DbUtil.getConnection();
        String sql = "select * from xbiquge_jobcache where jobtype=?";
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"sfjob");
        ResultSet rs= pstmt.executeQuery();


        List<SummaryFetchJob> list=new ArrayList<>();
        while(rs.next()){
            list.add(new SummaryFetchJob(rs.getString("url"),rs.getString("novel"),rs.getString("jobid")));
        }
        pstmt.close();
        return  list;
    }

    public static List<String> getCachedJobIDs(String jobtype) throws SQLException{

        Connection conn = DbUtil.getConnection();
        String sql = "select jobid from xbiquge_jobcache where jobtype=?";
        PreparedStatement pstmt;
        List<String> list=new ArrayList<>();
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1,jobtype);
        ResultSet rs= pstmt.executeQuery();

        while(rs.next()){
            list.add(rs.getString("jobid"));
        }
        pstmt.close();
        return  list;
    }
    public static void clearCache(String jobtype) throws SQLException{

        Connection conn = DbUtil.getConnection();
        String sql = "delete from xbiquge_jobcache where jobtype=?";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);

        pstmt.setString(1,jobtype);
        pstmt.executeUpdate();
        pstmt.close();
    }
    public static void cacheChapterDumpJob(String novel,String chapter,String url,String jobid) throws SQLException{
        Connection conn = DbUtil.getConnection();
        String sql = "insert into xbiquge_jobcache (jobtype,novel,chapter,url,jobid) values(?,?,?,?,?)";
        PreparedStatement pstmt;

        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        String str[]={"cdjob",novel,chapter,url,jobid};

        for(int i=1;i<=5;i++)
            pstmt.setString(i,str[i-1]);
        pstmt.executeUpdate();
        pstmt.close();

    }
}
