package com.elcompbase.repository;

import com.elcompbase.model.entity.User;
import com.elcompbase.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public void save(User user) {
        jdbcTemplate.update("""
                        INSERT INTO "user"
                        (name, login_email, password_hash, registered_at, visited_at, role)
                        VALUES(?, ?, ?, ?, ?, ?)
                        """,
                user.name(),
                user.loginEmail(),
                user.passwordHash(),
                new Timestamp(user.registeredAt().toEpochMilli()),
                new Timestamp(user.visitedAt().toEpochMilli()),
                user.role().name());
    }

    public void updateIsEnabled(UUID id) {
        jdbcTemplate.update("""
                        UPDATE "user" SET is_enabled=true WHERE id=?
                        """,
                id);
    }

    public Optional<User> findByCredentials(String email, String passwordHash) {
        MapSqlParameterSource params = new MapSqlParameterSource(Map.of(
                "email", email,
                "password", passwordHash
        ));

        return Optional.ofNullable(namedParameterJdbcTemplate.query("""
                        SELECT id, name, login_email, registered_at, visited_at, role, is_enabled FROM "user" WHERE is_enabled=true AND login_email=:email AND password_hash=:password
                        """,
                params,
                rs -> rs.next() ? new User(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("login_email"),
                        "",
                        rs.getTimestamp("registered_at").toInstant(),
                        rs.getTimestamp("visited_at").toInstant(),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("is_enabled")
                ) : null
        ));
    }

    public Optional<User> findById(UUID id) {
        MapSqlParameterSource params = new MapSqlParameterSource(Map.of(
                "id", id
        ));

        return Optional.ofNullable(namedParameterJdbcTemplate.query("""
                        SELECT id, name, login_email, registered_at, visited_at, role, is_enabled FROM "user" WHERE is_enabled=true AND id=:id
                        """,
                params,
                rs -> rs.next() ? new User(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("login_email"),
                        "",
                        rs.getTimestamp("registered_at").toInstant(),
                        rs.getTimestamp("visited_at").toInstant(),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("is_enabled")
                ) : null
        ));
    }

    public Optional<User> findByEmail(String email, Boolean isEnabled) {
        MapSqlParameterSource params = new MapSqlParameterSource(Map.of(
                "login", email,
                "enable", isEnabled
        ));

        return Optional.ofNullable(namedParameterJdbcTemplate.query("""
                        SELECT id, name, login_email, registered_at, visited_at, role, is_enabled FROM "user" WHERE login_email=:login AND is_enabled=:enable
                        """,
                params,
                rs -> rs.next() ? new User(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("login_email"),
                        "",
                        rs.getTimestamp("registered_at").toInstant(),
                        rs.getTimestamp("visited_at").toInstant(),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("is_enabled")
                ) : null
        ));
    }
}
