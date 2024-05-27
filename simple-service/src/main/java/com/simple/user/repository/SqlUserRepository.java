package com.simple.user.repository;

import com.simple.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class SqlUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public UserModel getUserById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id},
            (rs, row) -> new UserModel(rs.getString("id"), rs.getString("name"), rs.getString("email")));
    }

    @Override
    public void saveUser(UserModel userModel) {
        jdbcTemplate.update("INSERT INTO users (id, name, emails) VALUES (?, ?, ?)", userModel.getId(), userModel.getName(), userModel.getEmail());
    }
}
