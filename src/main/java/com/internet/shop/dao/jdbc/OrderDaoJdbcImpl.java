package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.OrderDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.model.Order;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoJdbcImpl implements OrderDao {

    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get orders of user with Id = " + userId, e);
        }
        for (Order order : orders) {
            order.setProducts(getProductsFromOrder(order.getId()));
        }
        return orders;
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders(user_id) VALUES (?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            return addProductsToOrder(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to add the order " + order, e);
        }
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
             PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
            }
            order.setProducts(getProductsFromOrder(order.getId()));
            return Optional.of(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Can not get order from DB with ID = " + id, e);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get orders from database ", e);
        }
        for (Order order : orders) {
            order.setProducts(getProductsFromOrder(order.getId()));
        }
        return orders;
    }

    @Override
    public Order update(Order order) {
        String query = "DELETE FROM order_products WHERE order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order "
                    + order, e);
        }
        addProductsToOrder(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE orders SET deleted = true WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete order with Id = " + id, e);
        }
    }

    private Order addProductsToOrder (Order order) {
        String query = "INSERT INTO order_products(order_id, user_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            for (Product product : order.getProducts()) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to add products to order with Id = " + order.getId(), e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        long orderId = resultSet.getLong("order_id");
        long userId = resultSet.getLong("user_id");
        return new Order(orderId, userId);
    }

    private List<Product> getProductsFromOrder(Long orderId) {
        String query = "SELECT * FROM products JOIN order_products  "
                + "ON order_products.product_id = products.product_id "
                + "WHERE order_products.order_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("product_price"));
                products.add(product);
            }
            return products;
        } catch (SQLException exception) {
            throw new DataProcessingException("Failed to get the products from order"
                    + "with Id = " + orderId, exception);
        }
    }
}
