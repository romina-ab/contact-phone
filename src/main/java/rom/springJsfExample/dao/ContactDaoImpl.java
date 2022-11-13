package rom.springJsfExample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rom.springJsfExample.entity.Contact;
import rom.springJsfExample.mapper.ContactRowMapper;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDaoImpl implements ContactDao {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public ContactDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Contact> displayContact() {
        String sql = "select * from contact_test";
        return template.query(sql, new ContactRowMapper());
    }

    @Override
    public void insertContact(Contact contact) {
        String sql = "insert into contact_test(first_name, last_name, number, email) values (:first_name, :last_name, :number, :email)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("first_name", contact.getFirstName())
                .addValue("last_name", contact.getLastName())
                .addValue("number", contact.getNumber())
                .addValue("email", contact.getEmail());

        template.update(sql, param, keyHolder);
    }

    @Override
    public void updateContact(Contact contact) {
        String sql = "update contact_test set first_name =:first_name, last_name =:last_name , number =:number , email =:email where id=:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", contact.getId());
        map.put("first_name", contact.getFirstName());
        map.put("last_name", contact.getLastName());
        map.put("number", contact.getNumber());
        map.put("email", contact.getEmail());
        template.execute(sql, map, (PreparedStatementCallback<Object>) PreparedStatement::executeUpdate);
    }

    @Override
    public void deleteContact(Contact contact) {
        String sql = "delete from contact_test  where id=:id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", contact.getId());
        template.execute(sql, map, (PreparedStatementCallback<Object>) PreparedStatement::executeUpdate);
    }

    public List<Contact> findByChar(String chr) {
        String sql = "select * from contact_test where first_name like :test ";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("test", "%"+chr+"%");
        return template.query(sql, param,new ContactRowMapper() );
    }

}
