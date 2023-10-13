package ie.jak.dao;

import ie.jak.entites.Stylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StylistDaoImpl implements StylistDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from salons", Integer.class);
    }

//    @Override
//    public List<Stylist> findAll() {
//        return jdbcTemplate.query("select * from cartoons", );
//    }
}
