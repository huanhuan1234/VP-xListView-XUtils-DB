package bawei.com.day421_homework_vp_xutils_db_demo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by huanhuan on 2017/4/23.
 */
@Table(name = "news")
public class Bean {

    @Column(name = "m_data")
    public String date;

    @Column(name = "m_id",isId = true,autoGen = true)
    public int id;
    @Column(name = "m_pic")
    public String pic;
    @Column(name = "m_title")
    public String title;
    @Column(name = "m_type")

    public int type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
