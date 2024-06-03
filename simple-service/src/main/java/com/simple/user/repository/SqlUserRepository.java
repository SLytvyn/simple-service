package com.simple.user.repository;

import com.simple.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class SqlUserRepository implements UserReposetory {

    private static final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Override
    public UserModel getByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?", new Object[]{email},
            (rs, row) -> new UserModel(rs.getString("email"), rs.getString("name")));
    }

    @Override
    public void saveUser(UserModel userModel) {
        jdbcTemplate.update("INSERT INTO users (name, emails) VALUES (?, ?)", userModel.getName(), userModel.getEmail());
    }
}
