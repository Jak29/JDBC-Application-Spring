package ie.jak.dao;

import ie.jak.entites.Salon;
import ie.jak.entites.SalonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class SalonDaoImpl implements SalonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from salons", Integer.class);
    }

    @Override
    public List<Salon> findAll() {
        return jdbcTemplate.query("select * from salons", new SalonRowMapper());
    }

    @Override
    public Optional<Salon> findById(int id) {
        try {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("id", id);
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    "select * from salons where salon_id=:id",
                    mapSqlParameterSource, new SalonRowMapper()));
        } catch (EmptyResultDataAccessException exception)
        {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update("delete from salons where salon_id = :id", mapSqlParameterSource) == 1;
    }

    @Override
    public void save(Salon salon) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", salon.getSalonId());
        mapSqlParameterSource.addValue("name", salon.getSalonName());
        mapSqlParameterSource.addValue("address", salon.getSalonAddress());
        mapSqlParameterSource.addValue("phone", salon.getSalonPhoneNumber());
        mapSqlParameterSource.addValue("days", salon.getSalonDaysOpen());
        String SQL = "insert into salons(salon_id, salon_name, salon_address, salon_phone, salon_days) values (:id, :name, :address, :phone, :days)";
        namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource);
    }

    @Override
    public boolean editName(String newName, int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("new_name", newName);
        mapSqlParameterSource.addValue("id", id);
        String SQL = "update salons set salon_name = :new_name where salon_id = :id";
        return namedParameterJdbcTemplate.update(SQL, mapSqlParameterSource) == 1;
    }

}
