package duonglnt.tOrder;

import java.io.Serializable;
import java.sql.Date;

public class TOrderDTO implements  Serializable{
    private String id;
    private Date date;
    private String name;
    private float total;

    public TOrderDTO() {
    }

    public TOrderDTO(String id, Date date, String name, float total) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.total = total;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
}
