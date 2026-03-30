package com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.adapter;

import com.jai.cleanarchitecture.domain.model.City;
import com.jai.cleanarchitecture.domain.model.User;
import com.jai.cleanarchitecture.domain.repository.UserRepository;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.mapper.UserMapper;
import com.jai.cleanarchitecture.infrastructure.persistence.r2dbc.repository.ReactiveUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final ReactiveUserRepository reactiveUserRepository;
    private final UserMapper userMapper;
    private final DatabaseClient databaseClient;

    @Override
    public Mono<User> save(User user) {
        return reactiveUserRepository.save(userMapper.toEntity(user)).map(userMapper::toDomain);
    }

    @Override
    public Mono<User> findById(Long id) {
        return reactiveUserRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public Flux<User> findAll() {
        String sql = """
            SELECT 
                p.id,
                p.first_name,
                p.last_name,
                p.birth_date,
                c.id AS city_id,
                c.name AS city_name
            FROM user p
            JOIN city c ON p.city_id = c.id
        """;

        return databaseClient.sql(sql)
                .map((row, metadata) -> {

                    City city = new City();
                    city.setId(row.get("city_id", Long.class));
                    city.setName(row.get("city_name", String.class));

                    User user = new User();
                    user.setId(row.get("id", Long.class));
                    user.setFirstName(row.get("first_name", String.class));
                    user.setLastName(row.get("last_name", String.class));
                    user.setBirthDate(row.get("birth_date", LocalDate.class));
                    user.setCity(city);

                    return user;

                })
                .all();
    }
}
