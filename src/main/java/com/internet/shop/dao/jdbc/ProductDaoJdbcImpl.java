package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.ProductDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.library.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        String query = "INSERT INTO products(product_name, product_price) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to add the product " + product.getName(), e);
        }
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = "SELECT product_id, product_name, product_price"
                + " WHERE product_id = ? AND deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                return Optional.ofNullable(product);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get product with corresponding id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT product_id, product_name, product_price"
                + " FROM products WHERE deleted = FALSE;";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all products", e);
        }
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE products SET product_name = ?, "
                + "product_price = ? WHERE product_id = ? AND deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Product " + product + " can't be updated", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE products SET deleted = TRUE WHERE product_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Removing product with id " + id + " failed. ", e);
        }
    }

    private Product getProduct(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        return new Product(id, name, price);
    }
}
