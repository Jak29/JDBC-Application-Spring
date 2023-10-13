package ie.jak.entites;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StylistRowMapper implements RowMapper<Salon> {
    @Override
    public Salon mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Salon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(6));
    }
}
