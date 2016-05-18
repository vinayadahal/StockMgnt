package Services;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {

    public List<Map> getMetaInfo(ResultSetMetaData rsMeta, ResultSet rs) throws SQLException {
        List<Map> Rows = new ArrayList<>();
        while (rs.next()) {
            Map map = new HashMap();
            for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
                map.put(rsMeta.getColumnName(i), rs.getString(i));
            }
            Rows.add(map);
        }
        rs.close();
        return Rows;
    }

    public int getPagination(int totalData, int dataPerPage) {
        int numPages = 1;
        if (totalData == dataPerPage) {
            numPages = 1;
        } else if (dataPerPage == 1) {
            numPages = totalData;
        } else {
            numPages = (totalData / dataPerPage) + 1;
        }
        return numPages;

    }
}
