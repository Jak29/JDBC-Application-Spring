package ie.jak.entites;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalonRowMapper implements RowMapper<Salon> {

    @Override
    public Salon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Salon salon = new Salon();
        salon.setSalonId(rs.getInt(1));
        salon.setSalonName(rs.getString(2));
        salon.setSalonAddress(rs.getString(3));
        salon.setSalonPhoneNumber(rs.getInt(4));
        salon.setSalonDaysOpen(rs.getInt(5));
        return salon;
    }
}
