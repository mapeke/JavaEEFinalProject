package db;

import entity.News;
import entity.User;
import entity.Comments;
import entity.NewsCategories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {

    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tech_orda_db",
                    "root",
                    "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.post_date, n.category_id, n.title, n.content " +
                    "FROM news n " +
                    "ORDER BY n.post_date DESC ");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                News n = new News();
                n.setId(resultSet.getInt("id"));
                n.setTitle(resultSet.getString("title"));
                n.setContent(resultSet.getString("content"));
                n.setCategoryId(resultSet.getInt("category_id"));
                n.setPostDate(resultSet.getTimestamp("post_date"));

                news.add(n);
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(int id) {
        News news = null;
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.post_date " +
                    "FROM news n " +
                    "WHERE n.id = ? ");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                news = new News();
                news.setId(resultSet.getInt("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setPostDate(resultSet.getTimestamp("post_date"));
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static User getUser(String email) {
        User user = null;
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole(resultSet.getInt("role_id"));
            }

            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void addUser(User user) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name, role_id) " +
                    "VALUES (?, ?, ?, ? )");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRole());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET full_name = ?, password = ? " +
                    "WHERE id = ?");

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Comments> getComments(int newsId) {

        ArrayList<Comments> comments = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT co.id, co.comment, co.post_date, co.news_id, co.user_id, u.full_name " +
                    "FROM comments co " +
                    "INNER JOIN users u ON u.id = co.user_id " +
                    "WHERE co.news_id = ? " +
                    "ORDER BY co.post_date DESC ");

            statement.setLong(1, newsId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Comments comment = new Comments();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                comment.setUser(user);

                News news = new News();
                news.setId(resultSet.getInt("news_id"));
                comment.setNews(news);

                comments.add(comment);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(Comments comment) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments (comment, user_id, news_id, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUser().getId());
            statement.setLong(3, comment.getNews().getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateNews(News news) {
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news SET title = ?, content = ? " +
                    "WHERE id = ?");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNews(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM news WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNews(News news){
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO news (title, content, post_date) " +
                    "VALUES (?, ?, NOW()) ");

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteComment(int newsId){
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM comments WHERE news_id = ?");

            statement.setInt(1, newsId);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
